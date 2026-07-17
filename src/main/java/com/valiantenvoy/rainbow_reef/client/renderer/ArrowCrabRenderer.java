package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.ArrowCrabModel;
import com.valiantenvoy.rainbow_reef.entity.ArrowCrab;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ArrowCrabRenderer extends MobRenderer<ArrowCrab, ArrowCrabModel> {

    public ArrowCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowCrabModel(context.bakeLayer(ReefModelLayers.ARROW_CRAB)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(ArrowCrab entity) {
        return entity.getVariantTexture();
    }
}