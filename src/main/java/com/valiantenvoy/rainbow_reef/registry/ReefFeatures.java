package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.world.features.BurrowFeature;
import com.valiantenvoy.rainbow_reef.world.features.LargeCoralClawFeature;
import com.valiantenvoy.rainbow_reef.world.features.LargeCoralMushroomFeature;
import com.valiantenvoy.rainbow_reef.world.features.LargeCoralTreeFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ReefFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, RainbowReef.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<BlockStateConfiguration>> BURROW = FEATURES.register("burrow", () -> new BurrowFeature(BlockStateConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LARGE_CORAL_MUSHROOM = FEATURES.register("large_coral_mushroom", () -> new LargeCoralMushroomFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LARGE_CORAL_TREE = FEATURES.register("large_coral_tree", () -> new LargeCoralTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> LARGE_CORAL_CLAW = FEATURES.register("large_coral_claw", () -> new LargeCoralClawFeature(NoneFeatureConfiguration.CODEC));

}