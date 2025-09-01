package net.thevaliantsquidward.rainbowreef.entity.pathing;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class AdvancedWaterboundPathNavigation extends PathNavigation {

    private boolean allowBreaching;
    private boolean preferCrevices;

    public AdvancedWaterboundPathNavigation(Mob mob, Level level) {
        this(mob, level, false, false);
    }

    public AdvancedWaterboundPathNavigation(Mob mob, Level level, boolean preferCrevices, boolean allowBreaching) {
        super(mob, level);
        this.preferCrevices = preferCrevices;
        this.allowBreaching = allowBreaching;

        if (this.nodeEvaluator instanceof AdvancedSwimNodeEvaluator) {
            ((AdvancedSwimNodeEvaluator) this.nodeEvaluator).setAllowBreaching(this.allowBreaching);
            ((AdvancedSwimNodeEvaluator) this.nodeEvaluator).setPreferCrevices(this.preferCrevices);
        }
    }

    @Override
    protected @NotNull PathFinder createPathFinder(int maxVisitedNodes) {
        this.nodeEvaluator = new AdvancedSwimNodeEvaluator(this.allowBreaching, this.preferCrevices);
        return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
    }

    public void setPreferCrevices(boolean preferCrevices) {
        this.preferCrevices = preferCrevices;
    }

    public void setAllowBreaching(boolean allowBreaching) {
        this.allowBreaching = allowBreaching;
    }

    @Override
    protected boolean canUpdatePath() {
        return this.allowBreaching || this.isInLiquid();
    }

    @Override
    protected @NotNull Vec3 getTempMobPos() {
        return new Vec3(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
    }

    @Override
    protected double getGroundY(Vec3 vec3) {
        return vec3.y;
    }

    @Override
    protected boolean canMoveDirectly(Vec3 vec3, Vec3 vec31) {
        return isClearForMovementBetween(this.mob, vec3, vec31, false);
    }

    @Override
    public boolean isStableDestination(BlockPos pos) {
        return !this.level.getBlockState(pos).isSolidRender(this.level, pos);
    }

    @Override
    public void setCanFloat(boolean pCanSwim) {
    }
}
