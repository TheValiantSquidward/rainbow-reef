package net.thevaliantsquidward.rainbowreef.entity.interfaces;


public interface SemiAquatic {

    boolean shouldEnterWater();

    boolean shouldLeaveWater();

    boolean shouldStopMoving();

    int getWaterSearchRange();
}