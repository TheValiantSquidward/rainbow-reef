package net.thevaliantsquidward.rainbowreef.entity.custom;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.StructureTags;
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ModEntities;
import net.thevaliantsquidward.rainbowreef.entity.custom.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.goalz.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.goalz.GroundseekingRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.VariantEntity;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import net.thevaliantsquidward.rainbowreef.util.MathHelpers;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class RayEntity extends VariantSchoolingFish implements GeoEntity, Bucketable, VariantEntity {

    //START of necessary IK shit
    public Vec3 rightRefPoint;
    public Vec3 rightRefOffset = new Vec3(1, 0, 0);

    public Vec3 leftRefPoint;
    public Vec3 leftRefOffset = new Vec3(-1, 0, 0);

    public Vec3 upRefPoint;
    public Vec3 upRefOffset = new Vec3(0, -1, 0);

    public Vec3 downRefPoint;
    public Vec3 downRefOffset = new Vec3(0, 1, 0);


    public Vec3 tail0Point;
    public Vec3 tail1Point;
    public Vec3 tail2Point;
    public Vec3 tail3Point;
    public Vec3 tail4Point;
    public Vec3 tail5Point;
    public Vec3 tail6Point;



    //Offset to the points relative to their parent point
    public Vec3 tail0Offset = new Vec3(0.0, 0.0, 0.47);
    public Vec3 tail1Offset = new Vec3(0.0, 0.0, 0.47);
    //technically the second segment's bone position offset, but affects the segment before it
    public Vec3 tail2Offset = new Vec3(0.0, 0.0, 0.47);
    public Vec3 tail3Offset = new Vec3(0.0, 0.0, 0.47);
    public Vec3 tail4Offset = new Vec3(0.0, 0.0, 0.47);
    public Vec3 tail5Offset = new Vec3(0.0, 0.0, 0.47);
    public Vec3 tail6Offset = new Vec3(0.0, 0.0, 0.47);
    //x = side to side offset
    //y = vert offset
    //z = fore to back offset(pos is back)

    //END of necessary IK shit


    public double tail1OldY;
    public double tail2OldY;
    public double tail3OldY;
    public double tail4OldY;
    public double tail5OldY;
    public double oldBodyPitch;
    public double tail1OldPitch;
    public double tail2OldPitch;
    public double tail3OldPitch;
    public double tail4OldPitch;
    public double tail5OldPitch;


    public double tail1Angle;
    public double tail2Angle;
    public double tail3Angle;
    public double tail4Angle;
    public double tail5Angle;
    public double bodyPitch;
    public double tail1Pitch;
    public double tail2Pitch;
    public double tail3Pitch;
    public double tail4Pitch;
    public double tail5Pitch;



    public int animTime;
    public double animSpeed = 1;


    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(RayEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(RayEntity.class, EntityDataSerializers.INT);

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "ornate";
            default -> "spotted";
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
        return 4;
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
        ItemStack stack = new ItemStack(ModItems.SPOTTED_EAGLE_RAY_BUCKET.get());
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
        if(variantChange <= 0.20F) {
            this.setVariant(4);
        } else if(variantChange <= 0.40F) {
            this.setVariant(3);
        } else if(variantChange <= 0.60F){
            this.setVariant(2);
        }else if(variantChange <= 0.80F){
            this.setVariant(1);
        }else{
            this.setVariant(0);
        }

        if (reason == MobSpawnType.CHUNK_GENERATION || reason == MobSpawnType.NATURAL
                //|| reason == MobSpawnType.SPAWN_EGG
        ) {
            float schoolsize = this.getRandom().nextFloat();
            int schoolcount = (int) ((this.getMaxSchoolSize() * schoolsize));

            if (schoolcount > 0 && !this.level().isClientSide()) {
                for (int i = 0; i < schoolcount; i++) {
                    RayEntity urine = new RayEntity(ModEntities.RAY.get(), this.level());
                    urine.setVariant(this.getVariant());
                    urine.moveTo(this.getX(), this.getY(), this.getZ());
                    urine.startFollowing(this);
                    this.level().addFreshEntity(urine);
                }
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public RayEntity(EntityType<? extends VariantSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 90, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        leftRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(leftRefOffset), (double) -this.getYRot());
        rightRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(rightRefOffset), (double) -this.getYRot());
        upRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(upRefOffset), (double) -this.getYRot());
        downRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(downRefOffset), (double) -this.getYRot());

        tail0Point = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(tail0Offset), (double) -this.getYRot());
        tail1Point = MathHelpers.rotateAroundCenterFlatDeg(tail0Point, tail0Point.subtract(tail1Offset), (double) -this.getYRot());
        tail2Point = MathHelpers.rotateAroundCenterFlatDeg(tail1Point, tail1Point.subtract(tail2Offset), (double) -this.getYRot());
        tail3Point = MathHelpers.rotateAroundCenterFlatDeg(tail2Point, tail2Point.subtract(tail3Offset), (double) -this.getYRot());
        tail4Point = MathHelpers.rotateAroundCenterFlatDeg(tail3Point, tail3Point.subtract(tail4Offset), (double) -this.getYRot());
        tail5Point = MathHelpers.rotateAroundCenterFlatDeg(tail4Point, tail4Point.subtract(tail5Offset), (double) -this.getYRot());
        tail6Point = MathHelpers.rotateAroundCenterFlatDeg(tail5Point, tail5Point.subtract(tail6Offset), (double) -this.getYRot());

    }

    public void tick() {
        //START of IK
        //the entity rotations must be negativized because we want the points to be transformed relative to the entity

        if (this.isInWater()){
        //START of IK
            //the entity rotations must be negativized because we want the points to be transformed relative to the entity

            tail1OldY = tail1Angle;
            tail2OldY = tail2Angle;
            tail3OldY = tail3Angle;
            tail4OldY = tail4Angle;
            tail5OldY = tail5Angle;

            //tail1OldPitch = tail1Pitch;
            //tail2OldPitch = tail2Pitch;
            //tail3OldPitch = tail3Pitch;
            //tail4OldPitch = tail4Pitch;
            //tail5OldPitch = tail5Pitch;

            tail1Angle = (MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(this.tail1Point, this.tail0Point, this.tail2Point, this.leftRefPoint, this.rightRefPoint), Mth.PI*0.75));
            tail2Angle = (MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(this.tail2Point, this.tail1Point, this.tail3Point, this.leftRefPoint, this.rightRefPoint), Mth.PI*0.75));
            tail3Angle = (MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(this.tail3Point, this.tail2Point, this.tail4Point, this.leftRefPoint, this.rightRefPoint), Mth.PI*0.75));
            tail4Angle = (MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(this.tail4Point, this.tail3Point, this.tail5Point, this.leftRefPoint, this.rightRefPoint), Mth.PI*0.75));
            tail5Angle = (MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(this.tail5Point, this.tail4Point, this.tail6Point, this.leftRefPoint, this.rightRefPoint), Mth.PI*0.75));

            //tail1Pitch = ((float) (MathHelpers.angleFromYdiff(this.position(), this.tail0Point, this.tail1Point)));
            //tail2Pitch = ((float) (Mth.PI * MathHelpers.angleFromYdiff(this.tail1Point, this.tail2Point, this.tail3Point)));
            //tail3Pitch = ((float) (Mth.PI * MathHelpers.angleFromYdiff(this.tail2Point, this.tail3Point, this.tail4Point)));
            //tail4Pitch = ((float) (Mth.PI * MathHelpers.angleFromYdiff(this.tail3Point, this.tail4Point, this.tail5Point)));
            //tail5Pitch = ((float) (Mth.PI * MathHelpers.angleFromYdiff(this.tail4Point, this.tail5Point, this.tail6Point)));

            tail0Point = MathHelpers.rotateAroundCenter3dDeg(this.position(), this.position().subtract(tail0Offset), -this.getYRot(), -this.getXRot());
            tail1Point = MathHelpers.rotateAroundCenter3dDeg(tail0Point, tail0Point.subtract(tail1Offset), -MathHelpers.angleTo(tail0Point, tail1Point).y, -MathHelpers.angleTo(tail0Point, tail1Point).x);
            tail2Point = MathHelpers.rotateAroundCenter3dDeg(tail1Point, tail1Point.subtract(tail2Offset), -MathHelpers.angleTo(tail1Point, tail2Point).y, -MathHelpers.angleTo(tail1Point, tail2Point).x);
            tail3Point = MathHelpers.rotateAroundCenter3dDeg(tail2Point, tail2Point.subtract(tail3Offset), -MathHelpers.angleTo(tail2Point, tail3Point).y, -MathHelpers.angleTo(tail2Point, tail3Point).x);
            tail4Point = MathHelpers.rotateAroundCenter3dDeg(tail3Point, tail3Point.subtract(tail4Offset), -MathHelpers.angleTo(tail3Point, tail4Point).y, -MathHelpers.angleTo(tail3Point, tail4Point).x);
            tail5Point = MathHelpers.rotateAroundCenter3dDeg(tail4Point, tail4Point.subtract(tail5Offset), -MathHelpers.angleTo(tail4Point, tail5Point).y, -MathHelpers.angleTo(tail4Point, tail5Point).x);
            tail6Point = MathHelpers.rotateAroundCenter3dDeg(tail5Point, tail5Point.subtract(tail6Offset), -MathHelpers.angleTo(tail5Point, tail6Point).y, -MathHelpers.angleTo(tail5Point, tail6Point).x);

            /*if (!this.level().isClientSide()) {
                ServerLevel llel = (ServerLevel) this.level();

                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail0Point.x), (tail0Point.y), (tail0Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail1Point.x), (tail1Point.y), (tail1Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail2Point.x), (tail2Point.y), (tail2Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail3Point.x), (tail3Point.y), (tail3Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail4Point.x), (tail4Point.y), (tail4Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail5Point.x), (tail5Point.y), (tail5Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                llel.sendParticles(ParticleTypes.BUBBLE_POP, (tail6Point.x), (tail6Point.y), (tail6Point.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }*/


            //side refs don't move vertically
            leftRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(leftRefOffset), (double) -this.getYRot());
            rightRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(rightRefOffset), (double) -this.getYRot());
            upRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(upRefOffset), (double) -this.getYRot());
            downRefPoint = MathHelpers.rotateAroundCenterFlatDeg(this.position(), this.position().subtract(downRefOffset), (double) -this.getYRot());
            //END of IK


            if(this.level().isClientSide()) {
                if (this.animTime == (int)(8 * 20 / (this.animSpeed))) {
                    this.animTime = 0;
                    this.animSpeed = 0.5 + (1 * Math.random());
                    //animation speed ranges from 0.5 times, to 1.5 times)

                } else {
                    this.animTime++;
                }
            }
        }

        super.tick();
    }

    @Override
    public boolean isNoGravity() {
        return this.isInWater();
    }

    protected PathNavigation createNavigation(Level p_27480_) {
        return new WaterBoundPathNavigation(this, p_27480_);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6D)
                .add(Attributes.MOVEMENT_SPEED, 2D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.8, 1, 20, 20, 2));
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 5, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        if(this.isUnderWater()) {
            geoAnimatableAnimationState.getController().setAnimationSpeed(1);
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("swimming", Animation.LoopType.LOOP));
        } else {
            geoAnimatableAnimationState.getController().setAnimationSpeed(1);
            geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("stranded", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }
    public static <T extends Mob> boolean canSpawn(EntityType<RayEntity> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


}