package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.TangEntity;
import software.bernie.geckolib.model.GeoModel;

public class TangModel extends GeoModel<TangEntity> {
    @Override
    public ResourceLocation getModelResource(TangEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/tangfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TangEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bluetang.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TangEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/tangfish.animation.json");
    }


}
