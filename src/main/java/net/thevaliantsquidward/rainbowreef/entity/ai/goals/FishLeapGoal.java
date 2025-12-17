package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;

public class FishLeapGoal extends JumpGoal {

    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    private final ReefMob reefMob;
    private final int interval;
    protected boolean breached;
    private final double jumpDistance;
    private final double jumpHeight;

    public FishLeapGoal(ReefMob reefMob) {
        this(reefMob, 10, 0.6D, 0.7D);
    }

    public FishLeapGoal(ReefMob reefMob, int interval, double jumpDistance, double jumpHeight) {
        this.reefMob = reefMob;
        this.interval = reducedTickDelay(interval);
        this.jumpDistance = jumpDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public boolean canUse() {
        if (this.reefMob.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.reefMob.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.reefMob.blockPosition();
            for (int k : STEPS_TO_CHECK) {
                if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
                    return false;
                }
            }
            return true;
        }
    }

    private boolean waterIsClear(BlockPos pos, int x, int z, int scale) {
        BlockPos blockpos = pos.offset(x * scale, 0, z * scale);
        return this.reefMob.level().getFluidState(blockpos).is(FluidTags.WATER) && !this.reefMob.level().getBlockState(blockpos).blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        return this.reefMob.level().getBlockState(pPos.offset(pDx * pScale, 1, pDz * pScale)).isAir() && this.reefMob.level().getBlockState(pPos.offset(pDx * pScale, 2, pDz * pScale)).isAir();
    }

    @Override
    public boolean canContinueToUse() {
        double y = this.reefMob.getDeltaMovement().y;
        return (!(y * y < (double) 0.03F) || this.reefMob.getXRot() == 0.0F || !(Math.abs(this.reefMob.getXRot()) < 10.0F) || !this.reefMob.isInWater()) && !this.reefMob.onGround();
    }

    @Override
    public boolean isInterruptable() {
        return false;
    }

    @Override
    public void start() {
        Direction direction = this.reefMob.getMotionDirection();
        this.reefMob.setDeltaMovement(this.reefMob.getDeltaMovement().add((double) direction.getStepX() * jumpDistance, jumpHeight, (double) direction.getStepZ() * jumpDistance));
        this.reefMob.getNavigation().stop();
        this.reefMob.setLeaping(true);
    }

    @Override
    public void stop() {
        this.reefMob.setXRot(0.0F);
        this.reefMob.setLeaping(false);
    }

    @Override
    public void tick() {
        boolean flag = this.breached;
        if (!flag) {
            FluidState fluidstate = this.reefMob.level().getFluidState(this.reefMob.blockPosition());
            this.breached = fluidstate.is(FluidTags.WATER);
        }

        if (this.breached && !flag) {
            this.reefMob.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vec3 vec3 = this.reefMob.getDeltaMovement();
        if (vec3.y * vec3.y < (double) 0.03F && this.reefMob.getXRot() != 0.0F) {
            this.reefMob.setXRot(Mth.rotLerp(0.2F, this.reefMob.getXRot(), 0.0F));
        } else if (vec3.length() > (double) 1.0E-5F) {
            double horizontalDistance = vec3.horizontalDistance();
            double xRot = Math.atan2(-vec3.y, horizontalDistance) * (double) (180F / (float) Math.PI);
            this.reefMob.setXRot((float) xRot);
            this.reefMob.setYRot(((float) Mth.atan2(reefMob.getMotionDirection().getStepZ(), reefMob.getMotionDirection().getStepX())) * Mth.RAD_TO_DEG - 90F);
        }
    }
}