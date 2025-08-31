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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.LocateNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.MoveToNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.NemHoster;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.RestInNemGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class Clownfish extends NemHoster {

    public Clownfish(EntityType<? extends NemHoster> entityType, Level level) {
        super(entityType, level, 200, 4, 600, 200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    protected void registerGoals() {
        // Anemone seeker goal plan:
        // priority of 0, but only works if the clown has a home nem and is over 10 blocks from it
        // Pathfinds back to home nem and makes it hide for 3 - 5 secs
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new RestInNemGoal(this, 3, 600, 200));
        this.goalSelector.addGoal(4, new MoveToNemGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new LocateNemGoal(this, 200));
        this.goalSelector.addGoal(6, new FishDigGoal(this, 10, ReefTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1, 40));
        this.goalSelector.addGoal(8, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CLOWNFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return ClownfishVariant.values().length;
    }

    public enum ClownfishVariant implements StringRepresentable {
        OCELLARIS(1, "ocellaris", ReefRarities.COMMON, null),
        BLACK_AND_WHITE(2, "black_and_white", ReefRarities.COMMON, null),
        PINK_SKUNK(3, "pink_skunk", ReefRarities.COMMON, null),
        MAROON(4, "maroon", ReefRarities.COMMON, null),
        CLARKII(5, "clarkii", ReefRarities.COMMON, null),
        TOMATO(6, "tomato", ReefRarities.COMMON, null),
        MADAGASCAR(7, "madagascar", ReefRarities.COMMON, null),
        ALLARD(8, "allard", ReefRarities.COMMON, null),
        RED_SADDLEBACK(9, "red_saddleback", ReefRarities.COMMON, null),
        BLIZZARD(10, "blizzard", ReefRarities.RARE, null),
        BLUESTRAIN(11, "bluestrain", ReefRarities.UNCOMMON, null),
        OMAN(12, "oman", ReefRarities.UNCOMMON, null),
        MOCHA(13, "mocha", ReefRarities.UNCOMMON, null),
        WHITESNOUT(14, "whitesnout", ReefRarities.RARE, null),
        GOLD_NUGGET(15, "gold_nugget", ReefRarities.RARE, null),
        SNOWSTORM(16, "snowstorm", ReefRarities.UNCOMMON, null),
        ORANGE_SKUNK(17, "orange_skunk", ReefRarities.RARE, null),
        DOMINO(18, "domino", ReefRarities.RARE, null),
        YELLOW_CLARKII(19, "yellow_clarkii", ReefRarities.UNCOMMON, null),
        NAKED(20, "naked", ReefRarities.RARE, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        ClownfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static ClownfishVariant getVariantId(int variants) {
            for (ClownfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return ClownfishVariant.OCELLARIS;
        }

        public static ClownfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<ClownfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(ReefRarities.values()).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<ClownfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<ClownfishVariant> variants = Lists.newArrayList();
            for (ClownfishVariant variant : ClownfishVariant.values()) {
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
        int variant = ClownfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        this.setVariant(ClownfishVariant.getVariantId(variant).getVariant());
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}