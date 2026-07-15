package com.valiantenvoy.rainbow_reef.entity;

import com.google.common.collect.Lists;
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
import com.valiantenvoy.rainbow_reef.entity.ai.goals.CustomizableRandomSwimGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishDigGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static com.valiantenvoy.rainbow_reef.entity.base.ReefMob.ReefRarities.COMMON;

public class Wrasse extends ReefMob {

    public Wrasse(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 15, 600, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 3, false));
    }

    @Override
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFLYFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return WrasseVariant.values().length;
    }

    public enum WrasseVariant implements StringRepresentable {
        BLACKEAR(1, "blackear"),
        BLUEHEAD(2, "bluehead"),
        BLUESTREAK_CLEANER(3, "bluestreak_cleaner"),
        CORTEZ_RAINBOW(4, "cortez_rainbow"),
        CREOLE(5, "creole"),
        GREENBIRD(6, "greenbird"),
        JANSENS(7, "jansens"),
        ORANGE_DOTTED(8, "orange_dotted"),
        PEACOCK(9, "peacock"),
        RAINBOW_MEDITERRANEAN(10, "rainbow_mediterranean"),
        SIXBAR(11, "sixbar"),
        SURGE(12, "surge"),
        YELLOWHEAD(13, "yellowhead"),
        YELLOWTAIL(14, "yellowtail");

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        WrasseVariant(int variant, String name) {
            this.variant = variant;
            this.name = name;
            this.rarity = ReefRarities.COMMON;
            this.biome = null;
        }

        public static WrasseVariant getVariantId(int variants) {
            for (WrasseVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return WrasseVariant.BLACKEAR;
        }

        public static WrasseVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<WrasseVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<WrasseVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<WrasseVariant> variants = Lists.newArrayList();
            for (WrasseVariant variant : WrasseVariant.values()) {
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
        int variant = WrasseVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (spawnData instanceof WrasseData) {
            variant = ((WrasseData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new WrasseData(variant);
            }
        }
        this.setVariant(WrasseVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    record WrasseData(int variantData) implements SpawnGroupData {
    }
}