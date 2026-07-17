package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.DamselfishModel;
import com.valiantenvoy.rainbow_reef.entity.Damselfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DamselfishRenderer extends MobRenderer<Damselfish, DamselfishModel> {

    public DamselfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DamselfishModel(context.bakeLayer(ReefModelLayers.DAMSELFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Damselfish entity) {
        return entity.getVariantTexture();
    }
}
