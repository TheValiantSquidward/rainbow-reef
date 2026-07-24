package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.*;
import com.valiantenvoy.rainbow_reef.entity.base.Anemonefish;
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

public class Clownfish extends Anemonefish {

    public Clownfish(EntityType<? extends Anemonefish> entityType, Level level) {
        super(entityType, level, 200, 4, 600, 200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    protected void registerGoals() {
        // Anemone seeker goal plan:
        // priority of 0, but only works if the clown has a home nem and is over 10 blocks from it
        // Pathfinds back to home nem and makes it hide for 3 - 5 secs
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new RestInAnemoneGoal(this, 3, 600, 200));
        this.goalSelector.addGoal(4, new MoveToAnemoneGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new LocateAnemoneGoal(this, 200));
        this.goalSelector.addGoal(6, new FishNibbleBlockGoal(this, 10, 400, ReefTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(7, new SwimWanderGoal(this, 1, 40));
        this.goalSelector.addGoal(8, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CLOWNFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/clownfish/clownfish_clarkii.png");
    }
}