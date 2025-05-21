package net.thevaliantsquidward.rainbowreef.entity.ai.goalz;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.thevaliantsquidward.rainbowreef.entity.CrabEntity;

public class ConditionalStopGoal extends Goal {
    private PathfinderMob creatura = null;

    public ConditionalStopGoal(CrabEntity creature) {
        this.creatura = creature;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public PathfinderMob getCreatura() {
        return creatura;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }


    public void start() {
        //System.out.println("start");
        this.getCreatura().getNavigation().stop();
    }

    public void stop() {
        this.getCreatura().getNavigation().recomputePath();
    }

    public void tick() {
        //System.out.println("works");
        this.creatura.setTarget(null);
        this.creatura.getNavigation().stop();
    }

}
