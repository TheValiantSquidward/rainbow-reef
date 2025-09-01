package net.thevaliantsquidward.rainbowreef.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public class ReefBiomeTags {

    public static final TagKey<Biome> HAS_ANGELFISH = modBiomeTag("has_spawn/angelfish");
    public static final TagKey<Biome> HAS_ARROW_CRAB = modBiomeTag("has_spawn/arrow_crab");
    public static final TagKey<Biome> HAS_BASSLET = modBiomeTag("has_spawn/basslet");
    public static final TagKey<Biome> HAS_BOXFISH = modBiomeTag("has_spawn/boxfish");
    public static final TagKey<Biome> HAS_BUTTERFLYFISH = modBiomeTag("has_spawn/butterflyfish");
    public static final TagKey<Biome> HAS_BUTTERFLYFISH_MANGROVE = modBiomeTag("has_spawn/butterflyfish_mangrove");
    public static final TagKey<Biome> HAS_CLOWNFISH = modBiomeTag("has_spawn/clownfish");
    public static final TagKey<Biome> HAS_CRAB = modBiomeTag("has_spawn/crab");
    public static final TagKey<Biome> HAS_DWARF_ANGELFISH = modBiomeTag("has_spawn/dwarf_angelfish");
    public static final TagKey<Biome> HAS_GOBY = modBiomeTag("has_spawn/goby");
    public static final TagKey<Biome> HAS_GOBY_MANGROVE = modBiomeTag("has_spawn/goby_mangrove");
    public static final TagKey<Biome> HAS_HOGFISH = modBiomeTag("has_spawn/hogfish");
    public static final TagKey<Biome> HAS_JELLYFISH = modBiomeTag("has_spawn/jellyfish");
    public static final TagKey<Biome> HAS_JELLYFISH_RARE = modBiomeTag("has_spawn/jellyfish_rare");
    public static final TagKey<Biome> HAS_MOORISH_IDOL = modBiomeTag("has_spawn/moorish_idol");
    public static final TagKey<Biome> HAS_PARROTFISH = modBiomeTag("has_spawn/parrotfish");
    public static final TagKey<Biome> HAS_PIPEFISH = modBiomeTag("has_spawn/pipefish");
    public static final TagKey<Biome> HAS_RAY = modBiomeTag("has_spawn/ray");
    public static final TagKey<Biome> HAS_SEAHORSE = modBiomeTag("has_spawn/seahorse");
    public static final TagKey<Biome> HAS_SMALL_SHARK = modBiomeTag("has_spawn/small_shark");
    public static final TagKey<Biome> HAS_TANG = modBiomeTag("has_spawn/tang");

    private static TagKey<Biome> modBiomeTag(String name) {
        return biomeTag(RainbowReef.MOD_ID, name);
    }

    private static TagKey<Biome> forgeBiomeTag(String name) {
        return biomeTag("forge", name);
    }

    public static TagKey<Biome> biomeTag(String modid, String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(modid, name));
    }
}
