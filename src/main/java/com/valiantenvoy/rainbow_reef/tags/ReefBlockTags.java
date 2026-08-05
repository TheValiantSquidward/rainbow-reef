package com.valiantenvoy.rainbow_reef.tags;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ReefBlockTags {

    public static final TagKey<Block> TALL_CORALS = modBlockTag("tall_corals");

    public static final TagKey<Block> BURROWS = modBlockTag("burrows");
    public static final TagKey<Block> GROUND_BURROWS = modBlockTag("ground_burrows");
    public static final TagKey<Block> WALL_BURROWS = modBlockTag("wall_burrows");
    public static final TagKey<Block> BURROWABLE_MUD = modBlockTag("burrowable/mud");
    public static final TagKey<Block> BURROWABLE_CORALSTONE = modBlockTag("burrowable/coralstone");

    public static final TagKey<Block> STARFISHES = modBlockTag("starfishes");

    public static final TagKey<Block> SEA_ANEMONES = modBlockTag("sea_anemones");

    public static final TagKey<Block> REEF_ROCK_CANNOT_REPLACE = modBlockTag("reef_rock_cannot_replace");

    public static final TagKey<Block> SEAGRASS = modBlockTag("seagrass");
    public static final TagKey<Block> CORAL_BLOCKS_AND_PLANTS = modBlockTag("coral_blocks_and_plants");
    public static final TagKey<Block> SPONGES = modBlockTag("sponges");
    public static final TagKey<Block> OCEAN_SEDIMENTS = modBlockTag("ocean_sediments");
    public static final TagKey<Block> SEAGRASS_AND_SPONGES = modBlockTag("seagrass_and_sponges");
    public static final TagKey<Block> SEAGRASS_AND_CORALS = modBlockTag("seagrass_and_corals");
    public static final TagKey<Block> OCEAN_SEDIMENTS_AND_CORALS = modBlockTag("ocean_sediments_and_corals");


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
