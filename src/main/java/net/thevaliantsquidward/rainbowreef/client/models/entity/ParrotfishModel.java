package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.ParrotfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ParrotfishModel extends GeoModel<ParrotfishEntity> {
    @Override
    public ResourceLocation getModelResource(ParrotfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/parrotfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParrotfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/blue.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ParrotfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/parrotfish.animation.json");
    }

    @Override
    public void setCustomAnimations(ParrotfishEntity entity, long uniqueID, AnimationState<ParrotfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }


}