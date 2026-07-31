package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishAvoidEntityGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishNibbleBlockGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishPanicGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.LevelReader;

public class Wrasse extends ReefMob {

    public Wrasse(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 7, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 7);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.85F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FishPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new FishAvoidEntityGoal<>(this, Player.class, 8.0F, 1.5D));
        this.goalSelector.addGoal(3, new FishNibbleBlockGoal(this, 15, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(4, new SwimWanderGoal(this, 1.0D, 40, 70));
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (this.getRandom().nextFloat() < 0.3F) {
            return super.getWalkTargetValue(pos, level);
        }
        return this.getDepthPathfindingFavor(pos, level);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.WRASSE_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/wrasse/wrasse_greenbird.png");
    }
}