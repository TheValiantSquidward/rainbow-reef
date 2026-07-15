package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.world.features.BurrowFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ReefFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, RainbowReef.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BURROW = FEATURES.register("burrow", () -> new BurrowFeature(BlockStateConfiguration.CODEC));

}