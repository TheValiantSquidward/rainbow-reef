package com.valiantenvoy.rainbow_reef.entity.ai.control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.animal.Turtle;

public class ReefTurtleMoveControl extends MoveControl {

    private final Turtle turtle;

    public ReefTurtleMoveControl(Turtle turtle) {
        super(turtle);
        this.turtle = turtle;
    }

    private void updateSpeed() {
        if (this.turtle.isInWater()) {
            this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0F, 0.005F, 0.0F));
            if (!this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 16.0F)) {
                this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 2.0F, 0.08F));
            }
            if (this.turtle.isBaby()) {
                this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 3.0F, 0.06F));
            }
        } else if (this.turtle.onGround()) {
            this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 2.0F, 0.06F));
        }

    }

    @Override
    public void tick() {
        this.updateSpeed();
        if (this.operation == Operation.MOVE_TO && !this.turtle.getNavigation().isDone()) {
            double x = this.wantedX - this.turtle.getX();
            double y = this.wantedY - this.turtle.getY();
            double z = this.wantedZ - this.turtle.getZ();
            double length = Math.sqrt(x * x + y * y + z * z);
            if (length < 1.0E-5D) {
                this.turtle.setSpeed(0.0F);
            }
            else {
                float speed = (float) (this.speedModifier * this.turtle.getAttributeValue(Attributes.MOVEMENT_SPEED));
                if (this.turtle.isInWater()) {
                    this.turtle.setSpeed(speed * 0.15F);
                    double d4 = Math.sqrt(x * x + z * z);
                    if (Math.abs(y) > 1.0E-5D || Math.abs(d4) > 1.0E-5D) {
                        float xRot = (float) -((Mth.atan2(y, d4) * Mth.RAD_TO_DEG));
                        xRot = Mth.clamp(Mth.wrapDegrees(xRot), -60.0F, 60.0F);
                        this.turtle.setXRot(this.rotlerp(this.turtle.getXRot(), xRot, 5.0F));
                    }
                    float f6 = Mth.cos(this.turtle.getXRot() * Mth.DEG_TO_RAD);
                    float f4 = Mth.sin(this.turtle.getXRot() * Mth.DEG_TO_RAD);
                    this.turtle.zza = f6 * speed;
                    this.turtle.yya = -f4 * speed;
                }
                else {
                    this.turtle.setSpeed(Mth.lerp(0.125F, this.turtle.getSpeed(), speed));
                }
                float yRot = (float) (Mth.atan2(z, x) * Mth.RAD_TO_DEG) - 90.0F;
                this.turtle.setYRot(this.rotlerp(this.turtle.getYRot(), yRot, 7.0F));
                this.turtle.yBodyRot = this.turtle.getYRot();
            }
        } else {
            this.turtle.setSpeed(0.0F);
        }
    }
}