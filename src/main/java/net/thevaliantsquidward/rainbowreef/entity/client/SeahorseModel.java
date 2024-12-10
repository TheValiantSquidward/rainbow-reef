package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.RayEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.SeahorseEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SeahorseModel extends GeoModel<SeahorseEntity> {

    @Override
    public ResourceLocation getModelResource(SeahorseEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/seahorse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeahorseEntity animatable) {
       return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/kelpy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeahorseEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/seahorse.animation.json");
    }

    @Override
    public void setCustomAnimations(SeahorseEntity entity, long uniqueID, AnimationState<SeahorseEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}