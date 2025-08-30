package net.thevaliantsquidward.rainbowreef.entity.ai.goals;

import com.mojang.datafixers.DataFixUtils;
import net.minecraft.world.entity.ai.goal.Goal;
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;

import java.util.List;
import java.util.function.Predicate;

public class FollowVariantLeaderGoal extends Goal {

    private final VariantSchoolingFish fish;
    private int timeToRecalcPath;
    private int nextStartTick;

    public FollowVariantLeaderGoal(VariantSchoolingFish fish) {
        this.fish = fish;
        this.nextStartTick = this.nextStartTick(fish);
    }

    protected int nextStartTick(VariantSchoolingFish p_25252_) {
        return reducedTickDelay(200 + p_25252_.getRandom().nextInt(200) % 20);
    }

    @Override
    public boolean canUse() {
        if (this.fish.hasFollowers()) {
            return false;
        } else if (this.fish.isFollower()) {
            return true;
        } else if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.fish);
            Predicate<VariantSchoolingFish> predicate = (fishy) -> fishy.canBeFollowed() || !fishy.isFollower();
            List<? extends VariantSchoolingFish> list = this.fish.level().getEntitiesOfClass(this.fish.getClass(), this.fish.getBoundingBox().inflate(10.0D, 10.0D, 10.0D), predicate);
            VariantSchoolingFish schoolingFish = DataFixUtils.orElse(list.stream().filter(VariantSchoolingFish::canBeFollowed).findAny(), this.fish);
            schoolingFish.addFollowers(list.stream().filter((fishy2) -> !fishy2.isFollower()));
            return this.fish.isFollower();
        }
    }

    public boolean canContinueToUse() {
        return this.fish.isFollower() && this.fish.inRangeOfLeader();
    }

    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.fish.stopFollowing();
    }

    @Override
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.fish.pathToLeader();
        }
    }
}
