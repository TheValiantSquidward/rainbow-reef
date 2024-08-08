package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ButterfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ClownfishModel extends GeoModel<ClownfishEntity> {
    @Override
    public ResourceLocation getModelResource(ClownfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/clownfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClownfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_3.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClownfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/clownfish.animation.json");
    }

    @Override
    public void setCustomAnimations(ClownfishEntity entity, long uniqueID, AnimationState<ClownfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}