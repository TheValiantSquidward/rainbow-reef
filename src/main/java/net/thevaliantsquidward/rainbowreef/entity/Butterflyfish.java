package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.RRTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.IntFunction;

public class Butterflyfish extends VariantSchoolingFish {

    public Butterflyfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level, 600);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 30, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 15);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6D)
                .add(Attributes.MOVEMENT_SPEED, 1D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 15, RRTags.BUTTERFLY_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 0.9, 1, 20, 20, 3, false));
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

    public enum ButterflyfishVariant implements StringRepresentable {
        COPPERBAND(0, "copperband"),
        WROUGHT_IRON(1, "wrought_iron"),
        THREADFIN(2, "threadfin"),
        BANNER(3, "banner"),
        BLUECHEEK(4, "bluecheek"),
        LONGNOSE(5, "longnose"),
        SPOTFIN(6, "spotfin"),
        HOODED(7, "hooded"),
        ARABIC(8, "arabic"),
        PYRAMID(9, "pyramid"),
        RED_SEA(10, "red_sea"),
        STRIPED(11, "striped"),
        SADDLEBACK(12, "saddleback"),
        AFRICAN(13, "african"),
        ERITREAN(14, "eritrean"),
        MARGINATED(15, "marginated"),
        THOMPSON(16, "thompson"),
        MULLERS(17, "mullers"),

        // can probably separate these in a better way but this works for now ig
        // mangrove
        SIX_SPINED(18, "six_spined"),
        FOUREYE(19, "foureye"),

        // rare
        EASTER_ISLAND(20, "easter_island"),
        DARK_LONGNOSE(21, "dark_longnose");

        private final int variant;
        private final String name;

        ButterflyfishVariant(int variant, String name) {
            this.variant = variant;
            this.name = name;
        }

        public int getVariant() {
            return this.variant;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        private static final IntFunction<ButterflyfishVariant> VARIANT_ID = ByIdMap.sparse(ButterflyfishVariant::getVariant, ButterflyfishVariant.values(), COPPERBAND);

        public static ButterflyfishVariant variantId(int id) {
            return VARIANT_ID.apply(id);
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        ButterflyfishVariant commonVariant = ButterflyfishVariant.variantId(level.getRandom().nextInt(17));
        Holder<Biome> biome = level.getBiome(this.blockPosition());

        if (biome.is(Biomes.MANGROVE_SWAMP)) {
            if (this.getRandom().nextBoolean()) {
                this.setVariant(ButterflyfishVariant.SIX_SPINED.getVariant());
            } else {
                this.setVariant(ButterflyfishVariant.FOUREYE.getVariant());
            }
        } else if (this.getRandom().nextFloat() <= 0.005F) {
            if (this.getRandom().nextBoolean()) {
                this.setVariant(ButterflyfishVariant.EASTER_ISLAND.getVariant());
            } else {
                this.setVariant(ButterflyfishVariant.DARK_LONGNOSE.getVariant());
            }
        } else {
            this.setVariant(commonVariant.getVariant());
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}