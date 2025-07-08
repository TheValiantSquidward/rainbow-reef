package net.thevaliantsquidward.rainbowreef.entity.base.NemHoster;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.thevaliantsquidward.rainbowreef.util.RRPOI;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LocateNemGoal extends Goal {

    NemHoster clown;
    int CD;


    public LocateNemGoal(NemHoster fish, int cooldown) {
        super();
        this.clown = fish;
        this.CD = cooldown;
    }

    @Override
    public boolean canUse() {
        return !this.clown.hasNem() && this.clown.nemSearchCooldown <= 0;
    }
    //can only use if the clown does not have a nem


    public boolean canContinueToUse() {
        return false;
    }

    public void start() {
        this.clown.nemSearchCooldown = this.CD;
        this.clown.findAndSetNems();
    }


}
