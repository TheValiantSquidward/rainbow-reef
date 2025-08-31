package net.thevaliantsquidward.rainbowreef.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

import java.util.Set;

public class ReefPoiTypes {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, RainbowReef.MOD_ID);

    public static final RegistryObject<PoiType> GREEN_NEM = POI_TYPES.register("green_nem", () ->new PoiType(getBlockStates(ReefBlocks.GREEN_SEA_ANEMONE.get()), 32, 6));
    public static final RegistryObject<PoiType> ORANGE_NEM = POI_TYPES.register("orange_nem", () ->new PoiType(getBlockStates(ReefBlocks.ORANGE_SEA_ANEMONE.get()), 32, 6));
    public static final RegistryObject<PoiType> YELLOW_NEM = POI_TYPES.register("yellow_nem", () ->new PoiType(getBlockStates(ReefBlocks.YELLOW_SEA_ANEMONE.get()), 32, 6));

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}