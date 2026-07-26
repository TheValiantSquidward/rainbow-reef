package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Objects;

public class AttackGoal extends Goal {

    protected int timer = 0;
    protected final ReefMob mob;

    public AttackGoal(ReefMob mob) {
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        this.mob = mob;
    }

    @Override
    public void start() {
        this.mob.setAggressive(true);
        this.timer = 0;
    }

    @Override
    public void stop() {
        this.mob.setTarget(null);
        this.mob.setAggressive(false);
        this.mob.setSprinting(false);
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean canUse() {
        return !this.mob.isBaby() && this.mob.getTarget() != null && this.mob.getTarget().isAlive() && !this.mob.isVehicle();
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = mob.getTarget();
        if (target == null) return false;
        else if (!target.isAlive()) return false;
        else if (!this.mob.isWithinRestriction(target.blockPosition())) return false;
        else return !(target instanceof Player) || !target.isSpectator() && !((Player) target).isCreative() || !this.mob.getNavigation().isDone();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    protected double getAttackReachSqr(LivingEntity target) {
        return this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + target.getBbWidth();
    }

    protected double getAttackReachSqr(LivingEntity target, double distance) {
        return this.mob.getBbWidth() * distance * this.mob.getBbWidth() * distance + target.getBbWidth();
    }

    protected boolean isInAttackRange(LivingEntity target, double reach) {
        return this.mob.hasLineOfSight(target) && this.mob.distanceTo(target) < this.mob.getBbWidth() + target.getBbWidth() + reach;
    }

    protected void chargeAtTarget(Entity target, float speed) {
        int speedFactor = this.mob.hasEffect(MobEffects.MOVEMENT_SPEED) ? Objects.requireNonNull(this.mob.getEffect(MobEffects.MOVEMENT_SPEED)).getAmplifier() + 1 : 0;
        int slownessFactor = this.mob.hasEffect(MobEffects.MOVEMENT_SLOWDOWN) ? Objects.requireNonNull(this.mob.getEffect(MobEffects.MOVEMENT_SLOWDOWN)).getAmplifier() + 1 : 0;
        float effectSpeed = 0.1F * (speedFactor - slownessFactor);
        Vec3 chargeDirection = new Vec3(target.getX() - this.mob.getX(), target.getY() - this.mob.getY(), target.getZ() - this.mob.getZ()).normalize();
        float YRot = Mth.approachDegrees(this.mob.getYRot(), (float) (Mth.atan2(chargeDirection.z, chargeDirection.x) * (180F / Math.PI)) - 90.0F, 0.25F);
        speed = speed + effectSpeed;
        this.mob.setYRot(YRot);
        this.mob.setYBodyRot(YRot);
        this.mob.setDeltaMovement(-Mth.sin(YRot * ((float) Math.PI / 180F)) * speed, this.mob.getDeltaMovement().y, Mth.cos(YRot * ((float) Math.PI / 180F)) * speed);
    }

    protected void lookAtTarget(LivingEntity target, float yaw, float pitch) {
        this.mob.getLookControl().setLookAt(target, yaw, pitch);
        this.mob.lookAt(target, yaw, pitch);
    }
}
