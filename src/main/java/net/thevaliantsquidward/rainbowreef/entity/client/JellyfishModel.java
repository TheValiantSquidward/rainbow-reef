package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.AngelfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.JellyfishEntity;
import software.bernie.example.entity.FakeGlassEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

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
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish_1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JellyfishEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/jellyfish.animation.json");
    }


}