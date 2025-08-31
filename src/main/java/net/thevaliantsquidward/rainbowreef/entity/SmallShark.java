package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.GroundseekingRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SmallShark extends ReefMob {

    public IKSolver tailKinematics;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();

    public SmallShark(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level, 400);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 3, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
        this.tailKinematics = new IKSolver(this, 2, 0.3, 0.95, true, true);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 7D).add(Attributes.MOVEMENT_SPEED, 0.8D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FishDigGoal(this, 40, RRTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new GroundseekingRandomSwimGoal(this, 1, 100, 20, 20, 0.01));
    }

    @Override
    public void tick() {
        super.tick();
        this.tailKinematics.TakePerTickAction(this);
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.idleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEyeInFluid(FluidTags.WATER) && !this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.01, 0.0));
        }
        super.travel(pTravelVector);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new AdvancedWaterboundPathNavigation(this, level, true, false);
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "pajama";
            case 2 -> "horned";
            case 3 -> "nurse";
            case 4 -> "zebra";
            case 5 -> "albino";
            case 6 -> "piebald";
            case 7 -> "portjackson";
            default -> "epaulette";
        };
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.SHARK_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float aberrant = this.getRandom().nextFloat();
        float aberrantVariantChange = this.getRandom().nextFloat();
        if(aberrant <= 0.01) {
            if(aberrantVariantChange <= 0.50F){
                this.setVariant(5);
            }else
            {
                this.setVariant(6);
            }
        } else
        if(variantChange <= 0.15F) {
            this.setVariant(1);
        } else if(variantChange <= 0.30F) {
            this.setVariant(2);
        } else if(variantChange <= 0.45F){
            this.setVariant(3);
        }else if(variantChange <= 0.60F){
            this.setVariant(4);
        }else if(variantChange <= 0.75F){
            this.setVariant(7);
        }else{
            this.setVariant(0);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}