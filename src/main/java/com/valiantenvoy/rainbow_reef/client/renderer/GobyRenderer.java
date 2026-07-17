package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.GobyModel;
import com.valiantenvoy.rainbow_reef.entity.Goby;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GobyRenderer extends MobRenderer<Goby, GobyModel> {

    public GobyRenderer(EntityRendererProvider.Context context) {
        super(context, new GobyModel(context.bakeLayer(ReefModelLayers.GOBY)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Goby entity) {
        return entity.getVariantTexture();
    }
}