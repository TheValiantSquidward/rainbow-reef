package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RRMob extends WaterAnimal {

    public float prevTilt;
    public float tilt;

    public int feedCDLim = 0;
    public int feedCD = 0;

    public SmoothSwimmingMoveControl feedingController = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, false);

    protected RRMob(EntityType<? extends WaterAnimal> pEntityType, Level pLevel, int feedCooldown) {
        super(pEntityType, pLevel);


        this.feedCDLim = feedCooldown;
        this.setFeedCD(this.feedCDLim + this.getRandom().nextInt(this.feedCDLim));
    }

    public void setMoveControl(MoveControl newControl) {
            this.moveControl = newControl;
    }

    public void setFeedCD(int set){
        this.feedCD = set;
    }

    public int getFeedCD(){
        return feedCD;
    }

    public void tick() {
        super.tick();

        prevTilt = tilt;
        if (this.isInWater()) {
            final float v = Mth.degreesDifference(this.getYRot(), yRotO);
            if (Math.abs(v) > 1) {
                if (Math.abs(tilt) < 25) {
                    tilt -= Math.signum(v);
                }
            } else {
                if (Math.abs(tilt) > 0) {
                    final float tiltSign = Math.signum(tilt);
                    tilt -= tiltSign * 0.85F;
                    if (tilt * tiltSign < 0) {
                        tilt = 0;
                    }
                }
            }
        } else{
            tilt = 0;
        }

        this.feedCD --;
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEyeInFluid(FluidTags.WATER) && this.isPathFinding() && checkFloat(this.blockPosition())) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.005 * this.getAttributeValue(Attributes.MOVEMENT_SPEED), 0.0));
            //checks if the fish is stuck underwater, and gives it a little lift to prevent it from getting stuck at the ledges of blocks
        }
        super.travel(pTravelVector);
    }

    public void spawnEffectsAtBlock(BlockPos target) {
        //this method is only called serverside(in a goal) so you have to use sendParticles

        float radius = 0.3F;
        for (int i1 = 0; i1 < 3; i1++) {
            double motionX = this.getRandom().nextGaussian() * 0.07D;
            double motionY = this.getRandom().nextGaussian() * 0.07D;
            double motionZ = this.getRandom().nextGaussian() * 0.07D;
            float angle = (float) ((0.0174532925 * this.yBodyRot) + i1);
            double extraX = radius * Mth.sin(Mth.PI + angle);
            double extraY = 0.8F;
            double extraZ = radius * Mth.cos(angle);
            BlockState state = this.level().getBlockState(target);
            if (state.isSolid()) {
                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), target.getX() + 0.5 + extraX, target.getY() + 0.5 + extraY, target.getZ() + 0.5 + extraZ, 1, motionX, motionY, motionZ, 1);
                //((ServerLevel) this.level()).sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, target.getX() + 0.5, target.getY() + 1.1, target.getZ() + 0.5, 1, 0.0D, 0.0D, 0.0D, 0);
            }
        }
    }

    private int findSeafloorDist(BlockPos selfpos) {
        int depth = 0;

        if (!this.level().isEmptyBlock(selfpos) && !this.level().getFluidState(selfpos).is(FluidTags.WATER)) {
            return Integer.MAX_VALUE;
        }

        while (this.level().getFluidState(selfpos).is(FluidTags.WATER) && selfpos.getY() > 1) {
            selfpos = selfpos.below();
            depth ++;
        }

        return depth;
    }

    private boolean checkFloat(BlockPos selfpos) {
        int north = findSeafloorDist(selfpos.above().north());
        int south = findSeafloorDist(selfpos.above().south());
        int east = findSeafloorDist(selfpos.above().east());
        int west = findSeafloorDist(selfpos.above().west());

        return north <= (1) || south <= (1) || east <= (1) || west <= (1);
    }
}
