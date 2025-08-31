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
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.IntFunction;

public class Goby extends ReefMob {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

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
        this.goalSelector.addGoal(0, new GroundseekingRandomSwimGoal(this, 0.8D, 75, 5, 10, 0.01));
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(!this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.GOBY_BUCKET.get());
    }

    public enum GobyVariant implements StringRepresentable {
        FIRE(0, "fire"),
        PURPLE_FIRE(1, "purple_fire"),
        CANDYCANE(2, "candycane"),
        MANDARIN(3, "mandarin"),
        YELLOW_WATCHMAN(4, "yellow_watchman"),
        CATALINA(5, "catalina"),
        BLACK_RAY(6, "black_ray"),
        HELFRICHI(7, "helfrichi"),
        BLUE_NEON(8, "blue_neon"),
        YELLOW_NEON(9, "yellow_neon"),
        NEON_HYBRID(10, "neon_hybrid"),
        BLUESTREAK(11, "bluestreak"),
        LEOPARD_SPOTTED(12, "leopard_spotted"),
        YELLOW_CLOWN(13, "yellow_clown"),
        DRACULA(14, "dracula"),
        BLACKFIN(15, "blackfin");

        private final int variant;
        private final String name;

        GobyVariant(int variant, String name) {
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

        private static final IntFunction<GobyVariant> VARIANT_ID = ByIdMap.sparse(GobyVariant::getVariant, GobyVariant.values(), FIRE);

        public static GobyVariant variantId(int id) {
            return VARIANT_ID.apply(id);
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            this.setVariant(14);
        } else {
            GobyVariant variant = Util.getRandom(GobyVariant.values(), this.random);
            this.setVariant(variant.getVariant());
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}