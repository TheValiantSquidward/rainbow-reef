package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.world.features.*;

public class ReefFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RainbowReef.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> REEF_NEM = FEATURES.register("reef_nem", () -> new AnemoneFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOOR_NEM = FEATURES.register("floor_nem", () -> new FloorNemFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<MultifaceGrowthConfiguration>> REEF_STAR = FEATURES.register("reef_star", () -> new ReefStarFeature(MultifaceGrowthConfiguration.CODEC));
    public static final RegistryObject<Feature<MultifaceGrowthConfiguration>> FLOOR_STAR = FEATURES.register("floor_star", () -> new FloorStarFeature(MultifaceGrowthConfiguration.CODEC));

    public static final RegistryObject<Feature<BlockStateConfiguration>> CORALSTONE_ROCK = FEATURES.register("coralstone_rock", () -> new CoralstoneRockFeature(BlockStateConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> TALL_CORAL = FEATURES.register("tall_coral", () -> new TallCoralFeature(NoneFeatureConfiguration.CODEC));

}