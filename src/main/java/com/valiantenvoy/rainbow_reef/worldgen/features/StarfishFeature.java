package com.valiantenvoy.rainbow_reef.worldgen.features;

import com.mojang.serialization.Codec;
import com.valiantenvoy.rainbow_reef.blocks.StarfishBlock;
import com.valiantenvoy.rainbow_reef.tags.ReefBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;

public class StarfishFeature extends Feature<NoneFeatureConfiguration> {

    public StarfishFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        LevelAccessor level = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();
        if (!level.getFluidState(pos).is(Fluids.WATER)) {
            return false;
        }
        else {
            List<Direction> validDirections = new ArrayList<>();
            for (Direction direction : Direction.values()) {
                BlockPos attachedPos = pos.relative(direction.getOpposite());
                if (level.getBlockState(attachedPos).isFaceSturdy(level, pos, direction)) {
                    validDirections.add(direction);
                }
            }
            if (validDirections.isEmpty()) {
                return false;
            }
            else {
                Direction direction = validDirections.get(random.nextInt(validDirections.size()));
                boolean waterlogged = level.getFluidState(pos).getType() == Fluids.WATER;
                BuiltInRegistries.BLOCK.getRandomElementOf(ReefBlockTags.STARFISHES, random).map(Holder::value).ifPresent((block) -> {
                    BlockState state = block.defaultBlockState()
                            .setValue(StarfishBlock.FACING, direction)
                            .setValue(StarfishBlock.WATERLOGGED, waterlogged)
                            .setValue(StarfishBlock.STARFISH_AMOUNT, random.nextInt(1, 3));
                    level.setBlock(pos, state, 3);
                });
                return true;
            }
        }
    }
}