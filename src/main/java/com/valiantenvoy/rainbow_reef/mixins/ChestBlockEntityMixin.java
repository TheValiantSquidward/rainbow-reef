package com.valiantenvoy.rainbow_reef.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestBlockEntity.class)
public class ChestBlockEntityMixin {

    @Inject(method = "playSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playSound(Lnet/minecraft/world/entity/player/Player;DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FF)V"))
    private static void makeBubbleParticles(Level level, BlockPos pos, BlockState state, SoundEvent sound, CallbackInfo ci, @Local ChestType chestType, @Local(ordinal = 0) double x, @Local(ordinal = 1) double y, @Local(ordinal = 2) double z) {
        if (sound != SoundEvents.CHEST_OPEN) {
            return;
        }

        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.playSound(null, x, y, z, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, 0.6F + level.getRandom().nextFloat() * 0.4F, 0.9F + level.getRandom().nextFloat() * 0.15F);
            if (chestType == ChestType.RIGHT) {
            } else {
//                BubbleHandler.spawnSingleChestBubbles(level, x, y, z);
            }
        }
    }
}
