package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CustomizableRandomSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MahiMahi extends VariantSchoolingFish implements Bucketable {

    public MahiMahi(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new CustomizableRandomSwimGoal(this, 1, 10));
        this.goalSelector.addGoal(3, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TANG_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/mahi_mahi/mahi_mahi.png");
    }
}
