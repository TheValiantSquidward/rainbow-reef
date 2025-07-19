package net.thevaliantsquidward.rainbowreef.entity.pathing;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.SwimNodeEvaluator;
import net.minecraft.world.phys.Vec3;

public class AdvancedWaterboundPathNavigation extends PathNavigation {
    private boolean allowBreaching;
    private boolean preferCrevices;

    public AdvancedWaterboundPathNavigation(Mob pMob, Level pLevel, boolean preferCrevices, boolean allowBreaching) {
        super(pMob, pLevel);
        this.preferCrevices = preferCrevices;
        this.allowBreaching = allowBreaching;

        if (this.nodeEvaluator instanceof AdvancedSwimNodeEvaluator) {
            ((AdvancedSwimNodeEvaluator) this.nodeEvaluator).setAllowBreaching(this.allowBreaching);
            ((AdvancedSwimNodeEvaluator) this.nodeEvaluator).setPreferCrevices(this.preferCrevices);
        }
    }

    protected PathFinder createPathFinder(int pMaxVisitedNodes) {
        //System.out.println("create " + this.preferCrevices);
        this.nodeEvaluator = new AdvancedSwimNodeEvaluator(this.allowBreaching, this.preferCrevices);
        return new PathFinder(this.nodeEvaluator, pMaxVisitedNodes);
    }

    public void setPreferCrevices(boolean preferCrevices) {
        this.preferCrevices = preferCrevices;
    }

    public void setAllowBreaching(boolean allowBreaching) {
        this.allowBreaching = allowBreaching;
    }

    /**
     * If on ground or swimming and can swim
     */
    protected boolean canUpdatePath() {
        return this.allowBreaching || this.isInLiquid();
    }

    protected Vec3 getTempMobPos() {
        return new Vec3(this.mob.getX(), this.mob.getY(0.5D), this.mob.getZ());
    }

    protected double getGroundY(Vec3 pVec) {
        return pVec.y;
    }

    /**
     * Checks if the specified entity can safely walk to the specified location.
     */
    protected boolean canMoveDirectly(Vec3 pPosVec31, Vec3 pPosVec32) {
        return isClearForMovementBetween(this.mob, pPosVec31, pPosVec32, false);
    }

    public boolean isStableDestination(BlockPos pPos) {
        return !this.level.getBlockState(pPos).isSolidRender(this.level, pPos);
    }

    public void setCanFloat(boolean pCanSwim) {
    }
}
