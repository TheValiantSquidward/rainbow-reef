package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishDigGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.RandomSleepyLookaroundGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.RandomSleepySwimGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
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

public class Parrotfish extends VariantSchoolingFish {

    public final AnimationState eepyAnimationState = new AnimationState();

    public Parrotfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 10, 800, ReefTags.PARROTFISH_DIET));
        this.goalSelector.addGoal(4, new RandomSleepySwimGoal(this, 1, 10));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
        this.goalSelector.addGoal(6, new RandomSleepyLookaroundGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public void setupAnimationStates() {
        super.setupAnimationStates();
        long roundedTime = this.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;

        this.eepyAnimationState.animateWhen(this.isInWaterOrBubble() && night, this.tickCount);
        if (night) {
            this.swimIdleAnimationState.stop();
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.PARROTFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/parrotfish/parrotfish_blue.png");
    }
}