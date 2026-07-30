package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.utils.DolphinAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.phys.Vec3;

public class DolphinLeapGoal extends JumpGoal {

    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    private final Dolphin dolphin;
    private final DolphinAccess dolphinAccess;
    private final int interval;
    private boolean breached;

    public DolphinLeapGoal(Dolphin dolphin, int interval) {
        this.dolphin = dolphin;
        this.dolphinAccess = (DolphinAccess) dolphin;
        this.interval = reducedTickDelay(interval);
    }

    @Override
    public boolean canUse() {
        if (this.dolphin.hasControllingPassenger()) {
            return false;
        } else if (this.dolphin.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.dolphin.getMotionDirection();
            int stepX = direction.getStepX();
            int stepZ = direction.getStepZ();
            BlockPos blockpos = this.dolphin.blockPosition();
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
        return this.dolphin.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.dolphin.level().getBlockState(blockpos).blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pos, int x, int z, int scale) {
        return this.dolphin.level().getBlockState(pos.offset(x * scale, 1, z * scale)).isAir() && this.dolphin.level().getBlockState(pos.offset(x * scale, 2, z * scale)).isAir();
    }

    @Override
    public boolean canContinueToUse() {
        double y = this.dolphin.getDeltaMovement().y;
        return (!(y * y < (double) 0.03F) || !this.dolphin.isInWater()) && !this.dolphin.onGround() && this.dolphinAccess.isLeaping();
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }

    @Override
    public void start() {
        this.breached = false;
        Direction direction = this.dolphin.getMotionDirection();
        this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add((double) direction.getStepX() * 0.6D, 0.7D, (double) direction.getStepZ() * 0.6D));
        this.dolphin.getNavigation().stop();
        this.dolphinAccess.setLeaping(true);
    }

    @Override
    public void stop() {
        this.dolphin.setXRot(0.0F);
        this.dolphinAccess.setLeaping(false);
    }

    @Override
    public void tick() {
        if (!this.breached && !this.dolphin.level().getFluidState(this.dolphin.blockPosition()).is(FluidTags.WATER)) {
            this.breached = true;
            this.dolphin.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        if (this.breached && this.dolphin.level().getFluidState(this.dolphin.blockPosition()).is(FluidTags.WATER)) {
            this.dolphinAccess.setLeaping(false);
            this.breached = false;
        }

        Vec3 deltaMovement = this.dolphin.getDeltaMovement();
        if (deltaMovement.length() > 1.0E-5F) {
            this.dolphin.setYRot(((float) Mth.atan2(this.dolphin.getMotionDirection().getStepZ(), this.dolphin.getMotionDirection().getStepX())) * Mth.RAD_TO_DEG - 90F);
        }
    }
}