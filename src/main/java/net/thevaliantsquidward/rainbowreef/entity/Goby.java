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
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Goby extends ReefMob {

    public Goby(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
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
        this.goalSelector.addGoal(3, new GroundseekingRandomSwimGoal(this, 0.8D, 75, 5, 10, 0.01));
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.GOBY_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return GobyVariant.values().length;
    }

    public enum GobyVariant implements StringRepresentable {
        FIRE(1, "fire", ReefRarities.COMMON, null),
        PURPLE_FIRE(2, "purple_fire", ReefRarities.UNCOMMON, null),
        CANDYCANE(3, "candycane", ReefRarities.UNCOMMON, null),
        MANDARIN(4, "mandarin", ReefRarities.COMMON, null),
        YELLOW_WATCHMAN(5, "yellow_watchman", ReefRarities.COMMON, null),
        CATALINA(6, "catalina", ReefRarities.COMMON, null),
        BLACK_RAY(7, "black_ray", ReefRarities.COMMON, null),
        HELFRICHI(8, "helfrichi", ReefRarities.COMMON, null),
        BLUE_NEON(9, "blue_neon", ReefRarities.UNCOMMON, null),
        YELLOW_NEON(10, "yellow_neon", ReefRarities.UNCOMMON, null),
        NEON_HYBRID(11, "neon_hybrid", ReefRarities.RARE, null),
        BLUESTREAK(12, "bluestreak", ReefRarities.COMMON, null),
        LEOPARD_SPOTTED(13, "leopard_spotted", ReefRarities.COMMON, null),
        YELLOW_CLOWN(14, "yellow_clown", ReefRarities.COMMON, null),
        DRACULA(15, "dracula", ReefRarities.RARE, null),
        BLACKFIN(16, "blackfin", ReefRarities.COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        GobyVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static GobyVariant getVariantId(int variants) {
            for (GobyVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return GobyVariant.FIRE;
        }

        public static GobyVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<GobyVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(ReefRarities.values()).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<GobyVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<GobyVariant> variants = Lists.newArrayList();
            for (GobyVariant variant : GobyVariant.values()) {
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
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            this.setVariant(GobyVariant.DRACULA.getVariant());
        } else {
            int variant = GobyVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
            this.setVariant(GobyVariant.getVariantId(variant).getVariant());
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}