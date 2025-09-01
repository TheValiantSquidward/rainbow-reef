package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import javax.annotation.Nullable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.phys.Vec3;

public class RandomSleepySwimGoal extends RandomSleepyWanderGoal {

    public RandomSleepySwimGoal(PathfinderMob mob, double speedModifier, int interval) {
        super(mob, speedModifier, interval);
    }

    @Nullable
    protected Vec3 getPosition() {
        return BehaviorUtils.getRandomSwimmablePos(this.mob, 10, 7);
    }
}