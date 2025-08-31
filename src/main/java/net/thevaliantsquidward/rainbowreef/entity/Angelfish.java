package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.Util;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.RRTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.IntFunction;

public class Angelfish extends VariantSchoolingFish {

    public Angelfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level, 300);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 5, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishDigGoal(this, 20, RRTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(4, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 3, false));
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 3;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.ANGELFISH_BUCKET.get());
    }

    public enum AngelfishVariant implements StringRepresentable {
        QUEEN(0, "queen"),
        FRENCH(1, "french"),
        EMPEROR(2, "emperor"),
        YELLOWBAND(3, "yellowband"),
        BLUERING(4, "bluering"),
        ROCK_BEAUTY(5, "rock_beauty"),
        BLUE_QUEEN(6, "blue_queen"),
        MAJESTIC(7, "majestic"),
        KING(8, "king"),
        SEMICIRCLE(9, "semicircle"),
        BANDED(10, "banded"),
        GRAY(11, "gray"),
        OLD_WOMAN(12, "old_woman"),
        GUINEAN(13, "guinean"),
        QUEENSLAND_YELLOWTAIL(14, "queensland_yellowtail"),
        CLARION(15, "clarion");

        private final int variant;
        private final String name;

        AngelfishVariant(int variant, String name) {
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

        private static final IntFunction<AngelfishVariant> VARIANT_ID = ByIdMap.sparse(AngelfishVariant::getVariant, AngelfishVariant.values(), QUEEN);

        public static AngelfishVariant variantId(int id) {
            return VARIANT_ID.apply(id);
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        AngelfishVariant variant = Util.getRandom(AngelfishVariant.values(), this.random);
        this.setVariant(variant.getVariant());
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}