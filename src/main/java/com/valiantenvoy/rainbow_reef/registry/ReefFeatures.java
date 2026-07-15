package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.world.features.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.world.features.*;

public class ReefFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, RainbowReef.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> REEF_NEM = FEATURES.register("reef_nem", () -> new AnemoneFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> FLOOR_NEM = FEATURES.register("floor_nem", () -> new FloorNemFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<MultifaceGrowthConfiguration>> REEF_STAR = FEATURES.register("reef_star", () -> new ReefStarFeature(MultifaceGrowthConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<MultifaceGrowthConfiguration>> FLOOR_STAR = FEATURES.register("floor_star", () -> new FloorStarFeature(MultifaceGrowthConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> CORALSTONE_ROCK = FEATURES.register("coralstone_rock", () -> new CoralstoneRockFeature(BlockStateConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> TALL_CORAL = FEATURES.register("tall_coral", () -> new TallCoralFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BURROW = FEATURES.register("burrow", () -> new BurrowFeature(BlockStateConfiguration.CODEC));

}