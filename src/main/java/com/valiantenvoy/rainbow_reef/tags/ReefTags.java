package com.valiantenvoy.rainbow_reef.tags;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public final class ReefTags {

    public static final TagKey<Block> HOG_DIGGABLE = registerBlockTag("hogfish_diggable");
    public static final TagKey<Block> BUTTERFLY_DIET = registerBlockTag("butterfly_diet");
    public static final TagKey<Block> PARROTFISH_DIET = registerBlockTag("parrotfish_diet");
    public static final TagKey<Block> ANGELFISH_DIET = registerBlockTag("angelfish_diet");
    public static final TagKey<Block> CLOWNFISH_DIET = registerBlockTag("clownfish_diet");
    public static final TagKey<Block> TANG_DIET = registerBlockTag("tang_diet");
    public static final TagKey<Block> MOORISH_DIET = registerBlockTag("moorish_diet");
    public static final TagKey<Item> NEM_DIET = registerItemTag();

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, RainbowReef.location(name));
    }

    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, RainbowReef.location(name));
    }

    private static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, RainbowReef.location(name));
    }

    private static TagKey<Item> registerItemTag() {
        return TagKey.create(Registries.ITEM, RainbowReef.location("nem_diet"));
    }
}