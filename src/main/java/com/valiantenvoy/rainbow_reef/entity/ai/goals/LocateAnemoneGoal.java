package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.Clownfish;
import net.minecraft.world.entity.ai.goal.Goal;

public class LocateAnemoneGoal extends Goal {

    private final Clownfish clownfish;
    private final int cooldown;

    public LocateAnemoneGoal(Clownfish clownfish, int cooldown) {
        this.clownfish = clownfish;
        this.cooldown = cooldown;
    }

    @Override
    public boolean canUse() {
        return !this.clownfish.hasAnemone() && this.clownfish.anemoneSearchCooldown <= 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        this.clownfish.anemoneSearchCooldown = this.cooldown;
        this.clownfish.findAndSetAnemone();
    }
}
