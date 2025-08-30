package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.CustomizableRandomSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.entity.pathing.AdvancedWaterboundPathNavigation;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.Month;

public class Basslet extends ReefMob {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Basslet.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Basslet.class, EntityDataSerializers.INT);

    public Basslet(EntityType<? extends ReefMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new AdvancedWaterboundPathNavigation(this, level, true, false);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4D)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(0, new CustomizableRandomSwimGoal(this, 1, 1, 20, 20, 3, true));
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "brazilian";
            case 2 -> "accessor";
            case 3 -> "blackcap";
            case 4 -> "candy";
            case 5 -> "gold";
            case 6 -> "gilded";
            case 7 -> "swissguard";
            case 8 -> "yellowscissortail";
            case 9 -> "midnight";
            default -> "fairy";
        };
    }

    @Override
    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive() && this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.idleAnimationState.animateWhen(this.isAlive() && !this.walkAnimation.isMoving() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.BASSLET_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {
            this.setVariant(4);
        }else
        if(variantChange <= 0.01){
            this.setVariant(5);
        } else if(variantChange <= 0.001){
            this.setVariant(6);
        }else if(variantChange <= 0.12){
            this.setVariant(1);
        }else if(variantChange <= 0.24){
            this.setVariant(2);
        }else if(variantChange <= 0.36){
            this.setVariant(3);
        }else if(variantChange <= 0.48){
            this.setVariant(4);
        }else if(variantChange <= 0.60){
            this.setVariant(7);
        }else if(variantChange <= 0.72){
            this.setVariant(8);
        }else if(variantChange <= 0.84){
            this.setVariant(9);
        }else {
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean canSpawn(EntityType<Basslet> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}