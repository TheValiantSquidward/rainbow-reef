package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.SeahorseModel;
import com.valiantenvoy.rainbow_reef.entity.Seahorse;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeahorseRenderer extends MobRenderer<Seahorse, SeahorseModel> {

    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super(context, new SeahorseModel(context.bakeLayer(ReefModelLayers.SEAHORSE)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Seahorse entity) {
        return entity.getVariantTexture();
    }
}