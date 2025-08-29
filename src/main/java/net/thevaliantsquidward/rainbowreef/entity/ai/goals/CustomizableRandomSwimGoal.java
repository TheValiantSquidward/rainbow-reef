package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class CustomizableRandomSwimGoal extends RandomStrollGoal {

    PathfinderMob fims;
    Vec3 wantedPos;

    int radius;
    int height;
    int prox;

    boolean preferCrevices;

    public CustomizableRandomSwimGoal(PathfinderMob fi, double spdmultiplier, int interval, int radius, int height, int proximity, boolean preferCrevices) {
        super(fi, spdmultiplier, interval);
        this.fims = fi;
        this.radius = radius;
        this.height = height;
        this.prox = proximity;
        this.preferCrevices = preferCrevices;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && fims.isInWater();
    }

    @Override
    public boolean canContinueToUse() {
        wantedPos = new Vec3(this.wantedX, this.wantedY, this.wantedZ);
        return super.canContinueToUse() && fims.isInWater() && !(this.wantedPos.distanceTo(this.fims.position()) <= this.fims.getBbWidth() * prox);
        //second part cancels the goal if the animal gets close enough
    }

    public void tick() {
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Nullable
    protected Vec3 getPosition() {
        return GoalUtils.getRandomSwimmablePosThatIsntTheSameDepth(this.mob, radius, height, this.preferCrevices);
    }
    //previously 32 and 12
}