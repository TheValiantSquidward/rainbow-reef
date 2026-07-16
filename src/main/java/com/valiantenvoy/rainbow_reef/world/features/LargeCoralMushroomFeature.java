package com.valiantenvoy.rainbow_reef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.CoralFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class LargeCoralMushroomFeature extends CoralFeature {

    public LargeCoralMushroomFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected boolean placeFeature(LevelAccessor level, RandomSource random, BlockPos blockPos, BlockState state) {
        int i = random.nextInt(3) + 4;
        int j = random.nextInt(3) + 4;
        int k = random.nextInt(3) + 4;
        int l = random.nextInt(3) + 2;
        BlockPos.MutableBlockPos mutable = blockPos.mutable();

        for (int i1 = -1; i1 <= j + 1; i1++) {
            for (int j1 = -1; j1 <= i + 1; j1++) {
                for (int k1 = -1; k1 <= k + 1; k1++) {
                    mutable.set(i1 + blockPos.getX(), j1 + blockPos.getY(), k1 + blockPos.getZ());
                    mutable.move(Direction.DOWN, l);
                    boolean outerShell = i1 <= 0 || i1 >= j || j1 <= 0 || j1 >= i || k1 <= 0 || k1 >= k;
                    boolean edge = i1 == -1 || i1 == j + 1 || k1 == -1 || k1 == k + 1;
                    if (outerShell && random.nextFloat() >= 0.1F) {
                        this.placeCoralBlock(level, random, mutable, state);
                        if (!edge) {
                            this.placeCoralBlock(level, random, mutable.above(), state);
                        }
                    }
                }
            }
        }
        return true;
    }
}
