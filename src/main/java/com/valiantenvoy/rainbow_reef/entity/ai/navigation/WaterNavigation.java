package com.valiantenvoy.rainbow_reef.entity.ai.navigation;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;

public class WaterNavigation extends WaterBoundPathNavigation {

    private final boolean canBreach;

    public WaterNavigation(Mob mob, Level level, boolean canBreach) {
        super(mob, level);
        this.canBreach = canBreach;
    }

    @Override
    protected PathFinder createPathFinder(int maxVisitedNodes) {
        this.allowBreaching = this.canBreach;
        this.nodeEvaluator = new SwimNodeEvaluator(this.allowBreaching);
        return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
    }
}