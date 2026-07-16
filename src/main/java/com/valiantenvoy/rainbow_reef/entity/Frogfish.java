package com.valiantenvoy.rainbow_reef.entity;

import com.google.common.collect.Lists;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.Holder;
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
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static com.valiantenvoy.rainbow_reef.entity.base.ReefMob.ReefRarities.COMMON;

public class Frogfish extends ReefMob {

    public Frogfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BOXFISH_BUCKET.get());
    }

    public enum FrogfishVariant implements StringRepresentable {
        CLOWN(1, "clown"),
        ORANGE_OCELLATED(2, "orange_ocellated"),
        PINK_OCELLATED(3, "pink_ocellated"),
        PSYCHEDELIC(4, "psychedelic"),
        RED_LONGLURE(5, "red_longlure"),
        SARGASSUM(6, "sargassum"),
        YELLOW_LONGLURE(6, "yellow_longlure");

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        FrogfishVariant(int variant, String name) {
            this.variant = variant;
            this.name = name;
            this.rarity = ReefRarities.COMMON;
            this.biome = null;
        }

        public static FrogfishVariant getVariantId(int variants) {
            for (FrogfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return FrogfishVariant.CLOWN;
        }

        public static FrogfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<FrogfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<FrogfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<FrogfishVariant> variants = Lists.newArrayList();
            for (FrogfishVariant variant : FrogfishVariant.values()) {
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
        int variant = FrogfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (spawnData instanceof FrogfishData) {
            variant = ((FrogfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new FrogfishData(variant);
            }
        }
        this.setVariant(FrogfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    record FrogfishData(int variantData) implements SpawnGroupData {
    }
}