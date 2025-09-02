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
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RandomSleepyLookaroundGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RandomSleepySwimGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Parrotfish extends VariantSchoolingFish {

    public final AnimationState eepyAnimationState = new AnimationState();

    public Parrotfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 10, 800, ReefTags.PARROTFISH_DIET));
        this.goalSelector.addGoal(4, new RandomSleepySwimGoal(this, 1, 10));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
        this.goalSelector.addGoal(6, new RandomSleepyLookaroundGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public void setupAnimationStates() {
        super.setupAnimationStates();
        long roundedTime = this.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;

        this.eepyAnimationState.animateWhen(this.isInWaterOrBubble() && night, this.tickCount);
        if (night) {
            this.swimAnimationState.stop();
        }
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.PARROTFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return ParrotfishVariant.values().length;
    }

    public enum ParrotfishVariant implements StringRepresentable {
        BLUE(1, "blue", COMMON, null),
        HUMPHEAD(2, "humphead", COMMON, null),
        RAINBOW(3, "rainbow", COMMON, null),
        MIDNIGHT(4, "midnight", COMMON, null),
        STOPLIGHT(5, "stoplight", COMMON, null),
        MEDITERRANEAN(6, "mediterranean", UNCOMMON, null),
        PRINCESS(7, "princess", UNCOMMON, null),
        YELLOWTAIL(8, "yellowtail", COMMON, null),
        BLUE_BUMPHEAD(9, "blue_bumphead", RARE, null),
        RED(10, "red", UNCOMMON, null),
        YELLOWBAND(11, "yellowband", UNCOMMON, null),
        OBISHIME(12, "obishime", RARE, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        ParrotfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static ParrotfishVariant getVariantId(int variants) {
            for (ParrotfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return ParrotfishVariant.BLUE;
        }

        public static ParrotfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<ParrotfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, UNCOMMON, RARE).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<ParrotfishVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<ParrotfishVariant> variants = Lists.newArrayList();
            for (ParrotfishVariant variant : ParrotfishVariant.values()) {
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
        int variant = ParrotfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(ParrotfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof ParrotfishData) {
            variant = ((ParrotfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new ParrotfishData(variant);
            }
        }
        this.setVariant(ParrotfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class ParrotfishData implements SpawnGroupData {
        public final int variantData;

        public ParrotfishData(int variant) {
            this.variantData = variant;
        }
    }
}