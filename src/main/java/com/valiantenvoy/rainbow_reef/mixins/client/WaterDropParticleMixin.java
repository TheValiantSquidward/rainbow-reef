package com.valiantenvoy.rainbow_reef.mixins.client;

import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.WaterDropParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterDropParticle.class)
public class WaterDropParticleMixin {

    @Inject(method = "getRenderType", at=@At("RETURN"), cancellable = true)
    private static void rainbowReef$changeWaterDropRenderType(CallbackInfoReturnable<ParticleRenderType> cir) {
        cir.setReturnValue(ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT);
    }
}
