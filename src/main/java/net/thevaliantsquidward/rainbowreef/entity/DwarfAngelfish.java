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

public class DwarfAngelfish extends ReefMob {

    public DwarfAngelfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 30, 200, ReefTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 10));
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.DWARF_ANGELFISH_BUCKET.get());
    }

    @Override
    public int getVariantCount() {
        return DwarfAngelfishVariant.values().length;
    }

    public enum DwarfAngelfishVariant implements StringRepresentable {
        BICOLOR(1, "bicolor", COMMON, null),
        CORAL_BEAUTY(2, "coral_beauty", COMMON, null),
        FLAME(3, "flame", COMMON, null),
        KEYHOLE(4, "keyhole", COMMON, null),
        MASKED(5, "masked", COMMON, null),
        CHERUB(6, "cherub", COMMON, null),
        BLACK_NOX(7, "black_nox", COMMON, null),
        LAMARCK(8, "lamarck", COMMON, null),
        LEMONPEEL(9, "lemonpeel", COMMON, null),
        YELLOW(10, "yellow", UNCOMMON, null),
        PEARLSCALE(11, "pearlscale", UNCOMMON, null),
        RESPLENDENT(12, "resplendent", UNCOMMON, null),
        PEPPERMINT(13, "peppermint", EPIC, null),
        BLACKSPOT(17, "blackspot", EPIC, null),
        JOCULATOR(18, "joculator", EPIC, null),
        JAPANESE(14, "japanese", RARE, null),
        YELLOWTAIL(15, "yellowtail", RARE, null),
        MULTIBAR(19, "multibar", RARE, null),
        ORANGEPEEL(16, "orangepeel", ABERRANT, null),
        WHITEBICOLOR(20,"whitebicolor", ABERRANT, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        DwarfAngelfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static DwarfAngelfishVariant getVariantId(int variants) {
            for (DwarfAngelfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return DwarfAngelfishVariant.BICOLOR;
        }

        public static DwarfAngelfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<DwarfAngelfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(ReefRarities.values()).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<DwarfAngelfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<DwarfAngelfishVariant> variants = Lists.newArrayList();
            for (DwarfAngelfishVariant variant : DwarfAngelfishVariant.values()) {
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
        int variant = DwarfAngelfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();

        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(DwarfAngelfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof DwarfAngelfishData) {
            variant = ((DwarfAngelfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new DwarfAngelfishData(variant);
            }
        }
        this.setVariant(DwarfAngelfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class DwarfAngelfishData implements SpawnGroupData {
        public final int variantData;

        public DwarfAngelfishData(int variant) {
            this.variantData = variant;
        }
    }
}