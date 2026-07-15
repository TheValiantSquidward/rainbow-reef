package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class RandomSleepyWanderGoal extends Goal {

    protected final PathfinderMob mob;
    protected double wantedX;
    protected double wantedY;
    protected double wantedZ;
    protected final double speedModifier;
    protected int interval;
    protected boolean forceTrigger;
    private final boolean checkNoActionTime;

    public RandomSleepyWanderGoal(PathfinderMob mob, double speedModifier) {
        this(mob, speedModifier, 120);
    }

    public RandomSleepyWanderGoal(PathfinderMob mob, double speedModifier, int interval) {
        this(mob, speedModifier, interval, true);
    }

    public RandomSleepyWanderGoal(PathfinderMob mob, double speedModifier, int interval, boolean checkNoActionTime) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.interval = interval;
        this.checkNoActionTime = checkNoActionTime;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }


    public boolean canUse() {
        if (this.mob.isVehicle()) {
            return false;
        } else {
            // Check if the current in-game time is within the specified range
            Level level = this.mob.level();
            long gameTime = level.getDayTime() % 24000; // Get the current time of day

            if (gameTime >= 13000 && gameTime <= 22000) {
                return false;
            }

            if (!this.forceTrigger) {
                if (this.checkNoActionTime && this.mob.getNoActionTime() >= 100) {
                    return false;
                }

                if (this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
                    return false;
                }
            }

            Vec3 vec3 = this.getPosition();
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                this.forceTrigger = false;
                return true;
            }
        }
    }


    @Nullable
    protected Vec3 getPosition() {
        return DefaultRandomPos.getPos(this.mob, 10, 7);
    }


    public boolean canContinueToUse() {
        Level level = this.mob.level();
        long gameTime = level.getDayTime() % 24000;
        if (gameTime >= 13000 && gameTime <= 22000) {
            return false;
        }
        return !this.mob.getNavigation().isDone() && !this.mob.isVehicle();
    }


    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }

    public void stop() {
        this.mob.getNavigation().stop();
        super.stop();
    }


    public void trigger() {
        this.forceTrigger = true;
    }


    public void setInterval(int newchance) {
        this.interval = newchance;
    }
}