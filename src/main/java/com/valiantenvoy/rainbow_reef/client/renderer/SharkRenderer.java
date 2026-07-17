package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.SharkModel;
import com.valiantenvoy.rainbow_reef.entity.Shark;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SharkRenderer extends MobRenderer<Shark, SharkModel> {

    public SharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SharkModel(context.bakeLayer(ReefModelLayers.SHARK)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Shark entity) {
        return entity.getVariantTexture();
    }
}