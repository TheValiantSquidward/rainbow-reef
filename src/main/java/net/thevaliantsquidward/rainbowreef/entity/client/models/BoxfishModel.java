package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.BoxfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class BoxfishModel extends GeoModel<BoxfishEntity> {
    @Override
    public ResourceLocation getModelResource(BoxfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/boxfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoxfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellowboxfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BoxfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/boxfish.animation.json");
    }


}