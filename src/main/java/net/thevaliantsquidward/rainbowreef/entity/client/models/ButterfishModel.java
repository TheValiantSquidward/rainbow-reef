package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ButterfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class ButterfishModel extends GeoModel<ButterfishEntity> {
    @Override
    public ResourceLocation getModelResource(ButterfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/butterflyfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ButterfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/copperband.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ButterfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/butterflyfish.animation.json");
    }


}