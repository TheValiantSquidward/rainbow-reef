package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.base.Anemonefish;

public class RestInAnemoneGoal extends RandomStrollGoal {

    private final Anemonefish fish;

    double speedMultiplier;

    int timer;

    public RestInAnemoneGoal(Anemonefish fish, double speedMultiplier, int restInterval, int restTimer) {
        super(fish, speedMultiplier, restInterval, true);
        this.fish = fish;
        this.speedMultiplier = speedMultiplier;
        this.timer = restTimer + this.mob.getRandom().nextIntBetweenInclusive(0, 200);
    }

    @Override
    public boolean canUse() {
        if (this.fish.hasAnemone() && !this.fish.level().isClientSide) {
            if (!this.forceTrigger) {
                if (this.mob.getNoActionTime() >= 100) {
                    return false;
                }

                if (this.mob.getRandom().nextIntBetweenInclusive(0, reducedTickDelay(this.interval)) != 0) {
                    return false;
                }
            }

            Vec3 vec3 = this.getPosition();
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                this.forceTrigger = false;
                this.mob.setNoActionTime(1200 + this.mob.getRandom().nextIntBetweenInclusive(0, this.interval));
                return fish.isInWater();
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.fish.hasAnemone() && fish.isInWater() && this.timer >= 0;
    }

    @Override
    public void tick() {
        this.timer--;
    }

    @Override
    public void start() {
        this.fish.getNavigation().stop();
        this.fish.getMoveControl().setWantedPosition(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.2F, this.fish.getAnemonePos().getZ() + 0.5F, 1F);
        this.fish.getNavigation().moveTo(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.2F, this.fish.getAnemonePos().getZ() + 0.5F, 1F);
    }
}
