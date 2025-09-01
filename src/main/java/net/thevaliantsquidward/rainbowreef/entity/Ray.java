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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics.IKSolver;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Ray extends VariantSchoolingFish {

    public IKSolver tailKinematics;
    public int animationTime;
    public double animationSpeed = 1;

    public Ray(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 360, 2, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
        this.tailKinematics = new IKSolver(this, 5, 0.5, 0.75,false, false);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new FishDigGoal(this, 40, 1200, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(3, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 2, false));
        this.goalSelector.addGoal(4, new FollowVariantLeaderGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        // the entity rotations must be negativized because we want the points to be transformed relative to the entity
        this.tailKinematics.TakePerTickAction(this);

        // animation speed ranges from 0.5 times, to 1.5 times)
        if (this.level().isClientSide()) {
            if (this.animationTime == (int) (8 * 20 / (this.animationSpeed))) {
                this.animationTime = 0;
                this.animationSpeed = 0.5 + (1 * Math.random());
            } else {
                this.animationTime++;
            }
        }
    }

    @Override
    public int getMaxSchoolSize() {
        return 4;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.RAY_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return RayVariant.values().length;
    }

    public enum RayVariant implements StringRepresentable {
        SPOTTED(1, "spotted", COMMON, null),
        COWNOSE(2, "cownose", COMMON, null),
        ORNATE(3, "ornate", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        RayVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static RayVariant getVariantId(int variants) {
            for (RayVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return RayVariant.SPOTTED;
        }

        public static RayVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<RayVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<RayVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<RayVariant> variants = Lists.newArrayList();
            for (RayVariant variant : RayVariant.values()) {
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
        int variant = RayVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(RayVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof RayData) {
            variant = ((RayData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new RayData(variant);
            }
        }
        this.setVariant(RayVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class RayData implements SpawnGroupData {
        public final int variantData;

        public RayData(int variant) {
            this.variantData = variant;
        }
    }
}