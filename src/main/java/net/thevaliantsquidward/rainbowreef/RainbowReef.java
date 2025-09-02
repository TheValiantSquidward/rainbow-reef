package net.thevaliantsquidward.rainbowreef;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thevaliantsquidward.rainbowreef.data.*;
import net.thevaliantsquidward.rainbowreef.registry.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefPoiTypes;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Mod(RainbowReef.MOD_ID)
public class RainbowReef {

    public static final String MOD_ID = "rainbowreef";

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }

    public RainbowReef() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RainbowReefTab.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

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
        generator.addProvider(server, new ReefBiomeTagProvider(output, provider, helper));

        boolean client = data.includeClient();
        generator.addProvider(client, new ReefBlockstateProvider(data));
        generator.addProvider(client, new ReefItemModelProvider(data));
        generator.addProvider(client, new ReefSoundDefinitionsProvider(output, helper));
        generator.addProvider(client, new ReefLanguageProvider(data));
    }

    public static ResourceLocation modPrefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }
}
