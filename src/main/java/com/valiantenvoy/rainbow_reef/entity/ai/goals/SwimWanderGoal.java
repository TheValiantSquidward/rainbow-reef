package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SwimWanderGoal extends RandomStrollGoal {

    private final int radius;
    private final int height;
    private final int proximity;
    private final boolean hasProximity;
    protected int recalculateTime;
    protected Vec3 wantedPos;

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int radius, int height) {
        this(entity, speedMultiplier, interval, radius, height, 0, false);
    }

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval) {
        this(entity, speedMultiplier, interval, 10, 7, 0, false);
    }

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int proximity) {
        this(entity, speedMultiplier, interval, 10, 7, proximity, true);
    }

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int radius, int height, int proximity, boolean hasProximity) {
        super(entity, speedMultiplier, interval);
        this.radius = radius;
        this.height = height;
        this.proximity = proximity;
        this.hasProximity = hasProximity;
    }

    @Override
    public void start() {
        super.start();
        this.recalculateTime = 0;
    }

    // should fix mobs circling forever (needs more testing)
    @Override
    public void tick() {
        this.recalculateTime++;
        final int recalculateThreshold = this.interval * 2;
        if (this.recalculateTime > recalculateThreshold) {
            this.recalculateTime = 0;
            Vec3 vec3 = this.getPosition();
            if (vec3 != null) {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        this.wantedPos = new Vec3(this.wantedX, this.wantedY, this.wantedZ);
        if (this.hasProximity) {
            return super.canContinueToUse() && !(this.wantedPos.distanceTo(this.mob.position()) <= this.mob.getBbWidth() * this.proximity);
        }
        return super.canContinueToUse();
    }

    @Nullable
    protected Vec3 getPosition() {
        return BehaviorUtils.getRandomSwimmablePos(this.mob, this.radius, this.height);
    }
}