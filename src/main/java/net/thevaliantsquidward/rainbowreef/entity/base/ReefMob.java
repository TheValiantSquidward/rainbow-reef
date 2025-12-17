package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.navigation.SmoothWaterBoundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.UnaryOperator;

@SuppressWarnings("deprecation")
public abstract class ReefMob extends WaterAnimal implements Bucketable {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FEED_COOLDOWN = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> LEAPING = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.BOOLEAN);

    public float prevTilt;
    public float tilt;

    public float prevOnLandProgress;
    public float onLandProgress;

    public final AnimationState swimIdleAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public SmoothSwimmingMoveControl feedingController = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, false);

    protected ReefMob(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        return new SmoothWaterBoundPathNavigation(this, level, false);
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
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(FEED_COOLDOWN, 600 + (4 * this.getRandom().nextInt(600)));
        this.entityData.define(LEAPING, false);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant());
        compoundTag.putBoolean("FromBucket", this.fromBucket());
        compoundTag.putInt("FeedingCooldown", this.getFeedCooldown());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setVariant(compoundTag.getInt("Variant"));
        this.setFromBucket(compoundTag.getBoolean("FromBucket"));
        this.setFeedCooldown(compoundTag.getInt("FeedingCooldown"));
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, Mth.clamp(variant, 1, this.getVariantCount()));
    }

    public int getVariantCount() {
        return 128;
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
    public void saveToBucketTag(@Nonnull ItemStack bucket) {
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundTag = bucket.getOrCreateTag();
        compoundTag.putInt("BucketVariantTag", this.getVariant());
    }

    @Override
    public void loadFromBucketTag(@Nonnull CompoundTag compoundTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, compoundTag);
        if (compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(compoundTag.getInt("BucketVariantTag"));
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
        } else {
            tilt = 0;
        }

        if (this.getFeedCooldown() > 0) {
            this.setFeedCooldown(this.getFeedCooldown() - 1);
        }

        this.tickFlopping();
    }

    // default animations
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float f1 = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float f2 = Math.min(f1 * 10.0F, 1.0F);
        this.walkAnimation.update(f2, 0.4F);
    }

    public float flopChance() {
        return 1.0F;
    }

    public boolean shouldFlop() {
        return true;
    }

    // flop here so it can be overridden if needed
    public void tickFlopping() {
        if (!this.isInWaterOrBubble() && onLandProgress < 5F) {
            onLandProgress++;
        }
        if (this.isInWaterOrBubble() && onLandProgress > 0F) {
            onLandProgress--;
        }

        if (!this.isInWater() && this.onGround() && this.getRandom().nextFloat() < this.flopChance() && this.shouldFlop()) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.2F, 0.5D, (this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.2F));
            if (this.getRandom().nextFloat() < 0.3F) this.setYRot(this.getRandom().nextFloat() * 360.0F);
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    public void travel(@NotNull Vec3 travelVec) {
        if (this.isEffectiveAi() && this.isInWaterOrBubble()) {
            this.fishTravel(travelVec);
        } else {
            super.travel(travelVec);
        }
    }

    public void fishTravel(Vec3 travelVector) {
        this.moveRelative(this.getSpeed(), travelVector);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        if (this.horizontalCollision && this.isEyeInFluid(FluidTags.WATER) && this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
        }
    }

    public enum ReefRarities implements WeightedEntry {
        COMMON(style -> style.withColor(ChatFormatting.GRAY), 60),
        UNCOMMON(style -> style.withColor(ChatFormatting.YELLOW), 30),
        RARE(style -> style.withColor(ChatFormatting.AQUA), 15),
        EPIC(style -> style.withColor(ChatFormatting.LIGHT_PURPLE), 5),
        ABERRANT(style -> style.withColor(15933054), 1);

        private final UnaryOperator<Style> style;
        private final Weight weight;

        ReefRarities(UnaryOperator<Style> style, int weight) {
            this.style = style;
            this.weight = Weight.of(weight);
        }

        @Override
        public @NotNull Weight getWeight() {
            return this.weight;
        }

        public UnaryOperator<Style> getStyle() {
            return this.style;
        }
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
    protected SoundEvent getHurtSound(@NotNull DamageSource source) {
        return ReefSoundEvents.FISH_HURT.get();
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState state) {
    }

    protected SoundEvent getFlopSound() {
        return ReefSoundEvents.FISH_FLOP.get();
    }

    public static boolean canSpawn(EntityType<? extends ReefMob> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}
