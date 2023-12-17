package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class ClownfishModel extends GeoModel<ClownfishEntity> {
    @Override
    public ResourceLocation getModelResource(ClownfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/clownfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClownfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_3.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClownfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/clownfish.animation.json");
    }


}