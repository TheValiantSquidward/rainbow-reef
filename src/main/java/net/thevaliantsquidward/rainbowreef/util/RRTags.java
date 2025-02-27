package net.thevaliantsquidward.rainbowreef.util;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public final class RRTags {
    public static final TagKey<Block> CONSIDERED_NEMS = registerBlockTag("considered_nems");
    public static final TagKey<Block> TROPICAL_NEMS = registerBlockTag("tropical_nems");
    public static final TagKey<Block> TEMPERATE_NEMS = registerBlockTag("temperate_nems");
    public static final TagKey<Block> TROPICAL_STARS = registerBlockTag("tropical_stars");
    public static final TagKey<Block> TEMPERATE_STARS = registerBlockTag("temperate_stars");
    public static final TagKey<Block> HOG_DIGGABLE = registerBlockTag("hogfish_diggable");
    public static final TagKey<Item> NEM_DIET = registerItemTag("nem_diet");

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(RainbowReef.MOD_ID, name));
    }

    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(RainbowReef.MOD_ID, name));
    }

    private static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(RainbowReef.MOD_ID, name));
    }

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(RainbowReef.MOD_ID, name));
    }
}