package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goalz.*;
import net.thevaliantsquidward.rainbowreef.entity.base.DancingEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Month;

public class Crab extends DancingEntity implements Bucketable, DancesToJukebox {

    // crabbing about
    // the crabby beast
    // crabbed to meet you

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Crab.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Crab.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();

    public Crab(EntityType<? extends DancingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
        if (this.isDancing() && this.getJukeboxPos() != null) {
            this.getLookControl().setLookAt(getJukeboxPos().getCenter());
        }
    }

    private void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive() && !this.isDancing(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isDancing(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.CRAB_BUCKET.get());
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
            case 1 -> "halloween";
            case 2 -> "ghost";
            case 3 -> "sally";
            case 4 -> "emerald";
            case 5 -> "blue";
            case 6 -> "purple";
            case 7 -> "candy";
            case 8 -> "redghost";
            default -> "vampire";
        };
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @javax.annotation.Nullable CompoundTag dataTag) {
        Holder<Biome> holder = worldIn.getBiome(this.blockPosition());
        float spookyVariantChange = this.getRandom().nextFloat();
        LocalDate currentDate = LocalDate.now();
        float variantChange = this.getRandom().nextFloat();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {

            if (spookyVariantChange <= 0.33) {
                this.setVariant(1);
            } else if (spookyVariantChange <= 0.66) {
                this.setVariant(2);
            } else if (spookyVariantChange <= 0.10) {
                this.setVariant(8);
            } else
                this.setVariant(0);
        } else if (holder.is(Biomes.MANGROVE_SWAMP)) {
            this.setVariant(1);
        } else if (holder.is(Biomes.BEACH) && variantChange <= 0.10) {
            this.setVariant(8);
        } else if (holder.is(Biomes.BEACH) && variantChange > 0.10) {
            this.setVariant(2);
        } else if (holder.is(Biomes.STONY_SHORE)) {
            this.setVariant(3);
        } else if (holder.is(Biomes.LUKEWARM_OCEAN) || holder.is(Biomes.DEEP_LUKEWARM_OCEAN)) {
            this.setVariant(4);
        } else if (holder.is(Biomes.OCEAN) || holder.is(Biomes.DEEP_OCEAN)) {
            this.setVariant(5);
        } else if (holder.is(Biomes.COLD_OCEAN) || holder.is(Biomes.DEEP_COLD_OCEAN)) {
            this.setVariant(6);
        } else if (holder.is(Biomes.WARM_OCEAN)) {
            this.setVariant(7);
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
        this.goalSelector.addGoal(0, new FishDigGoal(this, 120, RRTags.HOG_DIGGABLE));
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

    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return SoundEvents.TROPICAL_FISH_HURT;
    }

    public static <T extends Mob> boolean canSpawn(EntityType<Crab> p_223364_0_, LevelAccessor levelAccessor, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return !levelAccessor.getBlockState(p_223364_3_).isSolid();
    }
}
