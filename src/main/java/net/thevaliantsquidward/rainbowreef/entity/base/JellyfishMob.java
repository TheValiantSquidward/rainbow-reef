package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class JellyfishMob extends ReefMob {

    protected JellyfishMob(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isEffectiveAi()) {
            if (this.isPassenger()) {
                this.setYRot(0.0F);
                this.setXRot(0.0F);
                this.setAirSupply(this.getMaxAirSupply());
            } else if (this.isInWater() && this.getNavigation().isDone()) {
                float yaw = this.getYRot();
                float pitch = this.getXRot();
                float pitchFactor = Mth.cos(pitch * ((float) Math.PI / 180F));
                float x = -Mth.sin(yaw * ((float) Math.PI / 180F)) * pitchFactor;
                float y = -Mth.sin(pitch * ((float) Math.PI / 180F));
                float z = Mth.cos(yaw * ((float) Math.PI / 180F)) * pitchFactor;
                Vec3 motion = new Vec3(x, y, z).normalize().scale(0.008F);
                this.push(motion.x, motion.y, motion.z);
            }

            if (!this.level().isClientSide && (Mth.abs(this.xRotO - this.getXRot()) >= 1.0F || Mth.abs(this.yRotO - this.getYRot()) >= 1.0F)) {
                this.hasImpulse = true;
            }
        }
    }

    public static class JellyfishLookControl extends SmoothSwimmingLookControl {

        public JellyfishLookControl(Mob mob) {
            super(mob, 10);
        }

        @Override
        protected boolean resetXRotOnTick() {
            return false;
        }

    }
}
