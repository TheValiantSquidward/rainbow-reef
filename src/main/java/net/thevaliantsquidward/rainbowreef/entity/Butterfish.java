package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.VariantEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Butterfish extends VariantSchoolingFish implements Bucketable, VariantEntity {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Butterfish.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Butterfish.class, EntityDataSerializers.INT);

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "easterisland"; //r
            case 2 -> "threadfin";
            case 3 -> "banner";
            case 4 -> "bluecheek";
            case 5 -> "longnose";
            case 6 -> "spotfin";
            case 7 -> "hooded";
            case 8 -> "arabic";
            case 9 -> "pyramid";
            case 10 -> "redsea";
            case 11 -> "sixspined";
            case 12 -> "darklongnose"; //r
            case 13 -> "saddleback";
            case 14 -> "african";
            case 15 -> "eritrean";
            case 16 -> "marginated";
            case 17 -> "thompson";
            case 18 -> "mullers";
            case 19 -> "wroughtiron";
            case 20 -> "foureye";

            default -> "copperbanded";
        };
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    public int getMaxSchoolSize() {
        return 10;
    }

    public void tick() {
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(0,0,0);
            this.setDeltaMovement(this.getDeltaMovement().add(((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), 0.4F, ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(SoundEvents.COD_FLOP, this.getSoundVolume(), this.getVoicePitch());
            //use this stuff for fish flopping
        }

        super.tick();

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    public int variant() {
        return getVariant();
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
        ItemStack stack = new ItemStack(ReefItems.BUTTERFISH_BUCKET.get());
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
    public static <T extends Mob> boolean canSpawn(EntityType<Butterfish> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();
        Holder<Biome> holder = worldIn.getBiome(this.blockPosition());
        if (holder.is(Biomes.MANGROVE_SWAMP)) {
            if(variantChange <= 0.5) {
                this.setVariant(11);
            }
            else {
                this.setVariant(20);
            }
        }
        else if(rare <= 0.10) {
            if(rareVariantChange <= 0.50F){
                this.setVariant(1);
            }else
            {
                this.setVariant(12);
            }
        } else
        if(variantChange <= 0.05F){
            this.setVariant(2);
        }else if(variantChange <= 0.10F){
            this.setVariant(3);
        }else if(variantChange <= 0.15F){
            this.setVariant(4);
        }else if(variantChange <= 0.20F){
            this.setVariant(5);
        }else if(variantChange <= 0.25F){
            this.setVariant(6);
        }else if(variantChange <= 0.30F){
            this.setVariant(7);
        }else if(variantChange <= 0.35F){
            this.setVariant(8);
        }else if(variantChange <= 0.40F){
            this.setVariant(9);
        }else if(variantChange <= 0.45F){
            this.setVariant(10);
        }else if(variantChange <= 0.50F){
            this.setVariant(13);
        }else if(variantChange <= 0.55F){
            this.setVariant(14);
        }else if(variantChange <= 0.60F){
            this.setVariant(15);
        }else if(variantChange <= 0.65F){
            this.setVariant(16);
        }else if(variantChange <= 0.70F){
            this.setVariant(17);
        }else if(variantChange <= 0.75F){
            this.setVariant(18);
        }else if(variantChange <= 0.80F){
            this.setVariant(19);
        }else if(variantChange <= 0.85F){
            this.setVariant(20);
        } else{
            this.setVariant(0);
        }



        if (this.getRandom().nextFloat() >= 0.80) {
            if (reason == MobSpawnType.CHUNK_GENERATION || reason == MobSpawnType.NATURAL
                //|| reason == MobSpawnType.SPAWN_EGG
            ) {
                float schoolsize = this.getRandom().nextFloat();
                int schoolcount = (int) ((this.getMaxSchoolSize() * schoolsize));

                if (schoolcount > 0 && !this.level().isClientSide()) {
                    for (int i = 0; i < schoolcount; i++) {
                        Tang urine = new Tang(ReefEntities.BUTTERFISH.get(), this.level());
                        urine.setVariant(this.getVariant());
                        urine.moveTo(this.getX(), this.getY(), this.getZ());
                        urine.startFollowing(this);
                        this.level().addFreshEntity(urine);
                    }
                }
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public Butterfish(EntityType<? extends VariantSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 600);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    protected PathNavigation createNavigation(Level level) {
        return new WaterBoundPathNavigation(this, level);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6D)
                .add(Attributes.MOVEMENT_SPEED, 1D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 15, RRTags.BUTTERFLY_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.9, 1, 20, 20, 3, false));
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEyeInFluid(FluidTags.WATER) && this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.001, 0.0));
        }
        //checks if the fish is moving underwater, and gives it a little lift to prevent it from getting stuck at the ledges of blocks
        //must be applied independently to each fish

        super.travel(pTravelVector);
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