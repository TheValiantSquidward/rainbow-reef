package com.valiantenvoy.rainbow_reef.registry;

import com.google.common.collect.ImmutableSet;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class ReefPoiTypes {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> GREEN_NEM = POI_TYPES.register("green_nem", () ->new PoiType(getBlockStates(ReefBlocks.GREEN_SEA_ANEMONE.get()), 32, 6));
    public static final DeferredHolder<PoiType, PoiType> ORANGE_NEM = POI_TYPES.register("orange_nem", () ->new PoiType(getBlockStates(ReefBlocks.ORANGE_SEA_ANEMONE.get()), 32, 6));
    public static final DeferredHolder<PoiType, PoiType> YELLOW_NEM = POI_TYPES.register("yellow_nem", () ->new PoiType(getBlockStates(ReefBlocks.YELLOW_SEA_ANEMONE.get()), 32, 6));

    public static final DeferredHolder<PoiType, PoiType> BURROW = POI_TYPES.register("burrow", () -> new PoiType(getBlockStates(
            ReefBlocks.MUD_BURROW.get(), ReefBlocks.SAND_BURROW.get(), ReefBlocks.STONE_BURROW.get(), ReefBlocks.CORALSTONE_BURROW.get()), 32, 6));

    private static Set<BlockState> getBlockStates(Block... blocks) {
        ImmutableSet.Builder<BlockState> states = ImmutableSet.builder();
        for (Block block : blocks) {
            states.addAll(block.getStateDefinition().getPossibleStates());
        }
        return states.build();
    }
}