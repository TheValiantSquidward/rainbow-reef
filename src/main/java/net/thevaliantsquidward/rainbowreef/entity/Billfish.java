package net.thevaliantsquidward.rainbowreef.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishLeapGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Billfish extends ReefMob {

    public Billfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 8, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 7);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.6F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new FishLeapGoal(this, 10, 0.7D, 0.8D));
        this.goalSelector.addGoal(2, new CustomizableRandomSwimGoal(this, 1, 10, 48, 20, 3, false));
    }

    @Override
    @NotNull
    protected PathNavigation createNavigation(Level level) {
        return new AdvancedWaterboundPathNavigation(this, level, false, true);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            if (this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.05D) {
                Vec3 vec31 = this.getViewVector(0.0F);
                this.level().addParticle(ParticleTypes.BUBBLE, this.getRandomX(0.5D) - vec31.x * 0.8D, this.getRandomY() - vec31.y * 0.25D, this.getRandomZ(0.5D) - vec31.z * 0.8D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive(), this.tickCount);
    }

    @Override
    public void tickFlopping() {
        boolean onLand = !this.isInWaterOrBubble() && this.onGround();

        if (onLand  && onLandProgress < 5F) {
            onLandProgress++;
        }
        if (!onLand && onLandProgress > 0F) {
            onLandProgress--;
        }

        if (!this.isInWaterOrBubble() && this.isAlive()) {
            if (this.onGround() && this.getRandom().nextFloat() < 0.005F) {
                this.setDeltaMovement(this.getDeltaMovement().add((this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.1F, 0.3D, (this.getRandom().nextFloat() * 2.0F - 1.0F) * 0.1F));
                this.setYRot(this.getRandom().nextFloat() * 360.0F);
                this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
            }
        }
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public int getVariantCount() {
        return BillfishVariant.values().length;
    }

    public enum BillfishVariant implements StringRepresentable {
        SAILFISH(1, "sailfish", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        BillfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static BillfishVariant getVariantId(int variants) {
            for (BillfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return BillfishVariant.SAILFISH;
        }

        public static BillfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<BillfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<BillfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<BillfishVariant> variants = Lists.newArrayList();
            for (BillfishVariant variant : BillfishVariant.values()) {
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
        int variant = BillfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(BillfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof BillfishData) {
            variant = ((BillfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new BillfishData(variant);
            }
        }
        this.setVariant(BillfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class BillfishData implements SpawnGroupData {
        public final int variantData;

        public BillfishData(int variant) {
            this.variantData = variant;
        }
    }
}
