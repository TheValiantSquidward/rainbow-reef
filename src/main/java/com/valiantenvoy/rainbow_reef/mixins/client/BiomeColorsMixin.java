package com.valiantenvoy.rainbow_reef.mixins.client;

import com.valiantenvoy.rainbow_reef.utils.WaterColorNoise;
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
    private static void rainbowReef$addWaterColorNoise(BlockAndTintGetter world, BlockPos pos, CallbackInfoReturnable<Integer> info){
        info.setReturnValue(WaterColorNoise.INSTANCE.applyNoise(pos,info.getReturnValue(), 32.0F, 0.05F));
    }
}