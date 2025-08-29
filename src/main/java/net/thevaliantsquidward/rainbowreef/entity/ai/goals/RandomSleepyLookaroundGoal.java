package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import java.util.EnumSet;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public class RandomSleepyLookaroundGoal extends Goal {
    private final Mob mob;
    private double relX;
    private double relZ;
    private int lookTime;

    public RandomSleepyLookaroundGoal(Mob pMob) {
        this.mob = pMob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }


    public boolean canUse() {
        Level level = this.mob.level();
        long gameTime = level.getDayTime() % 24000;

        if (gameTime >= 13000 && gameTime <= 22000) {
            return false;
        }

        return this.mob.getRandom().nextFloat() < 0.02F;
    }


    public boolean canContinueToUse() {
        Level level = this.mob.level();
        long gameTime = level.getDayTime() % 24000;
        return this.lookTime >= 0 && (gameTime < 13000 || gameTime > 22000);
    }


    public void start() {
        double d0 = (Math.PI * 2D) * this.mob.getRandom().nextDouble();
        this.relX = Math.cos(d0);
        this.relZ = Math.sin(d0);
        this.lookTime = 20 + this.mob.getRandom().nextInt(20);
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        --this.lookTime;
        this.mob.getLookControl().setLookAt(this.mob.getX() + this.relX, this.mob.getEyeY(), this.mob.getZ() + this.relZ);
    }
}