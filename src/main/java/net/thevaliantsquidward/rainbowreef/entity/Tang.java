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
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Tang extends VariantSchoolingFish {

    public Tang(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, false);
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
        this.goalSelector.addGoal(3, new FishDigGoal(this, 10, 200, ReefTags.TANG_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10, 20, 20, 3, false));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 20;
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TANG_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return TangVariant.values().length;
    }

    public enum TangVariant implements StringRepresentable {
        BLUE(1, "blue", COMMON, null),
        POWDER_BLUE(2, "powder_blue", COMMON, null),
        YELLOW(3, "yellow", COMMON, null),
        UNICORN(4, "unicorn", UNCOMMON, null),
        CONVICT(5, "convict", COMMON, null),
        CLOWN(6, "clown", COMMON, null),
        ACHILLES(7, "achilles", COMMON, null),
        PURPLE(8, "purple", UNCOMMON, null),
        BLACK(9, "black", RARE, null),
        REGAL_BLUE(10, "regal_blue", EPIC, null),
        GEM(11, "gem", EPIC, null),
        PENGUIN(12, "penguin", ABERRANT, null),
        GREEN_SPOT(13, "green_spot", ABERRANT, null),
        RUSTY(14, "rusty", ABERRANT, null),
        PEARLY(15, "pearly", ABERRANT, null),
        YELLOWBELLY_BLUE(16, "yellowbelly_blue", ABERRANT, null),
        MUDDY(17, "muddy", ABERRANT, null),
        CHOCOLATE(18, "chocolate", UNCOMMON, null),
        SAILFIN(19, "sailfin", COMMON, null),
        ATLANTIC_BLUE(20, "atlantic_blue", COMMON, null),
        EYESTRIPE(21, "eyestripe", COMMON, null),
        WHITE_CHEEK(22, "white_cheek", UNCOMMON, null),
        SCOPAS(23, "scopas", COMMON, null),
        GOTH(24, "goth", ABERRANT, null),
        POWDER_BLUE_HYBRID(25, "powder_blue_hybrid", ABERRANT, null),
        PASTEL_BLUE(26, "pastel_blue", ABERRANT, null),
        YELLOWSTRIKE(27, "yellowstrike", ABERRANT, null),
        BLACK_SURGEON(28, "black_surgeon", RARE, null),
        ORANGEBAND(29, "orangeband", UNCOMMON, null),
        BLONDE_LIPSTICK(30, "blonde_lipstick", RARE, null),
        WHITETAIL_BRISTLETOOTH(31, "whitetail_bristletooth", UNCOMMON, null),
        ZEBRA(32, "zebra", RARE, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        TangVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static TangVariant getVariantId(int variants) {
            for (TangVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return TangVariant.BLUE;
        }

        public static TangVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<TangVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(ReefRarities.values()).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<TangVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<TangVariant> variants = Lists.newArrayList();
            for (TangVariant variant : TangVariant.values()) {
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
        int variant = TangVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(TangVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof TangData) {
            variant = ((TangData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new TangData(variant);
            }
        }
        this.setVariant(TangVariant.getVariantId(variant).getVariant());

        if (spawnType == MobSpawnType.CHUNK_GENERATION || spawnType == MobSpawnType.NATURAL) {
            int schoolCount = (int) (this.getMaxSchoolSize() * this.getRandom().nextFloat());
            if (schoolCount > 0 && !this.level().isClientSide()) {
                for (int i = 0; i < schoolCount; i++) {
                    float distance = 1.5F;
                    Tang entity = new Tang(ReefEntities.TANG.get(), this.level());
                    entity.setVariant(this.getVariant());
                    entity.moveTo(this.getX() + this.getRandom().nextFloat() * distance, this.getY() + this.getRandom().nextFloat() * distance, this.getZ() + this.getRandom().nextFloat() * distance);
                    entity.startFollowing(this);
                    this.level().addFreshEntity(entity);
                }
            }
        }
        return spawnData;
    }

    static class TangData implements SpawnGroupData {
        public final int variantData;

        public TangData(int variant) {
            this.variantData = variant;
        }
    }
}
