package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.PipefishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class PipefishModel extends GeoModel<PipefishEntity> {
    @Override
    public ResourceLocation getModelResource(PipefishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/pipefish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PipefishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/greenpipe.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PipefishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/pipefish.animation.json");
    }

    @Override
    public void setCustomAnimations(PipefishEntity entity, long uniqueID, AnimationState<PipefishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}