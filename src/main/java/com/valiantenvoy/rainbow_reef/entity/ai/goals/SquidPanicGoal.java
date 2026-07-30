package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class SquidPanicGoal extends Goal {

    private final Squid squid;
    private int fleeTicks;

    public SquidPanicGoal(Squid squid) {
        this.squid = squid;
    }

    @Override
    public boolean canUse() {
        LivingEntity lastHurtByMob = this.squid.getLastHurtByMob();
        return this.squid.isInWater() && lastHurtByMob != null && this.squid.distanceToSqr(lastHurtByMob) < 100.0D;
    }

    @Override
    public void start() {
        this.fleeTicks = 0;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        this.fleeTicks++;
        LivingEntity livingentity = this.squid.getLastHurtByMob();
        if (livingentity != null) {
            Vec3 vec3 = new Vec3(this.squid.getX() - livingentity.getX(), this.squid.getY() - livingentity.getY(), this.squid.getZ() - livingentity.getZ());
            BlockState blockstate = this.squid.level().getBlockState(BlockPos.containing(this.squid.getX() + vec3.x, this.squid.getY() + vec3.y, this.squid.getZ() + vec3.z));
            FluidState fluidstate = this.squid.level().getFluidState(BlockPos.containing(this.squid.getX() + vec3.x, this.squid.getY() + vec3.y, this.squid.getZ() + vec3.z));
            if (fluidstate.is(FluidTags.WATER) || blockstate.isAir()) {
                double length = vec3.length();
                if (length > 0.0) {
                    vec3.normalize();
                    double speed = 3.0D;
                    if (length > 5.0D) {
                        speed -= (length - 5.0D) / 5.0D;
                    }
                    if (speed > 0.0D) {
                        vec3 = vec3.scale(speed);
                    }
                }
                if (blockstate.isAir()) {
                    vec3 = vec3.subtract(0.0, vec3.y, 0.0);
                }
                this.squid.setMovementVector((float) vec3.x / 20.0F, (float) vec3.y / 20.0F, (float) vec3.z / 20.0F);
            }
            if (this.fleeTicks % 10 == 5) {
                this.squid.level().addParticle(ParticleTypes.BUBBLE, this.squid.getX(), this.squid.getY(), this.squid.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}