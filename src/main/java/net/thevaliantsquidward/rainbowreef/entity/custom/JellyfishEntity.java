package net.thevaliantsquidward.rainbowreef.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import net.thevaliantsquidward.rainbowreef.sound.ModSounds;
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

public class JellyfishEntity extends AbstractFish implements GeoEntity {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(JellyfishEntity.class, EntityDataSerializers.INT);



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

    public float xBodyRotO;
    public float zBodyRot;
    public float zBodyRotO;

    public static <T extends Mob> boolean canSpawn(EntityType<JellyfishEntity> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(JellyfishEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(JellyfishEntity.class, EntityDataSerializers.INT);

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "orange";
            case 2 -> "white";
            case 3 -> "yellow";
            default -> "pink";
        };
    }
    @Override
    public void tick() {
        super.tick();

        //if(this.level.isClientSide) return;
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
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(SCALE, 0);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ModItems.JELLYFISH_BUCKET.get());
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
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level().isClientSide) {
            if(itemstack.getItem() == Items.GLASS_BOTTLE) {
                if(!player.isCreative()) {
                    itemstack.shrink(1);
                }
                this.spawnAtLocation(ModItems.JELLYFISH_JELLY.get());
                this.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    public void playerTouch(Player pEntity) {

        if (pEntity instanceof ServerPlayer && pEntity.hurt(this.damageSources().mobAttack(this), (float)(2))) {
            this.playSound(ModSounds.JELLYZAP.get(), 1.0F, 1.0F);
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
        if(variantChange <= 0.25F){
            this.setVariant(3);
        }else if(variantChange <= 0.50F){
            this.setVariant(2);
        }else if(variantChange <= 0.75F){
            this.setVariant(1);
        } else{
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    public MobType getMobType() {
        return MobType.WATER;
    }

    public JellyfishEntity(EntityType<? extends AbstractFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 7D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .build();
    }
    public void travel(Vec3 pTravelVector) {
        this.move(MoverType.SELF, this.getDeltaMovement());
    }
    private float tx;
    private float ty;
    private float tz;
    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new JellyRandomMovementGoal(this));
        //this.goalSelector.addGoal(1, new JellyFleeGoal());
        }

    public void setMovementVector(float pTx, float pTy, float pTz) {
        this.tx = pTx;
        this.ty = pTy;
        this.tz = pTz;
    }

    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }
    class JellyFleeGoal extends Goal {
        private static final float SQUID_FLEE_SPEED = 3.0F;
        private static final float SQUID_FLEE_MIN_DISTANCE = 5.0F;
        private static final float SQUID_FLEE_MAX_DISTANCE = 10.0F;
        private int fleeTicks;


        public boolean canUse() {
            LivingEntity livingentity = JellyfishEntity.this.getLastHurtByMob();
            if (JellyfishEntity.this.isInWater() && livingentity != null) {
                return JellyfishEntity.this.distanceToSqr(livingentity) < 100.0D;
            } else {
                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.fleeTicks = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            ++this.fleeTicks;
            LivingEntity livingentity = JellyfishEntity.this.getLastHurtByMob();
            if (livingentity != null) {
                Vec3 vec3 = new Vec3(JellyfishEntity.this.getX() - livingentity.getX(), JellyfishEntity.this.getY() - livingentity.getY(), JellyfishEntity.this.getZ() - livingentity.getZ());
                BlockState blockstate = JellyfishEntity.this.level().getBlockState(BlockPos.containing(JellyfishEntity.this.getX() + vec3.x, JellyfishEntity.this.getY() + vec3.y, JellyfishEntity.this.getZ() + vec3.z));
                FluidState fluidstate = JellyfishEntity.this.level().getFluidState(BlockPos.containing(JellyfishEntity.this.getX() + vec3.x, JellyfishEntity.this.getY() + vec3.y, JellyfishEntity.this.getZ() + vec3.z));
                if (fluidstate.is(FluidTags.WATER) || blockstate.isAir()) {
                    double d0 = vec3.length();
                    if (d0 > 0.0D) {
                        vec3.normalize();
                        double d1 = 3.0D;
                        if (d0 > 5.0D) {
                            d1 -= (d0 - 5.0D) / 5.0D;
                        }

                        if (d1 > 0.0D) {
                            vec3 = vec3.scale(d1);
                        }
                    }

                    if (blockstate.isAir()) {
                        vec3 = vec3.subtract(0.0D, vec3.y, 0.0D);
                    }

                    JellyfishEntity.this.setMovementVector((float)vec3.x / 20.0F, (float)vec3.y / 20.0F, (float)vec3.z / 20.0F);
                }

                if (this.fleeTicks % 10 == 5) {
                    JellyfishEntity.this.level().addParticle(ParticleTypes.BUBBLE, JellyfishEntity.this.getX(), JellyfishEntity.this.getY(), JellyfishEntity.this.getZ(), 0.0D, 0.0D, 0.0D);
                }

            }
        }
    }


    class JellyRandomMovementGoal extends Goal {
        private final JellyfishEntity squid;

        public JellyRandomMovementGoal(JellyfishEntity pSquid) {
            this.squid = pSquid;
        }


        public boolean canUse() {
            return true;
        }


        public void tick() {
            int i = this.squid.getNoActionTime();
            if (i > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.wasTouchingWater || !this.squid.hasMovementVector()) {
                float f = this.squid.getRandom().nextFloat() * ((float)Math.PI * 2F);
                float f1 = Mth.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
                float f3 = Mth.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
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
        return ModSounds.JELLYHIT.get();
    }


    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.TROPICAL_FISH_FLOP;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.jellyfish.move", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
