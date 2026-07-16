package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.tags.ReefBiomeTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddFeaturesBiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.AddSpawnsBiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers.RemoveFeaturesBiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReefBiomeModifiers {

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        // VariantSchoolingFish spawn in groups so they spawn with much lower counts here
        addSpawn(context, "angelfish", ReefBiomeTags.HAS_ANGELFISH, new MobSpawnSettings.SpawnerData(ReefEntities.ANGELFISH.get(), 10, 2, 2));
        addSpawn(context, "arrow_crab", ReefBiomeTags.HAS_ARROW_CRAB, new MobSpawnSettings.SpawnerData(ReefEntities.ARROW_CRAB.get(), 10, 2, 2));
        addSpawn(context, "basslet", ReefBiomeTags.HAS_BASSLET, new MobSpawnSettings.SpawnerData(ReefEntities.BASSLET.get(), 15, 6, 6));
        addSpawn(context, "boxfish", ReefBiomeTags.HAS_BOXFISH, new MobSpawnSettings.SpawnerData(ReefEntities.BOXFISH.get(), 20, 3, 3));
        addSpawn(context, "butterflyfish", ReefBiomeTags.HAS_BUTTERFLYFISH, new MobSpawnSettings.SpawnerData(ReefEntities.BUTTERFLYFISH.get(), 40, 2, 2));
        addSpawn(context, "butterflyfish_mangrove", ReefBiomeTags.HAS_BUTTERFLYFISH_MANGROVE, new MobSpawnSettings.SpawnerData(ReefEntities.BUTTERFLYFISH.get(), 20, 1, 1));
        addSpawn(context, "clownfish", ReefBiomeTags.HAS_CLOWNFISH, new MobSpawnSettings.SpawnerData(ReefEntities.CLOWNFISH.get(), 20, 2, 2));
        addSpawn(context, "crab", ReefBiomeTags.HAS_CRAB, new MobSpawnSettings.SpawnerData(ReefEntities.CRAB.get(), 20, 2, 2));
        addSpawn(context, "dwarf_angelfish", ReefBiomeTags.HAS_DWARF_ANGELFISH, new MobSpawnSettings.SpawnerData(ReefEntities.DWARF_ANGELFISH.get(), 30, 4, 4));
        addSpawn(context, "goby", ReefBiomeTags.HAS_GOBY, new MobSpawnSettings.SpawnerData(ReefEntities.GOBY.get(), 35, 5, 5));
        addSpawn(context, "goby_mangrove", ReefBiomeTags.HAS_GOBY_MANGROVE, new MobSpawnSettings.SpawnerData(ReefEntities.GOBY.get(), 15, 3, 3));
        addSpawn(context, "hogfish", ReefBiomeTags.HAS_HOGFISH, new MobSpawnSettings.SpawnerData(ReefEntities.HOGFISH.get(), 10, 3, 3));
        addSpawn(context, "jellyfish", ReefBiomeTags.HAS_JELLYFISH, new MobSpawnSettings.SpawnerData(ReefEntities.JELLYFISH.get(), 8, 3, 3));
        addSpawn(context, "jellyfish_rare", ReefBiomeTags.HAS_JELLYFISH_RARE, new MobSpawnSettings.SpawnerData(ReefEntities.JELLYFISH.get(), 4, 2, 2));
        addSpawn(context, "moorish_idol", ReefBiomeTags.HAS_MOORISH_IDOL, new MobSpawnSettings.SpawnerData(ReefEntities.MOORISH_IDOL.get(), 8, 1, 1));
        addSpawn(context, "parrotfish", ReefBiomeTags.HAS_PARROTFISH, new MobSpawnSettings.SpawnerData(ReefEntities.PARROTFISH.get(), 12, 1, 1));
        addSpawn(context, "pipefish", ReefBiomeTags.HAS_PIPEFISH, new MobSpawnSettings.SpawnerData(ReefEntities.PIPEFISH.get(), 10, 3, 3));
        addSpawn(context, "ray", ReefBiomeTags.HAS_RAY, new MobSpawnSettings.SpawnerData(ReefEntities.RAY.get(), 2, 1, 1));
        addSpawn(context, "seahorse", ReefBiomeTags.HAS_SEAHORSE, new MobSpawnSettings.SpawnerData(ReefEntities.SEAHORSE.get(), 8, 4, 4));
        addSpawn(context, "small_shark", ReefBiomeTags.HAS_SMALL_SHARK, new MobSpawnSettings.SpawnerData(ReefEntities.SMALL_SHARK.get(), 10, 3, 3));
        addSpawn(context, "tang", ReefBiomeTags.HAS_TANG, new MobSpawnSettings.SpawnerData(ReefEntities.TANG.get(), 40, 2, 2));
    }

    private static void addSpawn(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, MobSpawnSettings.SpawnerData... spawns) {
        register(context, "add_spawn/" + name, () -> new AddSpawnsBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), List.of(spawns)));
    }

    @SafeVarargs
    private static void addFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "add_feature/" + name, () -> new AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), step));
    }

    @SafeVarargs
    private static void removeFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, GenerationStep.Decoration step, ResourceKey<PlacedFeature>... features) {
        register(context, "remove_feature/" + name, () -> new RemoveFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, features), Set.of(step)));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, RainbowReef.location(name)), modifier.get());
    }

    @SafeVarargs
    private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
    }
}
