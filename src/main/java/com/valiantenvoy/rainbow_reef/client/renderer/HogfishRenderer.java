package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.HogfishModel;
import com.valiantenvoy.rainbow_reef.entity.Hogfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HogfishRenderer extends MobRenderer<Hogfish, HogfishModel> {

    public HogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new HogfishModel(context.bakeLayer(ReefModelLayers.HOGFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Hogfish entity) {
        return entity.getVariantTexture();
    }
}