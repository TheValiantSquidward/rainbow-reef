package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.DwarfAngelfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GobyModel extends GeoModel<GobyEntity> {
    @Override
    public ResourceLocation getModelResource(GobyEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/goby.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GobyEntity animatable) {
       return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/firegoby.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GobyEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/goby.animation.json");
    }

    @Override
    public void setCustomAnimations(GobyEntity entity, long uniqueID, AnimationState<GobyEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }


}