package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.EnterBurrowGoal;
import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import com.valiantenvoy.rainbow_reef.tags.ReefBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

// crabbing about
// the crabby beast
// crabbed to meet you

public class Crab extends ReefMob {

    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(Crab.class, EntityDataSerializers.BOOLEAN);

    public final SmoothAnimationState idleAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState walkAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState danceAnimationState = new SmoothAnimationState();

    public Crab(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 20, 0.8F, 1.0F, false);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WATER_BORDER, 0.0F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DANCING, false);
    }

    public boolean isDancing() {
        return this.entityData.get(DANCING);
    }
    public void setDancing(boolean dancing) {
        this.entityData.set(DANCING, dancing);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.18F)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.STEP_HEIGHT, 1.25D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new EnterBurrowGoal(this, 1.0D, ReefBlockTags.BURROWS));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public PathNavigation createNavigation(Level level) {
        return new GroundPathNavigation(this, level);
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isDancing()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.0D, 1.0D, 0.0D));
            super.travel(Vec3.ZERO);
        }
        else if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            float horizontalScale = this.onGround() ? 0.6F : 0.08F;
            this.setDeltaMovement(this.getDeltaMovement().multiply(horizontalScale, 0.6F, horizontalScale));
            if (this.jumping) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.3D, 0.0D));
            } else {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.07D, 0.0D));
            }
        }
        else {
            super.travel(travelVector);
        }
    }

    @Nullable
    protected BlockPos getNearbyJukebox() {
        BlockPos pos = this.blockPosition();
        for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-3, -1, -3), pos.offset(3, 1, 3))) {
            BlockState state = this.level().getBlockState(blockPos);
            if (state.is(Blocks.JUKEBOX) && state.hasProperty(JukeboxBlock.HAS_RECORD) && state.getValue(JukeboxBlock.HAS_RECORD)) {
                return blockPos.immutable();
            }
        }
        return null;
    }

    protected boolean isJukeboxNearby() {
        return this.getNearbyJukebox() != null;
    }

    protected boolean shouldDance() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.shouldDance() && !this.level().isClientSide && this.tickCount % 20 == 0) {
            this.setDancing(this.getLastHurtByMob() == null && this.isJukeboxNearby());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isDancing()) {
            this.jumping = false;
            this.xxa = 0.0F;
            this.zza = 0.0F;
            this.getNavigation().stop();
        }
    }

    @Override
    protected void handleAirSupply(int airSupply) {
        this.setAirSupply(300);
    }

    @Override
    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(!this.isDancing(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isDancing(), this.tickCount);
        this.walkAnimationState.animateWhen(!this.isDancing(), this.tickCount);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CRAB_BUCKET.get());
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

    @SuppressWarnings("deprecation")
    public static boolean canSpawn(EntityType<? extends ReefMob> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.getBlockState(pos).isSolid();
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/crab/crab_vampire.png");
    }
}
