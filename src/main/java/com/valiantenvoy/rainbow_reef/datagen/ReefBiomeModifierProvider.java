package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.tags.ReefBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddSpawnsBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public class ReefBiomeModifierProvider {

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        addSpawn(context, "angelfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.ANGELFISH.get(), 25, 4, 4));
        addSpawn(context, "arrow_crab", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.ARROW_CRAB.get(), 20, 2, 2));
        addSpawn(context, "basslet", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.BASSLET.get(), 15, 6, 6));
        addSpawn(context, "boxfish", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.BOXFISH.get(), 20, 3, 3));
        addSpawn(context, "butterflyfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.BUTTERFLYFISH.get(), 25, 12, 12));
        addSpawn(context, "butterflyfish_mangrove", ReefBiomeTags.HAS_BUTTERFLYFISH_MANGROVE, new SpawnerData(ReefEntities.BUTTERFLYFISH.get(), 20, 12, 12));
        addSpawn(context, "clownfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.CLOWNFISH.get(), 20, 6, 6));
        addSpawn(context, "crab", ReefBiomeTags.HAS_CRAB, new SpawnerData(ReefEntities.CRAB.get(), 20, 2, 2));
        addSpawn(context, "dwarf_angelfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.DWARF_ANGELFISH.get(), 20, 6, 6));
        addSpawn(context, "goby", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.GOBY.get(), 20, 5, 5));
        addSpawn(context, "goby_mangrove", ReefBiomeTags.HAS_GOBY_MANGROVE, new SpawnerData(ReefEntities.GOBY.get(), 15, 3, 3));
        addSpawn(context, "hogfish", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.HOGFISH.get(), 15, 5, 5));
        addSpawn(context, "jellyfish", ReefBiomeTags.LUKEWARM_OCEANS, new SpawnerData(ReefEntities.JELLYFISH.get(), 8, 3, 3));
        addSpawn(context, "jellyfish_rare", ReefBiomeTags.TEMPERATE_OCEANS, new SpawnerData(ReefEntities.JELLYFISH.get(), 4, 2, 2));
        addSpawn(context, "moorish_idol", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.MOORISH_IDOL.get(), 10, 10, 10));
        addSpawn(context, "parrotfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.PARROTFISH.get(), 15, 5, 5));
        addSpawn(context, "pipefish", ReefBiomeTags.HAS_PIPEFISH, new SpawnerData(ReefEntities.PIPEFISH.get(), 10, 3, 3));
        addSpawn(context, "ray", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.RAY.get(), 6, 3, 3));
        addSpawn(context, "seahorse", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.SEAHORSE.get(), 10, 4, 4));
        addSpawn(context, "small_shark", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.SMALL_SHARK.get(), 10, 3, 3));
        addSpawn(context, "tang", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.TANG.get(), 25, 20, 20));
        addSpawn(context, "damselfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.DAMSELFISH.get(), 25, 24, 24));
        addSpawn(context, "fusilier", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.FUSILIER.get(), 20, 16, 16));
        addSpawn(context, "shark", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.SHARK.get(), 5, 3, 3));
        addSpawn(context, "large_shark", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.LARGE_SHARK.get(), 2, 1, 1));
        addSpawn(context, "lionfish", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new SpawnerData(ReefEntities.LIONFISH.get(), 5, 2, 2));
        addSpawn(context, "maori_wrasse", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.MAORI_WRASSE.get(), 5, 1, 1));
        addSpawn(context, "triggerfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.TRIGGERFISH.get(), 15, 4, 4));
        addSpawn(context, "wrasse", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.WRASSE.get(), 15, 5, 5));
        addSpawn(context, "mahi_mahi", ReefBiomeTags.LUKEWARM_AND_TEMPERATE_OCEANS, new SpawnerData(ReefEntities.MAHI_MAHI.get(), 10, 8, 8));
        addSpawn(context, "billfish", ReefBiomeTags.LUKEWARM_AND_TEMPERATE_OCEANS, new SpawnerData(ReefEntities.BILLFISH.get(), 5, 2, 2));
        addSpawn(context, "frogfish", ReefBiomeTags.WARM_OCEANS, new SpawnerData(ReefEntities.FROGFISH.get(), 20, 3, 3));
    }

    private static void addSpawn(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, SpawnerData... spawns) {
        register(context, "add_spawn/" + name, () -> new AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, RainbowReef.location(name)), modifier.get());
    }
}
