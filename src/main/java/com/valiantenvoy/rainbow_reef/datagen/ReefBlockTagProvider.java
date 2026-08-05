package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.blocks.BurrowBlock;
import com.valiantenvoy.rainbow_reef.tags.ReefBlockTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.valiantenvoy.rainbow_reef.registry.ReefBlocks.*;

public class ReefBlockTagProvider extends BlockTagsProvider {

    public ReefBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RainbowReef.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(Provider provider) {

        this.tag(BlockTags.CORAL_PLANTS).add(
                SHELF_CORAL.get(),
                BARREL_CORAL.get(),
                HAND_CORAL.get(),
                CHIMNEY_CORAL.get(),
                TOWER_CORAL.get(),
                ROSE_CORAL.get(),
                FLOWER_CORAL.get(),
                RING_CORAL.get(),
                BUSH_CORAL.get()
        );

        this.tag(BlockTags.CORAL_BLOCKS).add(
                SHELF_CORAL_BLOCK.get(),
                BARREL_CORAL_BLOCK.get(),
                HAND_CORAL_BLOCK.get(),
                CHIMNEY_CORAL_BLOCK.get(),
                TOWER_CORAL_BLOCK.get(),
                ROSE_CORAL_BLOCK.get(),
                FLOWER_CORAL_BLOCK.get(),
                RING_CORAL_BLOCK.get(),
                BUSH_CORAL_BLOCK.get()
        );

        this.tag(BlockTags.CORALS).add(
                SHELF_CORAL_FAN.get(),
                BARREL_CORAL_FAN.get(),
                HAND_CORAL_FAN.get(),
                CHIMNEY_CORAL_FAN.get(),
                TOWER_CORAL_FAN.get(),
                ROSE_CORAL_FAN.get(),
                FLOWER_CORAL_FAN.get(),
                RING_CORAL_FAN.get(),
                BUSH_CORAL_FAN.get()
        );

        this.tag(BlockTags.WALL_CORALS).add(
                SHELF_CORAL_WALL_FAN.get(),
                BARREL_CORAL_WALL_FAN.get(),
                HAND_CORAL_WALL_FAN.get(),
                CHIMNEY_CORAL_WALL_FAN.get(),
                TOWER_CORAL_WALL_FAN.get(),
                ROSE_CORAL_WALL_FAN.get(),
                FLOWER_CORAL_WALL_FAN.get(),
                RING_CORAL_WALL_FAN.get(),
                BUSH_CORAL_WALL_FAN.get()
        );

        for (Block block : List.of(MUD_BURROW.get(), SAND_BURROW.get(), STONE_BURROW.get(), CORALSTONE_BURROW.get())) {
            BurrowBlock burrow = (BurrowBlock) block;
            this.tag(burrow.isGround() ? ReefBlockTags.GROUND_BURROWS : ReefBlockTags.WALL_BURROWS).add(burrow);
        }

        this.tag(ReefBlockTags.BURROWS).addTag(ReefBlockTags.GROUND_BURROWS).addTag(ReefBlockTags.WALL_BURROWS);

        this.tag(ReefBlockTags.BURROWABLE_MUD).add(Blocks.MUD);
        this.tag(ReefBlockTags.BURROWABLE_CORALSTONE).add(CORALSTONE.get());

        this.tag(ReefBlockTags.STARFISHES).add(
                CORAL_STARFISH.get(),
                LAGOON_STARFISH.get(),
                PLUMERIA_STARFISH.get(),
                SKY_BLUE_STARFISH.get(),
                SUNNY_STARFISH.get(),
                SUNSET_STARFISH.get()
        );

        this.tag(ReefBlockTags.SEA_ANEMONES).add(
                PURPLE_SEA_ANEMONE.get(),
                BROWN_SEA_ANEMONE.get(),
                PINK_SEA_ANEMONE.get(),
                RED_SEA_ANEMONE.get(),
                MAGENTA_SEA_ANEMONE.get()
        );

        this.tag(ReefBlockTags.REEF_ROCK_CANNOT_REPLACE).addTags(
                BlockTags.SAND,
                BlockTags.STONE_ORE_REPLACEABLES
        );

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                MUD_BURROW.get(), SAND_BURROW.get()
        );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                STONE_BURROW.get(),
                CORALSTONE_BURROW.get(),

                CORALSTONE.get(),
                CORALSTONE_BRICKS.get(),
                POLISHED_CORALSTONE.get(),
                CHISELED_CORALSTONE.get(),

                SHELF_CORAL_BLOCK.get(),
                BARREL_CORAL_BLOCK.get(),
                HAND_CORAL_BLOCK.get(),
                CHIMNEY_CORAL_BLOCK.get(),
                TOWER_CORAL_BLOCK.get(),
                ROSE_CORAL_BLOCK.get(),
                FLOWER_CORAL_BLOCK.get(),
                RING_CORAL_BLOCK.get(),
                BUSH_CORAL_BLOCK.get(),
                DEAD_SHELF_CORAL_BLOCK.get(),
                DEAD_BARREL_CORAL_BLOCK.get(),
                DEAD_HAND_CORAL_BLOCK.get(),
                DEAD_CHIMNEY_CORAL_BLOCK.get(),
                DEAD_TOWER_CORAL_BLOCK.get(),
                DEAD_ROSE_CORAL_BLOCK.get(),
                DEAD_FLOWER_CORAL_BLOCK.get(),
                DEAD_RING_CORAL_BLOCK.get(),
                DEAD_BUSH_CORAL_BLOCK.get()
        );

        this.tag(ReefBlockTags.TALL_CORALS).add(
                TALL_TUBE_CORAL.get(),
                TALL_BRAIN_CORAL.get(),
                TALL_BUBBLE_CORAL.get(),
                TALL_FIRE_CORAL.get(),
                TALL_HORN_CORAL.get(),
                TALL_BARREL_CORAL.get(),
                TALL_BUSH_CORAL.get(),
                TALL_CHIMNEY_CORAL.get(),
                TALL_HAND_CORAL.get(),
                TALL_RING_CORAL.get(),
                TALL_FLOWER_CORAL.get(),
                TALL_ROSE_CORAL.get(),
                TALL_SHELF_CORAL.get()
        );

        this.tag(ReefBlockTags.SEAGRASS).add(
                Blocks.SEAGRASS,
                Blocks.TALL_SEAGRASS
        );

        this.tag(ReefBlockTags.CORAL_BLOCKS_AND_PLANTS).addTags(
                BlockTags.CORAL_BLOCKS,
                BlockTags.CORAL_PLANTS,
                ReefBlockTags.TALL_CORALS
        );

        this.tag(ReefBlockTags.SPONGES).add(
                Blocks.SPONGE,
                Blocks.WET_SPONGE
        );

        this.tag(ReefBlockTags.OCEAN_SEDIMENTS).addTags(
                BlockTags.SAND,
                BlockTags.DIRT,
                Tags.Blocks.GRAVELS
        ).add(Blocks.CLAY);

        this.tag(ReefBlockTags.SEAGRASS_AND_SPONGES).addTags(
                ReefBlockTags.SPONGES,
                ReefBlockTags.SEAGRASS
        );

        this.tag(ReefBlockTags.SEAGRASS_AND_CORALS).addTags(
                ReefBlockTags.CORAL_BLOCKS_AND_PLANTS,
                ReefBlockTags.SEAGRASS
        );

        this.tag(ReefBlockTags.OCEAN_SEDIMENTS_AND_CORALS).addTags(
                ReefBlockTags.CORAL_BLOCKS_AND_PLANTS,
                ReefBlockTags.OCEAN_SEDIMENTS
        );
    }


    @Override
    public String getName() {
        return RainbowReef.MOD_ID + " Block Tags";
    }
}
