package net.thevaliantsquidward.rainbowreef.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import java.util.Optional;

public class ReefNemFeature extends Feature<NoneFeatureConfiguration> {
    public ReefNemFeature(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        context.config();

        Optional<Block> nem = BuiltInRegistries.BLOCK.getTag(RRTags.TROPICAL_NEMS).flatMap((holders) -> holders.getRandomElement(world.getRandom())).map(Holder::value);

        if (!world.getFluidState(origin).is(FluidTags.WATER)) {
            return false;
            //do not spawn if not underwater
        } else {
            Direction[] directions = Direction.values();
            int listlength = directions.length;
            BlockState block = nem.map(Block::defaultBlockState).orElseGet(ReefBlocks.ORANGE_SEA_ANEMONE.get()::defaultBlockState).setValue(BlockStateProperties.FACING, Direction.UP);

            for(int var6 = 0; var6 < listlength; ++var6) {
                Direction direction = directions[var6];

                if(direction == Direction.NORTH) {
                    BlockPos northPos = new BlockPos(origin.getX(), origin.getY(), origin.getZ() + 1);
                    //return level.getBlockState(northPos).isFaceSturdy(level, pos, Direction.SOUTH);

                    if (world.getBlockState(northPos).isFaceSturdy(world, origin, Direction.SOUTH) && !world.getBlockState(northPos).is(RRTags.CONSIDERED_NEMS)) {
                        world.setBlock(origin, block.setValue(BlockStateProperties.FACING, Direction.NORTH), 2);
                    }
                    //if the substrate block checked is to north, check if the south face is solid


                } else if (direction == Direction.SOUTH) {
                    BlockPos southPos = new BlockPos(origin.getX(), origin.getY(), origin.getZ() - 1);

                    if (world.getBlockState(southPos).isFaceSturdy(world, origin, Direction.NORTH) && !world.getBlockState(southPos).is(RRTags.CONSIDERED_NEMS)) {
                        world.setBlock(origin, block.setValue(BlockStateProperties.FACING, Direction.SOUTH), 2);
                    }
                    //if the substrate block checked is to south, check if the north face is solid

                } else if (direction == Direction.EAST) {
                    BlockPos eastPos = new BlockPos(origin.getX() - 1, origin.getY(), origin.getZ());

                    if (world.getBlockState(eastPos).isFaceSturdy(world, origin, Direction.WEST) && !world.getBlockState(eastPos).is(RRTags.CONSIDERED_NEMS)) {
                        world.setBlock(origin, block.setValue(BlockStateProperties.FACING, Direction.EAST), 2);
                    }
                    //if the substrate block checked is to east, check if the west face is solid

                } else if (direction == Direction.WEST) {
                    BlockPos westPos = new BlockPos(origin.getX() + 1, origin.getY(), origin.getZ());

                    if (world.getBlockState(westPos).isFaceSturdy(world, origin, Direction.EAST) && !world.getBlockState(westPos).is(RRTags.CONSIDERED_NEMS)) {
                        world.setBlock(origin, block.setValue(BlockStateProperties.FACING, Direction.WEST), 2);
                    }
                    //if the substrate block checked is to west, check if the east face is solid

                } else if (direction == Direction.UP) {
                    BlockPos upPos = new BlockPos(origin.getX(), origin.getY() + 1, origin.getZ());

                    if (world.getBlockState(upPos).isFaceSturdy(world, origin, Direction.DOWN) && !world.getBlockState(upPos).is(RRTags.CONSIDERED_NEMS)) {
                        world.setBlock(origin, block.setValue(BlockStateProperties.FACING, Direction.DOWN), 2);
                    }
                    //if the substrate block checked is to up, check if the down face is solid

                } else {
                    BlockPos downPos = new BlockPos(origin.getX(), origin.getY() - 1, origin.getZ());

                    if (world.getBlockState(downPos).isFaceSturdy(world, origin, Direction.UP) && !world.getBlockState(downPos).is(RRTags.CONSIDERED_NEMS)) {
                        world.setBlock(origin, block.setValue(BlockStateProperties.FACING, Direction.UP), 2);
                    }
                    //if the substrate block checked is to down, check if the up face is solid
                }
                //test on -3321557746479081045
            }

            return false;
        }
    }
}
