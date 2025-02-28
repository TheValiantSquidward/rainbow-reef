package net.thevaliantsquidward.rainbowreef.world;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.MultifaceGrowthFeature;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.world.features.FloorNemFeature;
import net.thevaliantsquidward.rainbowreef.world.features.FloorStarFeature;
import net.thevaliantsquidward.rainbowreef.world.features.ReefNemFeature;
import net.thevaliantsquidward.rainbowreef.world.features.ReefStarFeature;

public class ModFeatures {

    //Tips for Noise Based Count
    //higher noise factor means that the noisemap will be "bigger" per se(more distance between clusters)(higher means farther)
    //Noise to count ratio means how dense the generation is in a cluster(higher means denser)
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, RainbowReef.MOD_ID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> REEF_NEM = FEATURES.register("reef_nem", () -> new ReefNemFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOOR_NEM = FEATURES.register("floor_nem", () -> new FloorNemFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<MultifaceGrowthConfiguration>> REEF_STAR = FEATURES.register("reef_star", () -> new ReefStarFeature(MultifaceGrowthConfiguration.CODEC));
    public static final RegistryObject<Feature<MultifaceGrowthConfiguration>> FLOOR_STAR = FEATURES.register("floor_star", () -> new FloorStarFeature(MultifaceGrowthConfiguration.CODEC));
}