package net.thevaliantsquidward.rainbowreef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.IPlantable;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;

import javax.annotation.Nullable;

public class AnemoneBlock extends DirectionalBlock implements LiquidBlockContainer, IPlantable {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty COLOUR = IntegerProperty.create("colour", 0, 2);
    //0 = yellow, 1 = orange, 2 = green
    protected static final VoxelShape EAST_AABB;
    protected static final VoxelShape WEST_AABB;
    protected static final VoxelShape SOUTH_AABB;
    protected static final VoxelShape NORTH_AABB;
    protected static final VoxelShape UP_AABB;
    protected static final VoxelShape DOWN_AABB;

    public AnemoneBlock(int color) {
        super(Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.FROGLIGHT).randomTicks().noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, true).setValue(COLOUR, color).setValue(FACING, Direction.UP));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(WATERLOGGED);
        definition.add(COLOUR);
        definition.add(FACING);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
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
    public BlockState getStateForPlacement(BlockPlaceContext world) {
        Direction direction = world.getClickedFace();
        BlockState oppositeDirection = world.getLevel().getBlockState(world.getClickedPos().relative(direction.getOpposite()));

        FluidState state = world.getLevel().getFluidState(world.getClickedPos());
        return (oppositeDirection.is(this) && oppositeDirection.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction)).setValue(WATERLOGGED, state.is(FluidTags.WATER) && state.getAmount() == 8);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case DOWN -> DOWN_AABB;
            case NORTH -> NORTH_AABB;
            case SOUTH -> SOUTH_AABB;
            case WEST -> WEST_AABB;
            case EAST -> EAST_AABB;
            default -> UP_AABB;
        };
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror flip) {
        return state.setValue(FACING, flip.mirror(state.getValue(FACING)));
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType computationType) {
        return switch (computationType) {
            case LAND -> true;
            case WATER -> level.getFluidState(pos).is(FluidTags.WATER);
            default -> false;
        };
    }

    @Deprecated
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (pHand != InteractionHand.MAIN_HAND) return InteractionResult.FAIL;
        System.out.println(itemStack.is(ItemTags.FISHES));
        if (itemStack.is(ReefTags.NEM_DIET) && !itemStack.is(Items.TROPICAL_FISH) && !itemStack.is(ReefItems.RAW_CLOWNFISH.get()) && !pLevel.isClientSide() && pState.getValue(WATERLOGGED) == true) {
            ItemStack drop;
            if (pState.getValue(COLOUR) == 0) {
                drop = new ItemStack(ReefBlocks.YELLOW_SEA_ANEMONE.get());
            } else if (pState.getValue(COLOUR) == 1) {
                drop = new ItemStack(ReefBlocks.ORANGE_SEA_ANEMONE.get());
            } else {
                drop = new ItemStack(ReefBlocks.GREEN_SEA_ANEMONE.get());
            }
            ItemEntity piss = new ItemEntity(pLevel, pPos.getX() + 0.5, pPos.getY(), pPos.getZ() + 0.5, drop);
            pLevel.addFreshEntity(piss);
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, FluidState fluidState) {
        return false;
    }

    @Override
    public BlockState getPlant(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState state = blockGetter.getBlockState(blockPos);
        return state.getBlock() != this ? this.defaultBlockState() : state;
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
