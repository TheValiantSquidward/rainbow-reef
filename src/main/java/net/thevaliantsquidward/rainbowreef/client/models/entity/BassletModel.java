package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.BassletEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BassletModel extends GeoModel<BassletEntity> {
    @Override
    public ResourceLocation getModelResource(BassletEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/basslet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BassletEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/fairy.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BassletEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/basslet.animation.json");
    }

    @Override
    public void setCustomAnimations(BassletEntity entity, long uniqueID, AnimationState<BassletEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}