package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.MoorishIdolEntity;
import software.bernie.geckolib.model.GeoModel;

public class MoorishIdolModel extends GeoModel<MoorishIdolEntity> {
    @Override
    public ResourceLocation getModelResource(MoorishIdolEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/moorish_idol.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MoorishIdolEntity animatable) {
       return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/defaultmoorishidol.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MoorishIdolEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/moorish_idol.animation.json");
    }



}