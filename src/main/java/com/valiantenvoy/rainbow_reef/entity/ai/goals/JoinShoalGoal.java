package com.valiantenvoy.rainbow_reef.entity.ai.goals;

import com.mojang.datafixers.DataFixUtils;
import com.valiantenvoy.rainbow_reef.entity.base.VariantShoalingFish;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class JoinShoalGoal extends Goal {

    private final VariantShoalingFish fish;
    private int timeToRecalcPath;
    private int nextStartTick;

    public JoinShoalGoal(VariantShoalingFish fish) {
        this.fish = fish;
        this.nextStartTick = this.nextStartTick(fish);
    }

    protected int nextStartTick(VariantShoalingFish fish) {
        return reducedTickDelay(200 + fish.getRandom().nextInt(200) % 20);
    }

    @Override
    public boolean canUse() {
        if (this.fish.isGroupLeader() || this.fish.isCircling()) {
            return false;
        } else if (this.fish.hasGroupLeader()) {
            return true;
        } else if (this.nextStartTick > 0) {
            this.nextStartTick--;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.fish);
            Predicate<VariantShoalingFish> predicate = (fish) -> fish.canGroupGrow() || !fish.hasGroupLeader();
            List<? extends VariantShoalingFish> list = this.fish.level().getEntitiesOfClass(this.fish.getClass(), this.fish.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
            VariantShoalingFish shoalingFish = DataFixUtils.orElse(list.stream().filter(VariantShoalingFish::canGroupGrow).findAny(), this.fish);
            shoalingFish.addFollowers(list.stream().filter((fish) -> !fish.hasGroupLeader()));
            return this.fish.hasGroupLeader();
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.fish.hasGroupLeader() && this.fish.inRangeOfGroupLeader() && !this.fish.isCircling();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.fish.leaveGroup();
    }

    @Override
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.fish.moveToGroupLeader();
        }
    }
}