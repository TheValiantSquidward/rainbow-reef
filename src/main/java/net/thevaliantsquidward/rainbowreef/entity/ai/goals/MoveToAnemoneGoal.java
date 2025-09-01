package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.base.Anemonefish;

public class MoveToAnemoneGoal extends Goal {

    private final Anemonefish fish;

    int radius;
    double speedMultiplier;

    public MoveToAnemoneGoal(Anemonefish fish, double speedMultiplier, int MaxRadius) {
        this.fish = fish;
        this.radius = MaxRadius;
        this.speedMultiplier = speedMultiplier;
    }

    @Override
    public boolean canUse() {
        if (this.fish.hasAnemone() && !this.fish.level().isClientSide) {

            Vec3 nempos = new Vec3(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.1F, this.fish.getAnemonePos().getZ() + 0.5F);
            return this.fish.position().distanceTo(nempos) > this.radius && fish.isInWater();
            //if the fish is farther than the given radius to the nem, then allowed to use

        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (this.fish.hasAnemone()) {

            Vec3 nempos = new Vec3(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.1F, this.fish.getAnemonePos().getZ() + 0.5F);
            //System.out.println(this.fish.position().distanceTo(nempos) > this.radius && fish.isInWater());
            //System.out.println(this.fish.position().distanceTo(nempos));
            //System.out.println(this.radius);
            return (this.fish.position().distanceTo(nempos) > radius) && fish.isInWater();

        } else {
            return false;
        }

        //second part cancels the goal if the animal gets close enough, but if the distance is greater than the maximum distance it keeps running
    }

    @Override
    public void tick() {
        this.fish.getNavigation().moveTo(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.1F, this.fish.getAnemonePos().getZ() + 0.5F, 1F);
    }


    @Override
    public void start() {
        this.fish.getNavigation().stop();
        this.fish.setSpeed(fish.getSpeed() * 4);
        this.fish.getMoveControl().setWantedPosition(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.1F, this.fish.getAnemonePos().getZ() + 0.5F, 1F);
        this.fish.getNavigation().moveTo(this.fish.getAnemonePos().getX() + 0.5F, this.fish.getAnemonePos().getY() + 0.1F, this.fish.getAnemonePos().getZ() + 0.5F, 1F);
    }
}
