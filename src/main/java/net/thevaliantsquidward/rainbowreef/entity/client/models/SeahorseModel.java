package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.SeahorseEntity;
import software.bernie.geckolib.model.GeoModel;

public class SeahorseModel extends GeoModel<SeahorseEntity> {
    @Override
    public ResourceLocation getModelResource(SeahorseEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/seahorse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeahorseEntity animatable) {
       return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/kelpy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeahorseEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/seahorse.animation.json");
    }


}