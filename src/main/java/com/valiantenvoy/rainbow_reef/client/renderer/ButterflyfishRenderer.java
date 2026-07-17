package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.ButterflyfishModel;
import com.valiantenvoy.rainbow_reef.entity.Butterflyfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ButterflyfishRenderer extends MobRenderer<Butterflyfish, ButterflyfishModel> {

    public ButterflyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyfishModel(context.bakeLayer(ReefModelLayers.BUTTERFLYFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Butterflyfish entity) {
        return entity.getVariantTexture();
    }
}