package com.valiantenvoy.rainbow_reef.world.features;

import com.mojang.serialization.Codec;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralWallFanBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

import java.util.Optional;

public class ReefRockFeature extends Feature<NoneFeatureConfiguration> {

    public ReefRockFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        ImprovedNoise noise = new ImprovedNoise(random);
        BlockPos heightmapPos = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR_WG, blockPos);
        blockPos = heightmapPos.offset(0, -4, 0);
        if (level.getBlockState(heightmapPos.below()).is(BlockTags.SAND) && level.getBlockState(blockPos.above(9)).is(Blocks.WATER) && random.nextFloat() > 0.75F) {
            this.createRock(level, blockPos, noise);
            return true;
        }
        else {
            return false;
        }
    }

    public void createRock(WorldGenLevel level, BlockPos origin, ImprovedNoise noise) {
        RandomSource random = level.getRandom();
        createRockSection(level, origin.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1), 3 + random.nextInt(4), 7 + random.nextInt(7), noise);
    }

    private static void createRockSection(WorldGenLevel level, BlockPos origin, int radius, int height, ImprovedNoise noise) {
        Optional<Block> coral = BuiltInRegistries.BLOCK.getTag(BlockTags.CORAL_BLOCKS).flatMap((blockNamed) -> blockNamed.getRandomElement(level.getRandom())).map(Holder::value);
        for (int x = -radius; x < radius; x++) {
            for (int y = -height; y < height; y++) {
                for (int z = -radius; z < radius; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    double scaledRadius = radius;
                    if (y < 0) {
                        scaledRadius = radius * (0.15D + 0.85D * ((y + height) / (double) height));
                    }
                    double distance = distance(x, y, z, scaledRadius, height, scaledRadius);
                    double warp = noise.noise(pos.getX() * 0.15D, pos.getY() * 0.15D, pos.getZ() * 0.15D) * 0.3D;
                    if (distance < 1.0D + warp) {
                        if (y > height * 0.6F && level.getRandom().nextFloat() < 0.12F) {
                            continue;
                        }
                        double coralNoise = noise.noise(pos.getX() * 0.35D, pos.getY() * 0.35D, pos.getZ() * 0.35D);
                        if (coralNoise < -0.15D && coralNoise > -0.165D && y <= height * 0.8F && y >= height * 0.3F) {
                            level.setBlock(pos, coral.map(Block::defaultBlockState).orElseGet(ReefBlocks.CORALSTONE.get()::defaultBlockState), 3);
                            createCoralPlates(level, pos, level.getBlockState(pos));
                        } else if (!level.getBlockState(pos).is(BlockTags.CORAL_BLOCKS)) {
                            level.setBlock(pos, ReefBlocks.CORALSTONE.get().defaultBlockState(), 3);
                        }
                    }
                }
            }
        }
    }

    public static void createCoralPlates(WorldGenLevel level, BlockPos coralPos, BlockState coralType) {
        Optional<Block> coral = BuiltInRegistries.BLOCK.getTag(BlockTags.WALL_CORALS).flatMap((blockNamed) -> blockNamed.getRandomElement(level.getRandom())).map(Holder::value);

        if (level.getRandom().nextFloat() > 0.6F) {
            for (int i = -2; i <= 2; i++) {
                for (int j = -2; j <= 2; j++) {
                    BlockPos pos = coralPos.offset(i, 0, j);
                    double distance = distance(i, 1, j, 3, 1, 3);
                    if (distance < 1.8) {
                        level.setBlock(pos, coralType, 3);
                        for (Direction dir : Direction.Plane.HORIZONTAL) {
                            BlockState block = coral.map(Block::defaultBlockState).orElseGet(Blocks.HORN_CORAL_WALL_FAN::defaultBlockState).setValue(CoralWallFanBlock.WATERLOGGED, true).setValue(CoralWallFanBlock.FACING, dir);
                            BlockPos fanPos0 = coralPos.relative(dir, 3);
                            BlockPos fanPos1 = coralPos.relative(dir, 3).relative(dir.getClockWise());
                            BlockPos fanPos2 = coralPos.relative(dir, 3).relative(dir.getCounterClockWise());
                            if (level.getBlockState(fanPos0).is(Blocks.WATER)) {
                                level.setBlock(fanPos0, block, 3);
                            }
                            if (level.getBlockState(fanPos1).is(Blocks.WATER)) {
                                level.setBlock(fanPos1, block, 3);
                            }
                            if (level.getBlockState(fanPos2).is(Blocks.WATER)) {
                                level.setBlock(fanPos2, block, 3);
                            }
                        }
                    }
                }
            }
        }
        else {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    BlockPos pos = coralPos.offset(i, 0, j);
                    double distance = distance(i, 1, j, 2, 1, 2);
                    if (distance < 1.8) {
                        level.setBlock(pos, coralType, 3);
                    }
                }
            }
        }
    }

    public static double distance(double x, double y, double z, double xRadius, double yRadius, double zRadius) {
        return Mth.square(x / (xRadius)) + Mth.square(y / (yRadius)) + Mth.square(z / (zRadius));
    }
}