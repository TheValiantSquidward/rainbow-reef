package net.thevaliantsquidward.rainbowreef.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefItemTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.thevaliantsquidward.rainbowreef.registry.ReefItems.*;

public class ReefItemTagProvider extends ItemTagsProvider {

    public ReefItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, completableFuture, RainbowReef.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ReefItemTags.RAINBOW_REEF_CRABS).add(
                RAW_CRAB.get(),
                RAW_ARROW_CRAB.get()
        );

        this.tag(ReefItemTags.CRABS).addTag(ReefItemTags.RAINBOW_REEF_CRABS).addOptional(new ResourceLocation("seafarer", "shore_crab")).addOptional(new ResourceLocation("seafarer", "horseshoe_crab"));
    }

    @Override
    public String getName() {
        return RainbowReef.MOD_ID + " Item Tags";
    }
}
