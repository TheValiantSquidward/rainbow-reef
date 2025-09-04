package net.thevaliantsquidward.rainbowreef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;

public class CoralstoneRockFeature extends Feature<BlockStateConfiguration> {

    public CoralstoneRockFeature(Codec<BlockStateConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        BlockPos blockpos = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();

        BlockStateConfiguration configuration;
        for (configuration = context.config(); blockpos.getY() > level.getMinBuildHeight() + 3; blockpos = blockpos.below()) {
            if (!level.isEmptyBlock(blockpos.below())) {
                BlockState blockstate = level.getBlockState(blockpos.below());
                if (isDirt(blockstate) || isStone(blockstate)) {
                    break;
                }
            }
        }

        if (blockpos.getY() <= level.getMinBuildHeight() + 3) {
            return false;
        } else {
            for (int i = 0; i < 3; ++i) {
                int x = random.nextInt(2, 3);
                int y = random.nextInt(2, 4);
                int z = random.nextInt(2, 3);
                float f = (float) (x + y + z) * 0.333F + 0.5F;

                for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset(-x, -y, -z), blockpos.offset(x, y, z))) {
                    if (blockpos1.distSqr(blockpos) <= (double) (f * f)) {
                        level.setBlock(blockpos1, configuration.state, 3);
                    }
                }

                blockpos = blockpos.offset(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
            }
            return true;
        }
    }
}