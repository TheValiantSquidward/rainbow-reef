package net.thevaliantsquidward.rainbowreef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.thevaliantsquidward.rainbowreef.blocks.TallCoralBlock;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefBlockTags;

import java.util.Optional;

public class TallCoralFeature extends Feature<NoneFeatureConfiguration> {

    public TallCoralFeature(Codec<NoneFeatureConfiguration> configuration) {
        super(configuration);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        Optional<Block> starfish = BuiltInRegistries.BLOCK.getTag(ReefBlockTags.TALL_CORALS).flatMap((holders) -> holders.getRandomElement(level.getRandom())).map(Holder::value);

        int i = 0;

        BlockState block = starfish.map(Block::defaultBlockState).orElseGet(ReefBlocks.TALL_HORN_CORAL.get()::defaultBlockState).setValue(TallCoralBlock.WATERLOGGED, true);

        for (int j = 0; j < 16; j++) {
            BlockPos blockPos = pos.offset(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (level.getFluidState(blockPos).is(FluidTags.WATER) && blockPos.getY() < 255 && block.canSurvive(level, blockPos)) {
                BlockState upperState = block.setValue(TallCoralBlock.HALF, DoubleBlockHalf.UPPER);
                BlockPos abovePos = blockPos.above();
                if (level.getBlockState(abovePos).is(Blocks.WATER)) {
                    level.setBlock(blockPos, block, 2);
                    level.setBlock(abovePos, upperState, 2);
                }
                i++;
            }
        }
        return i > 0;
    }
}