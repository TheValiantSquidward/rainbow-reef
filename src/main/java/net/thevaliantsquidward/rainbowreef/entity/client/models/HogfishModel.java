package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.HogfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class HogfishModel extends GeoModel<HogfishEntity> {
    @Override
    public ResourceLocation getModelResource(HogfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/hogfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HogfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/cuban.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HogfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/hogfish.animation.json");
    }


}