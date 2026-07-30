package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishNibbleBlockGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class Triggerfish extends ReefMob {

    @Nullable
    private SwimWanderGoal swimWanderGoal;

    public Triggerfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .add(Attributes.ATTACK_DAMAGE, 2.0F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.swimWanderGoal = new SwimWanderGoal(this, 1.0D, 80);
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.3D));
        this.goalSelector.addGoal(1, new TriggerfishBitePlayerGoal(this));
        this.goalSelector.addGoal(2, new FishNibbleBlockGoal(this, 15, 600, ReefTags.ANGELFISH_DIET));
        this.goalSelector.addGoal(3, this.swimWanderGoal);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.TRIGGERFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/triggerfish/triggerfish_blue.png");
    }

    private static class TriggerfishBitePlayerGoal extends Goal {

        private final Triggerfish triggerfish;
        @Nullable
        private Player followingPlayer;
        private int timeToRecalcPath;

        public TriggerfishBitePlayerGoal(Triggerfish triggerfish) {
            this.triggerfish = triggerfish;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            List<Player> list = this.triggerfish.level().getEntitiesOfClass(Player.class, this.triggerfish.getBoundingBox().inflate(10.0D));
            if (!list.isEmpty()) {
                for (Player mob : list) {
                    if (!mob.isInvisible()) {
                        this.followingPlayer = mob;
                        return this.triggerfish.getRandom().nextInt(600) == 0;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.followingPlayer != null && this.followingPlayer.isAlive() && !this.triggerfish.getNavigation().isDone() && this.triggerfish.distanceToSqr(this.followingPlayer) > 2.0D;
        }

        @Override
        public void start() {
            this.triggerfish.setSprinting(true);
            this.timeToRecalcPath = 0;
        }

        @Override
        public void stop() {
            this.triggerfish.setSprinting(false);
            if (this.followingPlayer != null && this.followingPlayer.distanceTo(this.triggerfish) < (double) 1.5F) {
                this.triggerfish.doHurtTarget(this.followingPlayer);
            }
            this.followingPlayer = null;
            this.triggerfish.getNavigation().stop();
            if (this.triggerfish.swimWanderGoal != null) {
                this.triggerfish.swimWanderGoal.trigger();
            }
        }

        @Override
        public void tick() {
            if (this.followingPlayer != null && !this.triggerfish.isLeashed()) {
                this.triggerfish.getLookControl().setLookAt(this.followingPlayer, 10.0F, (float) this.triggerfish.getMaxHeadXRot());
                if (--this.timeToRecalcPath <= 0) {
                    this.timeToRecalcPath = this.adjustedTickDelay(10);
                    double distanceToSqr = this.triggerfish.distanceToSqr(this.followingPlayer);
                    if (distanceToSqr > 2.0D) {
                        this.triggerfish.getNavigation().moveTo(this.followingPlayer, 1.75D);
                    }
                }
            }
        }
    }
}