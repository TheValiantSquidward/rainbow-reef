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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefSounds;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class Jellyfish extends ReefMob {

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

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.INT);

    public Jellyfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level, Integer.MAX_VALUE);
        this.random.setSeed(this.getId());
        this.tentacleSpeed = 2.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
        //        this number ^ deterimnes how often the jelly boosts
    }

    @Override
    public void travel(@NotNull Vec3 travelVector) {
        if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new JellyfishRandomMovementGoal(this));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .build();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (SCALE.equals(key)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(key);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(getScale(this.getModelScale()));
    }

    private static float getScale(int scale) {
        if (scale == 1) {
            return 1.8F;
        }
        return 0.9F;
    }

    public int getModelScale() {
        return this.entityData.get(SCALE);
    }

    public void setScale(int scale) {
        this.entityData.set(SCALE, scale);
    }

    public static boolean canSpawn(EntityType<Jellyfish> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }

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

    @Override
    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        float variantChange = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();
        if (rare <= 0.10) {
            if (rareVariantChange <= 0.20F) {
                this.setVariant(4);
            } else if (rareVariantChange <= 0.40F) {
                this.setVariant(5);
            } else if(rareVariantChange <= 0.60F) {
                this.setVariant(6);
            } else if (rareVariantChange <= 0.80F) {
                this.setVariant(7);
            } else {
                this.setVariant(8);
            }
        } else if (variantChange <= 0.20F) {
            this.setVariant(9);
        } else if(variantChange <= 0.40F) {
            this.setVariant(3);
        } else if(variantChange <= 0.60F) {
            this.setVariant(2);
        } else if(variantChange <= 0.80F) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }

    @Override
    public void tick() {
        super.tick();
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
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        this.oldTentacleMovement = this.tentacleMovement;
        this.oldTentacleAngle = this.tentacleAngle;
        this.tentacleMovement += this.tentacleSpeed;
        if ((double)this.tentacleMovement > (Math.PI * 2D)) {
            if (this.level().isClientSide) {
                this.tentacleMovement = ((float)Math.PI * 2F);
            } else {
                this.tentacleMovement -= ((float)Math.PI * 2F);
                if (this.random.nextInt(10) == 0) {
                    this.tentacleSpeed = 1.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
                }
                this.level().broadcastEntityEvent(this, (byte) 19);
            }
        }

        if (this.isInWaterOrBubble()) {
            if (this.tentacleMovement < (float) Math.PI) {
                float f = this.tentacleMovement / (float) Math.PI;
                this.tentacleAngle = Mth.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;
                if ((double) f > 0.75D) {
                    this.speed = 1.0F;
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.speed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.level().isClientSide) {
                this.setDeltaMovement(this.tx * this.speed, this.ty * this.speed, this.tz * this.speed);
            }

            Vec3 vec3 = this.getDeltaMovement();
            double d0 = vec3.horizontalDistance();
            this.yBodyRot += (-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float) Math.PI) - this.yBodyRot) * 0.1F;
            this.setYRot(this.yBodyRot);
            this.zBodyRot += (float) Math.PI * this.rotateSpeed * 1.5F;
            this.xBodyRot += (-((float) Mth.atan2(d0, vec3.y)) * (180F / (float) Math.PI) - this.xBodyRot) * 0.1F;
        } else {
            this.tentacleAngle = Mth.abs(Mth.sin(this.tentacleMovement)) * (float) Math.PI * 0.25F;
            if (!this.level().isClientSide) {
                double d1 = this.getDeltaMovement().y;
                if (this.hasEffect(MobEffects.LEVITATION)) {
                    d1 = 0.05D * (double)(this.getEffect(MobEffects.LEVITATION).getAmplifier() + 1);
                } else if (!this.isNoGravity()) {
                    d1 -= 0.08D;
                }
                this.setDeltaMovement(0.0D, d1 * (double)0.98F, 0.0D);
            }
            this.xBodyRot += (-90.0F - this.xBodyRot) * 0.02F;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
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

    @Override
    public void playerTouch(@NotNull Player player) {
        if (player instanceof ServerPlayer && player.hurt(this.damageSources().mobAttack(this), (float) (2))) {
            this.playSound(ReefSounds.JELLYZAP.get(), 1.0F, 1.0F);
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Scale", this.getModelScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setScale(Math.min(compound.getInt("Scale"), 0));
    }

    public void setMovementVector(float pTx, float pTy, float pTz) {
        this.tx = pTx;
        this.ty = pTy;
        this.tz = pTz;
    }

    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }

    static class JellyfishRandomMovementGoal extends Goal {

        private final Jellyfish jellyfish;

        public JellyfishRandomMovementGoal(Jellyfish pSquid) {
            this.jellyfish = pSquid;
        }

        public boolean canUse() {
            return true;
        }

        public void tick() {
            if (this.jellyfish.getRandom().nextInt(reducedTickDelay(60)) == 0 || !this.jellyfish.isInWater() || !this.jellyfish.hasMovementVector()) {
                var f = this.jellyfish.getRandom().nextFloat() * (float) (Math.PI * 2);
                var tx = Mth.cos(f) * 0.2F;
                var ty = -0.1F + this.jellyfish.getRandom().nextFloat() * 0.18F;
                var tz = Mth.sin(f) * 0.2F;
                this.jellyfish.setMovementVector(tx, ty, tz);
            }
        }
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSounds.JELLYHIT.get();
    }
}
