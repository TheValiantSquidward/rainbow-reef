package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.phys.Vec3;

public class FishLeapGoal extends JumpGoal {

    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    protected final ReefMob mob;
    protected final int interval;
    protected boolean breached;
    protected final double jumpDistance;
    protected final double jumpHeight;

    public FishLeapGoal(ReefMob mob) {
        this(mob, 10, 0.6D, 0.7D);
    }

    public FishLeapGoal(ReefMob mob, int interval) {
        this(mob, interval, 0.6D, 0.7D);
    }

    public FishLeapGoal(ReefMob mob, int interval, double jumpDistance, double jumpHeight) {
        this.mob = mob;
        this.interval = reducedTickDelay(interval);
        this.jumpDistance = jumpDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public boolean canUse() {
        if (this.mob.hasControllingPassenger()) {
            return false;
        } else if (this.mob.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.mob.getMotionDirection();
            int stepX = direction.getStepX();
            int stepZ = direction.getStepZ();
            BlockPos blockpos = this.mob.blockPosition();
            for (int steps : STEPS_TO_CHECK) {
                if (!this.waterIsClear(blockpos, stepX, stepZ, steps) || !this.surfaceIsClear(blockpos, stepX, stepZ, steps)) {
                    return false;
                }
            }
            return true;
        }
    }

    @SuppressWarnings("deprecation")
    private boolean waterIsClear(BlockPos pos, int x, int z, int scale) {
        BlockPos blockpos = pos.offset(x * scale, 0, z * scale);
        return this.mob.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.mob.level().getBlockState(blockpos).blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pos, int x, int z, int scale) {
        return this.mob.level().getBlockState(pos.offset(x * scale, 1, z * scale)).isAir() && this.mob.level().getBlockState(pos.offset(x * scale, 2, z * scale)).isAir();
    }

    @Override
    public boolean canContinueToUse() {
        double y = this.mob.getDeltaMovement().y;
        return (!(y * y < (double) 0.03F) || !this.mob.isInWater()) && !this.mob.onGround() && this.mob.isLeaping();
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }

    @Override
    public void start() {
        this.breached = false;
        Direction direction = this.mob.getMotionDirection();
        this.mob.setDeltaMovement(this.mob.getDeltaMovement().add((double) direction.getStepX() * this.jumpDistance, this.jumpHeight, (double) direction.getStepZ() * this.jumpDistance));
        this.mob.getNavigation().stop();
        this.mob.setLeaping(true);
    }

    @Override
    public void stop() {
        this.mob.setXRot(0.0F);
        this.mob.setLeaping(false);
    }

    @Override
    public void tick() {
        if (!this.breached && !this.mob.level().getFluidState(this.mob.blockPosition()).is(FluidTags.WATER)) {
            this.breached = true;
            this.mob.playSound(ReefSoundEvents.FISH_JUMP.get(), 1.0F, 1.0F);
        }

        if (this.breached && this.mob.level().getFluidState(this.mob.blockPosition()).is(FluidTags.WATER)) {
            this.mob.setLeaping(false);
            this.breached = false;
        }

        Vec3 deltaMovement = this.mob.getDeltaMovement();
        if (deltaMovement.length() > 1.0E-5F) {
            this.mob.setYRot(((float) Mth.atan2(this.mob.getMotionDirection().getStepZ(), this.mob.getMotionDirection().getStepX())) * Mth.RAD_TO_DEG - 90F);
        }
    }
}