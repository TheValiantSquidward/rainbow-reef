package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.RainbowReefConfig;
import com.valiantenvoy.rainbow_reef.entity.ai.control.ReefTurtleMoveControl;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.TurtleSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.utils.TurtleAccess;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@SuppressWarnings({"AddedMixinMembersNamePattern", "WrongEntityDataParameterClass"})
@Mixin(Turtle.class)
public abstract class TurtleMixin extends Animal implements TurtleAccess, ReefVariantMob, Bucketable {

    private static final @Unique EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Turtle.class, EntityDataSerializers.STRING);
    private static final @Unique EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Turtle.class, EntityDataSerializers.BOOLEAN);

    private static final @Unique double PITCH_MIN = 0.01D;
    private static final @Unique double PITCH_MAX = 0.05D;
    private static final @Unique float PITCH_LERP = 0.2F;
    private static final @Unique float PITCH_CLAMP = 60.0F;

    private static final @Unique float ROLL_DECAY = 0.9F;
    private static final @Unique float ROLL_CLAMP = 30.0F;

    private @Unique float prevSwimRoll;
    private @Unique float swimRoll;

    private @Unique float prevSwimPitch;
    private @Unique float swimPitch;

    public final @Unique SmoothAnimationState swimAnimationState = new SmoothAnimationState();
    public final @Unique SmoothAnimationState swimIdleAnimationState = new SmoothAnimationState();
    public final @Unique SmoothAnimationState idleAnimationState = new SmoothAnimationState();
    public final @Unique SmoothAnimationState walkAnimationState = new SmoothAnimationState();

    protected TurtleMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends Turtle> entityType, Level level, CallbackInfo ci) {
        if (RainbowReefConfig.TURTLE_OVERHAUL.get()) {
            Turtle turtle = (Turtle) (Object) this;
            this.moveControl = new ReefTurtleMoveControl(turtle);
            this.lookControl = new SmoothSwimmingLookControl(turtle, 7);
        }
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(VARIANT, this.defaultVariant().location().toString());
        builder.define(FROM_BUCKET, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.saveVariant(compoundTag);
        compoundTag.putBoolean("FromBucket", this.fromBucket());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.loadVariant(compoundTag);
        this.setFromBucket(compoundTag.getBoolean("FromBucket"));
    }

    @Override
    public String getVariantRawId() {
        return this.entityData.get(VARIANT);
    }

    @Override
    public void setVariantRawId(String id) {
        this.entityData.set(VARIANT, id);
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/turtle/turtle_flatback.png");
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        if (RainbowReefConfig.TURTLE_OVERHAUL.get()) {
            Turtle turtle = (Turtle) (Object) this;
            List<Goal> goalOverrides = turtle.goalSelector.getAvailableGoals().stream().map(WrappedGoal::getGoal).filter(goal -> goal instanceof Turtle.TurtleTravelGoal).toList();
            goalOverrides.forEach(turtle.goalSelector::removeGoal);
            turtle.goalSelector.addGoal(7, new TurtleSwimGoal(turtle, 1.0D));
        }
    }

    @ModifyConstant(method = "travel", constant = @Constant(floatValue = 0.1F, ordinal = 0))
    private float travel(float oldSpeed) {
        if (RainbowReefConfig.TURTLE_OVERHAUL.get()) {
            return this.getSpeed();
        }
        return oldSpeed;
    }

    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    public void rainbowReef$finalizeSpawnTurtle(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (!this.fromBucket()) {
            this.pickVariantForSpawn(level);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.setupAnimationStates();
            this.updateSwimRoll();
            this.updateSwimPitch();
        }
    }

    @Unique
    private void setupAnimationStates() {
        boolean inWater = this.isInWaterOrBubble();
        this.swimAnimationState.animateWhen(inWater, this.tickCount);
        this.swimIdleAnimationState.animateWhen(inWater, this.tickCount);
        this.idleAnimationState.animateWhen(!inWater, this.tickCount);
        this.walkAnimationState.animateWhen(!inWater, this.tickCount);
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        if (RainbowReefConfig.TURTLE_OVERHAUL.get()) {
            float f1 = (float) Mth.length(this.getX() - this.xo, this.isInWater() ? this.getY() - this.yo : 0.0D, this.getZ() - this.zo);
            float f2 = Math.min(f1 * (this.isInWater() ? 10.0F : 50.0F), 1.0F);
            this.walkAnimation.update(f2, 0.4F);
        } else {
            super.calculateEntityAnimation(flying);
        }
    }

    @Override
    public SmoothAnimationState getSwimAnimationState() {
        return this.swimAnimationState;
    }

    @Override
    public SmoothAnimationState getSwimIdleAnimationState() {
        return this.swimIdleAnimationState;
    }

    @Override
    public SmoothAnimationState getIdleAnimationState() {
        return this.idleAnimationState;
    }

    @Override
    public SmoothAnimationState getWalkAnimationState() {
        return this.walkAnimationState;
    }

    @Unique
    protected void updateSwimRoll() {
        this.prevSwimRoll = this.swimRoll;
        if (this.isInWater()) {
            float turn = Mth.degreesDifference(this.getYRot(), this.yRotO);
            if (Math.abs(turn) > 1.0F) {
                if (Math.abs(this.swimRoll) < ROLL_CLAMP) {
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

    @Unique
    protected void updateSwimPitch() {
        this.prevSwimPitch = this.swimPitch;
        float target = 0.0F;
        if (this.isInWater()) {
            double dx = this.getX() - this.xo;
            double dy = this.getY() - this.yo;
            double dz = this.getZ() - this.zo;
            double horizontal = Math.sqrt(dx * dx + dz * dz);
            double speed = Math.sqrt(horizontal * horizontal + dy * dy);
            float speedFactor = (float) Mth.clamp((speed - PITCH_MIN) / (PITCH_MAX - PITCH_MIN), 0.0D, 1.0D);
            if (speedFactor > 0.0F) {
                float angle = (float) (-(Mth.atan2(dy, horizontal) * (180.0D / Math.PI)));
                target = Mth.clamp(angle, -PITCH_CLAMP, PITCH_CLAMP) * speedFactor;
            }
        }
        this.swimPitch += (target - this.swimPitch) * PITCH_LERP;
    }

    @Override
    public float getSwimRoll(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevSwimRoll, this.swimRoll);
    }

    @Override
    public float getSwimPitch(float partialTicks) {
        return Mth.lerp(partialTicks, this.prevSwimPitch, this.swimPitch);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void saveToBucketTag(ItemStack stack) {
        Bucketable.saveDefaultDataToBucketTag(this, stack);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, (compoundTag) -> {
            this.saveVariant(compoundTag);
            compoundTag.putInt("Age", this.getAge());
            compoundTag.putInt("InLove", this.inLove);
            if (this.loveCause != null) {
                compoundTag.putUUID("LoveCause", this.loveCause);
            }
        });
        CompoundTag custom = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(custom));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void loadFromBucketTag(CompoundTag compoundTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, compoundTag);
        this.loadVariant(compoundTag);
        if (compoundTag.contains("Age")) {
            this.setAge(compoundTag.getInt("Age"));
        }
        if (compoundTag.contains("InLove")) {
            this.inLove = compoundTag.getInt("InLove");
        }
        this.loveCause = compoundTag.hasUUID("LoveCause") ? compoundTag.getUUID("LoveCause") : null;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TURTLE_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }
}
