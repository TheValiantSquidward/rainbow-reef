package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.blocks.BurrowBlockEntity;
import com.valiantenvoy.rainbow_reef.registry.ReefPoiTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumSet;

public class EnterBurrowGoal extends Goal {

    private static final int SEARCH_RADIUS = 16;
    private static final int SEARCH_CHANCE = 400;
    private static final int GIVE_UP_TICKS = 600;
    private static final double ENTER_DISTANCE_SQ = 4.0D;
    private static final int RETRY_COOLDOWN_TICKS = 400;

    private final PathfinderMob mob;
    private final double speed;
    private final TagKey<Block> validBurrows;
    @Nullable
    private BlockPos targetPos;
    private int timeOut;

    public EnterBurrowGoal(PathfinderMob mob, double speed, TagKey<Block> validBurrows) {
        this.mob = mob;
        this.speed = speed;
        this.validBurrows = validBurrows;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.mob.isInWater() || this.mob.isPassenger() || this.mob.isLeashed() || this.mob.getTarget() != null || this.mob.getLastHurtByMob() != null) {
            return false;
        }
        if (this.mob.getPersistentData().getLong(BurrowBlockEntity.COOLDOWN_TAG) > this.mob.level().getGameTime()) {
            return false;
        }
        if (this.mob.getRandom().nextInt(reducedTickDelay(SEARCH_CHANCE)) != 0) {
            return false;
        }
        this.targetPos = this.findBurrow();
        return this.targetPos != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPos != null && this.timeOut > 0 && this.mob.getTarget() == null
                && this.mob.level().getBlockState(this.targetPos).is(this.validBurrows);
    }

    @Override
    public void start() {
        this.timeOut = GIVE_UP_TICKS;
        this.moveToTarget();
    }

    @Override
    public void tick() {
        this.timeOut--;
        if (this.targetPos == null) {
            return;
        }
        if (this.mob.position().distanceToSqr(Vec3.atCenterOf(this.targetPos)) < ENTER_DISTANCE_SQ) {
            if (this.mob.level().getBlockEntity(this.targetPos) instanceof BurrowBlockEntity burrow) {
                burrow.tryEnter(this.mob);
            }
            this.targetPos = null;
        } else if (this.mob.getNavigation().isDone()) {
            this.moveToTarget();
        }
    }

    @Override
    public void stop() {
        this.mob.getPersistentData().putLong(BurrowBlockEntity.COOLDOWN_TAG,
                this.mob.level().getGameTime() + RETRY_COOLDOWN_TICKS + this.mob.getRandom().nextInt(RETRY_COOLDOWN_TICKS));
        this.targetPos = null;
        this.mob.getNavigation().stop();
    }

    private void moveToTarget() {
        if (this.targetPos != null) {
            this.mob.getNavigation().moveTo(this.targetPos.getX() + 0.5D, this.targetPos.getY() + 0.5D, this.targetPos.getZ() + 0.5D, this.speed);
        }
    }

    private boolean isValidBurrow(BlockPos pos) {
        return this.mob.level().getBlockState(pos).is(this.validBurrows)
                && this.mob.level().getBlockEntity(pos) instanceof BurrowBlockEntity burrow
                && burrow.canEnter(this.mob);
    }

    @Nullable
    private BlockPos findBurrow() {
        if (!(this.mob.level() instanceof ServerLevel level)) {
            return null;
        }
        BlockPos center = this.mob.blockPosition();
        return level.getPoiManager().getInRange(holder -> holder.is(ReefPoiTypes.BURROW.getKey()), center, SEARCH_RADIUS, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(this::isValidBurrow)
                .min(Comparator.comparingDouble(pos -> pos.distSqr(center)))
                .orElse(null);
    }
}
