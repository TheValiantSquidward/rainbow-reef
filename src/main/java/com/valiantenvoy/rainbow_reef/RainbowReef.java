package com.valiantenvoy.rainbow_reef;

import com.valiantenvoy.rainbow_reef.datagen.*;
import com.valiantenvoy.rainbow_reef.registry.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.thevaliantsquidward.rainbowreef.datagen.*;
import net.thevaliantsquidward.rainbowreef.registry.*;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(RainbowReef.MOD_ID)
public class RainbowReef {

    public static final String MOD_ID = "rainbow_reef";

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static ResourceLocation prefix(String name) {
        return location(name.toLowerCase(Locale.ROOT));
    }

    public RainbowReef(IEventBus modEventBus) {
        RainbowReefTab.register(modEventBus);

        ReefEntities.ENTITY_TYPES.register(modEventBus);
        ReefItems.ITEMS.register(modEventBus);
        ReefBlocks.BLOCKS.register(modEventBus);
        ReefBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ReefFeatures.FEATURES.register(modEventBus);
        ReefPoiTypes.POI_TYPES.register(modEventBus);
        ReefSoundEvents.SOUND_EVENTS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::dataSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void dataSetup(GatherDataEvent data) {
        DataGenerator generator = data.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = data.getLookupProvider();
        ExistingFileHelper helper = data.getExistingFileHelper();

        boolean server = data.includeServer();
        ReefDatapackBuiltinEntriesProvider datapackEntries = new ReefDatapackBuiltinEntriesProvider(output, provider);
        generator.addProvider(server, datapackEntries);
        provider = datapackEntries.getRegistryProvider();
        ReefBlockTagProvider blockTags = new ReefBlockTagProvider(output, provider, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new ReefItemTagProvider(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(server, new ReefBiomeTagProvider(output, provider, helper));
        generator.addProvider(server, new ReefRecipeProvider(output, provider));
        generator.addProvider(server, new LootTableProvider(output, Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(ReefBlockLootProvider::new, LootContextParamSets.BLOCK)), provider));

        boolean client = data.includeClient();
        generator.addProvider(client, new ReefBlockstateProvider(data));
        generator.addProvider(client, new ReefItemModelProvider(data));
        generator.addProvider(client, new ReefSoundDefinitionsProvider(output, helper));
        generator.addProvider(client, new ReefLanguageProvider(data));
    }

    public static ResourceLocation modPrefix(String name) {
        return location(name.toLowerCase(Locale.ROOT));
    }
}
