package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.*;
import net.thevaliantsquidward.rainbowreef.entity.base.DancingEntity;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;
import net.thevaliantsquidward.rainbowreef.registry.tags.ReefTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Month;

public class Crab extends DancingEntity implements DancesToJukebox {

    // crabbing about
    // the crabby beast
    // crabbed to meet you

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();

    public Crab(EntityType<? extends DancingEntity> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ARMOR, 2.0D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.targetSelector.addGoal(0, new ConditionalStopGoal(this) {
            @Override
            public boolean canUse() {
                Crab crab = (Crab) getCreatura();
                return crab.isDancing();
            }

            @Override
            public boolean canContinueToUse() {
                Crab crab = (Crab) getCreatura();
                return crab.isDancing();
            }
        });
        this.goalSelector.addGoal(0, new FishDigGoal(this, 120, 700, ReefTags.HOG_DIGGABLE));
        this.goalSelector.addGoal(1, new CrabFindWater(this));
        this.goalSelector.addGoal(1, new CrabLeaveWater(this));
        this.goalSelector.addGoal(3, new CrabBottomWander(this, 1, 10, 50));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        CrabPathfinder crab = new CrabPathfinder(this, level) {
            public boolean isStableDestination(BlockPos pos) {
                return this.level.getBlockState(pos).getFluidState().isEmpty();
            }
        };
        return crab;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isDancing() && this.getJukeboxPos() != null) {
            this.getLookControl().setLookAt(getJukeboxPos().getCenter());
        }
    }

    @Override
    public void setupAnimationStates() {
        this.idleAnimationState.animateWhen(this.isAlive() && !this.isDancing(), this.tickCount);
        this.danceAnimationState.animateWhen(this.isDancing(), this.tickCount);
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        ItemStack stack = new ItemStack(ReefItems.CRAB_BUCKET.get());
        if (this.hasCustomName()) {
            stack.setHoverName(this.getCustomName());
        }
        return stack;
    }

    public static String getVariantName(int variant) {
        return switch (variant) {
            case 1 -> "halloween";
            case 2 -> "ghost";
            case 3 -> "sally";
            case 4 -> "emerald";
            case 5 -> "blue";
            case 6 -> "purple";
            case 7 -> "candy";
            case 8 -> "redghost";
            default -> "vampire";
        };
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @javax.annotation.Nullable SpawnGroupData spawnDataIn, @javax.annotation.Nullable CompoundTag dataTag) {
        Holder<Biome> holder = worldIn.getBiome(this.blockPosition());
        float spookyVariantChange = this.getRandom().nextFloat();
        LocalDate currentDate = LocalDate.now();
        float variantChange = this.getRandom().nextFloat();
        if (currentDate.getMonth() == Month.OCTOBER && currentDate.getDayOfMonth() == 31) {

            if (spookyVariantChange <= 0.33) {
                this.setVariant(1);
            } else if (spookyVariantChange <= 0.66) {
                this.setVariant(2);
            } else if (spookyVariantChange <= 0.10) {
                this.setVariant(8);
            } else
                this.setVariant(0);
        } else if (holder.is(Biomes.MANGROVE_SWAMP)) {
            this.setVariant(1);
        } else if (holder.is(Biomes.BEACH) && variantChange <= 0.10) {
            this.setVariant(8);
        } else if (holder.is(Biomes.BEACH) && variantChange > 0.10) {
            this.setVariant(2);
        } else if (holder.is(Biomes.STONY_SHORE)) {
            this.setVariant(3);
        } else if (holder.is(Biomes.LUKEWARM_OCEAN) || holder.is(Biomes.DEEP_LUKEWARM_OCEAN)) {
            this.setVariant(4);
        } else if (holder.is(Biomes.OCEAN) || holder.is(Biomes.DEEP_OCEAN)) {
            this.setVariant(5);
        } else if (holder.is(Biomes.COLD_OCEAN) || holder.is(Biomes.DEEP_COLD_OCEAN)) {
            this.setVariant(6);
        } else if (holder.is(Biomes.WARM_OCEAN)) {
            this.setVariant(7);
        } else {
            this.setVariant(0);
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public @NotNull MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isDancing()) {
            travelVector = Vec3.ZERO;
        }
        if (this.isEffectiveAi() && this.isInWater() && !this.isDancing()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            if(this.jumping){
                this.setDeltaMovement(this.getDeltaMovement().scale(1.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.72D, 0.0D));
            }else{
                this.setDeltaMovement(this.getDeltaMovement().scale(0.4D));
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.08D, 0.0D));
            }

        } else {
            super.travel(travelVector);
        }
    }

    @Override
    public void fishTravel(Vec3 travelVector) {
    }

    @Override
    @Nullable
    protected SoundEvent getAmbientSound() {
        return ReefSoundEvents.CRAB_IDLE.get();
    }

    @Override
    @Nullable
    protected SoundEvent getDeathSound() {
        return ReefSoundEvents.CRAB_DEATH.get();
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSoundEvents.CRAB_HURT.get();
    }

    public static boolean canSpawn(EntityType<? extends ReefMob> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return !level.getBlockState(pos).isSolid();
    }
}
