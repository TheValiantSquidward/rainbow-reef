package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.worldgen.features.BurrowFeature;
import com.valiantenvoy.rainbow_reef.worldgen.features.ReefRockFeature;
import com.valiantenvoy.rainbow_reef.worldgen.features.StarfishFeature;
import com.valiantenvoy.rainbow_reef.worldgen.features.config.ReefRockConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ReefFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, RainbowReef.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BURROW = FEATURES.register("burrow", () -> new BurrowFeature(BlockStateConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<ReefRockConfig>> REEF_ROCK = FEATURES.register("reef_rock", () -> new ReefRockFeature(ReefRockConfig.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> STARFISH = FEATURES.register("starfish", () -> new StarfishFeature(NoneFeatureConfiguration.CODEC));

}