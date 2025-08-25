package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goalz.*;
import net.thevaliantsquidward.rainbowreef.entity.base.DancingEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;

public class ArrowCrab extends Crab implements Bucketable, DancesToJukebox {

    //crabbing about
    //the crabby beast
    //crabbed to meet you

    public final net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState danceAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState walkAnimationState = new net.minecraft.world.entity.AnimationState();

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ArrowCrab.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ArrowCrab.class, EntityDataSerializers.INT);

    public ArrowCrab(EntityType<? extends DancingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.ARROW_CRAB_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Override
    public void saveToBucketTag(@Nonnull ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
    }

    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
        if (compound.contains("BucketVariantTag", 3)) {
            this.setVariant(compound.getInt("BucketVariantTag"));
        }
    }

    @Override
    @Nonnull
    public InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }


    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, Integer.valueOf(variant));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("FromBucket", this.fromBucket());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.setFromBucket(compound.getBoolean("FromBucket"));
    }


    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean p_203706_1_) {
        this.entityData.set(FROM_BUCKET, p_203706_1_);
    }

    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }


    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "red";
            default -> "yellowlined";
        };
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @javax.annotation.Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();

        if (variantChange <= 0.10F) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ARMOR, 2.0D)
                .build();
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    //  public void spawnChildFromBreeding(ServerLevel pLevel, Animal pMate) {
    //      ItemStack itemstack = new ItemStack(Items.SNIFFER_EGG);
    //      ItemEntity itementity = new ItemEntity(pLevel, this.position().x(), this.position().y(), this.position().z(), itemstack);
    //      itementity.setDefaultPickUpDelay();
    //      this.finalizeSpawnChildFromBreeding(pLevel, pMate, (AgeableMob)null);
    //      this.playSound(SoundEvents.SNIFFER_EGG_PLOP, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.5F);
    //      pLevel.addFreshEntity(itementity);
    //  }

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
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
    }

    protected PathNavigation createNavigation(Level worldIn) {
        CrabPathfinder crab = new CrabPathfinder(this, worldIn) {
            public boolean isStableDestination(BlockPos pos) {
                return this.level.getBlockState(pos).getFluidState().isEmpty();
            }
        };
        return crab;
    }

    public void travel(Vec3 travelVector) {

        if (this.isDancing()) {
            travelVector = Vec3.ZERO;
        }

        if (this.isEffectiveAi() && this.isInWater() && !this.isDancing()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            if (this.jumping) {
                this.setDeltaMovement(this.getDeltaMovement().scale(1.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.72D, 0.0D));
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.08D, 0.0D));
            }

        } else {
            super.travel(travelVector);
        }

    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isDancing() && this.getJukeboxPos() != null) {
            this.getLookControl().setLookAt(getJukeboxPos().getCenter());
        }

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
        this.walkAnimationState.animateWhen(this.isAlive() && this.walkAnimation.isMoving(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isAlive() && (this.isDancing() && this.getJukeboxPos() != null), this.tickCount);
    }


    public static boolean canSpawn(EntityType<ArrowCrab> arrowCrabEntityEntityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos p_223364_3_, RandomSource randomSource) {
        return !levelAccessor.getBlockState(p_223364_3_).isSolid();
    }

}
