package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goalz.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.VariantEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public abstract class VariantSchoolingFish extends RRMob implements VariantEntity {
    @Nullable
    private VariantSchoolingFish leader;
    private int schoolSize = 1;

    public VariantSchoolingFish(EntityType<? extends WaterAnimal> p_27523_, Level p_27524_, int feedCooldown) {
        super(p_27523_, p_27524_, feedCooldown);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FollowVariantLeaderGoal(this));
    }

    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public int getMaxSchoolSize() {
        return super.getMaxSpawnClusterSize();
    }

    protected boolean canRandomSwim() {
        return !this.isFollower();
    }

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public VariantSchoolingFish startFollowing(VariantSchoolingFish p_27526_) {
        this.leader = p_27526_;
        p_27526_.addFollower();
        return p_27526_;
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

    @Override
    public int variant() {
        return 0;
    }

    public void addFollowers(Stream<? extends VariantSchoolingFish> p_27534_) {
        p_27534_.limit(this.getMaxSchoolSize() - this.schoolSize).filter((p_27538_) -> {
            return p_27538_ != this;
        }).forEach((p_27536_) -> {
            if (this.variant() == p_27536_.variant()) {
                p_27536_.startFollowing(this);
            }
        });
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_27528_, DifficultyInstance p_27529_, MobSpawnType p_27530_, @Nullable SpawnGroupData p_27531_, @Nullable CompoundTag p_27532_) {
        super.finalizeSpawn(p_27528_, p_27529_, p_27530_, p_27531_, p_27532_);
        if (p_27531_ == null) {
            p_27531_ = new SchoolSpawnGroupData(this);
        } else {
            this.startFollowing(((SchoolSpawnGroupData)p_27531_).leader);
        }

        return p_27531_;
    }

    public static class SchoolSpawnGroupData implements SpawnGroupData {
        public final VariantSchoolingFish leader;

        public SchoolSpawnGroupData(VariantSchoolingFish p_27553_) {
            this.leader = p_27553_;
        }
    }

}
