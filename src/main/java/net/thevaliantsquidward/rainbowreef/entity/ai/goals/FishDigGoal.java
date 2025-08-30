package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.Crab;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;

public class FishDigGoal extends Goal {
    private ReefMob fims;

    private int digTime = 0;
    private int digTimeLim;

    private int timeOut = 0;
    private int timeOutLim;

    public BlockPos digPos = null;

    public MoveControl originalMoveControl;

    public TagKey<Block> foodWhitelist;

    public FishDigGoal(ReefMob fisdh, int digtime, TagKey<Block> whitelist) {
        this.foodWhitelist = whitelist;
        this.fims = fisdh;

        this.digTime = digtime;
        this.digTimeLim = digtime;

        this.timeOut = 400;
        this.timeOutLim = 400;
    }

    @Override
    public boolean canUse() {

        if(fims.getFeedCooldown() <= 0 && fims.isInWater()){
            this.fims.setFeedCooldown(600 + fims.getRandom().nextInt(600));
            this.digPos = genDigPos();
            this.timeOut = 800;
            return this.digPos != null;
        }

        return false;

    }

    public boolean canContinueToUse() {
        return fims.getTarget() == null && fims.getLastHurtByMob() == null && this.digPos != null && fims.level().getBlockState(this.digPos).is(this.foodWhitelist) && fims.level().getFluidState(this.digPos.above()).is(FluidTags.WATER) && this.timeOut >= 0;
    }

    public void start() {
        this.digTime += fims.getRandom().nextInt(10);
        this.originalMoveControl = fims.getMoveControl();
        if (!(this.fims instanceof Crab)) {
            this.fims.setMoveControl(fims.feedingController);
        }
        this.fims.getNavigation().moveTo(((float) this.digPos.getX()) + 0.5D, this.digPos.getY(), ((float) this.digPos.getZ()) + 0.5D, 1.2);
        //System.out.println("start");
    }

    public void tick() {

        double dist = fims.position().distanceTo(Vec3.atCenterOf(this.digPos));
        double dy = this.digPos.getY() + 0.5 - this.fims.getY();
        double dx = this.digPos.getX() + 0.5 - this.fims.getX();
        double dz = this.digPos.getZ() + 0.5 - this.fims.getZ();
        float yaw = (float) (Mth.atan2(dz, dx) * 57.2957763671875D) - 90.0F;
        float pitch = (float) -(Mth.atan2(dy, Math.hypot(dx, dz)) * 57.2957763671875D);

        //System.out.println(fims.getNavigation().getPath().isDone());
        //((ServerLevel) this.fims.level()).sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, this.digPos.getX() + 0.5, this.digPos.getY() + 0.5, this.digPos.getZ() + 0.5, 1, 0, 1, 0, 0);
        this.timeOut --;

        if (this.fims instanceof Crab && this.fims.blockPosition().below().equals(this.digPos)) {
            this.fims.setDeltaMovement(Vec3.ZERO);
            this.fims.getNavigation().stop();
        }

        if (dist < this.fims.getBoundingBox().getXsize() + 1) {
            this.fims.setYRot(yaw);
            this.fims.setXRot(pitch);
            this.digTime--;
            //start digging when it is close enough, also makes it look at the blocks

            if (dist < this.fims.getBoundingBox().getXsize()) {
                this.fims.getNavigation().stop();
                //stop the fish before it starts spinning
            }

            if (this.digTime % 5 == 0) {
                SoundEvent sound = fims.level().getBlockState(this.digPos).getSoundType().getHitSound();
                this.fims.spawnEffectsAtBlock(this.digPos);
                this.fims.playSound(sound, 0.5F, 0.5F + fims.getRandom().nextFloat() * 0.5F);
                //the fish plays sound and makes particles as long as it digs every 5 ticks(sound lasts that long)
            }

            if (this.digTime <= 0) {
                this.digPos = null;
                //the fish stops digging after a time
            }

        } else {
            this.fims.getNavigation().moveTo(((float) this.digPos.getX()) + 0.5D, this.digPos.getY(), ((float) this.digPos.getZ()) + 0.5D, 1.2);
            //if the fish isn't close enough keep it moving

            if (this.timeOut <= 0) {
                this.digPos = null;
            }
        }

    }

    public void stop() {
        //this.fims.setFeedCD(0);
        this.fims.setFeedCooldown(this.fims.feedColldownLimit + fims.getRandom().nextInt(this.fims.feedColldownLimit));
        this.fims.setMoveControl(this.originalMoveControl);
        this.digPos = null;
        this.digTime = this.digTimeLim;
        this.timeOut = this.timeOutLim;
        //System.out.println("stop");
    }

    private BlockPos genSeafloorPos(BlockPos parent) {
        LevelAccessor world = fims.level();
        final RandomSource random = this.fims.getRandom();
        int range = 15;
        for (int i = 0; i < 25; i++) {
            BlockPos seafloor = parent.offset(random.nextInt(range) - range / 2, 0, random.nextInt(range) - range / 2);
            while (world.getFluidState(seafloor).is(FluidTags.WATER) && seafloor.getY() > 1) {
                BlockState state = world.getBlockState(seafloor);
                if (state.is(this.foodWhitelist)) {
                    return seafloor;
                }
                seafloor = seafloor.below();
            }
            BlockState state = world.getBlockState(seafloor);
            if (state.is(this.foodWhitelist)) {
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
            for (int i = 0; i < 25; i++) {
                BlockPos blockpos1 = this.fims.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while (this.fims.level().isEmptyBlock(blockpos1) && blockpos1.getY() > 1) {
                    blockpos1 = blockpos1.below();
                }
                if (this.fims.level().getFluidState(blockpos1).is(FluidTags.WATER)) {
                    BlockPos pos3 = genSeafloorPos(blockpos1);
                    if (pos3 != null && pos3.getY() < this.fims.getY()) {
                        return pos3;
                    }
                }
            }
        }
        return null;
    }


}