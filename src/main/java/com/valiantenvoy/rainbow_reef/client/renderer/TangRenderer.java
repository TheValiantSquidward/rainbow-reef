package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.TangModel;
import com.valiantenvoy.rainbow_reef.entity.Tang;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TangRenderer extends MobRenderer<Tang, TangModel> {

    public TangRenderer(EntityRendererProvider.Context context) {
        super(context, new TangModel(context.bakeLayer(ReefModelLayers.TANG)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Tang entity) {
        return entity.getVariantTexture();
    }
}
