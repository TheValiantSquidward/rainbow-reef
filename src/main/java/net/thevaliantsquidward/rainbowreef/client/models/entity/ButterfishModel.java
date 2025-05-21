package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.ButterfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ButterfishModel extends GeoModel<ButterfishEntity> {
    @Override
    public ResourceLocation getModelResource(ButterfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/butterflyfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ButterfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/copperband.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ButterfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/butterflyfish.animation.json");
    }

    @Override
    public void setCustomAnimations(ButterfishEntity entity, long uniqueID, AnimationState<ButterfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}