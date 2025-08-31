package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public abstract class VariantSchoolingFish extends ReefMob {

    @Nullable
    private VariantSchoolingFish leader;
    private int schoolSize = 1;

    public VariantSchoolingFish(EntityType<? extends WaterAnimal> entityType, Level level, int feedCooldown) {
        super(entityType, level, feedCooldown);
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
        this.leader.removeFollower();
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
        if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
            List<? extends WaterAnimal> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
            if (list.size() <= 1) {
                this.schoolSize = 1;
            }
        }
    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0D);
        }
    }

    public void addFollowers(Stream<? extends VariantSchoolingFish> fish) {
        fish.limit(this.getMaxSchoolSize() - this.schoolSize).filter((fish1) -> fish1 != this).forEach((fish2) -> {
            if (this.getVariant() == fish2.getVariant()) {
                fish2.startFollowing(this);
            }
        });
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        if (spawnData == null) {
            spawnData = new SchoolSpawnGroupData(this);
        } else {
            this.startFollowing(((SchoolSpawnGroupData) spawnData).leader);
        }
        return spawnData;
    }

    public static class SchoolSpawnGroupData implements SpawnGroupData {
        public final VariantSchoolingFish leader;

        public SchoolSpawnGroupData(VariantSchoolingFish fish) {
            this.leader = fish;
        }
    }
}
