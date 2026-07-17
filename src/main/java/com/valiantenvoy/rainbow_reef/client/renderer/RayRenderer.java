package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.RayModel;
import com.valiantenvoy.rainbow_reef.entity.Ray;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RayRenderer extends MobRenderer<Ray, RayModel> {

    public RayRenderer(EntityRendererProvider.Context context) {
        super(context, new RayModel(context.bakeLayer(ReefModelLayers.RAY)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(Ray entity) {
        return entity.getVariantTexture();
    }
}