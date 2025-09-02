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
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.LocateAnemoneGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.MoveToAnemoneGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.Anemonefish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.RestInAnemoneGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Clownfish extends Anemonefish {

    public Clownfish(EntityType<? extends Anemonefish> entityType, Level level) {
        super(entityType, level, 200, 4, 600, 200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
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
        this.goalSelector.addGoal(3, new RestInAnemoneGoal(this, 3, 600, 200));
        this.goalSelector.addGoal(4, new MoveToAnemoneGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new LocateAnemoneGoal(this, 200));
        this.goalSelector.addGoal(6, new FishDigGoal(this, 10, 400, ReefTags.CLOWNFISH_DIET));
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
        OCELLARIS(1, "ocellaris", COMMON, null),
        BLACK_AND_WHITE(2, "black_and_white", COMMON, null),
        PINK_SKUNK(3, "pink_skunk", COMMON, null),
        MAROON(4, "maroon", COMMON, null),
        CLARKII(5, "clarkii", COMMON, null),
        TOMATO(6, "tomato", COMMON, null),
        MADAGASCAR(7, "madagascar", UNCOMMON, null),
        ALLARD(8, "allard", UNCOMMON, null),
        RED_SADDLEBACK(9, "red_saddleback", UNCOMMON, null),
        BLIZZARD(10, "blizzard", RARE, null),
        BLUESTRAIN(11, "bluestrain", ABERRANT, null),
        OMAN(12, "oman", UNCOMMON, null),
        MOCHA(13, "mocha", RARE, null),
        WHITESNOUT(14, "whitesnout", RARE, null),
        GOLD_NUGGET(15, "gold_nugget", RARE, null),
        SNOWSTORM(16, "snowstorm", RARE, null),
        ORANGE_SKUNK(17, "orange_skunk", UNCOMMON, null),
        DOMINO(18, "domino", RARE, null),
        YELLOW_CLARKII(19, "yellow_clarkii", UNCOMMON, null),
        NAKED(20, "naked", RARE, null);

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
            List<ClownfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, UNCOMMON, RARE, ABERRANT).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<ClownfishVariant> getPossibleTypes(Holder<Biome> biome, ReefRarities rarity, boolean fromBucket) {
            List<ClownfishVariant> variants = Lists.newArrayList();
            for (ClownfishVariant variant : ClownfishVariant.values()) {
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
        int variant = ClownfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(ClownfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof ClownfishData) {
            variant = ((ClownfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new ClownfishData(variant);
            }
        }
        this.setVariant(ClownfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class ClownfishData implements SpawnGroupData {
        public final int variantData;

        public ClownfishData(int variant) {
            this.variantData = variant;
        }
    }
}