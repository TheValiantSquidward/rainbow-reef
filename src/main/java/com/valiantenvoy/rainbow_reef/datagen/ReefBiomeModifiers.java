package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.tags.ReefBiomeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddSpawnsBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

public class ReefBiomeModifiers {

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        addSpawn(context, "angelfish", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.ANGELFISH.get(), 10, 4, 4));
        addSpawn(context, "arrow_crab", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.ARROW_CRAB.get(), 10, 2, 2));
        addSpawn(context, "basslet", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.BASSLET.get(), 15, 6, 6));
        addSpawn(context, "boxfish", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.BOXFISH.get(), 20, 3, 3));
        addSpawn(context, "butterflyfish", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.BUTTERFLYFISH.get(), 40, 2, 2));
        addSpawn(context, "butterflyfish_mangrove", ReefBiomeTags.HAS_BUTTERFLYFISH_MANGROVE, new MobSpawnSettings.SpawnerData(ReefEntities.BUTTERFLYFISH.get(), 20, 12, 12));
        addSpawn(context, "clownfish", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.CLOWNFISH.get(), 20, 4, 4));
        addSpawn(context, "crab", ReefBiomeTags.HAS_CRAB, new MobSpawnSettings.SpawnerData(ReefEntities.CRAB.get(), 20, 2, 2));
        addSpawn(context, "dwarf_angelfish", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.DWARF_ANGELFISH.get(), 30, 6, 6));
        addSpawn(context, "goby", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.GOBY.get(), 30, 5, 5));
        addSpawn(context, "goby_mangrove", ReefBiomeTags.HAS_GOBY_MANGROVE, new MobSpawnSettings.SpawnerData(ReefEntities.GOBY.get(), 15, 3, 3));
        addSpawn(context, "hogfish", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.HOGFISH.get(), 15, 5, 5));
        addSpawn(context, "jellyfish", ReefBiomeTags.HAS_JELLYFISH, new MobSpawnSettings.SpawnerData(ReefEntities.JELLYFISH.get(), 8, 3, 3));
        addSpawn(context, "jellyfish_rare", ReefBiomeTags.HAS_JELLYFISH_RARE, new MobSpawnSettings.SpawnerData(ReefEntities.JELLYFISH.get(), 4, 2, 2));
        addSpawn(context, "moorish_idol", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.MOORISH_IDOL.get(), 10, 10, 10));
        addSpawn(context, "parrotfish", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.PARROTFISH.get(), 15, 5, 5));
        addSpawn(context, "pipefish", ReefBiomeTags.HAS_PIPEFISH, new MobSpawnSettings.SpawnerData(ReefEntities.PIPEFISH.get(), 10, 3, 3));
        addSpawn(context, "ray", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.RAY.get(), 5, 3, 3));
        addSpawn(context, "seahorse", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.SEAHORSE.get(), 10, 4, 4));
        addSpawn(context, "small_shark", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.SMALL_SHARK.get(), 10, 3, 3));
        addSpawn(context, "tang", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.TANG.get(), 40, 20, 20));
        addSpawn(context, "damselfish", ReefBiomeTags.WARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.DAMSELFISH.get(), 30, 24, 24));
        addSpawn(context, "fusilier", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.FUSILIER.get(), 25, 16, 16));
        addSpawn(context, "shark", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.SHARK.get(), 5, 3, 3));
        addSpawn(context, "large_shark", ReefBiomeTags.WARM_AND_LUKEWARM_OCEANS, new MobSpawnSettings.SpawnerData(ReefEntities.LARGE_SHARK.get(), 2, 1, 1));
    }

    private static void addSpawn(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
        register(context, "add_spawn/" + name, () -> new AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, RainbowReef.location(name)), modifier.get());
    }
}
