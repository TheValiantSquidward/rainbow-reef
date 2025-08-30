package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Frogfish extends ReefMob {

    public final AnimationState swimAnimationState = new AnimationState();
    public final AnimationState landAnimationState = new AnimationState();

    public Frogfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level, Integer.MAX_VALUE);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 2, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 7D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        //this.goalSelector.addGoal(8, new CrabBottomWander(this, 1.0D, 5, 0));
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "orangeocellated";
            case 2 -> "pinkocellated";
            case 3 -> "psychedelic";
            case 4 -> "redlonglure";
            case 5 -> "sargassum";
            case 6 -> "yellowlonglure";
            default -> "clown";
        };
    }

    public void setupAnimationStates() {
        this.swimAnimationState.animateWhen(this.isAlive() && this.isInWaterOrBubble(), this.tickCount);
        this.landAnimationState.animateWhen(this.isAlive() && !this.isInWaterOrBubble(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.BOXFISH_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        float variantChange = this.getRandom().nextFloat();
        if(variantChange <= 0.12F){
            this.setVariant(1);
        }else if(variantChange <= 0.24F){
            this.setVariant(2);
        }else if(variantChange <= 0.36F){
            this.setVariant(3);
        }else if(variantChange <= 0.48F){
            this.setVariant(4);
        }else if(variantChange <= 0.60F){
            this.setVariant(5);
        }else if(variantChange <= 0.72F){
            this.setVariant(6);
        }else if(variantChange <= 0.84F){
            this.setVariant(7);
        }else{
            this.setVariant(0);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static boolean canSpawn(EntityType<Frogfish> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return WaterAnimal.checkSurfaceWaterAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }
}