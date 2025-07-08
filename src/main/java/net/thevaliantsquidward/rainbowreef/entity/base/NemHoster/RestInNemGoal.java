package net.thevaliantsquidward.rainbowreef.entity.base.NemHoster;

import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.phys.Vec3;

public class RestInNemGoal extends RandomStrollGoal {

    NemHoster fims;

    double spd;

    int timer;

    public RestInNemGoal(NemHoster fi, double spdmultiplier, int restInterval, int restTimer) {
        super(fi, spdmultiplier, restInterval, true);
        this.fims = fi;
        this.spd = spdmultiplier;
        this.timer = restTimer + this.mob.getRandom().nextIntBetweenInclusive(0, 200);
    }

    public boolean canUse() {

        if (this.fims.hasNem() && !this.fims.level().isClientSide) {
            if (!this.forceTrigger) {
                if (this.mob.getNoActionTime() >= 100) {
                    return false;
                }

                if (this.mob.getRandom().nextIntBetweenInclusive(0, reducedTickDelay(this.interval)) != 0) {
                    return false;
                }
            }

            Vec3 $$0 = this.getPosition();
            if ($$0 == null) {
                return false;
            } else {
                this.wantedX = $$0.x;
                this.wantedY = $$0.y;
                this.wantedZ = $$0.z;
                this.forceTrigger = false;
                this.mob.setNoActionTime(1200 + this.mob.getRandom().nextIntBetweenInclusive(0, this.interval));
                return fims.isInWater();
            }
        }

        return false;

    }

    public boolean canContinueToUse() {
        return this.fims.hasNem() && fims.isInWater() && this.timer >= 0;
    }

    public void tick() {
        //Vec3 wantedPos = new Vec3(this.mob.getMoveControl().getWantedX(), this.mob.getMoveControl().getWantedY(), this.mob.getMoveControl().getWantedZ());
        //ServerLevel level = (ServerLevel) this.fims.level();
        //level.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 1, 0.0D, 0.0D, 0.0D, 0);
        this.timer --;
        System.out.println("ticK Rest");
    }


    public void start() {
        this.fims.getNavigation().stop();
        this.fims.getMoveControl().setWantedPosition(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.2F, this.fims.getNemPos().getZ() + 0.5F, 3F);
        this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.2F, this.fims.getNemPos().getZ() + 0.5F, 3F);
    }
}
