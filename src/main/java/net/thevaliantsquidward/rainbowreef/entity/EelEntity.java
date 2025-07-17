package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goalz.GroundseekingRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.RRMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EelEntity extends RRMob implements Bucketable {


    public IKSolver tailKinematics;

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EelEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EelEntity.class, EntityDataSerializers.INT);

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();
    public final AnimationState breathAnimationState = new AnimationState();
    public final AnimationState noBreathAnimationState = new AnimationState();

    public EelEntity(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 100, 4, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        this.tailKinematics = new IKSolver(this, 4, 0.3, 0.5, true, false);
    }

    public void tick() {
        super.tick();
        this.tailKinematics.TakePerTickAction(this);

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEyeInFluid(FluidTags.WATER) && !this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.01, 0.0));
        }
        super.travel(pTravelVector);
    }

    private void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
        this.breathAnimationState.animateWhen(this.isAlive() && this.isInWaterOrBubble(), this.tickCount);
    }


    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    protected PathNavigation createNavigation(Level p_27480_) {
        return new WaterBoundPathNavigation(this, p_27480_);
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "green";
            case 2 -> "dragon";
            case 3 -> "laced";
            case 4 -> "fimbriated";
            case 5 -> "japanese_dragon";
            case 6 -> "mud";
            case 7 -> "fresh";
            default -> "green";
        };
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
        ItemStack stack = new ItemStack(ReefItems.EEL_BUCKET.get());
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
    protected InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    private void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("FromBucket", this.fromBucket());
    }

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
    public static <T extends Mob> boolean canSpawn(EntityType<EelEntity> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }
    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float aberrant = this.getRandom().nextFloat();
        //float aberrantVariantChange = this.getRandom().nextFloat();
        System.out.println(worldIn.getBiome(this.blockPosition()));
        System.out.println(worldIn.getBiome(this.blockPosition()).is(RRTags.EEL_FRESH));

        if (worldIn.getBiome(this.blockPosition()).is(RRTags.EEL_FRESH)) {

            if (variantChange < 0.5) {
                this.setVariant(6);
            } else {
                this.setVariant(7);
            }
        } else {
            if (variantChange <= 0.25F) {
                this.setVariant(1);
            } else if (variantChange <= 0.5F) {

                if (aberrant < 0.1) {
                    this.setVariant(5);
                } else {
                    this.setVariant(2);
                }

            } else if (variantChange <= 0.75F) {
                this.setVariant(3);
            } else {
                this.setVariant(4);
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 7D).add(Attributes.MOVEMENT_SPEED, 0.8D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new GroundseekingRandomSwimGoal(this, 1, 200, 10, 10, 0.01));
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

    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }
}
