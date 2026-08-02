package com.valiantenvoy.rainbow_reef.worldgen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record ReefRockConfig(ReefRockBlockSettings blockSettings, GeodeLayerSettings layerSettings,
                             GeodeCrackSettings crackSettings, IntProvider outerWallDistance,
                             IntProvider distributionPoints, IntProvider pointOffset, int minGenOffset,
                             int maxGenOffset, double noiseMultiplier,
                             int invalidBlocksThreshold) implements FeatureConfiguration {

    public static final Codec<Double> CHANCE_RANGE = Codec.doubleRange(0.0D, 1.0D);

    public static final Codec<ReefRockConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(ReefRockBlockSettings.CODEC.fieldOf("blocks").forGetter((config) -> config.blockSettings),
                            GeodeLayerSettings.CODEC.fieldOf("layers").forGetter((config) -> config.layerSettings),
                            GeodeCrackSettings.CODEC.fieldOf("crack").forGetter((config) -> config.crackSettings),
                            IntProvider.codec(1, 20).fieldOf("outer_wall_distance").orElse(UniformInt.of(4, 5)).forGetter((config) -> config.outerWallDistance),
                            IntProvider.codec(1, 20).fieldOf("distribution_points").orElse(UniformInt.of(3, 4)).forGetter((config) -> config.distributionPoints),
                            IntProvider.codec(0, 10).fieldOf("point_offset").orElse(UniformInt.of(1, 2)).forGetter((config) -> config.pointOffset),
                            Codec.INT.fieldOf("min_gen_offset").orElse(-16).forGetter((config) -> config.minGenOffset),
                            Codec.INT.fieldOf("max_gen_offset").orElse(16).forGetter((config) -> config.maxGenOffset),
                            CHANCE_RANGE.fieldOf("noise_multiplier").orElse(0.05).forGetter((config) -> config.noiseMultiplier),
                            Codec.INT.fieldOf("invalid_blocks_threshold").forGetter((config) -> config.invalidBlocksThreshold))
                    .apply(instance, ReefRockConfig::new));

}