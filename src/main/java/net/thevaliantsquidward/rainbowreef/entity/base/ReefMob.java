package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class ReefMob extends WaterAnimal implements Bucketable {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.INT);

    public float prevTilt;
    public float tilt;

    public float prevOnLandProgress;
    public float onLandProgress;

    public int feedColldownLimit;
    public int feedCooldown = 0;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public SmoothSwimmingMoveControl feedingController = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, false);

    protected ReefMob(EntityType<? extends WaterAnimal> entityType, Level level, int feedCooldown) {
        super(entityType, level);
        this.feedColldownLimit = feedCooldown;
        this.setFeedCooldown(this.feedColldownLimit + this.getRandom().nextInt(this.feedColldownLimit));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new AdvancedWaterboundPathNavigation(this, level);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
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

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
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

    @Override
    @Nonnull
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    public void setMoveControl(MoveControl newControl) {
        this.moveControl = newControl;
    }

    public void setFeedCooldown(int cooldown) {
        this.feedCooldown = cooldown;
    }

    public int getFeedCooldown() {
        return feedCooldown;
    }

    @Override
    public void tick() {
        super.tick();

        prevTilt = tilt;
        prevOnLandProgress = onLandProgress;

        if (this.level().isClientSide()){
            this.setupAnimationStates();
        }

        if (this.isInWater()) {
            final float v = Mth.degreesDifference(this.getYRot(), yRotO);
            if (Math.abs(v) > 1) {
                if (Math.abs(tilt) < 25) {
                    tilt -= Math.signum(v);
                }
            } else {
                if (Math.abs(tilt) > 0) {
                    final float tiltSign = Math.signum(tilt);
                    tilt -= tiltSign * 0.85F;
                    if (tilt * tiltSign < 0) {
                        tilt = 0;
                    }
                }
            }
        } else{
            tilt = 0;
        }

        this.feedCooldown--;

        this.tickFlopping();
    }

    // default animations
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    // flop here so it can be overridden if needed
    public void tickFlopping() {
        boolean onLand = !this.isInWaterOrBubble() && this.onGround();

        if (onLand && onLandProgress < 5F) {
            onLandProgress++;
        }
        if (!onLand && onLandProgress > 0F) {
            onLandProgress--;
        }

        if (!isInWaterOrBubble() && this.isAlive()) {
            if (this.onGround() && random.nextFloat() < 0.05F) {
                this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.1F, 0.3D, (this.random.nextFloat() * 2.0F - 1.0F) * 0.1F));
                this.setYRot(this.random.nextFloat() * 360.0F);
                this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
            }
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.fishTravel(travelVector);
        } else {
            super.travel(travelVector);
        }
    }

    // here so we don't need to keep copying travel everywhere
    public void fishTravel(Vec3 travelVector) {
        this.moveRelative(this.getSpeed(), travelVector);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        if (this.getTarget() == null) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
        }
    }

    // this method is only called serverside(in a goal) so you have to use sendParticles
    public void spawnEffectsAtBlock(BlockPos target) {
        float radius = 0.3F;
        for (int i1 = 0; i1 < 3; i1++) {
            double motionX = this.getRandom().nextGaussian() * 0.07D;
            double motionY = this.getRandom().nextGaussian() * 0.07D;
            double motionZ = this.getRandom().nextGaussian() * 0.07D;
            float angle = (float) ((0.0174532925 * this.yBodyRot) + i1);
            double extraX = radius * Mth.sin(Mth.PI + angle);
            double extraY = 0.8F;
            double extraZ = radius * Mth.cos(angle);
            BlockState state = this.level().getBlockState(target);
            ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, state), target.getX() + 0.5 + extraX, target.getY() + 0.5 + extraY, target.getZ() + 0.5 + extraZ, 1, motionX, motionY, motionZ, 1);
        }
    }

    private int findSeafloorDist(BlockPos selfpos) {
        int depth = 0;

        if (!this.level().isEmptyBlock(selfpos) && !this.level().getFluidState(selfpos).is(FluidTags.WATER)) {
            return Integer.MAX_VALUE;
        }

        while (this.level().getFluidState(selfpos).is(FluidTags.WATER) && selfpos.getY() > 1) {
            selfpos = selfpos.below();
            depth ++;
        }

        return depth;
    }

    private boolean checkFloat(BlockPos selfpos) {
        int north = findSeafloorDist(selfpos.above().north());
        int south = findSeafloorDist(selfpos.above().south());
        int east = findSeafloorDist(selfpos.above().east());
        int west = findSeafloorDist(selfpos.above().west());

        return north <= (1) || south <= (1) || east <= (1) || west <= (1);
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return ReefSoundEvents.FISH_IDLE.get();
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return ReefSoundEvents.FISH_DEATH.get();
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSoundEvents.FISH_HURT.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    protected SoundEvent getFlopSound() {
        return ReefSoundEvents.FISH_FLOP.get();
    }

    public static boolean canSpawn(EntityType<? extends ReefMob> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}
