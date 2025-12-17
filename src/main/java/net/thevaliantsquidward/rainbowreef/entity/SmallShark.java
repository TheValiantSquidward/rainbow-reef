package net.thevaliantsquidward.rainbowreef.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class SmallShark extends ReefMob {

    public SmallShark(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 3, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 40, 600, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 100));
    }

    @Override
    public float getWalkTargetValue(@NotNull BlockPos pos, @NotNull LevelReader level) {
        return this.getDepthPathfindingFavor(pos, level);
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }

    @Override
    public void setupAnimationStates() {
        this.swimIdleAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public float flopChance() {
        return 0.1F;
    }

    @Override
    public void travel(@NotNull Vec3 travelVec) {
        if (this.isEyeInFluid(FluidTags.WATER) && !this.isPathFinding()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.005, 0.0));
        }
        super.travel(travelVec);
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.SMALL_SHARK_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return SmallSharkVariant.values().length;
    }

    public enum SmallSharkVariant implements StringRepresentable {
        EPAULETTE(1, "epaulette", COMMON, null),
        PAJAMA(2, "pajama", UNCOMMON, null),
        HORNED(3, "horned", COMMON, null),
        NURSE(4, "nurse", COMMON, null),
        ZEBRA(5, "zebra", COMMON, null),
        ALBINO(6, "albino", ABERRANT, null),
        PIEBALD(7, "piebald", ABERRANT, null),
        PORT_JACKSON(8, "port_jackson", RARE, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        SmallSharkVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static SmallSharkVariant getVariantId(int variants) {
            for (SmallSharkVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return SmallSharkVariant.EPAULETTE;
        }

        public static SmallSharkVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<SmallSharkVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, UNCOMMON, RARE, ABERRANT).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<SmallSharkVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<SmallSharkVariant> variants = Lists.newArrayList();
            for (SmallSharkVariant variant : SmallSharkVariant.values()) {
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
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        int variant = SmallSharkVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(SmallSharkVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof SmallSharkData) {
            variant = ((SmallSharkData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new SmallSharkData(variant);
            }
        }
        this.setVariant(SmallSharkVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class SmallSharkData implements SpawnGroupData {
        public final int variantData;

        public SmallSharkData(int variant) {
            this.variantData = variant;
        }
    }
}