package net.thevaliantsquidward.rainbowreef.util;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.block.ModBlocks;

import java.util.Set;

public class RRPOI {
    public static final DeferredRegister<PoiType> DEF_REG = DeferredRegister.create(ForgeRegistries.POI_TYPES, RainbowReef.MOD_ID);
    public static final RegistryObject<PoiType> GREEN_NEM = DEF_REG.register("green_nem", () ->new PoiType(getBlockStates(ModBlocks.GREEN_SEA_ANEMONE.get()), 32, 6));
    public static final RegistryObject<PoiType> ORANGE_NEM = DEF_REG.register("orange_nem", () ->new PoiType(getBlockStates(ModBlocks.ORANGE_SEA_ANEMONE.get()), 32, 6));
    public static final RegistryObject<PoiType> YELLOW_NEM = DEF_REG.register("yellow_nem", () ->new PoiType(getBlockStates(ModBlocks.YELLOW_SEA_ANEMONE.get()), 32, 6));

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }


}