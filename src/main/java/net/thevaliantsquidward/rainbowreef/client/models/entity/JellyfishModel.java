package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.JellyfishEntity;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class JellyfishModel extends GeoModel<JellyfishEntity> {

    @Override
    public RenderType getRenderType(JellyfishEntity animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }

    @Override
    public ResourceLocation getModelResource(JellyfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/jellyfish.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JellyfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JellyfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/jellyfish.animation.json");
    }

    @Override
    public void setCustomAnimations(JellyfishEntity entity, long uniqueID, AnimationState<JellyfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("bell");

        core.setRotX(entity.xBodyRot * (Mth.DEG_TO_RAD));
    }


}