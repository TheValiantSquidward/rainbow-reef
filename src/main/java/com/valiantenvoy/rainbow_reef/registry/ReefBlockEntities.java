package com.valiantenvoy.rainbow_reef.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.blocks.BurrowBlockEntity;

public class ReefBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BurrowBlockEntity>> BURROW = BLOCK_ENTITIES.register("burrow",
            () -> BlockEntityType.Builder.of(BurrowBlockEntity::new,
                    ReefBlocks.MUD_BURROW.get(),
                    ReefBlocks.SAND_BURROW.get(),
                    ReefBlocks.STONE_BURROW.get(),
                    ReefBlocks.CORALSTONE_BURROW.get()).build(null));
}
