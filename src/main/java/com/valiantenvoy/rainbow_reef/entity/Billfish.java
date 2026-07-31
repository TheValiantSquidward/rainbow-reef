package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.AttackGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishLeapGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.navigation.WaterNavigation;
import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class Billfish extends ReefMob {

    private static final float PITCH_CLAMP = 45.0F;

    public final SmoothAnimationState swimFastAnimationState = new SmoothAnimationState();

    public Billfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 4, 0.05F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.5F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BillfishAttackGoal(this));
        this.goalSelector.addGoal(1, new SwimWanderGoal(this, 1.0D, 30, 20, 7, 4, 50));
        this.goalSelector.addGoal(2, new FishLeapGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterNavigation(this, level, true);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (this.getRandom().nextFloat() <= 0.33F) {
            return super.getWalkTargetValue(pos, level);
        }
        return this.getSurfacePathfindingFavor(pos, level);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (super.doHurtTarget(entity)) {
            this.playSound(SoundEvents.TRIDENT_HIT, 2.0F, 0.9F + this.getRandom().nextFloat() * 0.25F);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected float getPitchClamp() {
        return PITCH_CLAMP;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.05D) {
                Vec3 vec31 = this.getViewVector(0.0F);
                this.level().addParticle(ParticleTypes.BUBBLE, this.getRandomX(0.5D) - vec31.x * 0.8D, this.getRandomY() - vec31.y * 0.25D, this.getRandomZ(0.5D) - vec31.z * 0.8D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void setupAnimationStates() {
        boolean inWater = this.isInWaterOrBubble();
        boolean leaping = this.isLeaping();
        boolean sprinting = this.isSprinting();
        this.swimAnimationState.animateWhen((inWater || leaping) && !sprinting, this.tickCount);
        this.swimFastAnimationState.animateWhen((inWater || leaping) && sprinting, this.tickCount);
        this.swimIdleAnimationState.animateWhen(inWater, this.tickCount);
        this.flopAnimationState.animateWhen(!inWater && !leaping, this.tickCount);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BILLFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/billfish/billfish_swordfish.png");
    }

    private static class BillfishAttackGoal extends AttackGoal {

        private final Billfish billfish;
        private boolean hasSkewered;

        @Nullable
        private Vec3 retreatPos;

        public BillfishAttackGoal(Billfish billfish) {
            super(billfish);
            this.billfish = billfish;
        }

        @Override
        public void start() {
            super.start();
            this.hasSkewered = true;
            this.retreatPos = null;
        }

        @Override
        public void stop() {
            super.stop();
            this.billfish.setSprinting(false);
        }

        @Override
        public boolean canUse() {
            return this.billfish.isInWaterOrBubble() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.billfish.isInWaterOrBubble() && super.canContinueToUse();
        }

        @Override
        public void tick() {
            LivingEntity target = this.billfish.getTarget();
            if (target != null) {
                if (this.hasSkewered) {
                    if (this.billfish.isSprinting()) {
                        this.billfish.setSprinting(false);
                    }
                    this.timer++;
                    if (this.retreatPos == null) {
                        Vec3 posAway = DefaultRandomPos.getPosAway(this.billfish, 20, 7, target.position());
                        if (posAway != null) {
                            this.retreatPos = posAway;
                        }
                    }
                    else {
                        this.billfish.getNavigation().moveTo(this.retreatPos.x, this.retreatPos.y, this.retreatPos.z, 1.25D);
                    }
                    if (this.timer >= 60 || (this.retreatPos != null && this.billfish.distanceToSqr(this.retreatPos) <= 10.0D)) {
                        this.hasSkewered = false;
                        this.retreatPos = null;
                        this.timer = 0;
                    }
                }
                else {
                    if (!this.billfish.isSprinting()) {
                        this.billfish.setSprinting(true);
                    }
                    this.lookAtTarget(target, 6.0F, 8.0F);
                    this.billfish.getNavigation().moveTo(target, 1.5D);
                    if (this.isInAttackRange(target, 0.5D)) {
                        this.billfish.doHurtTarget(target);
                        this.hasSkewered = true;
                    }
                }
            }
        }
    }
}
