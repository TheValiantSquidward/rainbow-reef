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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefBiomeTags;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Butterflyfish extends VariantSchoolingFish {

    public Butterflyfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F)
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
    public int getMaxSchoolSize() {
        return 10;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return ButterflyfishVariant.values().length;
    }

    public enum ButterflyfishVariant implements StringRepresentable {
        COPPERBAND(1, "copperband", COMMON, null),
        WROUGHT_IRON(2, "wrought_iron", RARE, null),
        THREADFIN(3, "threadfin", COMMON, null),
        BANNER(4, "banner", COMMON, null),
        BLUECHEEK(5, "bluecheek", COMMON, null),
        LONGNOSE(6, "longnose", COMMON, null),
        SPOTFIN(7, "spotfin", COMMON, null),
        HOODED(8, "hooded", UNCOMMON, null),
        ARABIC(9, "arabic", UNCOMMON, null),
        PYRAMID(10, "pyramid", UNCOMMON, null),
        RED_SEA(11, "red_sea", RARE, null),
        STRIPED(12, "striped", UNCOMMON, null),
        SADDLEBACK(13, "saddleback", UNCOMMON, null),
        AFRICAN(14, "african", UNCOMMON, null),
        ERITREAN(15, "eritrean", RARE, null),
        MARGINATED(16, "marginated", UNCOMMON, null),
        THOMPSON(17, "thompson", UNCOMMON, null),
        MULLERS(18, "mullers", UNCOMMON, null),
        SIX_SPINED(19, "six_spined", UNCOMMON, ReefBiomeTags.HAS_BUTTERFLYFISH_MANGROVE),
        FOUREYE(20, "foureye", COMMON, ReefBiomeTags.HAS_BUTTERFLYFISH_MANGROVE),
        EASTER_ISLAND(21, "easter_island", RARE, null),
        DARK_LONGNOSE(22, "dark_longnose", ABERRANT, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        ButterflyfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static ButterflyfishVariant getVariantId(int variants) {
            for (ButterflyfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return ButterflyfishVariant.COPPERBAND;
        }

        public static ButterflyfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<ButterflyfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(ReefRarities.values()).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<ButterflyfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<ButterflyfishVariant> variants = Lists.newArrayList();
            for (ButterflyfishVariant variant : ButterflyfishVariant.values()) {
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
        int variant = ButterflyfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(ButterflyfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof ButterflyfishData) {
            variant = ((ButterflyfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new ButterflyfishData(variant);
            }
        }
        this.setVariant(ButterflyfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class ButterflyfishData implements SpawnGroupData {
        public final int variantData;

        public ButterflyfishData(int variant) {
            this.variantData = variant;
        }
    }
}