package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;

public class FishAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {

    public FishAvoidEntityGoal(PathfinderMob mob, Class<T> entityClassToAvoid, float maxDistance, double speedModifier) {
        super(mob, entityClassToAvoid, maxDistance, speedModifier, speedModifier);
    }
}
