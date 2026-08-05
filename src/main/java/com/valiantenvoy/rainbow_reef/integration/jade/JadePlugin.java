package com.valiantenvoy.rainbow_reef.integration.jade;

import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.Turtle;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(new ReefMobProvider(), ReefMob.class);
        registration.registerEntityComponent(new DolphinProvider(), Dolphin.class);
        registration.registerEntityComponent(new TurtleProvider(), Turtle.class);
    }
}
