package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.DwarfAngelfishEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DwarfAngelfishModel extends GeoModel<DwarfAngelfishEntity> {
    @Override
    public ResourceLocation getModelResource(DwarfAngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/dwarf_angelfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarfAngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bicolor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarfAngelfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/dwarf_angelfish.animation.json");
    }

    @Override
    public void setCustomAnimations(DwarfAngelfishEntity entity, long uniqueID, AnimationState<DwarfAngelfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}