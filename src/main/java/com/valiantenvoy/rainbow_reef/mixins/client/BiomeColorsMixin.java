package com.valiantenvoy.rainbow_reef.mixins.client;

import com.valiantenvoy.rainbow_reef.RainbowReefConfig;
import com.valiantenvoy.rainbow_reef.utils.ColorNoise;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeColors.class)
public class BiomeColorsMixin {

    @Inject(method = "getAverageWaterColor", at=@At("RETURN"), cancellable = true)
    private static void rainbowReef$addWaterColorNoise(BlockAndTintGetter world, BlockPos pos, CallbackInfoReturnable<Integer> info) {
        if (RainbowReefConfig.WATER_COLOR_NOISE.getAsBoolean()) {
            info.setReturnValue(ColorNoise.INSTANCE.applyNoise(pos, info.getReturnValue(), (float) RainbowReefConfig.BIOME_COLOR_NOISE_SCALE.getAsDouble(), (float) RainbowReefConfig.BIOME_COLOR_NOISE_INTENSITY.getAsDouble()));
        }
    }

    @Inject(method = "getAverageGrassColor", at=@At("RETURN"), cancellable = true)
    private static void rainbowReef$addGrassColorNoise(BlockAndTintGetter world, BlockPos pos, CallbackInfoReturnable<Integer> info) {
        if (RainbowReefConfig.GRASS_COLOR_NOISE.getAsBoolean()) {
            info.setReturnValue(ColorNoise.INSTANCE.applyNoise(pos, info.getReturnValue(), (float) RainbowReefConfig.BIOME_COLOR_NOISE_SCALE.getAsDouble(), (float) RainbowReefConfig.BIOME_COLOR_NOISE_INTENSITY.getAsDouble()));
        }
    }
}