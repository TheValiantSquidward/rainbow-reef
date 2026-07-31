package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LargeShark extends Shark {

    public LargeShark(EntityType<? extends Shark> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 4, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    public int getMaxSchoolSize() {
        return 1;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SharkRotateGoal(this));
        this.goalSelector.addGoal(1, new SharkAttackGoal(this, 0.75F));
        this.goalSelector.addGoal(2, new SwimWanderGoal(this, 1.0D, 10, 20, 7, 4, 30));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return LargeShark.this.getRotatedTicks() <= 0 && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return LargeShark.this.getRotatedTicks() <= 0 && super.canContinueToUse();
            }
        });
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 200, true, true, this::canAttackLowHealthTargets) {
            @Override
            public boolean canUse() {
                return LargeShark.this.getRotatedTicks() <= 0 && super.canUse();
            }
            @Override
            public boolean canContinueToUse() {
                return LargeShark.this.getRotatedTicks() <= 0 && super.canContinueToUse();
            }
        });
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.LARGE_SHARK_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/large_shark/large_shark_bull.png");
    }
}