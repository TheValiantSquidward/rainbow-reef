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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.util.RRTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Tang extends VariantSchoolingFish {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState flopAnimationState = new AnimationState();

    public Tang(EntityType<? extends VariantSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, 180);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6D)
                .add(Attributes.MOVEMENT_SPEED, 1D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FishDigGoal(this, 10, RRTags.TANG_DIET));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 0.8, 1, 20, 20, 3, false));
    }

    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isInWaterOrBubble(), this.tickCount);
        this.flopAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "powderblue";
            case 2 -> "yellow";
            case 3 -> "unicorn";
            case 4 -> "convict";
            case 5 -> "clown";
            case 6 -> "achilles";
            case 7 -> "purple";
            case 8 -> "black";
            case 9 -> "regalblue";
            case 10 -> "gem";
            case 11 -> "penguin";
            case 12 -> "greenspot";
            case 13 -> "rusty";
            case 14 -> "pearly";
            case 15 -> "yellowbellyblue";
            case 16 -> "muddy";
            case 17 -> "chocolate";
            case 18 -> "sailfin";
            case 19 -> "atlanticblue";
            case 20 -> "eyestripe";
            case 21 -> "whitecheek";
            case 22 -> "scopas";
            case 23 -> "goth";
            case 24 -> "powderhybrid";
            case 25 -> "pastelblue";
            case 26 -> "yellowstrike";
            case 27 -> "blacksurgeon";
            case 28 -> "orangeband";
            case 29 -> "blondelipstick";
            case 30 -> "whitetailbristletooth";
            case 31 -> "zebra";
            default -> "bluehippo";
        };
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket();
    }

    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float aberrant = this.getRandom().nextFloat();
        float rare = this.getRandom().nextFloat();
        float variantChange = this.getRandom().nextFloat();
        float aberrantVariantChange = this.getRandom().nextFloat();
        float rareVariantChange = this.getRandom().nextFloat();

        if(aberrant <= 0.001){
            if (aberrantVariantChange <= 0.11F) {
                this.setVariant(11);
            } else
            if (aberrantVariantChange <= 0.22F) {
                this.setVariant(12);
            } else
            if (aberrantVariantChange <= 0.33F) {
                this.setVariant(13);
            } else
            if (aberrantVariantChange <= 0.44F) {
                this.setVariant(14);
            } else
            if (aberrantVariantChange <= 0.55F) {
                this.setVariant(15);
            } else if (aberrantVariantChange <= 0.66F) {
                this.setVariant(16);
            } else if (aberrantVariantChange <= 0.77F) {
                this.setVariant(23);
            } else if (aberrantVariantChange <= 0.88F) {
                this.setVariant(24);
            } else if (aberrantVariantChange <= 0.99F) {
                this.setVariant(26);
            } else {
                this.setVariant(25);
            }

        }
        if(rare <= 0.15){
            if (rareVariantChange <= 0.16F) {
                this.setVariant(7);
            } else
            if (rareVariantChange <= 0.32F) {
                this.setVariant(8);
            } else
            if (rareVariantChange <= 0.48F) {
                this.setVariant(9);
            } else
            if (rareVariantChange <= 0.64F) {
                this.setVariant(10);
            } else
            if (rareVariantChange <= 0.80F) {
                this.setVariant(21);
            } else
            if (rareVariantChange <= 0.96F) {
                this.setVariant(30);
            } else {
                this.setVariant(31);
        }
        }
        else {
            if (variantChange <= 0.07F) {
                this.setVariant(1);
            } else
                if (variantChange <= 0.14F) {
                this.setVariant(2);
            } else
                if (variantChange <= 0.21F) {
                    this.setVariant(3);
                } else
                if (variantChange <= 0.28F) {
                    this.setVariant(4);
                } else
                if (variantChange <= 0.35F) {
                    this.setVariant(5);
                } else
                if (variantChange <= 0.42F) {
                    this.setVariant(6);
                } else
                if (variantChange <= 0.49F) {
                    this.setVariant(17);
                } else
                if (variantChange <= 0.56F) {
                    this.setVariant(18);
                } else
                if (variantChange <= 0.63F) {
                    this.setVariant(19);
                } else
                if (variantChange <= 0.70F) {
                    this.setVariant(20);
                } else
                if (variantChange <= 0.77F) {
                    this.setVariant(22);
                } else
                if (variantChange <= 0.84F) {
                    this.setVariant(27);
                } else
                if (variantChange <= 0.91F) {
                    this.setVariant(28);
                } else
                if (variantChange <= 0.98F) {
                    this.setVariant(29);

                } else {
                    this.setVariant(0);
            }
        }

        if (this.getRandom().nextFloat() >= 0.90) {
            if (reason == MobSpawnType.CHUNK_GENERATION || reason == MobSpawnType.NATURAL
                //|| reason == MobSpawnType.SPAWN_EGG
            ) {
                float schoolsize = this.getRandom().nextFloat();
                int schoolcount = (int) ((this.getMaxSchoolSize() * schoolsize));

                if (schoolcount > 0 && !this.level().isClientSide()) {
                    for (int i = 0; i < schoolcount; i++) {
                        Tang urine = new Tang(ReefEntities.TANG.get(), this.level());
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

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.TANG_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    public static boolean canSpawn(EntityType<Tang> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}
