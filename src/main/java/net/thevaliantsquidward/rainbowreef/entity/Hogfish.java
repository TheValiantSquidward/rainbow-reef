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
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Hogfish extends ReefMob {

    public Hogfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 1D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 40, 500, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10));
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.HOGFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return HogfishVariant.values().length;
    }

    public enum HogfishVariant implements StringRepresentable {
        CUBAN(1, "cuban", COMMON, null),
        SPANISH(2, "spanish", COMMON, null),
        PEPPERMINT(3, "peppermint", UNCOMMON, null),
        LYRETAIL(4, "lyretail", UNCOMMON, null),
        CORAL(5, "coral", UNCOMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        HogfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static HogfishVariant getVariantId(int variants) {
            for (HogfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return HogfishVariant.CUBAN;
        }

        public static HogfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<HogfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, UNCOMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<HogfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<HogfishVariant> variants = Lists.newArrayList();
            for (HogfishVariant variant : HogfishVariant.values()) {
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
        int variant = HogfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(HogfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof HogfishData) {
            variant = ((HogfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new HogfishData(variant);
            }
        }
        this.setVariant(HogfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class HogfishData implements SpawnGroupData {
        public final int variantData;

        public HogfishData(int variant) {
            this.variantData = variant;
        }
    }
}