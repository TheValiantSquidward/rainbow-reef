package com.valiantenvoy.rainbow_reef.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeSpecialEffectsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.Optional;

public record BiomeColorModifier(HolderSet<Biome> biomes, Optional<Integer> fogColor, Optional<Integer> waterColor,
                                 Optional<Integer> waterFogColor, Optional<Integer> skyColor, Optional<Integer> grassColor,
                                 Optional<Integer> foliageColor) implements BiomeModifier {

    public static final MapCodec<BiomeColorModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(BiomeColorModifier::biomes),
                    Codec.INT.optionalFieldOf("fog_color").forGetter(BiomeColorModifier::fogColor),
                    Codec.INT.optionalFieldOf("water_color").forGetter(BiomeColorModifier::waterColor),
                    Codec.INT.optionalFieldOf("water_fog_color").forGetter(BiomeColorModifier::waterFogColor),
                    Codec.INT.optionalFieldOf("sky_color").forGetter(BiomeColorModifier::skyColor),
                    Codec.INT.optionalFieldOf("grass_color").forGetter(BiomeColorModifier::grassColor),
                    Codec.INT.optionalFieldOf("foliage_color").forGetter(BiomeColorModifier::foliageColor)
            ).apply(instance, BiomeColorModifier::new)
    );

    public BiomeColorModifier(HolderSet<Biome> biomes, int fogColor, int waterColor, int waterFogColor, int skyColor, int grassColor, int foliageColor) {
        this(biomes, Optional.of(fogColor), Optional.of(waterColor), Optional.of(waterFogColor), Optional.of(skyColor), Optional.of(grassColor), Optional.of(foliageColor));
    }

    public BiomeColorModifier(HolderSet<Biome> biomes, int fogColor, int waterColor, int waterFogColor, int skyColor, int foliageColor) {
        this(biomes, Optional.of(fogColor), Optional.of(waterColor), Optional.of(waterFogColor), Optional.of(skyColor), Optional.empty(), Optional.of(foliageColor));
    }

    public BiomeColorModifier(HolderSet<Biome> biomes, int fogColor, int waterColor, int waterFogColor, int skyColor) {
        this(biomes, Optional.of(fogColor), Optional.of(waterColor), Optional.of(waterFogColor), Optional.of(skyColor), Optional.empty(), Optional.empty());
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.BEFORE_EVERYTHING && biomes.contains(biome)) {
            BiomeSpecialEffectsBuilder effectsBuilder = builder.getSpecialEffects();
            this.fogColor.ifPresent(effectsBuilder::fogColor);
            this.waterColor.ifPresent(effectsBuilder::waterColor);
            this.waterFogColor.ifPresent(effectsBuilder::waterFogColor);
            this.skyColor.ifPresent(effectsBuilder::skyColor);
            if (effectsBuilder.getGrassColorModifier() == BiomeSpecialEffects.GrassColorModifier.NONE) {
                this.grassColor.ifPresent(effectsBuilder::grassColorOverride);
            }
            this.foliageColor.ifPresent(effectsBuilder::foliageColorOverride);
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
