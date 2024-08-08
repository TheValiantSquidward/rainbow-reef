package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.HogfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HogfishModel extends GeoModel<HogfishEntity> {
    @Override
    public ResourceLocation getModelResource(HogfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/hogfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HogfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/cuban.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HogfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/hogfish.animation.json");
    }

    @Override
    public void setCustomAnimations(HogfishEntity entity, long uniqueID, AnimationState<HogfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }


}