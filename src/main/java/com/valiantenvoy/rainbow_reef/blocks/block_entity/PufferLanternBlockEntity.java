package com.valiantenvoy.rainbow_reef.blocks.block_entity;

import com.valiantenvoy.rainbow_reef.registry.ReefBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PufferLanternBlockEntity extends BlockEntity {

    public PufferLanternBlockEntity(BlockPos pos, BlockState blockState) {
        super(ReefBlockEntities.PUFFER_LANTERN_BLOCK_ENTITY.get(), pos, blockState);
    }
}
