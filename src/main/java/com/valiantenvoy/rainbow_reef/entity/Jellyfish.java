package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Predicate;

public class Jellyfish extends ReefMob {

    private static final int PULSE_INTERVAL = 20;
    private static final double PULSE_FORCE = 0.16D;
    private static final float PITCH_LERP = 0.075F;

    private static final Predicate<LivingEntity> CAN_STING = (entity) -> {
        if (entity instanceof Player && ((Player) entity).isCreative()) {
            return false;
        }
        return !entity.isSpectator() && !(entity instanceof Jellyfish);
    };

    private int pulseCooldown = 0;

    public Jellyfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 20, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 20);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimWanderGoal(this, 1.0D, 100));
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return !effectInstance.is(MobEffects.POISON);
    }

    @Override
    public boolean shouldFlop() {
        return false;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.JELLYFISH_BUCKET.get());
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level().isClientSide) {
            if (itemstack.getItem() == Items.GLASS_BOTTLE) {
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }
                this.spawnAtLocation(ReefItems.JELLY_BOTTLE.get());
                this.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    protected void stingEntity(LivingEntity livingEntity) {
        if (livingEntity.hurt(this.damageSources().mobAttack(this), (float) Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).getValue())) {
            this.playSound(ReefSoundEvents.JELLYFISH_ZAP.get(), 0.5F, this.getRandom().nextFloat() * 0.2F + 1.0F);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive() && this.tickCount % 4 == 0) {
            for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.15D), CAN_STING)) {
                if (entity.isAlive()) {
                    this.stingEntity(entity);
                }
            }
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide && this.isAlive()) {
            if (this.pulseCooldown > 0) {
                this.pulseCooldown--;
            }
            if (this.isInWater() && this.pulseCooldown <= 0 && this.getMoveControl().hasWanted()) {
                this.setDeltaMovement(this.getDeltaMovement().add(this.getLookAngle().normalize().scale(PULSE_FORCE)));
                this.pulseCooldown = PULSE_INTERVAL;
            }
        }
    }

    @Override
    protected void updateSwimPitch() {
        this.prevSwimPitch = this.swimPitch;
        float target = this.getDeltaMovement().lengthSqr() > 1.0E-5D ? this.getXRot() + 90.0F : 0.0F;
        this.swimPitch += (target - this.swimPitch) * PITCH_LERP;
    }

    @Override
    public void setupAnimationStates() {
        boolean inWater = this.isInWaterOrBubble();
        boolean moving = this.getDeltaMovement().lengthSqr() > 1.0E-5D;
        this.swimAnimationState.animateWhen(inWater && moving, this.tickCount);
        this.swimIdleAnimationState.animateWhen(inWater && !moving, this.tickCount);
        this.flopAnimationState.animateWhen(!inWater, this.tickCount);
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSoundEvents.JELLYFISH_HURT.get();
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/jellyfish/jellyfish_pink.png");
    }
}
