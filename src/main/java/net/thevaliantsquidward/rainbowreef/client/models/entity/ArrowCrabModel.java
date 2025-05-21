package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.ArrowCrabEntity;
import software.bernie.geckolib.model.GeoModel;

public class ArrowCrabModel extends GeoModel<ArrowCrabEntity> {
    @Override
    public ResourceLocation getModelResource(ArrowCrabEntity animatable) {

        return new ResourceLocation(RainbowReef.MOD_ID, "geo/arrow_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArrowCrabEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/arrowcrab/yellowlinearrowcrab.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArrowCrabEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/arrow_crab.animation.json");
    }


}