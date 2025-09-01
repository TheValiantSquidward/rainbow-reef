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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Angelfish extends VariantSchoolingFish {

    public Angelfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 20, 300, ReefTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 3, false));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.ANGELFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return AngelfishVariant.values().length;
    }

    public enum AngelfishVariant implements StringRepresentable {
        QUEEN(1,  "queen", COMMON, null),
        FRENCH(2, "french", UNCOMMON, null),
        EMPEROR(3, "emperor", UNCOMMON, null),
        YELLOWBAND(4, "yellowband", COMMON, null),
        BLUERING(5, "bluering", COMMON, null),
        ROCK_BEAUTY(6, "rock_beauty", RARE, null),
        BLUE_QUEEN(7, "blue_queen", RARE, null),
        MAJESTIC(8, "majestic", UNCOMMON, null),
        KING(9, "king", RARE, null),
        SEMICIRCLE(10, "semicircle", COMMON, null),
        BANDED(11, "banded", COMMON, null),
        GRAY(12, "gray", COMMON, null),
        OLD_WOMAN(13, "old_woman", COMMON, null),
        GUINEAN(14, "guinean", COMMON, null),
        QUEENSLAND_YELLOWTAIL(15, "queensland_yellowtail", COMMON, null),
        CLARION(16, "clarion", UNCOMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        AngelfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static AngelfishVariant getVariantId(int variants) {
            for (AngelfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return AngelfishVariant.QUEEN;
        }

        public static AngelfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<AngelfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, UNCOMMON, RARE).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<AngelfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<AngelfishVariant> variants = Lists.newArrayList();
            for (AngelfishVariant variant : AngelfishVariant.values()) {
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
        @NotNull
        public String getSerializedName() {
            return this.name;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        int variant = AngelfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(AngelfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof AngelfishData) {
            variant = ((AngelfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new AngelfishData(variant);
            }
        }
        this.setVariant(AngelfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class AngelfishData implements SpawnGroupData {
        public final int variantData;

        public AngelfishData(int variant) {
            this.variantData = variant;
        }
    }
}