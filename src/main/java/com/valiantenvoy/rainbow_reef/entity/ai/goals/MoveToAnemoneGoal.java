package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.Clownfish;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class MoveToAnemoneGoal extends Goal {

    private final Clownfish clownfish;
    private final double radiusSqr;
    private final double speedMultiplier;
    private double wantedX;
    private double wantedY;
    private double wantedZ;

    public MoveToAnemoneGoal(Clownfish clownfish, double speedMultiplier, double radiusSqr) {
        this.clownfish = clownfish;
        this.radiusSqr = radiusSqr;
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public boolean canUse() {
        if (this.clownfish.hasAnemone()) {
            BlockPos anemonePos = this.clownfish.getAnemonePos();
            if (anemonePos == null) {
                return false;
            }
            else {
                this.wantedX = anemonePos.getX() + 0.5F;
                this.wantedY = anemonePos.getY() + 0.5F;
                this.wantedZ = anemonePos.getZ() + 0.5F;
                return this.clownfish.position().distanceToSqr(this.wantedX, this.wantedY, this.wantedZ) > this.radiusSqr && clownfish.isInWater();
            }
        }
        else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.clownfish.hasAnemone()) {
            return this.clownfish.position().distanceToSqr(this.wantedX, this.wantedY, this.wantedZ) > this.radiusSqr && this.clownfish.isInWater();
        }
        else {
            return false;
        }
    }

    @Override
    public void tick() {
        this.clownfish.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedMultiplier);
    }

    @Override
    public void start() {
        this.clownfish.getNavigation().stop();
        this.clownfish.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedMultiplier);
    }
}
