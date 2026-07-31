package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishPanicGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.network.ParticlePacket;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.FastColor;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

public class Boxfish extends ReefMob {

    public Boxfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.55F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FishPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new SwimWanderGoal(this, 1.0D, 120, 160));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
    }

    @Override
    protected void actuallyHurt(DamageSource source, float amount) {
        for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(3.25F))) {
            if (entity.isAlive()) {
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 80, 0), this);
            }
        }
        if (!this.level().isClientSide) {
            this.spawnParticles();
        }
        super.actuallyHurt(source, amount);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return !effectInstance.is(MobEffects.POISON);
    }

    private void spawnParticles() {
        ParticlePacket particlePacket = new ParticlePacket();
        for (int i = 0; i < 64; i++) {
            double xVelocity = this.getRandom().nextGaussian() * 0.8D;
            double yVelocity = this.getRandom().nextGaussian() * 0.8D;
            double zVelocity = this.getRandom().nextGaussian() * 0.8D;
            double xPos = this.position().x + this.getRandom().nextGaussian() * 0.8D;
            double yPos = this.position().y + this.getRandom().nextGaussian() * 0.8D;
            double zPos = this.position().z + this.getRandom().nextGaussian() * 0.8D;
            particlePacket.queueParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(255, 0x87a363)), xPos, yPos, zPos, xVelocity, yVelocity, zVelocity);
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            PacketDistributor.sendToPlayersTrackingChunk(serverLevel, new ChunkPos(this.blockPosition()), particlePacket);
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BOXFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/boxfish/boxfish_yellow.png");
    }
}