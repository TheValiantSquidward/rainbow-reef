package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.BassletEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.BoxfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BoxfishModel extends GeoModel<BoxfishEntity> {
    @Override
    public ResourceLocation getModelResource(BoxfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/boxfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoxfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellowboxfish.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BoxfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/boxfish.animation.json");
    }

    @Override
    public void setCustomAnimations(BoxfishEntity entity, long uniqueID, AnimationState<BoxfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }


}