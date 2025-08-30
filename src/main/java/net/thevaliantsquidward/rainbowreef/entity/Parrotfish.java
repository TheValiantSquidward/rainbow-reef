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
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RandomSleepyLookaroundGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RandomSleepySwimGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Parrotfish extends VariantSchoolingFish {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();
    public final AnimationState eepyAnimationState = new AnimationState();

    public Parrotfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level, 180);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 1.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.PARROTFISH_DIET));
        this.goalSelector.addGoal(4, new RandomSleepyLookaroundGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new RandomSleepySwimGoal(this, 0.8, 1));
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "humphead";
            case 2 -> "rainbow";
            case 3 -> "midnight";
            case 4 -> "stoplight";
            case 5 -> "mediterranean";
            case 6 -> "princess";
            case 7 -> "yellowtail";
            case 8 -> "bluebumphead";
            case 9 -> "red";
            case 10 -> "yellowband";
            case 11 -> "obishime";
            default -> "blue";
        };
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public void setupAnimationStates() {
        long roundedTime = this.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;

        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
        this.eepyAnimationState.animateWhen(this.isInWaterOrBubble() && night, this.tickCount);
        if (night) {
            this.swimAnimationState.stop();
        }
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.PARROTFISH_BUCKET.get());
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
        if(rare <= 0.10) {
            if(rareVariantChange <= 0.25F){
                this.setVariant(2);
            }else
            if(rareVariantChange <= 0.50F){
                this.setVariant(8);
            }else
            if(rareVariantChange <= 0.75F){
                this.setVariant(11);
            }else
            {
                this.setVariant(9);
            }
        } else
        if(variantChange <= 0.11F) {
            this.setVariant(7);
        } else if(variantChange <= 0.22F) {
            this.setVariant(6);
        } else if(variantChange <= 0.33F){
            this.setVariant(5);
        }else if(variantChange <= 0.44F){
            this.setVariant(4);
        }else if(variantChange <= 0.55F){
            this.setVariant(3);
        }else if(variantChange <= 0.66F){
            this.setVariant(1);
        }else if(variantChange <= 0.77F){
            this.setVariant(10);
        }else{
            this.setVariant(0);
        }


        if (this.getRandom().nextFloat() >= 0.75) {
            if (reason == MobSpawnType.CHUNK_GENERATION || reason == MobSpawnType.NATURAL
                //|| reason == MobSpawnType.SPAWN_EGG
            ) {
                float schoolsize = this.getRandom().nextFloat();
                int schoolcount = (int) ((this.getMaxSchoolSize() * schoolsize));

                if (schoolcount > 0 && !this.level().isClientSide()) {
                    for (int i = 0; i < schoolcount; i++) {
                        Parrotfish urine = new Parrotfish(ReefEntities.PARROTFISH.get(), this.level());
                        urine.setVariant(this.getVariant());
                        urine.moveTo(this.getX(), this.getY(), this.getZ());
                        urine.startFollowing(this);
                        this.level().addFreshEntity(urine);
                    }
                }
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean canSpawn(EntityType<Parrotfish> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}