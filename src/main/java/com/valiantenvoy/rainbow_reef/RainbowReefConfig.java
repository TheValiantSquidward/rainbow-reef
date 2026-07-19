package com.valiantenvoy.rainbow_reef;

import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec;

public class RainbowReefConfig {

    public static IConfigSpec CLIENT_CONFIG;

    // client
    public static ModConfigSpec.BooleanValue WATER_COLOR_NOISE;
    public static ModConfigSpec.BooleanValue GRASS_COLOR_NOISE;
    public static ModConfigSpec.DoubleValue BIOME_COLOR_NOISE_SCALE;
    public static ModConfigSpec.DoubleValue BIOME_COLOR_NOISE_INTENSITY;
    public static ModConfigSpec.BooleanValue DYEABLE_FISHING_RODS;

    static {
        ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();
        WATER_COLOR_NOISE = CLIENT_BUILDER.comment("Whether water colors should have a Perlin noise overlay").define("waterColorNoise", true);
        GRASS_COLOR_NOISE = CLIENT_BUILDER.comment("Whether grass colors should have a Perlin noise overlay").define("grassColorNoise", true);
        BIOME_COLOR_NOISE_SCALE = CLIENT_BUILDER.comment("Scale for biome color noise").defineInRange("biomeColorNoiseScale", 15.0D, Double.MIN_VALUE, Double.MAX_VALUE);
        BIOME_COLOR_NOISE_INTENSITY = CLIENT_BUILDER.comment("Intensity for biome color noise").defineInRange("biomeColorNoiseIntensity", 0.075D, 0.0D, Double.MAX_VALUE);
        // technically has stuff on the server too, but it's only called for the renderer replacement
        DYEABLE_FISHING_RODS = CLIENT_BUILDER.comment("Whether fishing bobber renderer should be replaced for dyed lines and bobbers").define("dyeableFishingRods", true);

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
