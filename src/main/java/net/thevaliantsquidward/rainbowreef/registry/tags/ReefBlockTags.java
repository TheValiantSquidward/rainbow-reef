package net.thevaliantsquidward.rainbowreef.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public class ReefBlockTags {

    public static final TagKey<Block> TALL_CORALS = modBlockTag("tall_corals");

    private static TagKey<Block> modBlockTag(String name) {
        return blockTag(RainbowReef.MOD_ID, name);
    }

    private static TagKey<Block> forgeBlockTag(String name) {
        return blockTag("forge", name);
    }

    public static TagKey<Block> blockTag(String modid, String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(modid, name));
    }
}
