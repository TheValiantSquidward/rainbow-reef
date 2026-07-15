package com.valiantenvoy.rainbow_reef.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import com.valiantenvoy.rainbow_reef.RainbowReef;

public class ReefBlockTags {

    public static final TagKey<Block> TALL_CORALS = modBlockTag("tall_corals");

    public static final TagKey<Block> BURROWS = modBlockTag("burrows");
    public static final TagKey<Block> GROUND_BURROWS = modBlockTag("ground_burrows");
    public static final TagKey<Block> WALL_BURROWS = modBlockTag("wall_burrows");
    public static final TagKey<Block> BURROWABLE_MUD = modBlockTag("burrowable/mud");
    public static final TagKey<Block> BURROWABLE_CORALSTONE = modBlockTag("burrowable/coralstone");

    private static TagKey<Block> modBlockTag(String name) {
        return blockTag(RainbowReef.MOD_ID, name);
    }

    private static TagKey<Block> forgeBlockTag(String name) {
        return blockTag("forge", name);
    }

    public static TagKey<Block> blockTag(String modid, String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(modid, name));
    }
}
