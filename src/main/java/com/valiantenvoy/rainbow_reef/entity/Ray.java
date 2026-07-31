package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.animation.BodyChain;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.entity.utils.BodyChainMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Ray extends VariantSchoolingFish implements BodyChainMob {

    private static final float PITCH_CLAMP = 45.0F;
    private static final float ROLL_CLAMP = 30.0F;

    private final BodyChain chain = new BodyChain(new float[]{0.22F, 0.2F, 0.18F, 0.17F, 0.16F}, new float[]{0.22F, 0.2F, 0.18F, 0.17F, 0.16F});

    public Ray(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 4, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.75F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new SwimWanderGoal(this, 1.0D, 50, 15, 7, 4));
        this.goalSelector.addGoal(3, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    @Override
    protected float getPitchClamp() {
        return PITCH_CLAMP;
    }

    @Override
    protected float getRollClamp() {
        return ROLL_CLAMP;
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
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.RAY_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/ray/ray_spotted.png");
    }
}