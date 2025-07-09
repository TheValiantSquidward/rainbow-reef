package net.thevaliantsquidward.rainbowreef.entity.ai.goalz;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.HogfishEntity;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

public class HogfishDigGoal extends Goal {
    private HogfishEntity fims;
    private int digTime = 0;
    private int timeOut = 400;

    public HogfishDigGoal(HogfishEntity fisdh) {
        this.fims = fisdh;
    }

    @Override
    public boolean canUse() {
        if(fims.getCD() <= 0 && fims.isInWater() && this.fims.getDigPos() == null){
            this.fims.setCD(600 + fims.getRandom().nextInt(600));
            this.fims.setDigPos(genDigPos());
            this.timeOut = 800;
            return this.fims.getDigPos() != null;
        }

        return false;

    }

    public boolean canContinueToUse() {
        return fims.getTarget() == null && fims.getLastHurtByMob() == null && this.fims.getDigPos() != null && fims.level().getBlockState(this.fims.getDigPos()).is(RRTags.HOG_DIGGABLE) && fims.level().getFluidState(this.fims.getDigPos().above()).is(FluidTags.WATER) && this.timeOut >= 0;
    }

    public void tick() {

        double dist = fims.distanceToSqr(Vec3.atCenterOf(this.fims.getDigPos()));
        double dy = this.fims.getDigPos().getY() + 0.5 - this.fims.getY();
        double dx = this.fims.getDigPos().getX() + 0.5 - this.fims.getX();
        double dz = this.fims.getDigPos().getZ() + 0.5 - this.fims.getZ();
        float yaw = (float) (Mth.atan2(dz, dx) * 57.2957763671875D) - 90.0F;
        float pitch = (float) -(Mth.atan2(dy, Math.hypot(dx, dz)) * 57.2957763671875D);
        this.timeOut --;

        if (dist < 2) {
            fims.getNavigation().stop();
            fims.setYRot(yaw);
            fims.setXRot(pitch);
            digTime++;
            //stop the fish and start digging when it is close enough, also makes it look at the blocks

            if (digTime % 5 == 0) {
                SoundEvent sound = fims.level().getBlockState(this.fims.getDigPos()).getSoundType().getHitSound();

                fims.spawnEffectsAtBlock(this.fims.getDigPos());

                fims.playSound(sound, 0.5F, 0.5F + fims.getRandom().nextFloat() * 0.5F);
                fims.setDigging(true);
                System.out.println(true);
                //the fish plays sound and makes particles as long as it digs every 5 ticks(sound lasts that long)
            }

            if (digTime >= 50) {
                fims.setDigging(false);
                this.fims.setDigPos(null);
                digTime = 0;
                //the fish stops digging after a time
            }

        } else {
            fims.setDigging(false);
            fims.getNavigation().moveTo(this.fims.getDigPos().getX() + 0.5, this.fims.getDigPos().getY() + 0.5, this.fims.getDigPos().getZ() + 0.5, 1.2);
            //fims.setYRot(f);
            //if the fish isn't close enough keep it moving
        }

    }

    public void stop() {
        this.fims.setCD(600 + fims.getRandom().nextInt(600));
        fims.setDigging(false);
        this.fims.setDigPos(null);
        digTime = 0;
    }

    private BlockPos genSeafloorPos(BlockPos parent) {
        LevelAccessor world = fims.level();
        final RandomSource random = this.fims.getRandom();
        int range = 15;
        for (int i = 0; i < 15; i++) {
            BlockPos seafloor = parent.offset(random.nextInt(range) - range / 2, 0, random.nextInt(range) - range / 2);
            while (world.getFluidState(seafloor).is(FluidTags.WATER) && seafloor.getY() > 1) {
                seafloor = seafloor.below();
            }
            BlockState state = world.getBlockState(seafloor);
            if (state.is(RRTags.HOG_DIGGABLE)) {
                return seafloor;
            }
        }
        return null;
    }

    private BlockPos genDigPos() {
        final RandomSource random = this.fims.getRandom();
        int range = 15;
        if (fims.isInWater()) {
            return genSeafloorPos(this.fims.blockPosition());
        } else {
            for (int i = 0; i < 15; i++) {
                BlockPos blockpos1 = this.fims.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while (this.fims.level().isEmptyBlock(blockpos1) && blockpos1.getY() > 1) {
                    blockpos1 = blockpos1.below();
                }
                if (this.fims.level().getFluidState(blockpos1).is(FluidTags.WATER)) {
                    BlockPos pos3 = genSeafloorPos(blockpos1);
                    if (pos3 != null) {
                        return pos3;
                    }
                }
            }
        }
        return null;
    }
}