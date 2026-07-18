package com.valiantenvoy.rainbow_reef;

import com.valiantenvoy.rainbow_reef.datagen.*;
import com.valiantenvoy.rainbow_reef.network.ParticlePacket;
import com.valiantenvoy.rainbow_reef.registry.*;
import com.valiantenvoy.rainbow_reef.registry.ReefBiomeModifiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod(RainbowReef.MOD_ID)
public class RainbowReef {

    public static final String MOD_ID = "rainbow_reef";

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public RainbowReef(IEventBus modEventBus) {
        RainbowReefTab.register(modEventBus);

        ReefEntities.ENTITY_TYPE.register(modEventBus);
        ReefItems.ITEMS.register(modEventBus);
        ReefBlocks.BLOCKS.register(modEventBus);
        ReefBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ReefFeatures.FEATURES.register(modEventBus);
        ReefPoiTypes.POI_TYPES.register(modEventBus);
        ReefSoundEvents.SOUND_EVENTS.register(modEventBus);
        ReefParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ReefBiomeModifiers.BIOME_MODIFIERS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::packetSetup);
        modEventBus.addListener(this::dataSetup);
        modEventBus.addListener(ReefMobVariants::registerVariantRegistries);
        modEventBus.addListener(this::addPackFinders);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    public void packetSetup(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MOD_ID).versioned("1.0.0").optional();
        registrar.playToClient(ParticlePacket.TYPE, ParticlePacket.CODEC, ParticlePacket::handle);
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

    public void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addPackFinders(location("resourcepacks/water_tweaks"), PackType.CLIENT_RESOURCES, Component.literal("Rainbow Reef Water Tweaks"), PackSource.BUILT_IN, true, Pack.Position.TOP);
        }
        if (event.getPackType() == PackType.SERVER_DATA) {
            event.addPackFinders(location("datapacks/water_colors"), PackType.SERVER_DATA, Component.literal("Rainbow Reef Water Color Overrides"), PackSource.BUILT_IN, true, Pack.Position.TOP);
        }
    }
}
