package net.thevaliantsquidward.rainbowreef.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GoalUtils {

    @Nullable
    public static Vec3 getRandomSwimmablePosWithSeabed(PathfinderMob pathfinder, int radius, int verticalDistance) {
        Vec3 testPos = DefaultRandomPos.getPos(pathfinder, radius, verticalDistance);
        int MaxSearchAmount = radius*radius*radius;

        for (int x = 0; testPos != null && x < MaxSearchAmount; testPos = DefaultRandomPos.getPos(pathfinder, radius, verticalDistance), x ++) {
            if (testPos != null) {
                Vec3 belowPos = testPos.subtract(0, 1, 0);

                if (pathfinder.level().getBlockState(BlockPos.containing(belowPos)).entityCanStandOn(pathfinder.level(), BlockPos.containing(testPos), pathfinder) && pathfinder.level().getBlockState(BlockPos.containing(testPos)).isPathfindable(PathComputationType.WATER)) {
                    return testPos;
                }

                if (x == MaxSearchAmount - 1) {
                    return testPos;
                    //basically this keeps the shark moving if it can't find somewhere to go. it's flawed because there's nothing to prevent double checks but you probably can't get the shark stuck in open waters unless you're intentionally doing it
                    //might be useful for drowned farms or smthing
                }
            }
        }

        return null;
    }



    @Nullable
    public static Vec3 getRandomSwimmablePosThatIsntTheSameDepth(PathfinderMob pathfinder, int radius, int verticalDistance, boolean preferCrevices) {
        LevelAccessor world = pathfinder.level();
        int MaxSearchAmount = radius*radius*radius;

        BlockPos currentPos = null;
        int currentSolidSidesCount = 0;


        for (int i = 0; i < MaxSearchAmount; i++) {
            Vec3 rand = DefaultRandomPos.getPos(pathfinder, radius, verticalDistance);
            if (rand != null) {
                BlockPos seafloor = BlockPos.containing(rand);

                while (world.getFluidState(seafloor).is(FluidTags.WATER) && seafloor.getY() > 1) {
                    int tempSolidSidesCount = countSolidSides(pathfinder.level(), seafloor.getX(), seafloor.getY(), seafloor.getZ());

                    if (seafloor.getY() != pathfinder.position().y()) {
                        if (preferCrevices) {
                            if (tempSolidSidesCount > currentSolidSidesCount) {
                                currentPos = seafloor;
                                currentSolidSidesCount = tempSolidSidesCount;
                            }
                        } else {
                            return seafloor.getCenter();
                        }
                    }

                    seafloor = seafloor.below();
                }
            }
        }

        if (currentPos == null) {
            return null;
        } else {
            return currentPos.getCenter();
        }
    }

    public static int countSolidSides(Level level, double x, double y, double z) {
        BlockPos nodepos = new BlockPos((int) x, (int) y, (int) z);
        int end = 0;

        if (level.getBlockState(nodepos.above()).isSolid()) {
            end += 1;
            //System.out.println("above");
        }
        if (level.getBlockState(nodepos.below()).isSolid()) {
            end += 1;
            //System.out.println("below");
        }
        if (level.getBlockState(nodepos.east()).isSolid()) {
            end += 1;
            //System.out.println("east");
        }
        if (level.getBlockState(nodepos.west()).isSolid()) {
            end += 1;
            //System.out.println("west");
        }
        if (level.getBlockState(nodepos.north()).isSolid()) {
            end += 1;
            //System.out.println("north");
        }
        if (level.getBlockState(nodepos.south()).isSolid()) {
            end += 1;
            //System.out.println("south");
        }

        return end;

    }
}
