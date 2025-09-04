package net.thevaliantsquidward.rainbowreef.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.thevaliantsquidward.rainbowreef.registry.ReefBlocks.*;

public class ReefBlockTagProvider extends BlockTagsProvider {

    public ReefBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RainbowReef.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

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
    }


    @Override
    @NotNull
    public String getName() {
        return RainbowReef.MOD_ID + " Block Tags";
    }
}
