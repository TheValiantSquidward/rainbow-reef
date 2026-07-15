package com.valiantenvoy.rainbow_reef.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import com.valiantenvoy.rainbow_reef.entity.ai.control.ReefSwimmingMoveControl;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CustomizableRandomSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishLeapGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.navigation.WaterNavigation;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.valiantenvoy.rainbow_reef.entity.base.ReefMob.ReefRarities.COMMON;

public class Billfish extends ReefMob {

    public Billfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new ReefSwimmingMoveControl(this, 20, 10, 0.02F, 0.1F);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new FishLeapGoal(this));
        this.goalSelector.addGoal(2, new CustomizableRandomSwimGoal(this, 1, 10));
    }

    @Override
    @NotNull
    protected PathNavigation createNavigation(@NotNull Level level) {
        return new WaterNavigation(this, level, true);
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.05D) {
                Vec3 vec31 = this.getViewVector(0.0F);
                this.level().addParticle(ParticleTypes.BUBBLE, this.getRandomX(0.5D) - vec31.x * 0.8D, this.getRandomY() - vec31.y * 0.25D, this.getRandomZ(0.5D) - vec31.z * 0.8D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public int getVariantCount() {
        return BillfishVariant.values().length;
    }

    public enum BillfishVariant implements StringRepresentable {
        SAILFISH();

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        BillfishVariant() {
            this.variant = 1;
            this.name = "sailfish";
            this.rarity = ReefRarities.COMMON;
            this.biome = null;
        }

        public static BillfishVariant getVariantId(int variants) {
            for (BillfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return BillfishVariant.SAILFISH;
        }

        public static BillfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<BillfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<BillfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<BillfishVariant> variants = Lists.newArrayList();
            for (BillfishVariant variant : BillfishVariant.values()) {
                if ((fromBucket || variant.biome == null || category.is(variant.biome)) && variant.rarity == rarity) {
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
        int variant = BillfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (spawnData instanceof BillfishData) {
            variant = ((BillfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new BillfishData(variant);
            }
        }
        this.setVariant(BillfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    record BillfishData(int variantData) implements SpawnGroupData {
    }
}
