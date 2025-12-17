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
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

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
        BLACKEAR(1, "blackear", COMMON, null),
        BLUEHEAD(2, "bluehead", COMMON, null),
        BLUESTREAK_CLEANER(3, "bluestreak_cleaner", COMMON, null),
        CORTEZ_RAINBOW(4, "cortez_rainbow", COMMON, null),
        CREOLE(5, "creole", COMMON, null),
        GREENBIRD(6, "greenbird", COMMON, null),
        JANSENS(7, "jansens", COMMON, null),
        ORANGE_DOTTED(8, "orange_dotted", COMMON, null),
        PEACOCK(9, "peacock", COMMON, null),
        RAINBOW_MEDITERRANEAN(10, "rainbow_mediterranean", COMMON, null),
        SIXBAR(11, "sixbar", COMMON, null),
        SURGE(12, "surge", COMMON, null),
        YELLOWHEAD(13, "yellowhead", COMMON, null),
        YELLOWTAIL(14, "yellowtail", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        WrasseVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        int variant = WrasseVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(WrasseVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
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

    static class WrasseData implements SpawnGroupData {
        public final int variantData;

        public WrasseData(int variant) {
            this.variantData = variant;
        }
    }
}