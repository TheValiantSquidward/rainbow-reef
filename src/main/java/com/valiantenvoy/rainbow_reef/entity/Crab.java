package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.*;
import com.valiantenvoy.rainbow_reef.entity.base.DancingEntity;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.entity.utils.DancesToJukebox;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import com.valiantenvoy.rainbow_reef.tags.ReefBlockTags;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class Crab extends DancingEntity implements DancesToJukebox {

    // crabbing about
    // the crabby beast
    // crabbed to meet you

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();

    public Crab(EntityType<? extends DancingEntity> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 0.0F);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ARMOR, 2.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new ConditionalStopGoal(this) {
            @Override
            public boolean canUse() {
                Crab crab = (Crab) getCreatura();
                return crab.isDancing();
            }

            @Override
            public boolean canContinueToUse() {
                Crab crab = (Crab) getCreatura();
                return crab.isDancing();
            }
        });
        this.goalSelector.addGoal(0, new FishDigGoal(this, 120, 700, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(1, new CrabFindWater(this));
        this.goalSelector.addGoal(1, new CrabLeaveWater(this));
        this.goalSelector.addGoal(2, new EnterBurrowGoal(this, 1.0D, ReefBlockTags.BURROWS));
        this.goalSelector.addGoal(3, new CrabBottomWander(this, 1, 10, 50));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new CrabPathfinder(Crab.this, level) {
            public boolean isStableDestination(BlockPos pos) {
                return this.level.getBlockState(pos).getFluidState().isEmpty();
            }
        };
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isDancing() && this.getJukeboxPos() != null) {
            this.getLookControl().setLookAt(getJukeboxPos().getCenter());
        }
    }

    @Override
    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive() && !this.isDancing(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isDancing(), this.tickCount);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CRAB_BUCKET.get());
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isDancing()) {
            travelVector = Vec3.ZERO;
        }
        if (this.isEffectiveAi() && this.isInWater() && !this.isDancing()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            if(this.jumping){
                this.setDeltaMovement(this.getDeltaMovement().scale(1.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.72D, 0.0D));
            }else{
                this.setDeltaMovement(this.getDeltaMovement().scale(0.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.08D, 0.0D));
            }

        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public void fishTravel(Vec3 travelVector) {
    }

    @Override
    public boolean shouldFlop() {
        return false;
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return ReefSoundEvents.CRAB_DEATH.get();
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSoundEvents.CRAB_HURT.get();
    }

    public static boolean canSpawn(EntityType<? extends ReefMob> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.getBlockState(pos).isSolid();
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/crab/crab_blue.png");
    }
}
