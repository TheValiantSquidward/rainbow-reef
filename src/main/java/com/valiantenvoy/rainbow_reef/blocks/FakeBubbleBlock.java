package com.valiantenvoy.rainbow_reef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FakeBubbleBlock extends Block implements BucketPickup {

    private static final int CHECK_PERIOD = 5;

    public FakeBubbleBlock(BlockBehaviour.Properties properties) {
        super(properties); }

    public void entityInside(@NotNull BlockState state, Level level, BlockPos pos, @NotNull Entity entity) {
        BlockState blockstate = level.getBlockState(pos.above());
        if (blockstate.isAir()) {
            if (!level.isClientSide) {
                ServerLevel serverlevel = (ServerLevel)level;

                for(int i = 0; i < 2; ++i) {
                    serverlevel.sendParticles(ParticleTypes.SPLASH, (double)pos.getX() + level.random.nextDouble(), (double)(pos.getY() + 1), (double)pos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    serverlevel.sendParticles(ParticleTypes.BUBBLE, (double)pos.getX() + level.random.nextDouble(), (double)(pos.getY() + 1), (double)pos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.01D, 0.0D, 0.2D);
                }
            }
        }
    }

    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        updateColumn(level, pos, state, level.getBlockState(pos.below()));
    }

    public @NotNull FluidState getFluidState(@NotNull BlockState state) {
        return Fluids.WATER.getSource(false);
    }

    public static void updateColumn(LevelAccessor level, BlockPos pos, BlockState state) {
        updateColumn(level, pos, level.getBlockState(pos), state);
    }

    public static void updateColumn(LevelAccessor level, BlockPos pos, BlockState fluid, BlockState state) {
        if (canExistIn(fluid)) {
            BlockState blockstate = getColumnState(state);
            level.setBlock(pos, blockstate, 2);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pos.mutable().move(Direction.UP);

            while(canExistIn(level.getBlockState(blockpos$mutableblockpos))) {
                if (!level.setBlock(blockpos$mutableblockpos, blockstate, 2)) {
                    return;
                }

                blockpos$mutableblockpos.move(Direction.UP);
            }

        }
    }

    private static boolean canExistIn(BlockState blockState) {
        return blockState.is(ReefBlocks.FAKE_BUBBLES.get()) || blockState.is(Blocks.WATER) && blockState.getFluidState().getAmount() >= 8 && blockState.getFluidState().isSource();
    }

    private static BlockState getColumnState(BlockState blockState) {
        if (blockState.is(ReefBlocks.FAKE_BUBBLES.get())) {
            return blockState;
        } else {
            return blockState.is(ReefBlocks.BUBBLER.get()) ? ReefBlocks.FAKE_BUBBLES.get().defaultBlockState() : Blocks.WATER.defaultBlockState();
        }
    }


    public void animateTick(@NotNull BlockState state, Level level, BlockPos pos, RandomSource random) {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();

        level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
        level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + (double)random.nextFloat(), d1 + (double)random.nextFloat(), d2 + (double)random.nextFloat(), 0.0D, 0.04D, 0.0D);
        if (random.nextInt(200) == 0) {
                level.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
    }


    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction facing, @NotNull BlockState facingState, LevelAccessor level, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        if (!state.canSurvive(level, currentPos) || facing == Direction.DOWN || facing == Direction.UP && !facingState.is(ReefBlocks.FAKE_BUBBLES.get()) && canExistIn(facingState)) {
            level.scheduleTick(currentPos, this, 5);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public boolean canSurvive(@NotNull BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos.below());
        return blockstate.is(ReefBlocks.FAKE_BUBBLES.get()) || blockstate.is(ReefBlocks.BUBBLER.get());
    }

    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.empty();
    }


    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add();
    }

    public @NotNull ItemStack pickupBlock(@Nullable Player player, LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockState state) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        return new ItemStack(Items.WATER_BUCKET);
    }

    public @NotNull Optional<SoundEvent> getPickupSound() {
        return Fluids.WATER.getPickupSound();
    }
}