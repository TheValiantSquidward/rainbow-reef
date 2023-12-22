package net.thevaliantsquidward.rainbowreef.entity.custom;

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
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class RayEntity extends AbstractFish implements GeoEntity {
    private static final EntityDataAccessor<BlockPos> TREASURE_POS = SynchedEntityData.defineId(RayEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> GOT_FISH = SynchedEntityData.defineId(RayEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(RayEntity.class, EntityDataSerializers.INT);
    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
    public static final int TOTAL_AIR_SUPPLY = 4800;
    private static final int TOTAL_MOISTNESS_LEVEL = 2400;
    public static final Predicate<ItemEntity> ALLOWED_ITEMS = (p_289436_) -> {
        return !p_289436_.hasPickUpDelay() && p_289436_.isAlive() && p_289436_.isInWater();
    };
    public static <T extends Mob> boolean canSpawn(EntityType<RayEntity> p_223364_0_, LevelAccessor p_223364_1_, MobSpawnType reason, BlockPos p_223364_3_, RandomSource p_223364_4_) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(p_223364_0_, p_223364_1_, reason, p_223364_3_, p_223364_4_);
    }
    public RayEntity(EntityType<? extends RayEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.setCanPickUpLoot(true);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public boolean canBreatheUnderwater() {
        return false;
    }

    protected void handleAirSupply(int pAirSupply) {
    }

    public void setTreasurePos(BlockPos pPos) {
        this.entityData.set(TREASURE_POS, pPos);
    }

    public BlockPos getTreasurePos() {
        return this.entityData.get(TREASURE_POS);
    }

    public boolean gotFish() {
        return this.entityData.get(GOT_FISH);
    }

    public void setGotFish(boolean pGotFish) {
        this.entityData.set(GOT_FISH, pGotFish);
    }

    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int pMoistnessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, pMoistnessLevel);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TREASURE_POS, BlockPos.ZERO);
        this.entityData.define(GOT_FISH, false);
        this.entityData.define(MOISTNESS_LEVEL, 2400);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("TreasurePosX", this.getTreasurePos().getX());
        pCompound.putInt("TreasurePosY", this.getTreasurePos().getY());
        pCompound.putInt("TreasurePosZ", this.getTreasurePos().getZ());
        pCompound.putBoolean("GotFish", this.gotFish());
        pCompound.putInt("Moistness", this.getMoistnessLevel());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag pCompound) {
        int i = pCompound.getInt("TreasurePosX");
        int j = pCompound.getInt("TreasurePosY");
        int k = pCompound.getInt("TreasurePosZ");
        this.setTreasurePos(new BlockPos(i, j, k));
        super.readAdditionalSaveData(pCompound);
        this.setGotFish(pCompound.getBoolean("GotFish"));
        this.setMoisntessLevel(pCompound.getInt("Moistness"));
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(9, new AvoidEntityGoal<>(this, Guardian.class, 8.0F, 1.0D, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Guardian.class)).setAlertOthers());
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .build();
    }


    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
            this.playSound(SoundEvents.DOLPHIN_ATTACK, 1.0F, 1.0F);
        }

        return flag;
    }

    public int getMaxAirSupply() {
        return 4800;
    }

    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.3F;
    }

    /**
     * The speed it takes to move the entity's head rotation through the faceEntity method.
     */
    public int getMaxHeadXRot() {
        return 1;
    }

    public int getMaxHeadYRot() {
        return 1;
    }

    protected boolean canRide(Entity pEntity) {
        return true;
    }

    public boolean canTakeItem(ItemStack pItemstack) {
        EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(pItemstack);
        if (!this.getItemBySlot(equipmentslot).isEmpty()) {
            return false;
        } else {
            return equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(pItemstack);
        }
    }

    /**
     * Tests if this entity should pick up a weapon or an armor piece. Entity drops current weapon or armor if the new
     * one is better.
     */
    protected void pickUpItem(ItemEntity pItemEntity) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack itemstack = pItemEntity.getItem();
            if (this.canHoldItem(itemstack)) {
                this.onItemPickup(pItemEntity);
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                this.setGuaranteedDrop(EquipmentSlot.MAINHAND);
                this.take(pItemEntity, itemstack.getCount());
                pItemEntity.discard();
            }
        }

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(2400);
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(this.damageSources().dryOut(), 1.0F);
                }

                if (this.onGround()) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.setOnGround(false);
                    this.hasImpulse = true;
                }
            }

            if (this.level().isClientSide && this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.03D) {
                Vec3 vec3 = this.getViewVector(0.0F);
                float f = Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f1 = Mth.sin(this.getYRot() * ((float)Math.PI / 180F)) * 0.3F;
                float f2 = 1.2F - this.random.nextFloat() * 0.7F;

                for(int i = 0; i < 2; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 + (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 + (double)f1, 0.0D, 0.0D, 0.0D);
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX() - vec3.x * (double)f2 - (double)f, this.getY() - vec3.y, this.getZ() - vec3.z * (double)f2 - (double)f1, 0.0D, 0.0D, 0.0D);
                }
            }

        }
    }

    /**
     * Handles an entity event received from a {@link net.minecraft.network.protocol.game.ClientboundEntityEventPacket}.
     */
    public void handleEntityEvent(byte pId) {
        if (pId == 38) {
            this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
        } else {
            super.handleEntityEvent(pId);
        }

    }

    private void addParticlesAroundSelf(ParticleOptions pParticleOption) {
        for(int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.01D;
            double d1 = this.random.nextGaussian() * 0.01D;
            double d2 = this.random.nextGaussian() * 0.01D;
            this.level().addParticle(pParticleOption, this.getRandomX(1.0D), this.getRandomY() + 0.2D, this.getRandomZ(1.0D), d0, d1, d2);
        }

    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!itemstack.isEmpty() && itemstack.is(ItemTags.FISHES)) {
            if (!this.level().isClientSide) {
                this.playSound(SoundEvents.DOLPHIN_EAT, 1.0F, 1.0F);
            }

            this.setGotFish(true);
            if (!pPlayer.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.DOLPHIN_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundEvents.DOLPHIN_AMBIENT_WATER : SoundEvents.DOLPHIN_AMBIENT;
    }

    protected SoundEvent getSwimSplashSound() {
        return SoundEvents.DOLPHIN_SPLASH;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }

    protected boolean closeToNextPos() {
        BlockPos blockpos = this.getNavigation().getTargetPos();
        return blockpos != null ? blockpos.closerToCenterThan(this.position(), 12.0D) : false;
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    @Override
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ModItems.SPOTTED_EAGLE_RAY_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.eagle_ray.swim", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

     @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    static class RayEntitySwimToTreasureGoal extends Goal {
        private final RayEntity dolphin;
        private boolean stuck;

        RayEntitySwimToTreasureGoal(RayEntity pRayEntity) {
            this.dolphin = pRayEntity;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean isInterruptable() {
            return false;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.dolphin.gotFish() && this.dolphin.getAirSupply() >= 100;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            BlockPos blockpos = this.dolphin.getTreasurePos();
            return !BlockPos.containing((double)blockpos.getX(), this.dolphin.getY(), (double)blockpos.getZ()).closerToCenterThan(this.dolphin.position(), 4.0D) && !this.stuck && this.dolphin.getAirSupply() >= 100;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            if (this.dolphin.level() instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)this.dolphin.level();
                this.stuck = false;
                this.dolphin.getNavigation().stop();
                BlockPos blockpos = this.dolphin.blockPosition();
                BlockPos blockpos1 = serverlevel.findNearestMapStructure(StructureTags.DOLPHIN_LOCATED, blockpos, 50, false);
                if (blockpos1 != null) {
                    this.dolphin.setTreasurePos(blockpos1);
                    serverlevel.broadcastEntityEvent(this.dolphin, (byte)38);
                } else {
                    this.stuck = true;
                }
            }
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            BlockPos blockpos = this.dolphin.getTreasurePos();
            if (BlockPos.containing((double)blockpos.getX(), this.dolphin.getY(), (double)blockpos.getZ()).closerToCenterThan(this.dolphin.position(), 4.0D) || this.stuck) {
                this.dolphin.setGotFish(false);
            }

        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            Level level = this.dolphin.level();
            if (this.dolphin.closeToNextPos() || this.dolphin.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atCenterOf(this.dolphin.getTreasurePos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.dolphin, 16, 1, vec3, (double)((float)Math.PI / 8F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.dolphin, 8, 4, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 != null) {
                    BlockPos blockpos = BlockPos.containing(vec31);
                    if (!level.getFluidState(blockpos).is(FluidTags.WATER) || !level.getBlockState(blockpos).isPathfindable(level, blockpos, PathComputationType.WATER)) {
                        vec31 = DefaultRandomPos.getPosTowards(this.dolphin, 8, 5, vec3, (double)((float)Math.PI / 2F));
                    }
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.dolphin.getLookControl().setLookAt(vec31.x, vec31.y, vec31.z, (float)(this.dolphin.getMaxHeadYRot() + 20), (float)this.dolphin.getMaxHeadXRot());
                this.dolphin.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, 1.3D);
                if (level.random.nextInt(this.adjustedTickDelay(80)) == 0) {
                    level.broadcastEntityEvent(this.dolphin, (byte)38);
                }
            }

        }
    }

    static class RayEntitySwimWithPlayerGoal extends Goal {
        private final RayEntity dolphin;
        private final double speedModifier;
        @Nullable
        private Player player;

        RayEntitySwimWithPlayerGoal(RayEntity pRayEntity, double pSpeedModifier) {
            this.dolphin = pRayEntity;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            this.player = this.dolphin.level().getNearestPlayer(RayEntity.SWIM_WITH_PLAYER_TARGETING, this.dolphin);
            if (this.player == null) {
                return false;
            } else {
                return this.player.isSwimming() && this.dolphin.getTarget() != this.player;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return this.player != null && this.player.isSwimming() && this.dolphin.distanceToSqr(this.player) < 256.0D;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100), this.dolphin);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.player = null;
            this.dolphin.getNavigation().stop();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            this.dolphin.getLookControl().setLookAt(this.player, (float)(this.dolphin.getMaxHeadYRot() + 20), (float)this.dolphin.getMaxHeadXRot());
            if (this.dolphin.distanceToSqr(this.player) < 6.25D) {
                this.dolphin.getNavigation().stop();
            } else {
                this.dolphin.getNavigation().moveTo(this.player, this.speedModifier);
            }

            if (this.player.isSwimming() && this.player.level().random.nextInt(6) == 0) {
                this.player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 100), this.dolphin);
            }

        }
    }

    class PlayWithItemsGoal extends Goal {
        private int cooldown;

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.cooldown > RayEntity.this.tickCount) {
                return false;
            } else {
                List<ItemEntity> list = RayEntity.this.level().getEntitiesOfClass(ItemEntity.class, RayEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), RayEntity.ALLOWED_ITEMS);
                return !list.isEmpty() || !RayEntity.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            List<ItemEntity> list = RayEntity.this.level().getEntitiesOfClass(ItemEntity.class, RayEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), RayEntity.ALLOWED_ITEMS);
            if (!list.isEmpty()) {
                RayEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
                RayEntity.this.playSound(SoundEvents.DOLPHIN_PLAY, 1.0F, 1.0F);
            }

            this.cooldown = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            ItemStack itemstack = RayEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.drop(itemstack);
                RayEntity.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                this.cooldown = RayEntity.this.tickCount + RayEntity.this.random.nextInt(100);
            }

        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            List<ItemEntity> list = RayEntity.this.level().getEntitiesOfClass(ItemEntity.class, RayEntity.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), RayEntity.ALLOWED_ITEMS);
            ItemStack itemstack = RayEntity.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (!itemstack.isEmpty()) {
                this.drop(itemstack);
                RayEntity.this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            } else if (!list.isEmpty()) {
                RayEntity.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }

        private void drop(ItemStack pStack) {
            if (!pStack.isEmpty()) {
                double d0 = RayEntity.this.getEyeY() - (double)0.3F;
                ItemEntity itementity = new ItemEntity(RayEntity.this.level(), RayEntity.this.getX(), d0, RayEntity.this.getZ(), pStack);
                itementity.setPickUpDelay(40);
                itementity.setThrower(RayEntity.this.getUUID());
                float f = 0.3F;
                float f1 = RayEntity.this.random.nextFloat() * ((float)Math.PI * 2F);
                float f2 = 0.02F * RayEntity.this.random.nextFloat();
                itementity.setDeltaMovement((double)(0.3F * -Mth.sin(RayEntity.this.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(RayEntity.this.getXRot() * ((float)Math.PI / 180F)) + Mth.cos(f1) * f2), (double)(0.3F * Mth.sin(RayEntity.this.getXRot() * ((float)Math.PI / 180F)) * 1.5F), (double)(0.3F * Mth.cos(RayEntity.this.getYRot() * ((float)Math.PI / 180F)) * Mth.cos(RayEntity.this.getXRot() * ((float)Math.PI / 180F)) + Mth.sin(f1) * f2));
                RayEntity.this.level().addFreshEntity(itementity);
            }
        }
    }
}
