package com.valiantenvoy.rainbow_reef.entity.variant;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class ReefMobVariant {

    public static final Codec<ReefMobVariant> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("texture").forGetter(variant -> variant.texture),
            RegistryCodecs.homogeneousList(Registries.BIOME).optionalFieldOf("biomes").forGetter(ReefMobVariant::biomes),
            Codec.INT.optionalFieldOf("priority", 0).forGetter(ReefMobVariant::priority),
            VariantTime.CODEC.optionalFieldOf("time", VariantTime.ANY).forGetter(ReefMobVariant::time),
            VariantWeather.CODEC.optionalFieldOf("weather", VariantWeather.ANY).forGetter(ReefMobVariant::weather),
            Codec.INT.optionalFieldOf("min_spawn_height").forGetter(ReefMobVariant::minSpawnHeight),
            Codec.INT.optionalFieldOf("max_spawn_height").forGetter(ReefMobVariant::maxSpawnHeight),
            VariantRarity.CODEC.optionalFieldOf("rarity", VariantRarity.COMMON).forGetter(ReefMobVariant::rarity),
            VariantRenderType.CODEC.optionalFieldOf("render_type", VariantRenderType.ENTITY_CUTOUT).forGetter(ReefMobVariant::renderType)
    ).apply(instance, ReefMobVariant::new));

    private final ResourceLocation texture;
    private final Optional<HolderSet<Biome>> biomes;
    private final int weight;
    private final int priority;
    private final VariantTime time;
    private final VariantWeather weather;
    private final Optional<Integer> minSpawnHeight;
    private final Optional<Integer> maxSpawnHeight;
    private final VariantRarity rarity;
    private final VariantRenderType renderType;

    public ReefMobVariant(ResourceLocation texture, Optional<HolderSet<Biome>> biomes, int priority, VariantTime time, VariantWeather weather, Optional<Integer> minSpawnHeight, Optional<Integer> maxSpawnHeight, VariantRarity rarity, VariantRenderType renderType) {
        this.texture = texture;
        this.biomes = biomes;
        this.weight = rarity.getWeight();
        this.priority = priority;
        this.time = time;
        this.weather = weather;
        this.minSpawnHeight = minSpawnHeight;
        this.maxSpawnHeight = maxSpawnHeight;
        this.rarity = rarity;
        this.renderType = renderType;
    }

    public ResourceLocation texture() {
        return fullTextureId(this.texture);
    }

    public ResourceLocation rawTexture() {
        return this.texture;
    }

    public Optional<HolderSet<Biome>> biomes() {
        return this.biomes;
    }

    public int weight() {
        return this.weight;
    }

    public int priority() {
        return this.priority;
    }

    public VariantTime time() {
        return this.time;
    }

    public VariantWeather weather() {
        return this.weather;
    }

    public Optional<Integer> minSpawnHeight() {
        return this.minSpawnHeight;
    }

    public Optional<Integer> maxSpawnHeight() {
        return this.maxSpawnHeight;
    }

    public VariantRarity rarity() {
        return this.rarity;
    }

    public VariantRenderType renderType() {
        return this.renderType;
    }

    public static ResourceLocation fullTextureId(ResourceLocation texture) {
        return texture.withPath(path -> "textures/" + path + ".png");
    }
}
