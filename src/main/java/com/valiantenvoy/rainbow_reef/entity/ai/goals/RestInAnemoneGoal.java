package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.Clownfish;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class RestInAnemoneGoal extends Goal {

    private final Clownfish clownfish;
    private final double speedModifier;
    private final int interval;
    private int timer;
    private double wantedX;
    private double wantedY;
    private double wantedZ;

    public RestInAnemoneGoal(Clownfish clownfish, double speedModifier, int interval, int timer) {
        this.clownfish = clownfish;
        this.speedModifier = speedModifier;
        this.interval = interval;
        this.timer = timer + clownfish.getRandom().nextInt(200);
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.clownfish.hasAnemone()) {
            if (this.clownfish.getNoActionTime() >= 100) {
                return false;
            }
            if (this.clownfish.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
                return false;
            }
            BlockPos anemonePos = this.clownfish.getAnemonePos();
            if (anemonePos == null) {
                return false;
            }
            else {
                this.wantedX = anemonePos.getX() + 0.5F;
                this.wantedY = anemonePos.getY() + 0.5F;
                this.wantedZ = anemonePos.getZ() + 0.5F;
                this.clownfish.setNoActionTime(1200 + this.clownfish.getRandom().nextInt(this.interval));
                return this.clownfish.isInWater();
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.clownfish.hasAnemone() && this.clownfish.isInWater() && this.timer >= 0;
    }

    @Override
    public void tick() {
        this.timer--;
    }

    @Override
    public void start() {
        this.clownfish.getNavigation().stop();
        this.clownfish.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }
}
