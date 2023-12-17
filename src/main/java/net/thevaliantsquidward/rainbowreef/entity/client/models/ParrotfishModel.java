package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.HogfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.ParrotfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class ParrotfishModel extends GeoModel<ParrotfishEntity> {
    @Override
    public ResourceLocation getModelResource(ParrotfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/parrotfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParrotfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/blue.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ParrotfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/parrotfish.animation.json");
    }


}