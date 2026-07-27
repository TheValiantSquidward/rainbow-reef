package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.valiantenvoy.rainbow_reef.entity.base.VariantShoalingFish;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class ShoalSwimGoal extends Goal {

    private final VariantShoalingFish fish;
    private final int radius;
    private final int height;
    private float circleDistance = 2.5F;
    private boolean clockwise = false;

    public ShoalSwimGoal(VariantShoalingFish fish) {
        this(fish, 10, 7);
    }

    public ShoalSwimGoal(VariantShoalingFish fish, int radius, int height) {
        this.fish = fish;
        this.radius = radius;
        this.height = height;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.fish.isGroupLeader() || this.fish.hasNoLeader() || this.fish.hasGroupLeader() && this.fish.groupLeader.circlePos != null;
    }

    @Override
    public void tick() {
        if (this.fish.circleTime > this.fish.maxCircleTime) {
            this.fish.circleTime = 0;
            this.fish.circlePos = null;
        }
        if (this.fish.circlePos != null) {
            this.fish.circleTime++;
            Vec3 orbitPos = this.orbitAroundPos(this.fish.circlePos);
            this.fish.getNavigation().moveTo(orbitPos.x, orbitPos.y, orbitPos.z, this.fish.circleSpeed);
        }
        else if (this.fish.isGroupLeader()) {
            if (this.fish.baitballCooldown == 0) {
                this.fish.baitballCooldown = 100 + this.fish.getRandom().nextInt(150);
                if (this.fish.circlePos == null || this.fish.circleTime >= this.fish.maxCircleTime) {
                    this.fish.circleTime = 0;
                    this.fish.maxCircleTime = 100 + this.fish.getRandom().nextInt(200);
                    this.circleDistance = 1 + this.fish.getRandom().nextFloat() * 1.5F;
                    this.fish.circleSpeed = 0.75F + this.fish.getRandom().nextFloat() * 0.5F;
                    this.clockwise = this.fish.getRandom().nextBoolean();
                    this.fish.circlePos = this.getPosition();
                }
            }
        }
        else if (this.fish.getRandom().nextInt(40) == 0 || this.fish.hasNoLeader()) {
            Vec3 position = this.getPosition();
            if (position != null) {
                this.fish.getNavigation().moveTo(position.x, position.y, position.z, 1.0F);
            }
        }
        else if (this.fish.hasGroupLeader() && this.fish.groupLeader.circlePos != null) {
            if (this.fish.circlePos == null) {
                this.fish.circlePos = this.fish.groupLeader.circlePos;
                this.fish.circleTime = this.fish.groupLeader.circleTime;
                this.fish.maxCircleTime = this.fish.groupLeader.maxCircleTime;
                this.circleDistance = 1 + this.fish.getRandom().nextFloat() * 1.5F;
                this.clockwise = this.fish.getRandom().nextBoolean();
                this.fish.circleSpeed = 0.75F + this.fish.getRandom().nextFloat() * 0.5F;
            }
        }
    }

    @Nullable
    protected Vec3 getPosition() {
        return BehaviorUtils.getRandomSwimmablePos(this.fish, this.radius, this.height);
    }

    public Vec3 orbitAroundPos(Vec3 target) {
        float progress = 1.0F - (this.fish.circleTime / (float) this.fish.maxCircleTime);
        float angle = (0.0174532925F * 10.0F * this.fish.circleSpeed * (this.clockwise ? -this.fish.circleTime : this.fish.circleTime));
        double extraX = (this.circleDistance * progress + 1.75F) * Mth.sin((angle));
        double extraY = Math.sin(1F + this.fish.getId() * 0.2F + this.fish.circleTime * 0.2F);
        double extraZ = (this.circleDistance * progress + 1.75F) * progress * Mth.cos(angle);
        return new Vec3(target.x + 0.5F + extraX, target.y + 0.5F + extraY, target.z + 0.5F + extraZ);
    }
}