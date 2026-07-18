package com.valiantenvoy.rainbow_reef.registry;

import com.mojang.serialization.MapCodec;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.utils.BiomeColorModifier;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ReefBiomeModifiers {

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, RainbowReef.MOD_ID);

    public static final Supplier<MapCodec<BiomeColorModifier>> MODIFY_COLORS = BIOME_MODIFIERS.register("modify_colors", () -> BiomeColorModifier.CODEC);
}
