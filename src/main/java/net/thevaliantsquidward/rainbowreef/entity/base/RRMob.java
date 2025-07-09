package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;

public class RRMob extends WaterAnimal {


    protected RRMob(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public void setMoveControl(MoveControl newControl) {
            this.setMoveControl(newControl);

    }
}
