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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.COMMON;

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
        this.goalSelector.addGoal(3, new FishDigGoal(this, 15, 600, ReefTags.BUTTERFLY_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 3, false));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public int getMaxSchoolSize() {
        return 30;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return DamselfishVariant.values().length;
    }

    public enum DamselfishVariant implements StringRepresentable {
        AZURE(1, "azure", COMMON, null),
        BARRIER_REEF(2, "barrier_reef", COMMON, null),
        BEAUGREGORY(3, "beaugregory", COMMON, null),
        BICOLOR(4, "bicolor", COMMON, null),
        BLUE(5, "blue", COMMON, null),
        BLUEFIN(6, "bluefin", COMMON, null),
        BROWN(7, "brown", COMMON, null),
        CANARY(8, "canary", COMMON, null),
        CHINESE_SCISSORTAIL(9, "chinese_scissortail", COMMON, null),
        DOMINO(10, "domino", COMMON, null),
        GREEN(11, "green", COMMON, null),
        INDIGO(12, "indigo", COMMON, null),
        JEWEL(13, "jewel", COMMON, null),
        SCISSORTAIL_SERGEANT_MAJOR(14, "scissortail_sergeant_major", COMMON, null),
        SERGEANT_MAJOR(15, "sergeant_major", COMMON, null),
        SUNSHINE(16, "sunshine", COMMON, null),
        THREE_STRIPE(17, "three_stripe", COMMON, null),
        TWO_TONE(18, "two_tone", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        DamselfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        int variant = DamselfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(DamselfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof DamselfishData) {
            variant = ((DamselfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new DamselfishData(variant);
            }
        }
        this.setVariant(DamselfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class DamselfishData implements SpawnGroupData {
        public final int variantData;

        public DamselfishData(int variant) {
            this.variantData = variant;
        }
    }
}