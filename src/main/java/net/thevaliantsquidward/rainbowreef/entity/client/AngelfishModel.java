package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.AngelfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.DwarfAngelfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class AngelfishModel extends GeoModel<AngelfishEntity> {
    @Override
    public ResourceLocation getModelResource(AngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/queenangel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/angelfish.animation.json");
    }

    @Override
    public void setCustomAnimations(AngelfishEntity entity, long uniqueID, AnimationState<AngelfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}