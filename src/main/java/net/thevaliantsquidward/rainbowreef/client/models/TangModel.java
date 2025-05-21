package net.thevaliantsquidward.rainbowreef.client.models;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.TangEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TangModel extends GeoModel<TangEntity> {
    @Override
    public ResourceLocation getModelResource(TangEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/tangfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TangEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/blue.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TangEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/tangfish.animation.json");
    }

    @Override
    public void setCustomAnimations(TangEntity entity, long uniqueID, AnimationState<TangEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}
