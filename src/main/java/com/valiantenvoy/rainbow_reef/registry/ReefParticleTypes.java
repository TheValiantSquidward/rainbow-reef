package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ReefParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BURROW_BUBBLE = PARTICLE_TYPES.register("burrow_bubble", () -> new SimpleParticleType(false) {});
}
