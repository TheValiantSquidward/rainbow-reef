package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ParrotfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.PipefishEntity;
import software.bernie.geckolib.model.GeoModel;

public class PipefishModel extends GeoModel<PipefishEntity> {
    @Override
    public ResourceLocation getModelResource(PipefishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/pipefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PipefishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/greenpipe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PipefishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/pipefish.animation.json");
    }


}