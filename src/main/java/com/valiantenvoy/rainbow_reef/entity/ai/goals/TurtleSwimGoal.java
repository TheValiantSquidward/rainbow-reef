package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.phys.Vec3;

public class TurtleSwimGoal extends Goal {

    private final Turtle turtle;
    private final double speedModifier;
    private boolean stuck;

    public TurtleSwimGoal(Turtle turtle, double speedModifier) {
        this.turtle = turtle;
        this.speedModifier = speedModifier;
    }

    @Override
    public boolean canUse() {
        if (this.turtle.isGoingHome() || this.turtle.hasEgg() || !this.turtle.isInWater()) {
            return false;
        }
        if (this.turtle.getRandom().nextInt(reducedTickDelay(70)) != 0) {
            return false;
        }
        return !this.turtle.isGoingHome() && !this.turtle.hasEgg() && this.turtle.isInWater();
    }

    @Override
    public void start() {
        int k = this.turtle.getRandom().nextInt(1025) - 512;
        int l = this.turtle.getRandom().nextInt(9) - 4;
        int i1 = this.turtle.getRandom().nextInt(1025) - 512;
        if ((double) l + this.turtle.getY() > (double) (this.turtle.level().getSeaLevel() - 1)) {
            l = 0;
        }
        BlockPos blockpos = BlockPos.containing((double) k + this.turtle.getX(), (double) l + this.turtle.getY(), (double) i1 + this.turtle.getZ());
        this.turtle.setTravelPos(blockpos);
        this.turtle.setTravelling(true);
        this.stuck = false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void tick() {
        if (this.turtle.getNavigation().isDone()) {
            Vec3 travelPos = Vec3.atBottomCenterOf(this.turtle.getTravelPos());
            Vec3 posTowards = DefaultRandomPos.getPosTowards(this.turtle, 16, 3, travelPos, (float) (Math.PI / 10));
            if (posTowards == null) {
                posTowards = DefaultRandomPos.getPosTowards(this.turtle, 8, 7, travelPos, (float) (Math.PI / 2));
            }

            if (posTowards != null) {
                int i = Mth.floor(posTowards.x);
                int j = Mth.floor(posTowards.z);
                int k = 34;
                if (!this.turtle.level().hasChunksAt(i - k, j - k, i + k, j + k)) {
                    posTowards = null;
                }
            }

            if (posTowards == null) {
                this.stuck = true;
                return;
            }

            this.turtle.getNavigation().moveTo(posTowards.x, posTowards.y, posTowards.z, this.speedModifier);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.turtle.getNavigation().isDone() && !this.stuck && !this.turtle.isGoingHome() && !this.turtle.isInLove() && !this.turtle.hasEgg();
    }

    @Override
    public void stop() {
        this.turtle.setTravelling(false);
        super.stop();
    }
}