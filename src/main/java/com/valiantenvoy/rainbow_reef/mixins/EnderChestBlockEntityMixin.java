package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.utils.ReefParticleUtils;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderChestBlockEntity.class)
public class EnderChestBlockEntityMixin {

    @Inject(method = "startOpen", at = @At("TAIL"))
    private void rainbowReef$spawnEnderChestBubbleParticles(Player player, CallbackInfo ci) {
        EnderChestBlockEntity blockEntity = (EnderChestBlockEntity) (Object) this;
        Level level = blockEntity.getLevel();
        BlockState state = blockEntity.getBlockState();
        if (level != null && !level.isClientSide && state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            if (blockEntity.getOpenNess(0.0F) == 0.0F) {
                double x = blockEntity.getBlockPos().getX() + 0.5;
                double y = blockEntity.getBlockPos().getY() + 0.5;
                double z = blockEntity.getBlockPos().getZ() + 0.5;
                level.playSound(null, x, y, z, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, 0.6F + level.random.nextFloat() * 0.4F, 0.9F + level.random.nextFloat() * 0.15F);
                ReefParticleUtils.spawnSingleChestBubbles(level, blockEntity.getBlockPos(), x, y, z);
            }
        }
    }
}