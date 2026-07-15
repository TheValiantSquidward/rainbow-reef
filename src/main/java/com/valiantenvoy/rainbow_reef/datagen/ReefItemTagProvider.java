package com.valiantenvoy.rainbow_reef.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.tags.ReefItemTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.valiantenvoy.rainbow_reef.registry.ReefItems.RAW_ARROW_CRAB;
import static com.valiantenvoy.rainbow_reef.registry.ReefItems.RAW_CRAB;

public class ReefItemTagProvider extends ItemTagsProvider {

    public ReefItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, completableFuture, RainbowReef.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {

        this.tag(ReefItemTags.RAINBOW_REEF_CRABS).add(
                RAW_CRAB.get(),
                RAW_ARROW_CRAB.get()
        );

        this.tag(ReefItemTags.CRABS).addTag(ReefItemTags.RAINBOW_REEF_CRABS).addOptional(ResourceLocation.fromNamespaceAndPath("seafarer", "shore_crab")).addOptional(ResourceLocation.fromNamespaceAndPath("seafarer", "horseshoe_crab"));
    }

    @Override
    public @NotNull String getName() {
        return RainbowReef.MOD_ID + " Item Tags";
    }
}
