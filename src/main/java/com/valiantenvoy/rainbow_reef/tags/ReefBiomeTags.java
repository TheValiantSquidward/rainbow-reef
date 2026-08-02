package com.valiantenvoy.rainbow_reef.tags;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ReefBiomeTags {

    public static final TagKey<Biome> WARM_OCEANS = modBiomeTag("warm_oceans");
    public static final TagKey<Biome> LUKEWARM_OCEANS = modBiomeTag("lukewarm_oceans");
    public static final TagKey<Biome> TEMPERATE_OCEANS = modBiomeTag("temperate_oceans");
    public static final TagKey<Biome> COLD_OCEANS = modBiomeTag("cold_oceans");
    public static final TagKey<Biome> FROZEN_OCEANS = modBiomeTag("frozen_oceans");

    public static final TagKey<Biome> WARM_AND_LUKEWARM_OCEANS = modBiomeTag("warm_and_lukewarm_oceans");
    public static final TagKey<Biome> LUKEWARM_AND_TEMPERATE_OCEANS = modBiomeTag("lukewarm_and_temperate_oceans");
    public static final TagKey<Biome> TEMPERATE_AND_COLD_OCEANS = modBiomeTag("temperate_and_cold_oceans");

    public static final TagKey<Biome> HAS_BUTTERFLYFISH_MANGROVE = modBiomeTag("has_spawn/butterflyfish_mangrove");
    public static final TagKey<Biome> HAS_CRAB = modBiomeTag("has_spawn/crab");
    public static final TagKey<Biome> HAS_GOBY_MANGROVE = modBiomeTag("has_spawn/goby_mangrove");
    public static final TagKey<Biome> HAS_PIPEFISH = modBiomeTag("has_spawn/pipefish");
    public static final TagKey<Biome> HAS_SEAHORSE = modBiomeTag("has_spawn/seahorse");

    private static TagKey<Biome> modBiomeTag(String name) {
        return biomeTag(RainbowReef.MOD_ID, name);
    }

    private static TagKey<Biome> forgeBiomeTag(String name) {
        return biomeTag("forge", name);
    }

    public static TagKey<Biome> biomeTag(String modId, String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(modId, name));
    }
}
