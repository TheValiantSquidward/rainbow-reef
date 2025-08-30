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
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DwarfAngelfish extends ReefMob implements Bucketable {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public DwarfAngelfish(EntityType<? extends ReefMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 120);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FishDigGoal(this, 30, RRTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 3, false));
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "coralbeauty";
            case 2 -> "candycane"; //r
            case 3 -> "flame";
            case 4 -> "spotted";
            case 5 -> "masked";
            case 6 -> "cherub";
            case 7 -> "japanese"; //r
            case 8 -> "blacknox";
            case 9 -> "lamarck";
            case 10 -> "lemonpeel";
            case 11 -> "yellow";
            case 12 -> "orangepeel"; //ab
            case 13 -> "pearlscale";
            case 14 -> "resplendent";
            case 15 -> "yellowtail"; //r
            default -> "bicolor";
        };
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.DWARF_ANGELFISH_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float aberrant = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float aberrantVariantChange = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();

        if(aberrant <= 0.001) {
            if(aberrantVariantChange <= 1F){
                this.setVariant(12);

            }

        } else
        if(rare <= 0.10) {
            if(rareVariantChange <= 0.33F){
                this.setVariant(2);
            }else
            if(rareVariantChange <= 0.66F){
                this.setVariant(7);
            }else
            {
                this.setVariant(15);
            }
        } else

        if(variantChange <= 0.08F){
            this.setVariant(1);
        }else if(variantChange <= 0.16F){
            this.setVariant(3);
        }else if(variantChange <= 0.24F){
            this.setVariant(4);
        }else if(variantChange <= 0.30F){
            this.setVariant(5);
        }else if(variantChange <= 0.38F){
            this.setVariant(6);
        }else if(variantChange <= 0.46F){
            this.setVariant(8);
        }else if(variantChange <= 0.54F){
            this.setVariant(9);
        }else if(variantChange <= 0.62F){
            this.setVariant(10);
        }else if(variantChange <= 0.70F){
            this.setVariant(11);
        }else if(variantChange <= 0.78F){
            this.setVariant(13);
        }else if(variantChange <= 0.86F){
            this.setVariant(14);
        } else{
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean canSpawn(EntityType<DwarfAngelfish> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}