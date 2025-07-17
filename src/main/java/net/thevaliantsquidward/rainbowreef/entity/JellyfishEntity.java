package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.base.RRMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefSounds;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class JellyfishEntity extends RRMob implements Bucketable {

    public float xBodyRot;
    public float xBodyRotO;
    public float zBodyRot;
    public float zBodyRotO;
    public float tentacleMovement;
    public float oldTentacleMovement;
    public float tentacleAngle;
    public float oldTentacleAngle;
    private float speed;
    private float tentacleSpeed;
    private float rotateSpeed;
    private float tx;
    private float ty;
    private float tz;

    public final net.minecraft.world.entity.AnimationState swimAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState landAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(JellyfishEntity.class, EntityDataSerializers.INT);

    public JellyfishEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 50, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
        this.random.setSeed((long)this.getId());
        this.tentacleSpeed = 3.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
        //        this number ^ deterimnes how often the jelly boosts
    }

    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new JelRandomMovementGoal(this));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .build();
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (SCALE.equals(pKey)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(pKey);
    }

    public EntityDimensions getDimensions(Pose pPose) {
        return super.getDimensions(pPose).scale(getScale(this.getModelScale()));
    }

    private static float getScale(int scale) {
        switch (scale) {
            case 1:
                return 1.8F;
            default:
                return 0.9F;
        }
    }

    public int getModelScale() {
        return this.entityData.get(SCALE);
    }

    public void setScale(int scale) {
        this.entityData.set(SCALE, scale);
    }

    public static <T extends Mob> boolean canSpawn(EntityType<JellyfishEntity> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }


    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(JellyfishEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(JellyfishEntity.class, EntityDataSerializers.INT);

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "orange";
            case 2 -> "white";
            case 3 -> "yellow";
            case 4 -> "muddy"; //r
            case 5 -> "abyssal"; //r
            case 6 -> "cherry"; //r
            case 7 -> "minty"; //r
            case 8 -> "azure"; //r
            case 9 -> "red";
            default -> "pink";
        };
    }
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();
        if(rare <= 0.10) {
            if(rareVariantChange <= 0.20F){
                this.setVariant(4);
            }else
            if(rareVariantChange <= 0.40F){
                this.setVariant(5);
            }else
            if(rareVariantChange <= 0.60F){
                this.setVariant(6);
            }else
            if(rareVariantChange <= 0.80F){
                this.setVariant(7);
            }else
            {
                this.setVariant(8);
            }
        } else

        if(variantChange <= 0.20F){
            this.setVariant(9);
        }else if(variantChange <= 0.40F){
            this.setVariant(3);
        }else if(variantChange <= 0.60F){
            this.setVariant(2);
        }else if(variantChange <= 0.80F){
            this.setVariant(1);
        } else{
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    @Override
    public void tick() {
        if (!this.hasCustomName()) {
            this.setScale(0);
        } else {
            if(!this.getCustomName().getString().toLowerCase().equals("squishy")){
                this.setScale(0);
            } else {
                if("squishy".equals(this.getName().getString().toLowerCase(Locale.ROOT)) && !this.isBaby()){
                    this.setScale(-3);
                }
            }
        }

        super.tick();

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }
    public void aiStep() {
        super.aiStep();


        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;

        if ((double)this.tentacleMovement > 6.283185307179586) {
            //                                number ^ is 2PI
            if (this.level().isClientSide) {
                this.tentacleMovement = 6.2831855F;
            } else {
                this.tentacleMovement -= 6.2831855F;
            }
        }

        if (this.isInWaterOrBubble()) {
            if (this.tentacleMovement < 3.1415927F) {
                //                    number ^ is PI
                //this part of the if cycle boosts the jellyfish when the tentaclemovement is at the correct position

                float extramovement = this.tentacleMovement / 3.1415927F;
                this.tentacleAngle = Mth.sin(extramovement * extramovement * 3.1415927F) * 3.1415927F * 0.25F;
                if ((double)extramovement > 0.75) {
                    this.speed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.speed *= 0.8F;
                //this represents drag as the jelly slows down
                this.rotateSpeed *= 0.99F;
            }

            if (!this.level().isClientSide) {
                this.setDeltaMovement((double)(this.tx * this.speed), (double)(this.ty * this.speed), (double)(this.tz * this.speed));
                //every tick, the entity's velocity is set to the direction vector times the velocity
            }

            Vec3 movement = this.getDeltaMovement();
            double HMovement = movement.horizontalDistance();
            this.yBodyRot += (-((float)Mth.atan2(movement.x, movement.z)) * 57.295776F - this.yBodyRot) * 0.1F;
            this.setYRot(this.yBodyRot);
            this.zBodyRot += 3.1415927F * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float)Mth.atan2(HMovement, movement.y)) * 57.295776F - this.xBodyRot) * 0.1F;

        } else {
            this.tentacleAngle = Mth.abs(Mth.sin(this.tentacleMovement)) * 3.1415927F * 0.25F;
            if (!this.level().isClientSide) {
                double VMovement = this.getDeltaMovement().y;
                if (this.hasEffect(MobEffects.LEVITATION)) {
                    VMovement = 0.05 * (double)(this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
                } else if (!this.isNoGravity()) {
                    VMovement -= 0.08;
                }

                this.setDeltaMovement(0.0, VMovement * 0.9800000190734863, 0.0);
            }

            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }

    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(SCALE, 0);
    }


    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.JELLYFISH_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }


    public void saveToBucketTag(@Nonnull ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putInt("BucketVariantTag", this.getVariant());
    }


    public void loadFromBucketTag(@Nonnull CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
        if (compound.contains("BucketVariantTag", 3)) {
            this.setVariant(compound.getInt("BucketVariantTag"));
        }
    }

    @Override
    @Nonnull
    protected InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level().isClientSide) {
            if(itemstack.getItem() == Items.GLASS_BOTTLE) {
                if(!player.isCreative()) {
                    itemstack.shrink(1);
                }
                this.spawnAtLocation(ReefItems.JELLYFISH_JELLY.get());
                this.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    public void playerTouch(Player pEntity) {

        if (pEntity instanceof ServerPlayer && pEntity.hurt(this.damageSources().mobAttack(this), (float)(2))) {
            this.playSound(ReefSounds.JELLYZAP.get(), 1.0F, 1.0F);
            pEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }

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
        compound.putInt("scale", this.getModelScale());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.setFromBucket(compound.getBoolean("FromBucket"));
        this.setScale(Math.min(compound.getInt("scale"), 0));
    }

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean p_203706_1_) {
        this.entityData.set(FROM_BUCKET, p_203706_1_);
    }

    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }



    public MobType getMobType() {
        return MobType.WATER;
    }

    public void travel(Vec3 pTravelVector) {

        this.move(MoverType.SELF, this.getDeltaMovement());
        super.travel(pTravelVector);
    }

    public void setMovementVector(float pTx, float pTy, float pTz) {
        this.tx = pTx;
        this.ty = pTy;
        this.tz = pTz;
    }

    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }







    class JelRandomMovementGoal extends Goal {
        private final JellyfishEntity squid;

        public JelRandomMovementGoal(JellyfishEntity pSquid) {
            this.squid = pSquid;
        }

        public boolean canUse() {
            return true;
        }

        public void tick() {
            int pauseTime = this.squid.getNoActionTime();
            if (pauseTime > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.wasTouchingWater || !this.squid.hasMovementVector()) {
                float randomMotion = this.squid.getRandom().nextFloat() * 6.2831855F;
                float randX = Mth.cos(randomMotion) * 0.2F;
                float randY = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
                float randZ = Mth.sin(randomMotion) * 0.2F;
                this.squid.setMovementVector(randX, randY, randZ);
                //the goal modifies the angle of the squid, using setMovementVector to set the direction of the jelly by scaling each dimension's movement vector
            }

        }
    }
















    protected SoundEvent getAmbientSound() {
        return SoundEvents.TROPICAL_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.TROPICAL_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_28281_) {
        return ReefSounds.JELLYHIT.get();
    }


    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

}
