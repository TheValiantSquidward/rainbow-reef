package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.AngelfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.DwarfAngelfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class AngelfishModel extends GeoModel<AngelfishEntity> {
    @Override
    public ResourceLocation getModelResource(AngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/emperorangel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/angelfish.animation.json");
    }


}