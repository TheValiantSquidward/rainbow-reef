package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.CrabEntity;
import software.bernie.geckolib.model.GeoModel;

public class CrabModel extends GeoModel<CrabEntity> {
    @Override
    public ResourceLocation getModelResource(CrabEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CrabEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/vampire.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CrabEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/crab.animation.json");
    }


}