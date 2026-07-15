package net.thevaliantsquidward.rainbowreef.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.blocks.BurrowBlock;
import net.thevaliantsquidward.rainbowreef.tags.ReefBlockTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.thevaliantsquidward.rainbowreef.registry.ReefBlocks.*;

public class ReefBlockTagProvider extends BlockTagsProvider {

    public ReefBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RainbowReef.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(BlockTags.CORAL_PLANTS).add(
                SHELF_CORAL.get(),
                SHELF_CORAL_FAN.get(),
                BARREL_CORAL.get(),
                BARREL_CORAL_FAN.get(),
                HAND_CORAL.get(),
                HAND_CORAL_FAN.get(),
                CHIMNEY_CORAL.get(),
                CHIMNEY_CORAL_FAN.get(),
                TOWER_CORAL.get(),
                TOWER_CORAL_FAN.get(),
                ROSE_CORAL.get(),
                ROSE_CORAL_FAN.get(),
                FLOWER_CORAL.get(),
                FLOWER_CORAL_FAN.get(),
                RING_CORAL.get(),
                RING_CORAL_FAN.get(),
                BUSH_CORAL.get(),
                BUSH_CORAL_FAN.get(),
                GREEN_SEA_ANEMONE.get(),
                ORANGE_SEA_ANEMONE.get(),
                YELLOW_SEA_ANEMONE.get()
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

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MUD_BURROW.get(), SAND_BURROW.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(STONE_BURROW.get(), CORALSTONE_BURROW.get());

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
    }


    @Override
    @NotNull
    public String getName() {
        return RainbowReef.MOD_ID + " Block Tags";
    }
}
