package com.valiantenvoy.rainbow_reef.worldgen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record ReefRockBlockSettings(BlockStateProvider layerProvider, TagKey<Block> cannotReplace, TagKey<Block> invalidBlocks) {

    public static final Codec<ReefRockBlockSettings> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(BlockStateProvider.CODEC.fieldOf("layer_provider").forGetter((settings) -> settings.layerProvider),
                            TagKey.hashedCodec(Registries.BLOCK).fieldOf("cannot_replace").forGetter((settings) -> settings.cannotReplace),
                            TagKey.hashedCodec(Registries.BLOCK).fieldOf("invalid_blocks").forGetter((settings) -> settings.invalidBlocks))
                    .apply(instance, ReefRockBlockSettings::new));

}