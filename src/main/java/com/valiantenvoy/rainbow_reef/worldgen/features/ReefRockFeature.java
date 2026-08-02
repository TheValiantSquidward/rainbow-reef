package com.valiantenvoy.rainbow_reef.worldgen.features;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.valiantenvoy.rainbow_reef.tags.ReefBlockTags;
import com.valiantenvoy.rainbow_reef.worldgen.features.config.ReefRockBlockSettings;
import com.valiantenvoy.rainbow_reef.worldgen.features.config.ReefRockConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.material.FluidState;

import java.util.List;
import java.util.function.Predicate;

public class ReefRockFeature extends Feature<ReefRockConfig> {

    private static final Direction[] DIRECTIONS = Direction.values();

    public ReefRockFeature(Codec<ReefRockConfig> codec) {
        super(codec);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean place(FeaturePlaceContext<ReefRockConfig> context) {
        ReefRockConfig config = context.config();
        RandomSource random = context.random();
        BlockPos origin = context.origin().below(1);
        WorldGenLevel level = context.level();
        int minGenOffset = config.minGenOffset();
        int maxGenOffset = config.maxGenOffset();
        List<Pair<BlockPos, Integer>> list = Lists.newLinkedList();
        int k = config.distributionPoints().sample(random);
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(level.getSeed()));
        NormalNoise noise = NormalNoise.create(worldgenrandom, -4, 1.0);
        List<BlockPos> list1 = Lists.newLinkedList();
        double d0 = (double) k / (double) config.outerWallDistance().getMaxValue();
        GeodeLayerSettings layerSettings = config.layerSettings();
        ReefRockBlockSettings blockSettings = config.blockSettings();
        GeodeCrackSettings crackSettings = config.crackSettings();
        double fillingSqrt = 1.0 / Math.sqrt(layerSettings.filling);
        double innerLayerSqrt = 1.0 / Math.sqrt(layerSettings.innerLayer + d0);
        double middleLayerSqrt = 1.0 / Math.sqrt(layerSettings.middleLayer + d0);
        double outerLayerSqrt = 1.0 / Math.sqrt(layerSettings.outerLayer + d0);
        double crackSize = 1.0 / Math.sqrt(crackSettings.baseCrackSize + random.nextDouble() / 2.0 + (k > 3 ? d0 : 0.0));
        boolean generateCrack = (double) random.nextFloat() < crackSettings.generateCrackChance;
        int l = 0;

        for (int i1 = 0; i1 < k; i1++) {
            int outerSample1 = config.outerWallDistance().sample(random);
            int outerSample2 = config.outerWallDistance().sample(random);
            int outerSample3 = config.outerWallDistance().sample(random);
            BlockPos offsetOrigin = origin.offset(outerSample1, outerSample2, outerSample3);
            BlockState blockstate = level.getBlockState(offsetOrigin);
            if (blockstate.isAir() || blockstate.is(BlockTags.GEODE_INVALID_BLOCKS)) {
                if (++l > config.invalidBlocksThreshold()) {
                    return false;
                }
            }
            list.add(Pair.of(offsetOrigin, config.pointOffset().sample(random)));
        }

        if (generateCrack) {
            int i2 = random.nextInt(4);
            int j2 = k * 2 + 1;
            if (i2 == 0) {
                list1.add(origin.offset(j2, 7, 0));
                list1.add(origin.offset(j2, 5, 0));
                list1.add(origin.offset(j2, 1, 0));
            } else if (i2 == 1) {
                list1.add(origin.offset(0, 7, j2));
                list1.add(origin.offset(0, 5, j2));
                list1.add(origin.offset(0, 1, j2));
            } else if (i2 == 2) {
                list1.add(origin.offset(j2, 7, j2));
                list1.add(origin.offset(j2, 5, j2));
                list1.add(origin.offset(j2, 1, j2));
            } else {
                list1.add(origin.offset(0, 7, 0));
                list1.add(origin.offset(0, 5, 0));
                list1.add(origin.offset(0, 1, 0));
            }
        }

        Predicate<BlockState> replaceable = isReplaceable(config.blockSettings().cannotReplace());

        for (BlockPos blockPos : BlockPos.betweenClosed(origin.offset(minGenOffset, minGenOffset, minGenOffset), origin.offset(maxGenOffset, maxGenOffset, maxGenOffset))) {

            double noiseValue = noise.getValue(blockPos.getX(), blockPos.getY(), blockPos.getZ()) * config.noiseMultiplier();
            double d6 = 0.0;
            double d7 = 0.0;

            for (Pair<BlockPos, Integer> posPair : list) {
                d6 += Mth.invSqrt(blockPos.distSqr(posPair.getFirst()) + (double) posPair.getSecond()) + noiseValue;
            }

            for (BlockPos blockPos1 : list1) {
                d7 += Mth.invSqrt(blockPos.distSqr(blockPos1) + (double) crackSettings.crackPointOffset) + noiseValue;
            }

            if (!(d6 < outerLayerSqrt)) {
                BlockState fillState = blockPos.getY() < level.getSeaLevel() ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                BlockState rockState = blockSettings.layerProvider().getState(random, blockPos);

                if (generateCrack && !level.getBlockState(blockPos).is(ReefBlockTags.REEF_ROCK_CANNOT_REPLACE)) {
                    if (d7 >= crackSize && d6 < fillingSqrt) {
                        this.safeSetBlock(level, blockPos, fillState, replaceable);
                    }

                    else if (d6 >= fillingSqrt) {
                        this.safeSetBlock(level, blockPos, fillState, replaceable);
                    }
                    else if (d6 >= innerLayerSqrt || d6 >= middleLayerSqrt || d6 >= outerLayerSqrt) {
                        this.safeSetBlock(level, blockPos, rockState, replaceable);
                    }

                    if (fillState.is(Blocks.WATER)) {
                        for (Direction directions : DIRECTIONS) {
                            BlockPos relative = blockPos.relative(directions);
                            FluidState fluidState = level.getFluidState(relative);
                            if (!fluidState.isEmpty()) {
                                level.scheduleTick(relative, fluidState.getType(), 0);
                            }
                        }
                    }
                }
                else {
                    this.safeSetBlock(level, blockPos, blockSettings.layerProvider().getState(random, blockPos), replaceable);
                }
            }
        }
        return true;
    }
}