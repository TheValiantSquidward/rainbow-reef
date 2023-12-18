package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.BassletEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class BassletModel extends GeoModel<BassletEntity> {
    @Override
    public ResourceLocation getModelResource(BassletEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/basslet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BassletEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/fairy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BassletEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/basslet.animation.json");
    }


}