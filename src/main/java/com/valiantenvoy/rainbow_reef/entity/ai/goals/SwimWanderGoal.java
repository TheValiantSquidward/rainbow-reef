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
    private final int timeoutThreshold;
    protected int timeout;
    protected Vec3 wantedPos;

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int radius, int height, int timeoutThreshold) {
        this(entity, speedMultiplier, interval, radius, height, 3, timeoutThreshold);
    }

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int timeoutThreshold) {
        this(entity, speedMultiplier, interval, 10, 7, 3, timeoutThreshold);
    }

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int proximity, int timeoutThreshold) {
        this(entity, speedMultiplier, interval, 10, 7, proximity, timeoutThreshold);
    }

    public SwimWanderGoal(PathfinderMob entity, double speedMultiplier, int interval, int radius, int height, int proximity, int timeoutThreshold) {
        super(entity, speedMultiplier, interval);
        this.radius = radius;
        this.height = height;
        this.proximity = proximity;
        this.timeoutThreshold = timeoutThreshold;
    }

    @Override
    public void start() {
        super.start();
        this.timeout = 0;
    }

    @Override
    public void tick() {
        this.timeout++;
    }

    @Override
    public boolean canContinueToUse() {
        this.wantedPos = new Vec3(this.wantedX, this.wantedY, this.wantedZ);
        return super.canContinueToUse() && this.timeout < this.timeoutThreshold && !(this.wantedPos.distanceTo(this.mob.position()) <= this.mob.getBbWidth() * this.proximity);
    }

    @Nullable
    protected Vec3 getPosition() {
        return BehaviorUtils.getRandomSwimmablePos(this.mob, this.radius, this.height);
    }
}