package net.thevaliantsquidward.rainbowreef.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.goalz.CrabBottomWander;
import net.thevaliantsquidward.rainbowreef.entity.goalz.CrabFindWater;
import net.thevaliantsquidward.rainbowreef.entity.goalz.CrabLeaveWater;
import net.thevaliantsquidward.rainbowreef.entity.goalz.CrabPathfinder;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;

public class CrabEntity extends Animal implements GeoEntity, Bucketable {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    //crabbing about
    //the crabby beast
    //crabbed to meet you

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(TangEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(TangEntity.class, EntityDataSerializers.INT);



    public CrabEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
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
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ModItems.CRAB_BUCKET.get());
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
            default -> "vampire";
        };
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @javax.annotation.Nullable CompoundTag dataTag) {
        Holder<Biome> holder = worldIn.getBiome(this.blockPosition());
        if (holder.is(Biomes.MANGROVE_SWAMP)) {
            this.setVariant(1);
        } else if (holder.is(Biomes.BEACH)) {
            this.setVariant(2); 
        }  else if (holder.is(Biomes.STONY_SHORE)) {
            this.setVariant(3);
        }  else if (holder.is(Biomes.LUKEWARM_OCEAN)) {
            this.setVariant(4);
        } else if (holder.is(Biomes.OCEAN)) {
            this.setVariant(5);
        }  else if (holder.is(Biomes.COLD_OCEAN)) {
            this.setVariant(6);
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
        this.goalSelector.addGoal(1, new CrabFindWater(this));
        this.goalSelector.addGoal(1, new CrabLeaveWater(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.MANGROVE_PROPAGULE), false));
        this.goalSelector.addGoal(4, new CrabBottomWander(this, 1.0D, 10, 50));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));

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
        if (this.isEffectiveAi() && this.isInWater()) {
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 10, this::predicate));
    }
    private boolean partyCrab;
    private BlockPos jukebox;

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() || this.isPartyCrab();
    }

    public void aiStep() {
        if (this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level().getBlockState(this.jukebox).is(Blocks.JUKEBOX)) {
            this.partyCrab = false;
            this.jukebox = null;
        }


        super.aiStep();

    }
    public boolean isPartyCrab() {
        return this.partyCrab;
    }

    private static final RawAnimation CRAB_IDLE = RawAnimation.begin().thenLoop("animation.crab.idle");
    private static final RawAnimation CRAB_DANCE = RawAnimation.begin().thenLoop("animation.crab.dance");
    private static final RawAnimation CRAB_WALK = RawAnimation.begin().thenLoop("animation.crab.walk1");
    private static final RawAnimation CRAB_FEED = RawAnimation.begin().thenLoop("animation.crab.feed");

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        if(this.isPartyCrab()) {
            geoAnimatableAnimationState.setAndContinue(CRAB_DANCE);
            geoAnimatableAnimationState.getController().setAnimationSpeed(1.0D);
            return PlayState.CONTINUE;
        } else
        if (this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 ) {
            {
                geoAnimatableAnimationState.setAndContinue(CRAB_WALK);
                geoAnimatableAnimationState.getController().setAnimationSpeed(1.0D);
                return PlayState.CONTINUE;
            }
        } else {
            geoAnimatableAnimationState.setAndContinue(CRAB_IDLE);
            geoAnimatableAnimationState.getController().setAnimationSpeed(1.0D);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
