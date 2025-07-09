package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RRMob extends WaterAnimal {


    protected RRMob(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void setMoveControl(MoveControl newControl) {
            this.setMoveControl(newControl);

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
                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), target.getX() + extraX, target.getY() + extraY, target.getZ() + extraZ, 1, motionX, motionY, motionZ, 1);
            }
        }
    }
}
