package com.valiantenvoy.rainbow_reef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BubblerBlock extends Block {
    private static final int BUBBLE_COLUMN_CHECK_DELAY = 20;
    public BubblerBlock(Properties properties) {
        super(properties);
    }

    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, BlockPos pos, @NotNull RandomSource random) {
        FakeBubbleBlock.updateColumn(level, pos.above(), state);
    }
    public @NotNull BlockState updateShape(@NotNull BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        if (facing == Direction.UP && facingState.is(Blocks.WATER)) {
            level.scheduleTick(currentPos, this, 20);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public void onPlace(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean isMoving) {
        level.scheduleTick(pos, this, 20);
    }

}