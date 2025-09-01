package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.utils.GoalUtils;

import javax.annotation.Nullable;

public class CustomizableRandomSwimGoal extends RandomStrollGoal {

    private final PathfinderMob mob;

    int radius;
    int height;
    int prox;

    boolean preferCrevices;

    public CustomizableRandomSwimGoal(PathfinderMob mob, double speedMultiplier, int interval, int radius, int height, int proximity, boolean preferCrevices) {
        super(mob, speedMultiplier, interval);
        this.mob = mob;
        this.radius = radius;
        this.height = height;
        this.prox = proximity;
        this.preferCrevices = preferCrevices;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && mob.isInWater();
    }

    // second part cancels the goal if the animal gets close enough
    @Override
    public boolean canContinueToUse() {
        Vec3 wantedPos = new Vec3(this.wantedX, this.wantedY, this.wantedZ);
        return super.canContinueToUse() && mob.isInWater() && !(wantedPos.distanceTo(this.mob.position()) <= this.mob.getBbWidth() * prox);
    }

    // previously 32 and 12
    @Nullable
    protected Vec3 getPosition() {
        return GoalUtils.getRandomSwimmablePosThatIsntTheSameDepth(this.mob, radius, height, this.preferCrevices);
    }
}