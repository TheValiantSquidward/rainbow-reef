package net.thevaliantsquidward.rainbowreef.entity.goalz;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.custom.CrabEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.DancingEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.SemiAquatic;

import javax.annotation.Nullable;

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
