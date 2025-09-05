package net.thevaliantsquidward.rainbowreef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.thevaliantsquidward.rainbowreef.blocks.AnemoneBlock;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;

import java.util.Optional;

public class AnemoneFeature extends Feature<NoneFeatureConfiguration> {

    public AnemoneFeature(Codec<NoneFeatureConfiguration> configuration) {
        super(configuration);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        Optional<Block> anemone = BuiltInRegistries.BLOCK.getTag(ReefTags.TROPICAL_NEMS).flatMap((holders) -> holders.getRandomElement(level.getRandom())).map(Holder::value);

        int i = 0;

        BlockState block = anemone.map(Block::defaultBlockState).orElseGet(ReefBlocks.ORANGE_SEA_ANEMONE.get()::defaultBlockState).setValue(AnemoneBlock.FACING, Direction.UP).setValue(AnemoneBlock.WATERLOGGED, true);
        for (int j = 0; j < 5; j++) {
            BlockPos blockpos = pos.offset(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            if (level.getFluidState(blockpos).is(FluidTags.WATER) && blockpos.getY() < 255 && block.canSurvive(level, blockpos)) {
                Direction direction = Direction.getRandom(random);
                while (!block.setValue(AnemoneBlock.FACING, direction).canSurvive(level, blockpos)) {
                    direction = Direction.getRandom(random);
                }
                level.setBlock(blockpos, block.setValue(AnemoneBlock.FACING, direction).setValue(AnemoneBlock.WATERLOGGED, true), 2);
                i++;
            }
        }
        return i > 0;
    }
}
