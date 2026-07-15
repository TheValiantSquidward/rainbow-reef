package com.valiantenvoy.rainbow_reef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class FloorStarFeature extends Feature<MultifaceGrowthConfiguration> {
    public FloorStarFeature(Codec<MultifaceGrowthConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<MultifaceGrowthConfiguration> context) {
        WorldGenLevel $$1 = context.level();
        BlockPos $$2 = context.origin();
        RandomSource $$3 = context.random();
        MultifaceGrowthConfiguration $$4 = context.config();
        if (isAirOrWater($$1.getBlockState($$2))) {
            return false;
        } else {
            List<Direction> $$5 = $$4.getShuffledDirections($$3);
            if (placeGrowthIfPossible($$1, $$2, $$1.getBlockState($$2), $$4, $$3, $$5)) {
                return true;
            } else {
                BlockPos.MutableBlockPos $$6 = $$2.mutable();

                for (Direction $$7 : $$5) {
                    $$6.set($$2);
                    List<Direction> $$8 = $$4.getShuffledDirectionsExcept($$3, $$7.getOpposite());

                    for (int $$9 = 0; $$9 < $$4.searchRange; ++$$9) {
                        $$6.setWithOffset($$2, $$7);
                        BlockState $$10 = $$1.getBlockState($$6);
                        if (isAirOrWater($$10) && !$$10.is($$4.placeBlock)) {
                            break;
                        }

                        if (placeGrowthIfPossible($$1, $$6, $$10, $$4, $$3, $$8)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    public static boolean placeGrowthIfPossible(WorldGenLevel level, BlockPos pos, BlockState state, MultifaceGrowthConfiguration config, RandomSource random, List<Direction> directions) {
        BlockPos.MutableBlockPos $$6 = pos.mutable();
        Iterator<Direction> var7 = directions.iterator();
        Optional<Block> star = BuiltInRegistries.BLOCK.getTag(ReefTags.TEMPERATE_STARS).flatMap((holders) -> holders.getRandomElement(level.getRandom())).map(Holder::value);
        //BlockState block = star.map(Block::defaultBlockState).orElseGet(ModBlocks.SAFFRON_STARFISH.get()::defaultBlockState);
        MultifaceBlock placeBlock = (MultifaceBlock) BuiltInRegistries.BLOCK.getTag(ReefTags.TEMPERATE_STARS).flatMap((holders) -> holders.getRandomElement(level.getRandom())).map(Holder::value).get();

        Direction $$7;
        BlockState $$8;
        do {
            if (!var7.hasNext()) {
                return false;
            }

            $$7 = (Direction)var7.next();
            $$8 = level.getBlockState($$6.setWithOffset(pos, $$7));
        } while(!$$8.is(config.canBePlacedOn));

        BlockState $$9 = placeBlock.getStateForPlacement(state, level, pos, $$7);
        if ($$9 == null) {
            return false;
        } else {
            level.setBlock(pos, $$9, 3);
            return true;
        }
    }

    private static boolean isAirOrWater(BlockState state) {
        return !state.isAir() && !state.is(Blocks.WATER);
    }
}
