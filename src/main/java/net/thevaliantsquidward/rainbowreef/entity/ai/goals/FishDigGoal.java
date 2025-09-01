package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

    private final ReefMob fish;

    private int digTime;
    private final int digTimeLimit;
    private int timeOut;
    private final int timeOutLimit;

    private final int feedCooldownLimit;

    public BlockPos digPos = null;
    public MoveControl originalMoveControl;
    public TagKey<Block> foodBlocks;

    public FishDigGoal(ReefMob fish, int digtime, int feedCooldownLimit, TagKey<Block> foodBlocks) {
        this.foodBlocks = foodBlocks;
        this.fish = fish;

        this.digTime = digtime;
        this.digTimeLimit = digtime;
        this.feedCooldownLimit = feedCooldownLimit;

        this.timeOut = 400;
        this.timeOutLimit = 400;
    }

    @Override
    public boolean canUse() {
        if (fish.getFeedCooldown() <= 0 && fish.isInWater()) {
            this.fish.setFeedCooldown(600 + fish.getRandom().nextInt(600));
            this.digPos = getDigPos();
            this.timeOut = 800;
            return this.digPos != null;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return fish.getTarget() == null && fish.getLastHurtByMob() == null && this.digPos != null && fish.level().getBlockState(this.digPos).is(this.foodBlocks) && fish.level().getFluidState(this.digPos.above()).is(FluidTags.WATER) && this.timeOut >= 0;
    }

    @Override
    public void start() {
        this.digTime += fish.getRandom().nextInt(10);
        this.originalMoveControl = fish.getMoveControl();
        if (!(this.fish instanceof Crab)) {
            this.fish.setMoveControl(fish.feedingController);
        }
        this.fish.getNavigation().moveTo(((float) this.digPos.getX()) + 0.5D, this.digPos.getY(), ((float) this.digPos.getZ()) + 0.5D, 1.2);
    }

    @Override
    public void tick() {
        double dist = fish.position().distanceTo(Vec3.atCenterOf(this.digPos));
        double dy = this.digPos.getY() + 0.5 - this.fish.getY();
        double dx = this.digPos.getX() + 0.5 - this.fish.getX();
        double dz = this.digPos.getZ() + 0.5 - this.fish.getZ();
        float yaw = (float) (Mth.atan2(dz, dx) * 57.2957763671875D) - 90.0F;
        float pitch = (float) -(Mth.atan2(dy, Math.hypot(dx, dz)) * 57.2957763671875D);

        //System.out.println(fish.getNavigation().getPath().isDone());
        //((ServerLevel) this.fish.level()).sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, this.digPos.getX() + 0.5, this.digPos.getY() + 0.5, this.digPos.getZ() + 0.5, 1, 0, 1, 0, 0);
        this.timeOut --;

        if (this.fish instanceof Crab && this.fish.blockPosition().below().equals(this.digPos)) {
            this.fish.setDeltaMovement(Vec3.ZERO);
            this.fish.getNavigation().stop();
        }

        if (dist < this.fish.getBoundingBox().getXsize() + 1) {
            this.fish.setYRot(yaw);
            this.fish.setXRot(pitch);
            this.digTime--;
            //start digging when it is close enough, also makes it look at the blocks

            if (dist < this.fish.getBoundingBox().getXsize()) {
                this.fish.getNavigation().stop();
                //stop the fish before it starts spinning
            }

            if (this.digTime % 5 == 0) {
                SoundEvent sound = fish.level().getBlockState(this.digPos).getSoundType().getHitSound();
                spawnEffectsAtBlock(this.digPos);
                this.fish.playSound(sound, 0.05F, 0.8F + fish.getRandom().nextFloat() * 0.25F);
                //the fish plays sound and makes particles as long as it digs every 5 ticks(sound lasts that long)
            }

            if (this.digTime <= 0) {
                this.digPos = null;
                //the fish stops digging after a time
            }

        } else {
            this.fish.getNavigation().moveTo(((float) this.digPos.getX()) + 0.5D, this.digPos.getY(), ((float) this.digPos.getZ()) + 0.5D, 1.2);
            //if the fish isn't close enough keep it moving

            if (this.timeOut <= 0) {
                this.digPos = null;
            }
        }
    }

    @Override
    public void stop() {
        this.fish.setFeedCooldown(this.feedCooldownLimit + (4 * fish.getRandom().nextInt(this.feedCooldownLimit)));
        this.fish.setMoveControl(this.originalMoveControl);
        this.digPos = null;
        this.digTime = this.digTimeLimit;
        this.timeOut = this.timeOutLimit;
    }

    private BlockPos getSeafloorPos(BlockPos parent) {
        LevelAccessor world = fish.level();
        final RandomSource random = this.fish.getRandom();
        int range = 15;
        for (int i = 0; i < 25; i++) {
            BlockPos seafloor = parent.offset(random.nextInt(range) - range / 2, 0, random.nextInt(range) - range / 2);
            while (world.getFluidState(seafloor).is(FluidTags.WATER) && seafloor.getY() > 1) {
                BlockState state = world.getBlockState(seafloor);
                if (state.is(this.foodBlocks)) {
                    return seafloor;
                }
                seafloor = seafloor.below();
            }
            BlockState state = world.getBlockState(seafloor);
            if (state.is(this.foodBlocks)) {
                return seafloor;
            }
        }
        return null;
    }

    private BlockPos getDigPos() {
        final RandomSource random = this.fish.getRandom();
        int range = 15;
        if (fish.isInWater()) {
            return getSeafloorPos(this.fish.blockPosition());
        } else {
            for (int i = 0; i < 25; i++) {
                BlockPos blockpos1 = this.fish.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while (this.fish.level().isEmptyBlock(blockpos1) && blockpos1.getY() > 1) {
                    blockpos1 = blockpos1.below();
                }
                if (this.fish.level().getFluidState(blockpos1).is(FluidTags.WATER)) {
                    BlockPos pos3 = getSeafloorPos(blockpos1);
                    if (pos3 != null && pos3.getY() < this.fish.getY()) {
                        return pos3;
                    }
                }
            }
        }
        return null;
    }

    public void spawnEffectsAtBlock(BlockPos target) {
        float radius = 0.3F;
        for (int i1 = 0; i1 < 3; i1++) {
            double motionX = fish.getRandom().nextGaussian() * 0.07D;
            double motionY = fish.getRandom().nextGaussian() * 0.07D;
            double motionZ = fish.getRandom().nextGaussian() * 0.07D;
            float angle = (float) ((0.0174532925 * fish.yBodyRot) + i1);
            double extraX = radius * Mth.sin(Mth.PI + angle);
            double extraY = 0.8F;
            double extraZ = radius * Mth.cos(angle);
            BlockState state = fish.level().getBlockState(target);
            ((ServerLevel) fish.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), target.getX() + 0.5 + extraX, target.getY() + 0.5 + extraY, target.getZ() + 0.5 + extraZ, 1, motionX, motionY, motionZ, 1);
        }
    }
}