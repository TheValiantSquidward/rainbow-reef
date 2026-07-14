package net.thevaliantsquidward.rainbowreef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlockEntities;
import org.jetbrains.annotations.Nullable;

public class BurrowBlock extends Block implements EntityBlock {

    private final boolean ground;
    private final TagKey<Block> substrate;

    public BurrowBlock(boolean ground, TagKey<Block> substrate, Properties properties) {
        super(properties);
        this.ground = ground;
        this.substrate = substrate;
    }

    public boolean isGround() {
        return this.ground;
    }

    public TagKey<Block> substrate() {
        return this.substrate;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BurrowBlockEntity(pos, state);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide && type == ReefBlockEntities.BURROW.get()) {
            return (BlockEntityTicker<T>) (BlockEntityTicker<BurrowBlockEntity>) BurrowBlockEntity::serverTick;
        }
        return null;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock()) && level.getBlockEntity(pos) instanceof BurrowBlockEntity burrow) {
            burrow.evacuate();
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
