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
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goalz.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.LocateNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.MoveToNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.NemHoster;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.RestInNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.VariantEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Clownfish extends NemHoster implements Bucketable, VariantEntity {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Clownfish.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Clownfish.class, EntityDataSerializers.INT);

    int nemSearchCooldown;

    public final net.minecraft.world.entity.AnimationState swimAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState landAnimationState = new net.minecraft.world.entity.AnimationState();

    public Clownfish(EntityType<? extends NemHoster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 200, 4, 600, 200);

        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }


    protected PathNavigation createNavigation(Level p_27480_) {
        return new WaterBoundPathNavigation(this, p_27480_);
    }

    public static <T extends Mob> boolean canSpawn(EntityType<Clownfish> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }
    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "blackandwhite";
            case 2 -> "maroon";
            case 3 -> "pinkskunk";
            case 4 -> "clarkii";
            case 5 -> "blizzard"; //r
            case 6 -> "tomato";
            case 7 -> "bluestrain"; //a
            case 8 -> "madagascar";
            case 9 -> "oman"; //r
            case 10 -> "allard";
            case 11 -> "mocha"; //r
            case 12 -> "whitesnout"; //r
            case 13 -> "goldnugget"; //r
            case 14 -> "redsaddleback";
            case 15 -> "snowstorm"; //r
            case 16 -> "orangeskunk"; //r
            case 17 -> "domino"; //r
            case 18 -> "yellowclarkii"; //r
            case 19 -> "naked"; //r


            default -> "ocellaris";
        };
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    public void tick() {
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(0, 0, 0);
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
        this.swimAnimationState.animateWhen(this.isAlive() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
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
        ItemStack stack = new ItemStack(ReefItems.CLOWNFISH_BUCKET.get());
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

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();
        if(variantChange <= 0.001){
            this.setVariant(7);
        } else
        if (variantChange <= 0.13F) {
            this.setVariant(1);
        } else
        if (variantChange <= 0.26F) {
            this.setVariant(2);
        } else
        if (variantChange <= 0.39F) {
            this.setVariant(3);
        } else
        if (variantChange <= 0.42F) {
            this.setVariant(4);
        } else
        if (variantChange <= 0.55F) {
            this.setVariant(6);
        } else
        if (variantChange <= 0.60F) {
            this.setVariant(8);
        } else
        if (variantChange <= 0.81F) {
            this.setVariant(10);
        } else
        if (variantChange <= 0.94F) {
            this.setVariant(14);
        }else{
            this.setVariant(0);
        }
        if(rare <= 0.05){
            if (rareVariantChange <= 0.10F) {
                this.setVariant(9);
            } else
            if (rareVariantChange <= 0.20F) {
                this.setVariant(11);
            } else
            if (rareVariantChange <= 0.30F) {
                this.setVariant(12);
            } else {
            if (rareVariantChange <= 0.40F) {
                    this.setVariant(13);
           } else
           if (rareVariantChange <= 0.50F) {
                    this.setVariant(15);
           } else
           if (rareVariantChange <= 0.60F) {
                    this.setVariant(16);
           } else
           if (rareVariantChange <= 0.70F) {
                    this.setVariant(17);
           } else
           if (rareVariantChange <= 0.80F) {
               this.setVariant(18);
           } else
           if (rareVariantChange <= 0.90F) {
               this.setVariant(19);
           }else{
                    this.setVariant(5);
                }
            }
        }

        findAndSetNems();

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    public MobType getMobType() {
        return MobType.WATER;
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 0.8D, 40));

        this.goalSelector.addGoal(0, new LocateNemGoal(this, 200));
        this.goalSelector.addGoal(0, new MoveToNemGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new RestInNemGoal(this, 3, 600, 200));
        //Anemone seeker goal plan:
        //priority of 0, but only works if the clown has a home nem and is over 10 blocks from it
        //Pathfinds back to home nem and makes it hide for 3 - 5 secs
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