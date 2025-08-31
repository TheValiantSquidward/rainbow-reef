package net.thevaliantsquidward.rainbowreef.entity.base.NemHoster;

import net.minecraft.world.entity.ai.goal.Goal;

public class LocateNemGoal extends Goal {

    private final NemHoster clown;
    int CD;


    public LocateNemGoal(NemHoster fish, int cooldown) {
        super();
        this.clown = fish;
        this.CD = cooldown;
    }

    @Override
    public boolean canUse() {
        return !this.clown.hasNem() && this.clown.nemSearchCooldown <= 0;
    }
    //can only use if the clown does not have a nem


    public boolean canContinueToUse() {
        return false;
    }

    public void start() {
        this.clown.nemSearchCooldown = this.CD;
        this.clown.findAndSetNems();
    }


}
