package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.DancingEntity;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArrowCrab extends Crab implements DancesToJukebox {

    // crabbing about
    // the crabby beast
    // crabbed to meet you

    public final net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState danceAnimationState = new net.minecraft.world.entity.AnimationState();
    public final net.minecraft.world.entity.AnimationState walkAnimationState = new net.minecraft.world.entity.AnimationState();

    public ArrowCrab(EntityType<? extends DancingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ARMOR, 2.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new ConditionalStopGoal(this) {
            @Override
            public boolean canUse() {
                ArrowCrab crab = (ArrowCrab) getCreatura();
                //System.out.println(crab.isDancing());
                return crab.isDancing();
            }

            @Override
            public boolean canContinueToUse() {
                ArrowCrab crab = (ArrowCrab) getCreatura();
                return crab.isDancing();
            }
        });
        this.goalSelector.addGoal(1, new CrabFindWater(this));
        this.goalSelector.addGoal(1, new CrabLeaveWater(this));
        this.goalSelector.addGoal(3, new CrabBottomWander(this, 1.0D, 10, 50));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.ARROW_CRAB_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "red";
            default -> "yellowlined";
        };
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        float variantChange = this.getRandom().nextFloat();

        if (variantChange <= 0.10F) {
            this.setVariant(1);
        } else {
            this.setVariant(0);
        }

        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving(), this.tickCount);
        this.walkAnimationState.animateWhen(this.isAlive() && this.walkAnimation.isMoving(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isAlive() && (this.isDancing() && this.getJukeboxPos() != null), this.tickCount);
    }

    public static boolean canSpawn(EntityType<ArrowCrab> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.getBlockState(pos).isSolid();
    }
}
