package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishAvoidEntityGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishPanicGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.JoinShoalGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.ShoalSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantShoalingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Fusilier extends VariantShoalingFish {

    public Fusilier(EntityType<? extends VariantShoalingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 8, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 8);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.95F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FishPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new FishAvoidEntityGoal<>(this, Player.class, 8.0F, 1.5D));
        this.goalSelector.addGoal(3, new ShoalSwimGoal(this));
        this.goalSelector.addGoal(4, new JoinShoalGoal(this));
    }

    @Override
    public int getMaxShoalSize() {
        return 16;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.FUSILIER_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/fusilier/fusilier_redbelly_yellowtail.png");
    }
}