package com.valiantenvoy.rainbow_reef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.CoralFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;
import java.util.stream.Stream;

public class LargeCoralClawFeature extends CoralFeature {

    public LargeCoralClawFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected boolean placeFeature(LevelAccessor level, RandomSource random, BlockPos blockPos, BlockState state) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                this.placeCoralBlock(level, random, blockPos.offset(x, 0, z), state);
            }
        }

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int i = random.nextInt(2) + 2;
        List<Direction> list = Util.toShuffledList(Stream.of(direction, direction.getClockWise(), direction.getCounterClockWise()), random);

        for (Direction direction1 : list.subList(0, i)) {
            BlockPos.MutableBlockPos mutable = blockPos.mutable();
            int j = random.nextInt(2) + 2;
            mutable.move(direction1);

            int k;
            Direction direction2;

            if (direction1 == direction) {
                direction2 = direction;
                k = random.nextInt(3) + 5;
            }
            else {
                mutable.move(Direction.UP);
                Direction[] adirection = new Direction[]{direction1, Direction.UP};
                direction2 = Util.getRandom(adirection, random);
                k = random.nextInt(3) + 7;
            }

            for (int l = 0; l < j && this.placeCoralBlock(level, random, mutable, state); l++) {
                mutable.move(direction2);
            }

            mutable.move(direction2.getOpposite());
            mutable.move(Direction.UP);

            for (int i1 = 0; i1 < k; i1++) {
                mutable.move(direction);

                if (i1 < 2) {
                    Direction side = direction.getClockWise();
                    this.placeCoralBlock(level, random, mutable, state);
                    this.placeCoralBlock(level, random, mutable.relative(side), state);
                    this.placeCoralBlock(level, random, mutable.above(), state);
                    this.placeCoralBlock(level, random, mutable.relative(side).above(), state);
                }
                else {
                    if (!this.placeCoralBlock(level, random, mutable, state)) {
                        break;
                    }
                }
                if (random.nextFloat() < 0.25F) {
                    mutable.move(Direction.UP);
                }
            }
        }
        return true;
    }
}
