package com.valiantenvoy.rainbow_reef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import com.valiantenvoy.rainbow_reef.blocks.BurrowBlock;

public class BurrowFeature extends Feature<BlockStateConfiguration> {

    public BurrowFeature(Codec<BlockStateConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BlockStateConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        BlockState burrow = context.config().state;

        if (!(burrow.getBlock() instanceof BurrowBlock block) || !world.getFluidState(origin).is(FluidTags.WATER)) {
            return false;
        }

        if (block.isGround()) {
            BlockPos floorPos = origin.below();
            if (world.getBlockState(floorPos).is(block.substrate())) {
                world.setBlock(floorPos, burrow, 2);
                return true;
            }
            return false;
        }

        RandomSource random = context.random();
        for (int i = 0; i < 48; i++) {
            BlockPos candidate = origin.offset(random.nextInt(17) - 8, random.nextInt(15) - 12, random.nextInt(17) - 8);
            if (!world.getBlockState(candidate).is(block.substrate())) {
                continue;
            }
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (world.getFluidState(candidate.relative(direction)).is(FluidTags.WATER)) {
                    world.setBlock(candidate, burrow, 2);
                    return true;
                }
            }
        }
        return false;
    }
}
