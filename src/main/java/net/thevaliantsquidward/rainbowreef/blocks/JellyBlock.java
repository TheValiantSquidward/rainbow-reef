package net.thevaliantsquidward.rainbowreef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;
import org.jetbrains.annotations.NotNull;

public class JellyBlock extends Block {
    public JellyBlock(Properties properties) {
        super(properties);
    }

    public void fallOn(@NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, Entity entity, float fallDistance) {
        if (entity.isSuppressingBounce()) {
            super.fallOn(level, state, pos, entity, fallDistance);
        } else {
            entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
            entity.playSound(ReefSoundEvents.JELLY_BLOCK_BOUNCE.get());
        }
    }

    public void updateEntityAfterFallOn(@NotNull BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            this.bounceUp(entity);
        }

    }

    private void bounceUp(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        double d0 = entity instanceof LivingEntity ? 1.0D : 0.8D;

        if (vec3.y < 0.0D) {
            entity.setDeltaMovement(vec3.x, -vec3.y * d0, vec3.z);
        }

    }

    public void stepOn(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, Entity entity) {
        double d0 = Math.abs(entity.getDeltaMovement().y);
        if (d0 < 0.1D && !entity.isSteppingCarefully()) {

            double d1 = 0.4D + d0 * 0.2D;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(d1, 1.0D, d1));
        }

        super.stepOn(level, pos, state, entity);
    }

}
