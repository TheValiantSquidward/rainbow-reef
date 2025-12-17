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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.COMMON;

public class Fusilier extends VariantSchoolingFish {

    public Fusilier(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
    }

    @Override
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public int getMaxSchoolSize() {
        return 16;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.BUTTERFLYFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return FusilierVariant.values().length;
    }

    public enum FusilierVariant implements StringRepresentable {
        YELLOWBACK(1, "yellowback", COMMON, null),
        DARK_BANDED(2, "dark_banded", COMMON, null),
        LUNAR(3, "lunar", COMMON, null),
        REDBELLY_YELLOWTAIL(4, "redbelly_yellowtail", COMMON, null),
        STRIPED(5, "striped", COMMON, null),
        YELLOW_AND_BLUE_BACK(6, "yellow_and_blue_back", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        FusilierVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static FusilierVariant getVariantId(int variants) {
            for (FusilierVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return FusilierVariant.YELLOWBACK;
        }

        public static FusilierVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<FusilierVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<FusilierVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<FusilierVariant> variants = Lists.newArrayList();
            for (FusilierVariant variant : FusilierVariant.values()) {
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
        int variant = FusilierVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(FusilierVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof FusilierData) {
            variant = ((FusilierData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new FusilierData(variant);
            }
        }
        this.setVariant(FusilierVariant.getVariantId(variant).getVariant());

        if (spawnType == MobSpawnType.CHUNK_GENERATION || spawnType == MobSpawnType.NATURAL) {
            int schoolCount = (int) (this.getMaxSchoolSize() * this.getRandom().nextFloat());
            if (schoolCount > 0 && !this.level().isClientSide()) {
                for (int i = 0; i < schoolCount; i++) {
                    float distance = 1.5F;
                    Fusilier entity = new Fusilier(ReefEntities.FUSILIER.get(), this.level());
                    entity.setVariant(this.getVariant());
                    entity.moveTo(this.getX() + this.getRandom().nextFloat() * distance, this.getY() + this.getRandom().nextFloat() * distance, this.getZ() + this.getRandom().nextFloat() * distance);
                    entity.startFollowing(this);
                    this.level().addFreshEntity(entity);
                }
            }
        }
        return spawnData;
    }

    static class FusilierData implements SpawnGroupData {
        public final int variantData;

        public FusilierData(int variant) {
            this.variantData = variant;
        }
    }
}