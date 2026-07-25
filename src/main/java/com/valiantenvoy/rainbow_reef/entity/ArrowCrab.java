package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ArrowCrab extends Crab {

    private int fleeTicks = 0;
    private Vec3 fleeFromPosition;

    public ArrowCrab(EntityType<? extends Crab> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.STEP_HEIGHT, 1.25D)
                .build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FleeGoal(this, 2.0D));
    }

    @Override
    protected boolean shouldDance() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.fleeTicks > 0) {
                this.fleeTicks--;
            }

            if (this.tickCount % 20 == 0 && this.isJukeboxNearby()) {
                BlockPos jukeboxPos = this.getNearbyJukebox();
                if (jukeboxPos != null) {
                    this.fleeTicks = 40 + this.getRandom().nextInt(20);
                    this.fleeFromPosition = Vec3.atCenterOf(jukeboxPos);
                }
            }
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.ARROW_CRAB_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/arrow_crab/arrow_crab_yellowline.png");
    }

    private static class FleeGoal extends Goal {

        private final ArrowCrab arrowCrab;
        private final double speedModifier;

        public FleeGoal(ArrowCrab arrowCrab, double speedModifier) {
            this.arrowCrab = arrowCrab;
            this.speedModifier = speedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return this.arrowCrab.fleeTicks > 0 && this.arrowCrab.fleeFromPosition != null;
        }

        @Override
        public void stop() {
            this.arrowCrab.fleeFromPosition = null;
        }

        @Override
        public void tick() {
            if (this.arrowCrab.getNavigation().isDone()) {
                Vec3 posAway = DefaultRandomPos.getPosAway(this.arrowCrab, 10, 4, this.arrowCrab.fleeFromPosition);
                if (posAway != null) {
                    this.arrowCrab.getNavigation().moveTo(posAway.x, posAway.y, posAway.z, speedModifier);
                }
            }
        }
    }
}
