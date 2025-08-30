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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.LocateNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.MoveToNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.NemHoster;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.RestInNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.VariantEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Clownfish extends NemHoster {

    int nemSearchCooldown;

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Clownfish(EntityType<? extends NemHoster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 200, 4, 600, 200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, true);
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
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 0.8D, 40));

        this.goalSelector.addGoal(0, new LocateNemGoal(this, 200));
        this.goalSelector.addGoal(0, new MoveToNemGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new RestInNemGoal(this, 3, 600, 200));
        //Anemone seeker goal plan:
        //priority of 0, but only works if the clown has a home nem and is over 10 blocks from it
        //Pathfinds back to home nem and makes it hide for 3 - 5 secs
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    public static boolean canSpawn(EntityType<Clownfish> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "blackandwhite";
            case 2 -> "maroon";
            case 3 -> "pinkskunk";
            case 4 -> "clarkii";
            case 5 -> "blizzard"; //r
            case 6 -> "tomato";
            case 7 -> "bluestrain"; //a
            case 8 -> "madagascar";
            case 9 -> "oman"; //r
            case 10 -> "allard";
            case 11 -> "mocha"; //r
            case 12 -> "whitesnout"; //r
            case 13 -> "goldnugget"; //r
            case 14 -> "redsaddleback";
            case 15 -> "snowstorm"; //r
            case 16 -> "orangeskunk"; //r
            case 17 -> "domino"; //r
            case 18 -> "yellowclarkii"; //r
            case 19 -> "naked"; //r


            default -> "ocellaris";
        };
    }

    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.CLOWNFISH_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();
        if(variantChange <= 0.001){
            this.setVariant(7);
        } else
        if (variantChange <= 0.13F) {
            this.setVariant(1);
        } else
        if (variantChange <= 0.26F) {
            this.setVariant(2);
        } else
        if (variantChange <= 0.39F) {
            this.setVariant(3);
        } else
        if (variantChange <= 0.42F) {
            this.setVariant(4);
        } else
        if (variantChange <= 0.55F) {
            this.setVariant(6);
        } else
        if (variantChange <= 0.60F) {
            this.setVariant(8);
        } else
        if (variantChange <= 0.81F) {
            this.setVariant(10);
        } else
        if (variantChange <= 0.94F) {
            this.setVariant(14);
        }else{
            this.setVariant(0);
        }
        if(rare <= 0.05){
            if (rareVariantChange <= 0.10F) {
                this.setVariant(9);
            } else
            if (rareVariantChange <= 0.20F) {
                this.setVariant(11);
            } else
            if (rareVariantChange <= 0.30F) {
                this.setVariant(12);
            } else {
            if (rareVariantChange <= 0.40F) {
                    this.setVariant(13);
           } else
           if (rareVariantChange <= 0.50F) {
                    this.setVariant(15);
           } else
           if (rareVariantChange <= 0.60F) {
                    this.setVariant(16);
           } else
           if (rareVariantChange <= 0.70F) {
                    this.setVariant(17);
           } else
           if (rareVariantChange <= 0.80F) {
               this.setVariant(18);
           } else
           if (rareVariantChange <= 0.90F) {
               this.setVariant(19);
           }else{
                    this.setVariant(5);
                }
            }
        }

        findAndSetNems();

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}