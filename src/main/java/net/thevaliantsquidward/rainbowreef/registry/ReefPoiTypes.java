package net.thevaliantsquidward.rainbowreef.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

import java.util.Set;

public class ReefPoiTypes {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<PoiType, PoiType> GREEN_NEM = POI_TYPES.register("green_nem", () ->new PoiType(getBlockStates(ReefBlocks.GREEN_SEA_ANEMONE.get()), 32, 6));
    public static final DeferredHolder<PoiType, PoiType> ORANGE_NEM = POI_TYPES.register("orange_nem", () ->new PoiType(getBlockStates(ReefBlocks.ORANGE_SEA_ANEMONE.get()), 32, 6));
    public static final DeferredHolder<PoiType, PoiType> YELLOW_NEM = POI_TYPES.register("yellow_nem", () ->new PoiType(getBlockStates(ReefBlocks.YELLOW_SEA_ANEMONE.get()), 32, 6));

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}