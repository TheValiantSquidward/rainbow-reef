package net.thevaliantsquidward.rainbowreef;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.thevaliantsquidward.rainbowreef.datagen.*;
import net.thevaliantsquidward.rainbowreef.registry.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefPoiTypes;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Mod(RainbowReef.MOD_ID)
public class RainbowReef {

    public static final String MOD_ID = "rainbowreef";

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
        ReefLootModifiers.LOOT_MODIFIERS.register(modEventBus);
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
