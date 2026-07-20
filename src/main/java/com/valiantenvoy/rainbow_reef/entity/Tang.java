package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishDigGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Tang extends VariantSchoolingFish {

    private static final float MAX_TILT = 85.0F;
    private static final float MAX_ROLL = 15.0F;
    private static final float ROLL_PER_YAW = 2.0F;

    public Tang(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 6, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 6);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(2, new FishDigGoal(this, 10, 200, ReefTags.TANG_DIET));
        this.goalSelector.addGoal(3, new SwimWanderGoal(this, 1.0D, 40));
        this.goalSelector.addGoal(4, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

    @Override
    public void tick() {
        super.tick();
        this.tickRotations(MAX_TILT, MAX_ROLL, ROLL_PER_YAW);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TANG_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/tang/tang_blue.png");
    }
}
