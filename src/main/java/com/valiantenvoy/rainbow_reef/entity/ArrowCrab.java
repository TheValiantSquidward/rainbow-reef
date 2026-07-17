package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.ConditionalStopGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CrabBottomWander;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CrabFindWater;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CrabLeaveWater;
import com.valiantenvoy.rainbow_reef.entity.base.DancingEntity;
import com.valiantenvoy.rainbow_reef.entity.utils.DancesToJukebox;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;

public class ArrowCrab extends Crab implements DancesToJukebox {

    // crabbing about
    // the crabby beast
    // crabbed to meet you

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();

    public ArrowCrab(EntityType<? extends DancingEntity> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 0.0F);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ARMOR, 2.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new ConditionalStopGoal(this) {
            @Override
            public boolean canUse() {
                ArrowCrab crab = (ArrowCrab) getCreatura();
                //System.out.println(crab.isDancing());
                return crab.isDancing();
            }

            @Override
            public boolean canContinueToUse() {
                ArrowCrab crab = (ArrowCrab) getCreatura();
                return crab.isDancing();
            }
        });
        this.goalSelector.addGoal(1, new CrabFindWater(this));
        this.goalSelector.addGoal(1, new CrabLeaveWater(this));
        this.goalSelector.addGoal(3, new CrabBottomWander(this, 1.0D, 10, 50));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.ARROW_CRAB_BUCKET.get());
    }

    @Override
    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
        this.walkAnimationState.animateWhen(this.isAlive() && this.walkAnimation.isMoving(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isAlive() && (this.isDancing() && this.getJukeboxPos() != null), this.tickCount);
    }

    public static boolean canSpawn(EntityType<ArrowCrab> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.getBlockState(pos).isSolid();
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/arrow_crab/arrow_crab_red.png");
    }
}
