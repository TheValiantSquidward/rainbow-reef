package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import software.bernie.geckolib.model.GeoModel;

public class GobyModel extends GeoModel<GobyEntity> {
    @Override
    public ResourceLocation getModelResource(GobyEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/goby.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GobyEntity animatable) {
       return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/firegoby.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GobyEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/goby.animation.json");
    }


}