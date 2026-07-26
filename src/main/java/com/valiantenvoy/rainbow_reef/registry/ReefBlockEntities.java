package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.blocks.BurrowBlockEntity;
import com.valiantenvoy.rainbow_reef.blocks.block_entity.PufferLanternBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ReefBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BurrowBlockEntity>> BURROW_BLOCK_ENTITY = BLOCK_ENTITIES.register("burrow_block_entity",
            () -> BlockEntityType.Builder.of(BurrowBlockEntity::new,
                    ReefBlocks.MUD_BURROW.get(),
                    ReefBlocks.SAND_BURROW.get(),
                    ReefBlocks.STONE_BURROW.get(),
                    ReefBlocks.CORALSTONE_BURROW.get()).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PufferLanternBlockEntity>> PUFFER_LANTERN_BLOCK_ENTITY = BLOCK_ENTITIES.register("puffer_lantern_block_entity",
            () -> BlockEntityType.Builder.of(PufferLanternBlockEntity::new,
                    ReefBlocks.BLUE_PUFFER_LANTERN.get(),
                    ReefBlocks.GREEN_PUFFER_LANTERN.get(),
                    ReefBlocks.ORANGE_PUFFER_LANTERN.get()).build(null));

}
