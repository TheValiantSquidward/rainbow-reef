package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.AttackGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.animation.BodyChain;
import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.entity.utils.BodyChainMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class Shark extends VariantSchoolingFish implements BodyChainMob {

    private static final EntityDataAccessor<Integer> ROTATED_TICKS = SynchedEntityData.defineId(Shark.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(Shark.class, EntityDataSerializers.BOOLEAN);

    private static final float PITCH_CLAMP = 45.0F;

    private final BodyChain chain = new BodyChain(new float[]{0.35F, 0.16F, 0.3F}, new float[]{0.24F, 0.12F, 0.2F});

    public final SmoothAnimationState attackAnimationState = new SmoothAnimationState(2.0F);
    public final SmoothAnimationState rotatedAnimationState = new SmoothAnimationState(0.25F);
    public final SmoothAnimationState swimFastAnimationState = new SmoothAnimationState();

    public Shark(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ROTATED_TICKS, 0);
        builder.define(ATTACKING, false);
    }

    public int getRotatedTicks() {
        return this.entityData.get(ROTATED_TICKS);
    }
    public void setRotatedTicks(int rotatedTicks) {
        this.entityData.set(ROTATED_TICKS, rotatedTicks);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }
    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SharkRotateGoal(this));
        this.goalSelector.addGoal(1, new SharkAttackGoal(this, 1.0F));
        this.goalSelector.addGoal(2, new SwimWanderGoal(this, 1.0D, 10, 15, 7, 4));
        this.goalSelector.addGoal(3, new FollowVariantLeaderGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return Shark.this.getRotatedTicks() <= 0 && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return Shark.this.getRotatedTicks() <= 0 && super.canContinueToUse();
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 200, true, true, this::canAttackLowHealthTargets) {
            @Override
            public boolean canUse() {
                return Shark.this.getRotatedTicks() <= 0 && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return Shark.this.getRotatedTicks() <= 0 && super.canContinueToUse();
            }
        });
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    @Override
    protected float getPitchClamp() {
        return PITCH_CLAMP;
    }

    protected boolean canAttackLowHealthTargets(LivingEntity target) {
        return this.canAttack(target) && this.isInWaterOrBubble() && target.isInWaterOrBubble() && target.getHealth() <= target.getMaxHealth() * 0.5F;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (stack.isEmpty() && this.isInWaterOrBubble() && this.getRotatedTicks() <= 0) {
            this.setAttacking(false);
            this.setRotatedTicks(100 + this.getRandom().nextInt(100));
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.getRotatedTicks() > 0) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.0D, 1.0D, 0.0D));
            super.travel(Vec3.ZERO);
        }
        else {
            super.travel(travelVector);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.getRotatedTicks() > 0) {
            this.jumping = false;
            this.xxa = 0.0F;
            this.zza = 0.0F;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getRotatedTicks() > 0) {
            this.setRotatedTicks(this.getRotatedTicks() - 1);
        }
    }

    @Override
    public void setupAnimationStates() {
        boolean inWater = this.isInWaterOrBubble();
        boolean sprinting = this.isSprinting();
        this.swimAnimationState.animateWhen(inWater && !sprinting, this.tickCount);
        this.swimFastAnimationState.animateWhen(inWater && sprinting, this.tickCount);
        this.swimIdleAnimationState.animateWhen(inWater, this.tickCount);
        this.flopAnimationState.animateWhen(!inWater, this.tickCount);
        this.rotatedAnimationState.animateWhen(this.getRotatedTicks() > 0 && this.isInWaterOrBubble(), this.tickCount);
        this.attackAnimationState.animateWhen(this.isAttacking(), this.tickCount);
    }

    @Override
    protected float getWalkAnimationSpeed() {
        return 17.5F;
    }

    @Override
    public BodyChain getBodyChain() {
        return this.chain;
    }

    @Override
    public float getRenderYaw(float partialTicks) {
        return this.chain.getRenderYaw(partialTicks);
    }

    @Override
    public float getSegmentYawOffset(int index, float partialTicks) {
        return this.chain.getSegmentYawOffset(index, partialTicks);
    }

    @Override
    public float getSegmentPitchOffset(int index, float partialTicks) {
        return this.chain.getSegmentPitchOffset(index, partialTicks, this.getSwimPitch(partialTicks));
    }

    @Override
    public boolean shouldFlop() {
        return false;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.SHARK_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/shark/shark_blacktip_reef.png");
    }

    protected static class SharkAttackGoal extends AttackGoal {

        private final Shark shark;
        private final float soundPitch;
        private int circlingTime;
        private int maxCirclingTime;
        private boolean clockwise;
        private float circleDistance;
        private boolean playedAttackSound;

        public SharkAttackGoal(Shark shark, float soundPitch) {
            super(shark);
            this.shark = shark;
            this.soundPitch = soundPitch;
        }

        @Override
        public void start() {
            super.start();
            this.shark.setAttacking(false);
            this.circlingTime = 0;
            this.maxCirclingTime = 250 + this.shark.getRandom().nextInt(150);
            this.clockwise = this.shark.getRandom().nextBoolean();
            this.circleDistance = 5.0F + this.shark.getRandom().nextFloat() * 3.0F;
            this.playedAttackSound = false;
        }

        @Override
        public void stop() {
            super.stop();
            this.shark.setAttacking(false);
        }

        @Override
        public boolean canUse() {
            return this.shark.isInWaterOrBubble() && this.shark.getRotatedTicks() <= 0 && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return this.shark.isInWaterOrBubble() && this.shark.getRotatedTicks() <= 0 && super.canContinueToUse();
        }

        @Override
        public void tick() {
            LivingEntity target = this.shark.getTarget();
            if (target != null) {
                double distance = this.shark.distanceTo(target);
                if (this.shark.isAttacking()) {
                    this.tickAttack(target);
                }
                else if (!this.shark.isAttacking()) {
                    this.tickCircling(target, distance);
                }
            }
        }

        private void tickCircling(LivingEntity target, double distance) {
            if (this.circlingTime < this.maxCirclingTime && distance <= 25.0D) {
                this.circlingTime++;
                Vec3 orbitPos = this.getCirclePos(target);
                this.shark.getNavigation().moveTo(orbitPos.x, orbitPos.y, orbitPos.z, 1.0D);
                if (this.circlingTime % 60 == 0) {
                    this.shark.playSound(ReefSoundEvents.SHARK_WARN.get(), 1.0F, this.soundPitch + this.shark.getRandom().nextFloat() * 0.2F);
                }
            }
            else {
                this.lookAtTarget(target, 30.0F, 30.0F);
                if (!this.playedAttackSound) {
                    this.shark.playSound(ReefSoundEvents.SHARK_ATTACK.get(), 1.25F, this.soundPitch + this.shark.getRandom().nextFloat() * 0.2F);
                    this.playedAttackSound = true;
                }
                this.shark.setSprinting(true);
                this.shark.getNavigation().moveTo(target, 1.7D);
                double distanceSqr = this.shark.distanceToSqr(target);
                if (distanceSqr <= this.getAttackReachSqr(target, 1.5D)) {
                    this.circlingTime = 0;
                    this.shark.setAttacking(true);
                }
            }
            if (distance > 25.0D) {
                this.shark.getNavigation().moveTo(target, 1.25D);
            }
        }

        protected void tickAttack(LivingEntity target) {
            this.timer++;
            if (this.timer == 5) {
                if (this.isInAttackRange(target, 1.25D)) {
                    if (this.targetWearingChainmail(target)) {
                        float strength = (float) this.shark.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
                        target.knockback(strength * 0.5F, Mth.sin(this.shark.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(this.shark.getYRot() * ((float) Math.PI / 180F)));
                        target.hurtMarked = true;
                        this.shark.level().playSound(null, target.blockPosition(), SoundEvents.CHAIN_BREAK, SoundSource.NEUTRAL, 1.0F, 0.9F + target.getRandom().nextFloat() * 0.25F);
                    }
                    else {
                        this.shark.doHurtTarget(target);
                    }
                }
            }
            if (this.timer > 10) {
                this.timer = 0;
                this.playedAttackSound = false;
                this.clockwise = this.shark.getRandom().nextBoolean();
                this.shark.setAttacking(false);
                this.shark.setSprinting(false);
            }
        }

        private boolean targetWearingChainmail(LivingEntity target) {
            return target.getItemBySlot(EquipmentSlot.HEAD).is(Items.CHAINMAIL_HELMET) &&
                    target.getItemBySlot(EquipmentSlot.CHEST).is(Items.CHAINMAIL_CHESTPLATE) &&
                    target.getItemBySlot(EquipmentSlot.LEGS).is(Items.CHAINMAIL_LEGGINGS) &&
                    target.getItemBySlot(EquipmentSlot.FEET).is(Items.CHAINMAIL_BOOTS);
        }

        private Vec3 getCirclePos(LivingEntity target) {
            float angle = (0.0174532925F * (this.clockwise ? -this.circlingTime : this.circlingTime));
            double extraX = this.circleDistance * Mth.sin(angle);
            double extraZ = this.circleDistance * Mth.cos(angle);
            return new Vec3(target.getX() + 0.5F + extraX, target.getEyeY(), target.getZ() + 0.5F + extraZ);
        }
    }

    protected static class SharkRotateGoal extends Goal {

        private final Shark shark;

        public SharkRotateGoal(Shark shark) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
            this.shark = shark;
        }

        @Override
        public boolean canUse() {
            return this.shark.getRotatedTicks() > 0;
        }

        @Override
        public boolean canContinueToUse() {
            return this.shark.getRotatedTicks() > 0;
        }

        @Override
        public void start() {
            this.shark.setAttacking(false);
            this.shark.setJumping(false);
            this.shark.getNavigation().stop();
            this.shark.getMoveControl().setWantedPosition(this.shark.getX(), this.shark.getY(), this.shark.getZ(), 0.0D);
            this.shark.stopFollowing();
        }

        @Override
        public void tick() {
            if (this.shark.getNavigation().getPath() != null) {
                this.shark.getNavigation().stop();
            }
            if (this.shark.getMoveControl().hasWanted()) {
                this.shark.getMoveControl().setWantedPosition(this.shark.getX(), this.shark.getY(), this.shark.getZ(), 0.0D);
            }
        }
    }
}