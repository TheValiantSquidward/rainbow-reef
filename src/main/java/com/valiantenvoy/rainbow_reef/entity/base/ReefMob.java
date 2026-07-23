package com.valiantenvoy.rainbow_reef.entity.base;

import com.valiantenvoy.rainbow_reef.entity.ai.navigation.WaterNavigation;
import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public abstract class ReefMob extends WaterAnimal implements Bucketable, ReefVariantMob {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> FEED_COOLDOWN = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> LEAPING = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.BOOLEAN);

    private static final double PITCH_MIN = 0.01D;
    private static final double PITCH_MAX = 0.05D;
    private static final float PITCH_LERP = 0.2F;
    protected static final float DEFAULT_PITCH_CLAMP = 85.0F;

    protected static final float ROLL_DECAY = 0.9F;
    protected static final float DEFAULT_ROLL_CLAMP = 20.0F;

    public float prevSwimRoll;
    public float swimRoll;

    public float prevSwimPitch;
    public float swimPitch;

    public final SmoothAnimationState swimAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState swimIdleAnimationState = new SmoothAnimationState();
    public final SmoothAnimationState flopAnimationState = new SmoothAnimationState();

    public final SmoothSwimmingMoveControl feedingController = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, false);

    protected ReefMob(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterNavigation(this, level, false);
    }

    public float getDepthPathfindingFavor(BlockPos pos, LevelReader level) {
        int y = pos.getY() + Math.abs(level.getMinBuildHeight());
        return 1.0F / (float) (y == 0 ? 1 : y);
    }

    public float getSurfacePathfindingFavor(BlockPos pos, LevelReader level) {
        int y = Math.abs(level.getMaxBuildHeight()) - pos.getY();
        return 1.0F / (float) (y == 0 ? 1 : y);
    }

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, this.defaultVariant().location().toString());
        builder.define(FROM_BUCKET, false);
        builder.define(FEED_COOLDOWN, 600 + (4 * this.getRandom().nextInt(600)));
        builder.define(LEAPING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        this.saveVariant(compoundTag);
        compoundTag.putBoolean("FromBucket", this.fromBucket());
        compoundTag.putInt("FeedingCooldown", this.getFeedCooldown());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.loadVariant(compoundTag);
        this.setFromBucket(compoundTag.getBoolean("FromBucket"));
        this.setFeedCooldown(compoundTag.getInt("FeedingCooldown"));
    }

    @Override
    public String getVariantRawId() {
        return this.entityData.get(VARIANT);
    }
    @Override
    public void setVariantRawId(String id) {
        this.entityData.set(VARIANT, id);
    }

    public void setFeedCooldown(int cooldown) {
        this.entityData.set(FEED_COOLDOWN, cooldown);
    }

    public int getFeedCooldown() {
        return this.entityData.get(FEED_COOLDOWN);
    }

    public void setLeaping(boolean leaping) {
        this.entityData.set(LEAPING, leaping);
    }

    public boolean isLeaping() {
        return this.entityData.get(LEAPING);
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
    public void saveToBucketTag(ItemStack stack) {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, this::saveVariant);
        CompoundTag custom = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        this.saveVariant(custom);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(custom));
    }

    @Override
    public void loadFromBucketTag(CompoundTag compoundTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, compoundTag);
        this.loadVariant(compoundTag);
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    public void setMoveControl(MoveControl newControl) {
        this.moveControl = newControl;
    }

    private boolean renderedInTooltip;

    public void setTooltipWaterState() {
        this.wasTouchingWater = true;
    }

    public void setRenderedInTooltip(boolean renderedInTooltip) {
        this.renderedInTooltip = renderedInTooltip;
    }

    public boolean isRenderedInTooltip() {
        return this.renderedInTooltip;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            this.setupAnimationStates();
            this.updateTilt();
            this.updateSwimPitch();
        }

        if (this.getFeedCooldown() > 0) {
            this.setFeedCooldown(this.getFeedCooldown() - 1);
        }

        this.tickFlopping();
    }

    // animations
    public void setupAnimationStates() {
        boolean inWater = this.isInWaterOrBubble();
        boolean leaping = this.isLeaping();
        this.swimAnimationState.animateWhen((inWater || leaping), this.tickCount);
        this.swimIdleAnimationState.animateWhen(inWater, this.tickCount);
        this.flopAnimationState.animateWhen(!inWater && !leaping, this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float f1 = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float f2 = Math.min(f1 * 10.0F, 1.0F);
        this.walkAnimation.update(f2, 0.4F);
    }

    protected void updateTilt() {
        this.prevSwimRoll = this.swimRoll;
        if (this.isInWater()) {
            float turn = Mth.degreesDifference(this.getYRot(), this.yRotO);
            if (Math.abs(turn) > 1.0F) {
                if (Math.abs(this.swimRoll) < this.getRollClamp()) {
                    this.swimRoll -= Math.signum(turn);
                }
            } else if (this.swimRoll != 0.0F) {
                float sign = Math.signum(this.swimRoll);
                this.swimRoll -= sign * ROLL_DECAY;
                if (this.swimRoll * sign < 0.0F) {
                    this.swimRoll = 0.0F;
                }
            }
        } else {
            this.swimRoll = 0.0F;
        }
    }

    protected void updateSwimPitch() {
        this.prevSwimPitch = this.swimPitch;
        float target = 0.0F;
        if (this.isInWater() || this.isLeaping()) {
            double dx = this.getX() - this.xo;
            double dy = this.getY() - this.yo;
            double dz = this.getZ() - this.zo;
            double horizontal = Math.sqrt(dx * dx + dz * dz);
            double speed = Math.sqrt(horizontal * horizontal + dy * dy);
            float speedFactor = (float) Mth.clamp((speed - PITCH_MIN) / (PITCH_MAX - PITCH_MIN), 0.0D, 1.0D);
            if (speedFactor > 0.0F) {
                float angle = (float) (-(Mth.atan2(dy, horizontal) * (180.0D / Math.PI)));
                target = Mth.clamp(angle, -this.getPitchClamp(), this.getPitchClamp()) * speedFactor;
            }
        }
        this.swimPitch += (target - this.swimPitch) * PITCH_LERP;
    }

    public float getSwimRoll(float partialTick) {
        return Mth.lerp(partialTick, this.prevSwimRoll, this.swimRoll);
    }

    public float getSwimPitch(float partialTick) {
        return Mth.lerp(partialTick, this.prevSwimPitch, this.swimPitch);
    }

    protected float getPitchClamp() {
        return DEFAULT_PITCH_CLAMP;
    }

    protected float getRollClamp() {
        return DEFAULT_ROLL_CLAMP;
    }

    public float flopChance() {
        return 1.0F;
    }

    public boolean shouldFlop() {
        return true;
    }

    public void tickFlopping() {
        if (!this.isInWater() && this.onGround() && this.getRandom().nextFloat() < this.flopChance() && this.shouldFlop()) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.2F, 0.5D, (this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.2F));
            if (this.getRandom().nextFloat() < 0.3F) this.setYRot(this.getRandom().nextFloat() * 360.0F);
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWaterOrBubble()) {
            this.fishTravel(travelVector);
        } else {
            super.travel(travelVector);
        }
    }

    public void fishTravel(Vec3 travelVector) {
        this.moveRelative(this.getSpeed(), travelVector);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        if (this.horizontalCollision && this.isEyeInFluid(FluidTags.WATER) && this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.01D, 0.0D));
        }
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

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        if (spawnType == MobSpawnType.BUCKET) {
            return data;
        }
        this.pickVariantForSpawn(level);
        return data;
    }
}
