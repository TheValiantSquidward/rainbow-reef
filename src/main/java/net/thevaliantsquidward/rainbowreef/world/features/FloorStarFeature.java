package net.thevaliantsquidward.rainbowreef.world.features;

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
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.thevaliantsquidward.rainbowreef.registry.tags.RRTags;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class FloorStarFeature extends Feature<MultifaceGrowthConfiguration> {
    public FloorStarFeature(Codec<MultifaceGrowthConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<MultifaceGrowthConfiguration> pContext) {
        WorldGenLevel $$1 = pContext.level();
        BlockPos $$2 = pContext.origin();
        RandomSource $$3 = pContext.random();
        MultifaceGrowthConfiguration $$4 = pContext.config();
        if (!isAirOrWater($$1.getBlockState($$2))) {
            return false;
        } else {
            List<Direction> $$5 = $$4.getShuffledDirections($$3);
            if (placeGrowthIfPossible($$1, $$2, $$1.getBlockState($$2), $$4, $$3, $$5)) {
                return true;
            } else {
                BlockPos.MutableBlockPos $$6 = $$2.mutable();
                Iterator var8 = $$5.iterator();

                while(var8.hasNext()) {
                    Direction $$7 = (Direction)var8.next();
                    $$6.set($$2);
                    List<Direction> $$8 = $$4.getShuffledDirectionsExcept($$3, $$7.getOpposite());

                    for(int $$9 = 0; $$9 < $$4.searchRange; ++$$9) {
                        $$6.setWithOffset($$2, $$7);
                        BlockState $$10 = $$1.getBlockState($$6);
                        if (!isAirOrWater($$10) && !$$10.is($$4.placeBlock)) {
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

    public static boolean placeGrowthIfPossible(WorldGenLevel pLevel, BlockPos pPos, BlockState pState, MultifaceGrowthConfiguration pConfig, RandomSource pRandom, List<Direction> pDirections) {
        BlockPos.MutableBlockPos $$6 = pPos.mutable();
        Iterator var7 = pDirections.iterator();
        Optional<Block> star = BuiltInRegistries.BLOCK.getTag(RRTags.TEMPERATE_STARS).flatMap((holders) -> holders.getRandomElement(pLevel.getRandom())).map(Holder::value);
        //BlockState block = star.map(Block::defaultBlockState).orElseGet(ModBlocks.SAFFRON_STARFISH.get()::defaultBlockState);
        MultifaceBlock placeBlock = (MultifaceBlock) BuiltInRegistries.BLOCK.getTag(RRTags.TEMPERATE_STARS).flatMap((holders) -> holders.getRandomElement(pLevel.getRandom())).map(Holder::value).get();

        Direction $$7;
        BlockState $$8;
        do {
            if (!var7.hasNext()) {
                return false;
            }

            $$7 = (Direction)var7.next();
            $$8 = pLevel.getBlockState($$6.setWithOffset(pPos, $$7));
        } while(!$$8.is(pConfig.canBePlacedOn));

        BlockState $$9 = placeBlock.getStateForPlacement(pState, pLevel, pPos, $$7);
        if ($$9 == null) {
            return false;
        } else {
            pLevel.setBlock(pPos, $$9, 3);
            return true;
        }
    }

    private static boolean isAirOrWater(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER);
    }
}
