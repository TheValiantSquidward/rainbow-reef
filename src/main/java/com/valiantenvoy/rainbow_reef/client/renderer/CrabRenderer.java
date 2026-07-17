package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.CrabModel;
import com.valiantenvoy.rainbow_reef.entity.Crab;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<Crab, CrabModel> {

    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CrabModel(context.bakeLayer(ReefModelLayers.CRAB)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Crab entity) {
        return entity.getVariantTexture();
    }
}