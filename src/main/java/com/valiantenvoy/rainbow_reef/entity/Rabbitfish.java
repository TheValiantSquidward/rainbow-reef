package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishDigGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Rabbitfish extends ReefMob {

    public Rabbitfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 15, 600, ReefTags.BUTTERFLY_DIET));
        this.goalSelector.addGoal(4, new SwimWanderGoal(this, 1, 10));
    }

    @Override
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFLYFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/rabbitfish/rabbitfish_foxface.png");
    }
}