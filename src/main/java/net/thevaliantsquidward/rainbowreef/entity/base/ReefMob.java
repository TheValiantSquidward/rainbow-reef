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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.UnaryOperator;

public abstract class ReefMob extends WaterAnimal implements Bucketable {

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FEED_COOLDOWN = SynchedEntityData.defineId(ReefMob.class, EntityDataSerializers.INT);

    public float prevTilt;
    public float tilt;

    public float prevOnLandProgress;
    public float onLandProgress;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public SmoothSwimmingMoveControl feedingController = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, false);

    protected ReefMob(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
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
        this.entityData.define(FEED_COOLDOWN, 600 + (4 * this.getRandom().nextInt(600)));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Variant", this.getVariant());
        compoundTag.putBoolean("FromBucket", this.fromBucket());
        compoundTag.putInt("FeedingCooldown", this.getFeedCooldown());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
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
        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float f1 = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float f2 = Math.min(f1 * 10.0F, 1.0F);
        this.walkAnimation.update(f2, 0.4F);
    }

    public float flopChance() {
        return 0.5F;
    }

    // flop here so it can be overridden if needed
    public void tickFlopping() {
        if (!this.isInWaterOrBubble() && onLandProgress < 5F) {
            onLandProgress++;
        }
        if (this.isInWaterOrBubble() && onLandProgress > 0F) {
            onLandProgress--;
        }

        if (!this.isInWaterOrBubble() && this.isAlive()) {
            if (this.onGround() && this.getRandom().nextFloat() < flopChance()) {
                this.setDeltaMovement(this.getDeltaMovement().add((this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.1F, 0.3D, (this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.1F));
                this.setYRot(this.getRandom().nextFloat() * 360.0F);
                this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
            }
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
        if (this.isEyeInFluid(FluidTags.WATER) && this.isPathFinding() && checkFloat(this.blockPosition())) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.005 * this.getSpeed(), 0.0));
        }
        this.moveRelative(this.getSpeed(), travelVector);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
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
