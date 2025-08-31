package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Ray extends VariantSchoolingFish {

    public IKSolver tailKinematics;

    public int animTime;
    public double animSpeed = 1;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public Ray(EntityType<? extends VariantSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 1200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 360, 2, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        this.tailKinematics = new IKSolver(this, 5, 0.5, 0.75,false, false);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 2.0F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new FishDigGoal(this, 40, RRTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(3, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 2, false));
        this.goalSelector.addGoal(4, new FollowVariantLeaderGoal(this));
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "ornate";
            case 2 -> "cownose";
            default -> "spotted";
        };
    }

    @Override
    public int getMaxSchoolSize() {
        return 4;
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.SPOTTED_EAGLE_RAY_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        if(variantChange <= 0.33F) {
        } else if(variantChange <= 0.66F){
            this.setVariant(2);
        }else if(variantChange <= 0.99F){
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
                    Ray urine = new Ray(ReefEntities.RAY.get(), this.level());
                    urine.setVariant(this.getVariant());
                    urine.moveTo(this.getX(), this.getY(), this.getZ());
                    urine.startFollowing(this);
                    this.level().addFreshEntity(urine);
                }
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public void tick() {
        //START of IK
        //the entity rotations must be negativized because we want the points to be transformed relative to the entity

        super.tick();
        this.tailKinematics.TakePerTickAction(this);

        if (this.level().isClientSide()) {
            if (this.animTime == (int)(8 * 20 / (this.animSpeed))) {
                this.animTime = 0;
                this.animSpeed = 0.5 + (1 * Math.random());
                //animation speed ranges from 0.5 times, to 1.5 times)

            } else {
                this.animTime++;
            }
        }
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.idleAnimationState.animateWhen(this.isAlive() && this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
    }
}