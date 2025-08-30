package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.GroundseekingRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.Month;

public class Goby extends ReefMob {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Goby(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000000, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 100.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new GroundseekingRandomSwimGoal(this, 0.8D, 75, 5, 10, 0.01));
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "purplefire";
            case 2 -> "candycane";
            case 3 -> "mandarin";
            case 4 -> "yellowwatchman";
            case 5 -> "catalina";
            case 6 -> "blackray";
            case 7 -> "helfrichi";
            case 8 -> "blueneon";
            case 9 -> "yellowneon";
            case 10 -> "neonhybrid";
            case 11 -> "bluestreak";
            case 12 -> "leopardspotted";
            case 13 -> "yellowclown";
            case 14 -> "dracula";
            case 15 -> "blackfin";
            default -> "fire";
        };
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

   @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.GOBY_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            this.setVariant(14);
        } if(variantChange <= 0.001F){
            this.setVariant(10);
        } else if(variantChange <= 0.06F){
            this.setVariant(1);
        } else if(variantChange <= 0.12F){
            this.setVariant(2);
        } else if(variantChange <= 0.18F){
            this.setVariant(3);
        }else if(variantChange <= 0.24F){
            this.setVariant(4);
        }else if(variantChange <= 0.30F){
            this.setVariant(5);
        }else if(variantChange <= 0.36F){
            this.setVariant(6);
        }else if(variantChange <= 0.42F){
            this.setVariant(7);
        }else if(variantChange <= 0.48F){
            this.setVariant(8);
        }else if(variantChange <= 0.54F){
            this.setVariant(9);
        }else if(variantChange <= 0.60F){
            this.setVariant(11);
        }else if(variantChange <= 0.66F){
            this.setVariant(12);
        }else if(variantChange <= 0.72F){
            this.setVariant(13);
        }else if(variantChange <= 0.78F){
            this.setVariant(14);
        }else if(variantChange <= 0.84F){
            this.setVariant(15);
        }else{
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean canSpawn(EntityType<Goby> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}