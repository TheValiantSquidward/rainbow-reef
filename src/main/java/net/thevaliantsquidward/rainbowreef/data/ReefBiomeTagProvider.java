package net.thevaliantsquidward.rainbowreef.data;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.thevaliantsquidward.rainbowreef.registry.tags.ReefBiomeTags.*;

public class ReefBiomeTagProvider extends BiomeTagsProvider {

    public ReefBiomeTagProvider(PackOutput output, CompletableFuture<Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, RainbowReef.MOD_ID, helper);
    }

    @Override
    public void addTags(Provider provider) {
        this.tag(WARM_OCEANS).add(Biomes.WARM_OCEAN).addOptional(new ResourceLocation("seafarer", "warm_reef"));

        this.tag(HAS_ANGELFISH).addTag(WARM_OCEANS);
        this.tag(HAS_ARROW_CRAB).addTag(WARM_OCEANS);
        this.tag(HAS_BASSLET).addTag(WARM_OCEANS);

        this.tag(HAS_BOXFISH).addTag(WARM_OCEANS).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_BUTTERFLYFISH).addTag(WARM_OCEANS);
        this.tag(HAS_BUTTERFLYFISH_MANGROVE).add(Biomes.MANGROVE_SWAMP);
        this.tag(HAS_CLOWNFISH).addTag(WARM_OCEANS);

        this.tag(HAS_CRAB).addTag(WARM_OCEANS).add(
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN,
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN,
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN,
                Biomes.BEACH,
                Biomes.STONY_SHORE,
                Biomes.JUNGLE,
                Biomes.SWAMP,
                Biomes.MANGROVE_SWAMP
        ).addOptional(new ResourceLocation("seafarer", "warm_reef"));;

        this.tag(HAS_DWARF_ANGELFISH).addTag(WARM_OCEANS);
        this.tag(HAS_GOBY).addTag(WARM_OCEANS);
        this.tag(HAS_GOBY_MANGROVE).add(Biomes.MANGROVE_SWAMP);

        this.tag(HAS_HOGFISH).addTag(WARM_OCEANS).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_JELLYFISH).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_JELLYFISH_RARE).add(Biomes.OCEAN, Biomes.DEEP_OCEAN);

        this.tag(HAS_MOORISH_IDOL).addTag(WARM_OCEANS).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_PARROTFISH).addTag(WARM_OCEANS).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_PIPEFISH).add(Biomes.MANGROVE_SWAMP);

        this.tag(HAS_RAY).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_SEAHORSE).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.MANGROVE_SWAMP);

        this.tag(HAS_SMALL_SHARK).addTag(WARM_OCEANS).add(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN);

        this.tag(HAS_TANG).addTag(WARM_OCEANS);
    }
}