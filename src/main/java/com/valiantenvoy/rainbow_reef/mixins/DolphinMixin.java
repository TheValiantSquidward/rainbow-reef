package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.RainbowReefConfig;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.DolphinFollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.DolphinLeapGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.utils.DolphinAccess;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.DolphinJumpGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@SuppressWarnings({"WrongEntityDataParameterClass", "AddedMixinMembersNamePattern"})
@Mixin(Dolphin.class)
public abstract class DolphinMixin extends PathfinderMob implements DolphinAccess, ReefVariantMob {

    private static final @Unique EntityDataAccessor<String> VARIANT = SynchedEntityData.defineId(Dolphin.class, EntityDataSerializers.STRING);
    private static final @Unique EntityDataAccessor<Boolean> LEAPING = SynchedEntityData.defineId(Dolphin.class, EntityDataSerializers.BOOLEAN);

    private static final @Unique double PITCH_MIN = 0.01D;
    private static final @Unique double PITCH_MAX = 0.05D;
    private static final @Unique float PITCH_LERP = 0.2F;
    private static final @Unique float PITCH_CLAMP = 85.0F;

    private static final @Unique float ROLL_DECAY = 0.9F;
    private static final @Unique float ROLL_CLAMP = 20.0F;

    @Nullable
    private @Unique Dolphin leader;
    private @Unique int schoolSize = 1;

    private @Unique float prevSwimRoll;
    private @Unique float swimRoll;

    private @Unique float prevSwimPitch;
    private @Unique float swimPitch;

    public final @Unique SmoothAnimationState swimAnimationState = new SmoothAnimationState();
    public final @Unique SmoothAnimationState swimIdleAnimationState = new SmoothAnimationState();
    public final @Unique SmoothAnimationState flopAnimationState = new SmoothAnimationState();
    public final @Unique SmoothAnimationState jumpAnimationState = new SmoothAnimationState();

    protected DolphinMixin(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends Dolphin> entityType, Level level, CallbackInfo ci) {
        if (RainbowReefConfig.DOLPHIN_OVERHAUL.getAsBoolean()) {
            this.moveControl = new SmoothSwimmingMoveControl(this, 85, 6, 0.02F, 0.1F, true);
            this.lookControl = new SmoothSwimmingLookControl(this, 6);
        }
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    public void defineSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(VARIANT, this.defaultVariant().location().toString());
        builder.define(LEAPING, false);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.saveVariant(compoundTag);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.loadVariant(compoundTag);
    }

    @Override
    public String getVariantRawId() {
        return this.entityData.get(VARIANT);
    }

    @Override
    public void setVariantRawId(String id) {
        this.entityData.set(VARIANT, id);
    }

    public void setLeaping(boolean leaping) {
        this.entityData.set(LEAPING, leaping);
    }

    public boolean isLeaping() {
        return this.entityData.get(LEAPING);
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/dolphin/dolphin_bottlenose.png");
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        if (RainbowReefConfig.DOLPHIN_OVERHAUL.getAsBoolean()) {
            Dolphin dolphin = (Dolphin) (Object) this;
            List<Goal> goalOverrides = dolphin.goalSelector.getAvailableGoals().stream().map(WrappedGoal::getGoal).filter(goal -> goal instanceof RandomSwimmingGoal || goal instanceof DolphinJumpGoal).toList();
            goalOverrides.forEach(dolphin.goalSelector::removeGoal);
            dolphin.goalSelector.addGoal(4, new SwimWanderGoal(dolphin, 1.0D, 10, 20, 7, 3));
            dolphin.goalSelector.addGoal(5, new DolphinLeapGoal(dolphin, 10));
            this.goalSelector.addGoal(5, new DolphinFollowVariantLeaderGoal(dolphin));
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void rainbowReef$tickDolphin(CallbackInfo ci) {
        Dolphin dolphin = (Dolphin) (Object) this;
        if (dolphin.level().isClientSide) {
            this.updateSwimRoll();
            this.updateSwimPitch();
            this.setupAnimationStates();
        }

        // fix death by drowning by simply not drowning!
        if (RainbowReefConfig.DOLPHIN_OVERHAUL.getAsBoolean()) {
            if (dolphin.getAirSupply() <= 10) {
                dolphin.setAirSupply(dolphin.getMaxAirSupply());
            }
        }
    }

    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    public void rainbowReef$finalizeSpawnDolphin(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        this.pickVariantForSpawn(level);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        if (RainbowReefConfig.DOLPHIN_OVERHAUL.getAsBoolean()) {
            return this.getMaxSchoolSize();
        }
        return super.getMaxSpawnClusterSize();
    }

    public @Unique int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (RainbowReefConfig.DOLPHIN_OVERHAUL.getAsBoolean()) {
            if (this.getRandom().nextBoolean()) {
                return super.getWalkTargetValue(pos, level);
            }
            return this.getSurfacePathfindingFavor(pos, level);
        }
        return super.getWalkTargetValue(pos, level);
    }

    private @Unique float getSurfacePathfindingFavor(BlockPos pos, LevelReader level) {
        int y = Math.abs(level.getMaxBuildHeight()) - pos.getY();
        return 1.0F / (float) (y == 0 ? 1 : y);
    }

    public SmoothAnimationState getSwimAnimationState() {
        return this.swimAnimationState;
    }

    public SmoothAnimationState getIdleAnimationState() {
        return this.swimIdleAnimationState;
    }

    public SmoothAnimationState getFlopAnimationState() {
        return this.flopAnimationState;
    }

    public SmoothAnimationState getJumpAnimationState() {
        return this.jumpAnimationState;
    }

    @Unique
    private void setupAnimationStates() {
        boolean inWater = this.isInWaterOrBubble();
        boolean leaping = this.isLeaping();
        this.swimAnimationState.animateWhen(inWater && !leaping, this.tickCount);
        this.swimIdleAnimationState.animateWhen(inWater && !leaping, this.tickCount);
        this.flopAnimationState.animateWhen(!inWater && !leaping, this.tickCount);
        this.jumpAnimationState.animateWhen(leaping && !inWater, this.tickCount);
    }

    @Unique
    protected void updateSwimRoll() {
        Dolphin dolphin = (Dolphin) (Object) this;
        this.prevSwimRoll = this.swimRoll;
        if (dolphin.isInWater()) {
            float turn = Mth.degreesDifference(dolphin.getYRot(), dolphin.yRotO);
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
        if (this.isInWater() || this.isLeaping()) {
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
    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    @Override
    public void startFollowing(Dolphin dolphin) {
        this.leader = dolphin;
        ((DolphinAccess) dolphin).addFollower();
    }

    @Override
    public void stopFollowing() {
        if (this.leader != null) {
            ((DolphinAccess) this.leader).removeFollower();
        }
        this.leader = null;
    }

    @Override
    public void addFollower() {
        this.schoolSize++;
    }

    @Override
    public void removeFollower() {
        this.schoolSize--;
    }

    @Override
    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    @Override
    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    @Override
    public boolean inRangeOfLeader() {
        return this.distanceToSqr(Objects.requireNonNull(this.leader)) <= 256.0D;
    }

    @Override
    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(Objects.requireNonNull(this.leader), 1.0D);
        }
    }

    @Override
    public void addFollowers(Stream<Dolphin> dolphin) {
        dolphin.limit(this.getMaxSchoolSize() - this.schoolSize).filter((dolphin1) -> dolphin1 != (Object) this).forEach((dolphin2) -> {
            if (Objects.equals(this.getVariantRawId(), ((ReefVariantMob) dolphin2).getVariantRawId())) {
                ((DolphinAccess) dolphin2).startFollowing((Dolphin) (Object) this);
            }
        });
    }
}
