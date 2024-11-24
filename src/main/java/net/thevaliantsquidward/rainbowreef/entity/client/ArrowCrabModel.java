package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.AngelfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.ArrowCrabEntity;
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
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/dwarf_angelfish.animation.json");
    }


}