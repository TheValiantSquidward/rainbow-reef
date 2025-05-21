package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.MoorishIdolEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MoorishIdolModel extends GeoModel<MoorishIdolEntity> {
    @Override
    public ResourceLocation getModelResource(MoorishIdolEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/moorish_idol.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MoorishIdolEntity animatable) {
       return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/moorishidol/defaultmoorishidol.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MoorishIdolEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/moorish_idol.animation.json");
    }

    @Override
    public void setCustomAnimations(MoorishIdolEntity entity, long uniqueID, AnimationState<MoorishIdolEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}