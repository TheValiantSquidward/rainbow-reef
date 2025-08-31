package net.thevaliantsquidward.rainbowreef.registry.tags;

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
    public static final TagKey<Block> BUTTERFLY_DIET = registerBlockTag("butterfly_diet");
    public static final TagKey<Block> PARROTFISH_DIET = registerBlockTag("parrotfish_diet");
    public static final TagKey<Block> ANGELFISH_DIET = registerBlockTag("angelfish_diet");
    public static final TagKey<Block> CLOWNFISH_DIET = registerBlockTag("clownfish_diet");
    public static final TagKey<Block> TANG_DIET = registerBlockTag("tang_diet");
    public static final TagKey<Block> MOORISH_DIET = registerBlockTag("moorish_diet");
    public static final TagKey<Item> NEM_DIET = registerItemTag("nem_diet");
    public static final TagKey<Biome> EEL_FRESH = registerBiomeTag("eel_fresh");
    public static final TagKey<Biome> EEL_SALT = registerBiomeTag("eel_salt");

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