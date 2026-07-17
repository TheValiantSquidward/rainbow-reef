package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.LargeSharkModel;
import com.valiantenvoy.rainbow_reef.entity.LargeShark;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LargeSharkRenderer extends MobRenderer<LargeShark, LargeSharkModel> {

    public LargeSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new LargeSharkModel(context.bakeLayer(ReefModelLayers.LARGE_SHARK)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(LargeShark entity) {
        return entity.getVariantTexture();
    }
}