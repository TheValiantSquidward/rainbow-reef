package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.PipefishModel;
import com.valiantenvoy.rainbow_reef.entity.Pipefish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PipefishRenderer extends MobRenderer<Pipefish, PipefishModel> {

    public PipefishRenderer(EntityRendererProvider.Context context) {
        super(context, new PipefishModel(context.bakeLayer(ReefModelLayers.PIPEFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Pipefish entity) {
        return entity.getVariantTexture();
    }
}