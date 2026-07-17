package com.valiantenvoy.rainbow_reef.entity.base;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class VariantSchoolingFish extends ReefMob {

    @Nullable
    private VariantSchoolingFish leader;
    private int schoolSize = 1;

    public VariantSchoolingFish(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public void startFollowing(VariantSchoolingFish fish) {
        this.leader = fish;
        fish.addFollower();
    }

    public void stopFollowing() {
        Objects.requireNonNull(this.leader).removeFollower();
        this.leader = null;
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isInWater()) {
            if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
                List<? extends VariantSchoolingFish> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
                if (list.size() <= 1) {
                    this.schoolSize = 1;
                }
            }
        }
    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(Objects.requireNonNull(this.leader)) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(Objects.requireNonNull(this.leader), 1.0D);
        }
    }

    public void addFollowers(Stream<? extends VariantSchoolingFish> fish) {
        fish.limit(this.getMaxSchoolSize() - this.schoolSize).filter((fish1) -> fish1 != this).forEach((fish2) -> {
            if (Objects.equals(this.getVariantRawId(), fish2.getVariantRawId())) {
                fish2.startFollowing(this);
            }
        });
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
        if (spawnGroupData == null) {
            spawnGroupData = new SchoolSpawnGroupData(this, this.getVariantRawId());
        } else {
            SchoolSpawnGroupData data = (SchoolSpawnGroupData) spawnGroupData;
            this.setVariantRawId(data.variant());
            this.startFollowing(data.leader());
        }
        return spawnGroupData;
    }

    public record SchoolSpawnGroupData(VariantSchoolingFish leader, String variant) implements SpawnGroupData {
    }
}
