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
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class MaoriWrasse extends ReefMob {

    public MaoriWrasse(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.6F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new CustomizableRandomSwimGoal(this, 1, 10));
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TANG_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return MaoriWrasseVariant.values().length;
    }

    public enum MaoriWrasseVariant implements StringRepresentable {
        TEAL(1, "teal", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        MaoriWrasseVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static MaoriWrasseVariant getVariantId(int variants) {
            for (MaoriWrasseVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return MaoriWrasseVariant.TEAL;
        }

        public static MaoriWrasseVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<MaoriWrasseVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<MaoriWrasseVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<MaoriWrasseVariant> variants = Lists.newArrayList();
            for (MaoriWrasseVariant variant : MaoriWrasseVariant.values()) {
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
        int variant = MaoriWrasseVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(MaoriWrasseVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof MaoriWrasseData) {
            variant = ((MaoriWrasseData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new MaoriWrasseData(variant);
            }
        }
        this.setVariant(MaoriWrasseVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class MaoriWrasseData implements SpawnGroupData {
        public final int variantData;

        public MaoriWrasseData(int variant) {
            this.variantData = variant;
        }
    }
}
