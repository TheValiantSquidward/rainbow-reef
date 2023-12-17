package net.thevaliantsquidward.rainbowreef.entity.client.models;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.DwarfAngelfishEntity;
import software.bernie.geckolib.model.GeoModel;

public class DwarfAngelfishModel extends GeoModel<DwarfAngelfishEntity> {
    @Override
    public ResourceLocation getModelResource(DwarfAngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/dwarf_angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfAngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bicolor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfAngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/dwarf_angelfish.animation.json");
    }


}