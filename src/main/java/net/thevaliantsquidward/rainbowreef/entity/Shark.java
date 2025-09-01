package net.thevaliantsquidward.rainbowreef.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Shark extends VariantSchoolingFish {

    public final AnimationState biteAnimationState = new AnimationState();

    public Shark(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 3, false));
        this.goalSelector.addGoal(2, new FollowVariantLeaderGoal(this));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
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
        return SharkVariant.values().length;
    }

    public enum SharkVariant implements StringRepresentable {
        WHITETIP_REEF(1, "whitetip_reef", COMMON, null),
        BLACKTIP_REEF(2, "blacktip_reef", COMMON, null),
        LEMON(3, "lemon", RARE, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        SharkVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static SharkVariant getVariantId(int variants) {
            for (SharkVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return SharkVariant.WHITETIP_REEF;
        }

        public static SharkVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<SharkVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, RARE).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<SharkVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<SharkVariant> variants = Lists.newArrayList();
            for (SharkVariant variant : SharkVariant.values()) {
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        int variant = SharkVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(SharkVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof SharkData) {
            variant = ((SharkData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new SharkData(variant);
            }
        }
        this.setVariant(SharkVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class SharkData implements SpawnGroupData {
        public final int variantData;

        public SharkData(int variant) {
            this.variantData = variant;
        }
    }
}