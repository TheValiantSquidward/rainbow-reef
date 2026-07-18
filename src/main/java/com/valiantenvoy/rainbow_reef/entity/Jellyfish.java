package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.JellyfishMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class Jellyfish extends JellyfishMob {

    public Jellyfish(EntityType<? extends JellyfishMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 10, 0.02F, 0.0F, false);
        this.lookControl = new JellyfishLookControl(this);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.55F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new SwimWanderGoal(this, 1, 60));
    }

    @Override
    public void playerTouch(Player player) {
        if (player instanceof ServerPlayer serverPlayer && serverPlayer.hurt(this.damageSources().mobAttack(this), 2)) {
            this.playSound(ReefSoundEvents.JELLYFISH_ZAP.get(), 1.0F, 1.0F);
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }
    }

    @Override
    public void tickFlopping() {
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
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
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
