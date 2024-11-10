package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.CrabEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

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