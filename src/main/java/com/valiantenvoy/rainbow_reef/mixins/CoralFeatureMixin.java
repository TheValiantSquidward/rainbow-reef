package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.blocks.TallCoralBlock;
import com.valiantenvoy.rainbow_reef.tags.ReefBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.CoralFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralFeature.class)
public class CoralFeatureMixin {

    // can probably make this less invasive later if needed
    @Inject(method = "placeCoralBlock", at = @At("HEAD"), cancellable = true)
    private void rainbowReef$placeTallCorals(LevelAccessor level, RandomSource random, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.cancel();
        BlockPos blockPos = pos.above();
        BlockState blockstate = level.getBlockState(pos);
        if ((blockstate.is(Blocks.WATER) || blockstate.is(BlockTags.CORALS)) && level.getBlockState(blockPos).is(Blocks.WATER)) {
            level.setBlock(pos, state, 3);
            if (random.nextFloat() < 0.25F) {
                BuiltInRegistries.BLOCK.getRandomElementOf(BlockTags.CORALS, random).map(Holder::value).ifPresent(block -> level.setBlock(blockPos, block.defaultBlockState(), 2));
            }
            else if (random.nextFloat() < 0.15F && level.getBlockState(blockPos.above()).is(Blocks.WATER)) {
                BuiltInRegistries.BLOCK.getRandomElementOf(ReefBlockTags.TALL_CORALS, random).map(Holder::value).ifPresent(block -> {
                    BlockState blockState = block.defaultBlockState();
                    if (blockState.hasProperty(TallCoralBlock.HALF)) {
                        level.setBlock(blockPos, blockState.setValue(TallCoralBlock.HALF, DoubleBlockHalf.LOWER), 2);
                        level.setBlock(blockPos.above(), blockState.setValue(TallCoralBlock.HALF, DoubleBlockHalf.UPPER), 2);
                    }
                });
            }
            else if (random.nextFloat() < 0.1F) {
                BuiltInRegistries.BLOCK.getRandomElementOf(ReefBlockTags.SEA_ANEMONES, random).map(Holder::value).ifPresent(block -> level.setBlock(blockPos, block.defaultBlockState(), 2));
            }
            else if (random.nextFloat() < 0.05F) {
                level.setBlock(blockPos, Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, random.nextInt(4) + 1), 2);
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (random.nextFloat() < 0.2F) {
                    BlockPos relativePos = pos.relative(direction);
                    if (level.getBlockState(relativePos).is(Blocks.WATER)) {
                        BuiltInRegistries.BLOCK.getRandomElementOf(BlockTags.WALL_CORALS, random).map(Holder::value).ifPresent(block -> {
                            BlockState blockstate1 = block.defaultBlockState();
                            if (blockstate1.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                blockstate1 = blockstate1.setValue(BaseCoralWallFanBlock.FACING, direction);
                            }
                            level.setBlock(relativePos, blockstate1, 2);
                        });
                    }
                }
            }
            cir.setReturnValue(true);
        }
        else {
            cir.setReturnValue(false);
        }
    }
}