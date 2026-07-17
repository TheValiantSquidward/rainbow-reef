package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.AngelfishModel;
import com.valiantenvoy.rainbow_reef.entity.Angelfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AngelfishRenderer extends MobRenderer<Angelfish, AngelfishModel> {

    public AngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new AngelfishModel(context.bakeLayer(ReefModelLayers.ANGELFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Angelfish entity) {
        return entity.getVariantTexture();
    }
}