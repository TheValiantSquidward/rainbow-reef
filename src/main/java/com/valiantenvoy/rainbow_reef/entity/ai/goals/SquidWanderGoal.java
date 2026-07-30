package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Squid;

public class SquidWanderGoal extends Goal {

    private final Squid squid;

    public SquidWanderGoal(Squid squid) {
        this.squid = squid;
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void tick() {
        if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.isInWater() || !this.squid.hasMovementVector()) {
            float random = this.squid.getRandom().nextFloat() * (float) (Math.PI * 2);
            float x = Mth.cos(random) * 0.2F;
            float y = -0.1F + this.squid.getRandom().nextFloat() * 0.15F;
            float z = Mth.sin(random) * 0.2F;
            this.squid.setMovementVector(x, y, z);
        }
    }
}