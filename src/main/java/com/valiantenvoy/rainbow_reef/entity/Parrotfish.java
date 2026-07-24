package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishNibbleBlockGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class Parrotfish extends VariantSchoolingFish {

    private static final EntityDataAccessor<Boolean> EEPY = SynchedEntityData.defineId(Parrotfish.class, EntityDataSerializers.BOOLEAN);

    private int eepyTicks = this.getRandom().nextInt(100);

    public Parrotfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(EEPY, false);
    }

    public boolean isEepy() {
        return this.entityData.get(EEPY);
    }
    public void setEepy(boolean eepy) {
        this.entityData.set(EEPY, eepy);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new ParrotfishSleepGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new FishNibbleBlockGoal(this, 10, BlockTags.CORAL_BLOCKS));
        this.goalSelector.addGoal(4, new SwimWanderGoal(this, 1.0D, 50) {
            @Override
            public boolean canUse() {
                return !Parrotfish.this.isEepy() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !Parrotfish.this.isEepy() && super.canContinueToUse();
            }
        });
        this.goalSelector.addGoal(5, new FollowVariantLeaderGoal(this) {
            @Override
            public boolean canUse() {
                return !Parrotfish.this.isEepy() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !Parrotfish.this.isEepy() && super.canContinueToUse();
            }
        });
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isEepy() || this.isImmobile()) {
            this.jumping = false;
            this.xxa = 0.0F;
            this.zza = 0.0F;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide && this.isEepy()) {
            Vec3 lookVec = new Vec3(0.0D, 0.55D, this.getBbWidth() * 0.4F).yRot(-this.yBodyRot * ((float) Math.PI / 180F));
            if (this.eepyTicks == 0) {
                this.eepyTicks = 100;
                this.level().addParticle(ReefParticleTypes.EEPY.get(), this.getX() + lookVec.x, this.getEyeY() + lookVec.y, this.getZ() + lookVec.z, 0, 0, 0);
            }
            if (this.eepyTicks > 0) {
                this.eepyTicks--;
            }
        }
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.PARROTFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/parrotfish/parrotfish_blue.png");
    }

    private static class ParrotfishSleepGoal extends Goal {

        private final Parrotfish parrotfish;

        public ParrotfishSleepGoal(Parrotfish mob) {
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
            this.parrotfish = mob;
        }

        @Override
        public boolean canUse() {
            if (this.parrotfish.xxa == 0.0F && this.parrotfish.yya == 0.0F && this.parrotfish.zza == 0.0F) {
                return this.canSleep();
            } else {
                return false;
            }
        }

        @Override
        public boolean canContinueToUse() {
            return this.canSleep();
        }

        @Override
        public void start() {
            this.parrotfish.setJumping(false);
            this.parrotfish.getNavigation().stop();
            this.parrotfish.getMoveControl().setWantedPosition(this.parrotfish.getX(), this.parrotfish.getY(), this.parrotfish.getZ(), 0.0D);
            this.parrotfish.setEepy(true);
            this.parrotfish.stopFollowing();
        }

        @Override
        public void stop() {
            this.parrotfish.setEepy(false);
        }

        @Override
        public void tick() {
            if (this.parrotfish.getNavigation().getPath() != null) {
                this.parrotfish.getNavigation().stop();
            }
            if (this.parrotfish.getMoveControl().hasWanted()) {
                this.parrotfish.getMoveControl().setWantedPosition(this.parrotfish.getX(), this.parrotfish.getY(), this.parrotfish.getZ(), 0.0D);
            }
        }

        public boolean canSleep() {
            return this.parrotfish.level().isNight() && this.parrotfish.getLastHurtByMob() == null && this.parrotfish.level().isWaterAt(this.parrotfish.blockPosition());
        }
    }
}