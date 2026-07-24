package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class FishNibbleBlockGoal extends Goal {

    protected final ReefMob mob;
    protected int digTime;
    private final int digTimeLimit;
    private int timeOut = 400;
    private final int cooldown;
    protected BlockPos digPos = null;
    protected TagKey<Block> foodBlocks;
    protected final double speedModifier;

    public FishNibbleBlockGoal(ReefMob mob, TagKey<Block> foodBlocks) {
        this(mob, 20, 600, foodBlocks, 1.1D);
    }

    public FishNibbleBlockGoal(ReefMob mob, int digTime, TagKey<Block> foodBlocks) {
        this(mob, digTime, 600, foodBlocks, 1.1D);
    }

    public FishNibbleBlockGoal(ReefMob mob, int digTime, int cooldown, TagKey<Block> foodBlocks) {
        this(mob, digTime, cooldown, foodBlocks, 1.1D);
    }

    public FishNibbleBlockGoal(ReefMob mob, int digTime, int cooldown, TagKey<Block> foodBlocks, double speedModifier) {
        this.foodBlocks = foodBlocks;
        this.mob = mob;
        this.digTime = digTime;
        this.digTimeLimit = digTime;
        this.cooldown = cooldown;
        this.speedModifier = speedModifier;
    }

    @Override
    public boolean canUse() {
        if (this.mob.getFeedCooldown() <= 0 && this.mob.isInWater()) {
            this.mob.setFeedCooldown(600 + this.mob.getRandom().nextInt(600 * 4));
            this.digPos = this.getDigPos();
            this.timeOut = 800;
            return this.digPos != null;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.getTarget() == null && this.mob.getLastHurtByMob() == null && this.digPos != null && this.mob.level().getBlockState(this.digPos).is(this.foodBlocks) && this.mob.level().getFluidState(this.digPos.above()).is(FluidTags.WATER) && this.timeOut >= 0;
    }

    @Override
    public void start() {
        this.digTime += this.mob.getRandom().nextInt(10);
        this.mob.getNavigation().moveTo(((float) this.digPos.getX()) + 0.5D, this.digPos.getY(), ((float) this.digPos.getZ()) + 0.5D, this.speedModifier);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        double dist = this.mob.position().distanceTo(Vec3.atCenterOf(this.digPos));
        double dy = this.digPos.getY() + 0.5 - this.mob.getY();
        double dx = this.digPos.getX() + 0.5 - this.mob.getX();
        double dz = this.digPos.getZ() + 0.5 - this.mob.getZ();
        float yaw = (float) (Mth.atan2(dz, dx) * 57.2957763671875D) - 90.0F;
        float pitch = (float) -(Mth.atan2(dy, Math.hypot(dx, dz)) * 57.2957763671875D);

        this.timeOut--;

        if (dist < mob.getBoundingBox().getXsize() + 1) {
            this.mob.setYRot(yaw);
            this.mob.setXRot(pitch);
            this.digTime--;
            if (dist < mob.getBoundingBox().getXsize()) {
                this.mob.getNavigation().stop();
            }

            if (digTime % 5 == 0) {
                this.spawnEffectsAtBlock(this.digPos);
                this.mob.playSound(this.mob.level().getBlockState(this.digPos).getSoundType().getHitSound(), 0.2F, 0.8F + mob.getRandom().nextFloat() * 0.25F);
            }

            if (digTime <= 0) {
                this.digPos = null;
            }
        } else {
            this.mob.getNavigation().moveTo(((float) digPos.getX()) + 0.5D, digPos.getY(), ((float) digPos.getZ()) + 0.5D, speedModifier);
            if (timeOut <= 0) {
                this.digPos = null;
            }
        }
    }

    @Override
    public void stop() {
        this.mob.setFeedCooldown(this.cooldown + (this.mob.getRandom().nextInt(this.cooldown * 4)));
        this.digPos = null;
        this.digTime = this.digTimeLimit;
        this.timeOut = 400;
    }

    private BlockPos getSeafloorPos(BlockPos parent) {
        LevelAccessor world = this.mob.level();
        final RandomSource random = this.mob.getRandom();
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
        final RandomSource random = this.mob.getRandom();
        int range = 15;
        if (this.mob.isInWater()) {
            return this.getSeafloorPos(this.mob.blockPosition());
        } else {
            for (int i = 0; i < 25; i++) {
                BlockPos blockpos1 = this.mob.blockPosition().offset(random.nextInt(range) - range / 2, 3, random.nextInt(range) - range / 2);
                while (this.mob.level().isEmptyBlock(blockpos1) && blockpos1.getY() > 1) {
                    blockpos1 = blockpos1.below();
                }
                if (this.mob.level().getFluidState(blockpos1).is(FluidTags.WATER)) {
                    BlockPos pos3 = this.getSeafloorPos(blockpos1);
                    if (pos3 != null && pos3.getY() < this.mob.getY()) {
                        return pos3;
                    }
                }
            }
        }
        return null;
    }

    public void spawnEffectsAtBlock(BlockPos blockPos) {
        float radius = 0.3F;
        for (int i1 = 0; i1 < 3; i1++) {
            double motionX = this.mob.getRandom().nextGaussian() * 0.07D;
            double motionY = this.mob.getRandom().nextGaussian() * 0.07D;
            double motionZ = this.mob.getRandom().nextGaussian() * 0.07D;
            float angle = (float) ((0.0174532925 * this.mob.yBodyRot) + i1);
            double extraX = radius * Mth.sin(Mth.PI + angle);
            double extraY = 0.8F;
            double extraZ = radius * Mth.cos(angle);
            BlockState state = this.mob.level().getBlockState(blockPos);
            ((ServerLevel) this.mob.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), blockPos.getX() + 0.5 + extraX, blockPos.getY() + 0.5 + extraY, blockPos.getZ() + 0.5 + extraZ, 1, motionX, motionY, motionZ, 1);
        }
    }
}