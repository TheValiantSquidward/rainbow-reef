package com.valiantenvoy.rainbow_reef.blocks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AnemoneBlock extends DirectionalBlock implements LiquidBlockContainer {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty COLOUR = IntegerProperty.create("colour", 0, 2);
    //0 = yellow, 1 = orange, 2 = green
    protected static final VoxelShape EAST_AABB;
    protected static final VoxelShape WEST_AABB;
    protected static final VoxelShape SOUTH_AABB;
    protected static final VoxelShape NORTH_AABB;
    protected static final VoxelShape UP_AABB;
    protected static final VoxelShape DOWN_AABB;

    public static final MapCodec<AnemoneBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("colour").forGetter(block -> block.color)
    ).apply(instance, AnemoneBlock::new));

    private final int color;

    @Override
    protected @NotNull MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    public AnemoneBlock(int color) {
        super(Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.FROGLIGHT).randomTicks().noOcclusion());
        this.color = color;
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, true).setValue(COLOUR, color).setValue(FACING, Direction.UP));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(WATERLOGGED, COLOUR, FACING);
    }

    @Override
    public boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        if(state.getValue(FACING) == Direction.NORTH) {
            BlockPos northPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
            return level.getBlockState(northPos).isFaceSturdy(level, pos, Direction.SOUTH);
        } else if (state.getValue(FACING) == Direction.SOUTH) {
            BlockPos southPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
            return level.getBlockState(southPos).isFaceSturdy(level, pos, Direction.NORTH);
        } else if (state.getValue(FACING) == Direction.EAST) {
            BlockPos eastPos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
            return level.getBlockState(eastPos).isFaceSturdy(level, pos, Direction.WEST);
        } else if (state.getValue(FACING) == Direction.WEST) {
            BlockPos westPos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
            return level.getBlockState(westPos).isFaceSturdy(level, pos, Direction.EAST);

        } else if (state.getValue(FACING) == Direction.UP) {
            BlockPos upPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
            return level.getBlockState(upPos).isFaceSturdy(level, pos, Direction.DOWN);
        } else {
            BlockPos downPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            return level.getBlockState(downPos).isFaceSturdy(level, pos, Direction.UP);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext world) {
        Direction direction = world.getClickedFace();
        BlockState oppositeDirection = world.getLevel().getBlockState(world.getClickedPos().relative(direction.getOpposite()));

        FluidState state = world.getLevel().getFluidState(world.getClickedPos());
        return (oppositeDirection.is(this) && oppositeDirection.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction)).setValue(WATERLOGGED, state.is(FluidTags.WATER) && state.getAmount() == 8);
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> DOWN_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            default -> UP_AABB;
        };
    }

    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public @NotNull BlockState mirror(BlockState state, Mirror flip) {
        return state.setValue(FACING, flip.mirror(state.getValue(FACING)));
    }

    protected boolean isPathfindable(@NotNull BlockState state, PathComputationType computationType) {
        return switch (computationType) {
            case LAND -> true;
            case WATER -> state.getFluidState().is(FluidTags.WATER);
            default -> false;
        };
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (hand != InteractionHand.MAIN_HAND) return ItemInteractionResult.FAIL;
        if (stack.is(ReefTags.NEM_DIET) && !stack.is(Items.TROPICAL_FISH) && !stack.is(ReefItems.RAW_CLOWNFISH.get()) && !level.isClientSide() && state.getValue(WATERLOGGED)) {
            ItemStack drop;
            if (state.getValue(COLOUR) == 0) {
                drop = new ItemStack(ReefBlocks.YELLOW_SEA_ANEMONE.get());
            } else if (state.getValue(COLOUR) == 1) {
                drop = new ItemStack(ReefBlocks.ORANGE_SEA_ANEMONE.get());
            } else {
                drop = new ItemStack(ReefBlocks.GREEN_SEA_ANEMONE.get());
            }
            level.addFreshEntity(new ItemEntity(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, drop));
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    public boolean canPlaceLiquid(@Nullable Player player, @NotNull BlockGetter blockGetter, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(@NotNull LevelAccessor levelAccessor, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull FluidState fluidState) {
        return false;
    }


    static {
        EAST_AABB = Block.box(0.0, 0.0, 0.0, 5.0, 16.0, 16.0);
        WEST_AABB = Block.box(11.0, 0.0, 0.0, 16.0, 16.0, 16.0);
        SOUTH_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 5.0);
        NORTH_AABB = Block.box(0.0, 0.0, 11.0, 16.0, 16.0, 16.0);
        UP_AABB = Block.box(0.0, 0.0, 0.0, 16.0, 5.0, 16.0);
        DOWN_AABB = Block.box(0.0, 11.0, 0.0, 16.0, 16.0, 16.0);
    }
}
