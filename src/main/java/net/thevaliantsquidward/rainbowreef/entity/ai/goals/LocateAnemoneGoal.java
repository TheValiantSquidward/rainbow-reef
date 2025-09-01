package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.thevaliantsquidward.rainbowreef.entity.base.Anemonefish;

public class LocateAnemoneGoal extends Goal {

    private final Anemonefish fish;
    int cooldown;

    public LocateAnemoneGoal(Anemonefish fish, int cooldown) {
        super();
        this.fish = fish;
        this.cooldown = cooldown;
    }

    @Override
    public boolean canUse() {
        return !this.fish.hasAnemone() && this.fish.anemoneSearchCooldown <= 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        this.fish.anemoneSearchCooldown = this.cooldown;
        this.fish.findAndSetAnemone();
    }
}
