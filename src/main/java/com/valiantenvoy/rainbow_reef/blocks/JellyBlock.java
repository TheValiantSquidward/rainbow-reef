package com.valiantenvoy.rainbow_reef.blocks;

import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class JellyBlock extends Block {

    public JellyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 0.0F, level.damageSources().fall());
        if (!entity.isSuppressingBounce()) {
            level.playSound(null, pos, ReefSoundEvents.JELLY_BLOCK_BOUNCE.get(), SoundSource.BLOCKS, 1.0F, 0.9F + level.getRandom().nextFloat() * 0.2F);
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            this.bounceUp(entity);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        double vertical = Math.abs(entity.getDeltaMovement().y);
        if (vertical < 0.1D && !entity.isSteppingCarefully()) {
            double horizontal = 0.4D + vertical * 0.2D;
            entity.setDeltaMovement(entity.getDeltaMovement().multiply(horizontal, 1.0D, horizontal));
        }
        super.stepOn(level, pos, state, entity);
    }

    private void bounceUp(Entity entity) {
        Vec3 deltaMovement = entity.getDeltaMovement();
        double multiplier = entity instanceof LivingEntity ? 1.0D : 0.8D;
        if (deltaMovement.y < 0.0D) {
            entity.setDeltaMovement(deltaMovement.x, -deltaMovement.y * multiplier, deltaMovement.z);
        }
    }
}
