package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.mojang.datafixers.DataFixUtils;
import com.valiantenvoy.rainbow_reef.entity.utils.DolphinAccess;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Dolphin;

import java.util.List;
import java.util.function.Predicate;

public class DolphinFollowVariantLeaderGoal extends Goal {

    private final Dolphin dolphin;
    private final DolphinAccess dolphinAccess;
    private int timeToRecalcPath;
    private int nextStartTick;

    public DolphinFollowVariantLeaderGoal(Dolphin dolphin) {
        this.dolphin = dolphin;
        this.dolphinAccess = (DolphinAccess) dolphin;
        this.nextStartTick = this.nextStartTick(dolphin);
    }

    protected int nextStartTick(Dolphin dolphin) {
        return reducedTickDelay(200 + dolphin.getRandom().nextInt(200) % 20);
    }

    @Override
    public boolean canUse() {
        if (this.dolphinAccess.hasFollowers()) {
            return false;
        }
        else if (this.dolphinAccess.isFollower()) {
            return true;
        }
        else if (this.nextStartTick > 0) {
            this.nextStartTick--;
            return false;
        }
        else {
            this.nextStartTick = this.nextStartTick(this.dolphin);
            Predicate<Dolphin> predicate = (dolphin) -> {
                DolphinAccess access = (DolphinAccess) dolphin;
                return access.canBeFollowed() || !access.isFollower();
            };
            List<Dolphin> list = this.dolphin.level().getEntitiesOfClass(Dolphin.class, this.dolphin.getBoundingBox().inflate(10.0D), predicate);
            Dolphin dolphin1 = DataFixUtils.orElse(list.stream().filter(dolphin2 -> ((DolphinAccess) dolphin2).canBeFollowed()).findAny(), this.dolphin);
            ((DolphinAccess) dolphin1).addFollowers(list.stream().filter((dolphin2) -> !((DolphinAccess) dolphin2).isFollower()));
            return this.dolphinAccess.isFollower();
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.dolphinAccess.isFollower() && this.dolphinAccess.inRangeOfLeader();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.dolphinAccess.stopFollowing();
    }

    @Override
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.dolphinAccess.pathToLeader();
        }
    }
}
