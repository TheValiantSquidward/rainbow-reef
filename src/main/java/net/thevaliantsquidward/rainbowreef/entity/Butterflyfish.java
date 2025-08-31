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
import net.minecraftforge.common.Tags;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class Butterflyfish extends VariantSchoolingFish {

    public Butterflyfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level, 600);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, true);
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
        this.goalSelector.addGoal(3, new FishDigGoal(this, 15, ReefTags.BUTTERFLY_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 3, false));
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
        COPPERBAND(1, "copperband", ReefRarities.COMMON, null),
        WROUGHT_IRON(2, "wrought_iron", ReefRarities.COMMON, null),
        THREADFIN(3, "threadfin", ReefRarities.COMMON, null),
        BANNER(4, "banner", ReefRarities.COMMON, null),
        BLUECHEEK(5, "bluecheek", ReefRarities.COMMON, null),
        LONGNOSE(6, "longnose", ReefRarities.COMMON, null),
        SPOTFIN(7, "spotfin", ReefRarities.COMMON, null),
        HOODED(8, "hooded", ReefRarities.UNCOMMON, null),
        ARABIC(9, "arabic", ReefRarities.UNCOMMON, null),
        PYRAMID(10, "pyramid", ReefRarities.UNCOMMON, null),
        RED_SEA(11, "red_sea", ReefRarities.UNCOMMON, null),
        STRIPED(12, "striped", ReefRarities.COMMON, null),
        SADDLEBACK(13, "saddleback", ReefRarities.COMMON, null),
        AFRICAN(14, "african", ReefRarities.UNCOMMON, null),
        ERITREAN(15, "eritrean", ReefRarities.COMMON, null),
        MARGINATED(16, "marginated", ReefRarities.UNCOMMON, null),
        THOMPSON(17, "thompson", ReefRarities.COMMON, null),
        MULLERS(18, "mullers", ReefRarities.COMMON, null),
        SIX_SPINED(19, "six_spined", ReefRarities.COMMON, Tags.Biomes.IS_SWAMP),
        FOUREYE(20, "foureye", ReefRarities.COMMON, Tags.Biomes.IS_SWAMP),
        EASTER_ISLAND(21, "easter_island", ReefRarities.RARE, null),
        DARK_LONGNOSE(22, "dark_longnose", ReefRarities.RARE, null);

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
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        int variant = ButterflyfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        this.setVariant(ButterflyfishVariant.getVariantId(variant).getVariant());
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}