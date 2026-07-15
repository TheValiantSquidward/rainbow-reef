package com.valiantenvoy.rainbow_reef.entity;

import com.google.common.collect.Lists;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CustomizableRandomSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.valiantenvoy.rainbow_reef.entity.base.ReefMob.ReefRarities.COMMON;

public class Damselfish extends VariantSchoolingFish {

    public Damselfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 3, false));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
    }

    @Override
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public int getMaxSchoolSize() {
        return 24;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFLYFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return DamselfishVariant.values().length;
    }

    public enum DamselfishVariant implements StringRepresentable {
        AZURE(1, "azure"),
        BARRIER_REEF(2, "barrier_reef"),
        BEAUGREGORY(3, "beaugregory"),
        BICOLOR(4, "bicolor"),
        BLUE(5, "blue"),
        BLUEFIN(6, "bluefin"),
        BROWN(7, "brown"),
        CANARY(8, "canary"),
        CHINESE_SCISSORTAIL(9, "chinese_scissortail"),
        DOMINO(10, "domino"),
        GREEN(11, "green"),
        INDIGO(12, "indigo"),
        JEWEL(13, "jewel"),
        SCISSORTAIL_SERGEANT_MAJOR(14, "scissortail_sergeant_major"),
        SERGEANT_MAJOR(15, "sergeant_major"),
        SUNSHINE(16, "sunshine"),
        THREE_STRIPE(17, "three_stripe"),
        TWO_TONE(18, "two_tone");

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        DamselfishVariant(int variant, String name) {
            this.variant = variant;
            this.name = name;
            this.rarity = ReefRarities.COMMON;
            this.biome = null;
        }

        public static DamselfishVariant getVariantId(int variants) {
            for (DamselfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return DamselfishVariant.AZURE;
        }

        public static DamselfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<DamselfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<DamselfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<DamselfishVariant> variants = Lists.newArrayList();
            for (DamselfishVariant variant : DamselfishVariant.values()) {
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
        int variant = DamselfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (spawnData instanceof DamselfishData) {
            variant = ((DamselfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new DamselfishData(variant);
            }
        }
        this.setVariant(DamselfishVariant.getVariantId(variant).getVariant());

        if (spawnType == MobSpawnType.CHUNK_GENERATION || spawnType == MobSpawnType.NATURAL) {
            int schoolCount = (int) (this.getMaxSchoolSize() * this.getRandom().nextFloat());
            if (schoolCount > 0 && !this.level().isClientSide()) {
                for (int i = 0; i < schoolCount; i++) {
                    float distance = 1.5F;
                    Damselfish entity = new Damselfish(ReefEntities.DAMSELFISH.get(), this.level());
                    entity.setVariant(this.getVariant());
                    entity.moveTo(this.getX() + this.getRandom().nextFloat() * distance, this.getY() + this.getRandom().nextFloat() * distance, this.getZ() + this.getRandom().nextFloat() * distance);
                    entity.startFollowing(this);
                    this.level().addFreshEntity(entity);
                }
            }
        }
        return spawnData;
    }

    record DamselfishData(int variantData) implements SpawnGroupData {
    }
}