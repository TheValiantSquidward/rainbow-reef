package com.valiantenvoy.rainbow_reef.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.stream.Stream;

public abstract class VariantShoalingFish extends ReefMob {

    public float circleSpeed = 1.0F;
    public int baitballCooldown = 100 + this.getRandom().nextInt(100);
    public int circleTime = 0;
    public int maxCircleTime = 300;
    public Vec3 circlePos;
    public VariantShoalingFish groupLeader;
    public int groupSize = 1;

    protected VariantShoalingFish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.baitballCooldown > 0) {
            this.baitballCooldown--;
        }
    }

    public void leaveGroup() {
        if (this.groupLeader != null) {
            this.groupLeader.decreaseGroupSize();
        }
        this.groupLeader = null;
    }

    public boolean hasNoLeader() {
        return !this.hasGroupLeader();
    }

    public boolean hasGroupLeader() {
        return this.groupLeader != null && this.groupLeader.isAlive();
    }

    private void increaseGroupSize() {
        this.groupSize++;
    }

    private void decreaseGroupSize() {
        this.groupSize--;
    }

    public boolean canGroupGrow() {
        return this.isGroupLeader() && this.groupSize < this.getMaxShoalSize();
    }

    public int getMaxShoalSize() {
        return 20;
    }

    public int getMaxSpawnClusterSize() {
        return this.getMaxShoalSize();
    }

    public boolean isMaxGroupSizeReached(int sizeIn) {
        return false;
    }

    public boolean isGroupLeader() {
        return this.groupSize > 1;
    }

    public boolean inRangeOfGroupLeader() {
        return this.distanceToSqr(this.groupLeader) <= 121.0D;
    }

    public void moveToGroupLeader() {
        if (this.hasGroupLeader()) {
            this.getNavigation().moveTo(this.groupLeader.getX(), this.groupLeader.getY(), this.groupLeader.getZ(), 1.0D);
        }
    }

    public boolean isCircling() {
        return this.circlePos != null && this.circleTime < this.maxCircleTime;
    }

    public void createAndSetLeader(VariantShoalingFish leader) {
        this.groupLeader = leader;
        leader.increaseGroupSize();
    }

    public void addFollowers(Stream<? extends VariantShoalingFish> stream) {
        stream.limit(this.getMaxShoalSize() - this.groupSize)
                .filter(fish -> fish != this)
                .filter(fish -> this.getVariantRawId().equals(fish.getVariantRawId()))
                .forEach(fish -> fish.createAndSetLeader(this));
    }
}
