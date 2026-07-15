package com.valiantenvoy.rainbow_reef.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CustomizableRandomSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.valiantenvoy.rainbow_reef.entity.base.ReefMob.ReefRarities.COMMON;

public class LargeShark extends ReefMob {

    public final AnimationState biteAnimationState = new AnimationState();

    public LargeShark(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 360, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 28.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new CustomizableRandomSwimGoal(this, 1, 10));

    }
    @Override
    public void setupAnimationStates() {
        super.setupAnimationStates();
//        this.biteAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public void tickFlopping() {
        if (!this.isInWaterOrBubble() && onLandProgress < 5F) {
            onLandProgress++;
        }
        if (this.isInWaterOrBubble() && onLandProgress > 0F) {
            onLandProgress--;
        }
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public int getVariantCount() {
        return LargeSharkVariant.values().length;
    }

    public enum LargeSharkVariant implements StringRepresentable {
        BULL(1, "bull"),
        TIGER(2, "tiger");

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        LargeSharkVariant(int variant, String name) {
            this.variant = variant;
            this.name = name;
            this.rarity = ReefRarities.COMMON;
            this.biome = null;
        }

        public static LargeSharkVariant getVariantId(int variants) {
            for (LargeSharkVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return LargeSharkVariant.BULL;
        }

        public static LargeSharkVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<LargeSharkVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<LargeSharkVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<LargeSharkVariant> variants = Lists.newArrayList();
            for (LargeSharkVariant variant : LargeSharkVariant.values()) {
                if ((fromBucket || variant.biome == null || biome.is(variant.biome)) && variant.rarity == rarity) {
                    variants.add(variant);
                }
            }
            return variants;
        }

        public int getVariant() {
            return this.variant;
        }

        public ReefRarities getRarity() {
            return this.rarity;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnData) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData);
        int variant = LargeSharkVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (spawnData instanceof LargeSharkData) {
            variant = ((LargeSharkData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new LargeSharkData(variant);
            }
        }
        this.setVariant(LargeSharkVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    record LargeSharkData(int variantData) implements SpawnGroupData {
    }
}