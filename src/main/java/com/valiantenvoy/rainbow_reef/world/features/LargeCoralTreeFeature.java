package com.valiantenvoy.rainbow_reef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.CoralFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class LargeCoralTreeFeature extends CoralFeature {

    public LargeCoralTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected boolean placeFeature(LevelAccessor level, RandomSource random, BlockPos blockPos, BlockState state) {
        BlockPos.MutableBlockPos mutable = blockPos.mutable();
        int i = random.nextInt(3) + 4;

        for (int j = 0; j < i; j++) {
            this.placeCoralBlock(level, random, mutable, state);
            this.placeCoralBlock(level, random, mutable.east(), state);
            this.placeCoralBlock(level, random, mutable.south(), state);
            this.placeCoralBlock(level, random, mutable.east().south(), state);
            mutable.move(Direction.UP);
        }

        BlockPos pos = mutable.immutable();
        int k = random.nextInt(3) + 1;
        List<Direction> list = Direction.Plane.HORIZONTAL.shuffledCopy(random);

        for (Direction direction : list.subList(0, k)) {
            mutable.set(pos);
            mutable.move(direction);

            int l = random.nextInt(5) + 6;
            int i1 = 0;
            for (int j1 = 0; j1 < l; j1++) {
                if (j1 < random.nextInt(3) + 2) {
                    this.placeCoralBlock(level, random, mutable, state);
                    this.placeCoralBlock(level, random, mutable.east(), state);
                    this.placeCoralBlock(level, random, mutable.south(), state);
                    this.placeCoralBlock(level, random, mutable.east().south(), state);
                } else if (j1 < random.nextInt(3) + 2 + random.nextInt(3) + 1) {
                    this.placeCoralBlock(level, random, mutable, state);
                    if (random.nextBoolean()) {
                        this.placeCoralBlock(level, random, mutable.east(), state);
                    } else {
                        this.placeCoralBlock(level, random, mutable.south(), state);
                    }
                } else {
                    if (!this.placeCoralBlock(level, random, mutable, state)) {
                        break;
                    }
                }
                i1++;
                mutable.move(Direction.UP);
                if (j1 == 0 || i1 >= 2 && random.nextFloat() < 0.25F) {
                    mutable.move(direction);
                    i1 = 0;
                }
            }
        }
        return true;
    }
}
