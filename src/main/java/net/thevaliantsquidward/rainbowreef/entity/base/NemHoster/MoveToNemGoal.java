package net.thevaliantsquidward.rainbowreef.entity.base.NemHoster;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class MoveToNemGoal extends Goal {

    NemHoster fims;

    int radius;

    double spd;

    public MoveToNemGoal(NemHoster fi, double spdmultiplier, int MaxRadius) {
        this.fims = fi;
        this.radius = MaxRadius;
        this.spd = spdmultiplier;
    }

    public boolean canUse() {
        if (this.fims.hasNem() && !this.fims.level().isClientSide) {

            Vec3 nempos = new Vec3(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F);
            return this.fims.position().distanceTo(nempos) > this.radius && fims.isInWater();
            //if the fims is farther than the given radius to the nem, then allowed to use

        } else {
            return false;
        }
    }

    public boolean canContinueToUse() {
        if (this.fims.hasNem()) {

            Vec3 nempos = new Vec3(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F);
            System.out.println(this.fims.position().distanceTo(nempos) > this.radius && fims.isInWater());
            System.out.println(this.fims.position().distanceTo(nempos));
            System.out.println(this.radius);
            return (this.fims.position().distanceTo(nempos) > radius) && fims.isInWater();

        } else {
            return false;
        }

        //second part cancels the goal if the animal gets close enough, but if the distance is greater than the maximum distance it keeps running
    }

    public void tick() {
        this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 4F);

        Vec3 nempos = new Vec3(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F);
        //System.out.println(this.fims.position().distanceTo(nempos));
    }


    public void start() {

        this.fims.getNavigation().stop();
        this.fims.setSpeed(fims.getSpeed() * 4);
        this.fims.getMoveControl().setWantedPosition(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 4F);
        this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 4F);
    }
}
