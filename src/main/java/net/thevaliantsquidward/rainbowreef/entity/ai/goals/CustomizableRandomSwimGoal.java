package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CustomizableRandomSwimGoal extends RandomStrollGoal {

    private final int radius;
    private final int height;
    private final int proximity;
    private final boolean hasProximity;
    protected Vec3 wantedPos;

    public CustomizableRandomSwimGoal(PathfinderMob entity, double speedMultiplier, int interval, int radius, int height) {
        this(entity, speedMultiplier, interval, radius, height, 0, false);
    }

    public CustomizableRandomSwimGoal(PathfinderMob entity, double speedMultiplier, int interval) {
        this(entity, speedMultiplier, interval, 10, 7, 0, false);
    }

    public CustomizableRandomSwimGoal(PathfinderMob entity, double speedMultiplier, int interval, int proximity) {
        this(entity, speedMultiplier, interval, 10, 7, proximity, true);
    }

    public CustomizableRandomSwimGoal(PathfinderMob entity, double speedMultiplier, int interval, int radius, int height, int proximity, boolean hasProximity) {
        super(entity, speedMultiplier, interval);
        this.radius = radius;
        this.height = height;
        this.proximity = proximity;
        this.hasProximity = hasProximity;
    }

    @Override
    public boolean canContinueToUse() {
        this.wantedPos = new Vec3(this.wantedX, this.wantedY, this.wantedZ);
        if (this.hasProximity) {
            return super.canContinueToUse() && !(this.wantedPos.distanceTo(mob.position()) <= mob.getBbWidth() * proximity);
        }
        return super.canContinueToUse();
    }

    @Nullable
    protected Vec3 getPosition() {
        return BehaviorUtils.getRandomSwimmablePos(this.mob, radius, height);
    }
}