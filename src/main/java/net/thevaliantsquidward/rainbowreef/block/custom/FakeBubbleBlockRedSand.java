package net.thevaliantsquidward.rainbowreef.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
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
import net.thevaliantsquidward.rainbowreef.block.ModBlocks;

import java.util.Optional;

public class FakeBubbleBlockRedSand extends Block implements BucketPickup {

    private static final int CHECK_PERIOD = 5;

    public FakeBubbleBlockRedSand(BlockBehaviour.Properties pProperties) {
        super(pProperties); }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        BlockState blockstate = pLevel.getBlockState(pPos.above());
        if (blockstate.isAir()) {
            if (!pLevel.isClientSide) {
                ServerLevel serverlevel = (ServerLevel)pLevel;

                for(int i = 0; i < 2; ++i) {
                    serverlevel.sendParticles(ParticleTypes.SPLASH, (double)pPos.getX() + pLevel.random.nextDouble(), (double)(pPos.getY() + 1), (double)pPos.getZ() + pLevel.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                    serverlevel.sendParticles(ParticleTypes.BUBBLE, (double)pPos.getX() + pLevel.random.nextDouble(), (double)(pPos.getY() + 1), (double)pPos.getZ() + pLevel.random.nextDouble(), 1, 0.0D, 0.01D, 0.0D, 0.2D);
                }
            }
        }
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        updateColumn(pLevel, pPos, pState, pLevel.getBlockState(pPos.below()));
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.WATER.getSource(false);
    }

    public static void updateColumn(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        updateColumn(pLevel, pPos, pLevel.getBlockState(pPos), pState);
    }

    public static void updateColumn(LevelAccessor pLevel, BlockPos pPos, BlockState pFluid, BlockState pState) {
        if (canExistIn(pFluid)) {
            BlockState blockstate = getColumnState(pState);
            pLevel.setBlock(pPos, blockstate, 2);
            BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable().move(Direction.UP);

            while(canExistIn(pLevel.getBlockState(blockpos$mutableblockpos))) {
                if (!pLevel.setBlock(blockpos$mutableblockpos, blockstate, 2)) {
                    return;
                }

                blockpos$mutableblockpos.move(Direction.UP);
            }

        }
    }

    private static boolean canExistIn(BlockState pBlockState) {
        return pBlockState.is(ModBlocks.FAKE_BUBBLES.get()) || pBlockState.is(Blocks.WATER) && pBlockState.getFluidState().getAmount() >= 8 && pBlockState.getFluidState().isSource();
    }

    private static BlockState getColumnState(BlockState pBlockState) {
        if (pBlockState.is(ModBlocks.FAKE_BUBBLES.get())) {
            return pBlockState;
        } else {
            return pBlockState.is(ModBlocks.RED_SAND_BUBBLER.get()) ? ModBlocks.FAKE_BUBBLES.get().defaultBlockState() : Blocks.WATER.defaultBlockState();
        }
    }


    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double d0 = (double)pPos.getX();
        double d1 = (double)pPos.getY();
        double d2 = (double)pPos.getZ();

        pLevel.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
        pLevel.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + (double)pRandom.nextFloat(), d1 + (double)pRandom.nextFloat(), d2 + (double)pRandom.nextFloat(), 0.0D, 0.04D, 0.0D);
        if (pRandom.nextInt(200) == 0) {
            pLevel.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_WHIRLPOOL_AMBIENT, SoundSource.BLOCKS, 0.2F + pRandom.nextFloat() * 0.2F, 0.9F + pRandom.nextFloat() * 0.15F, false);
        }
    }


    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        if (!pState.canSurvive(pLevel, pCurrentPos) || pFacing == Direction.DOWN || pFacing == Direction.UP && !pFacingState.is(ModBlocks.FAKE_BUBBLES.get()) && canExistIn(pFacingState)) {
            pLevel.scheduleTick(pCurrentPos, this, 5);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos.below());
        return blockstate.is(ModBlocks.FAKE_BUBBLES.get()) || blockstate.is(ModBlocks.RED_SAND_BUBBLER.get());
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }


    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add();
    }

    public ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
        return new ItemStack(Items.WATER_BUCKET);
    }

    public Optional<SoundEvent> getPickupSound() {
        return Fluids.WATER.getPickupSound();
    }
}