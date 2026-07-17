package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.WrasseModel;
import com.valiantenvoy.rainbow_reef.entity.Wrasse;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class WrasseRenderer extends MobRenderer<Wrasse, WrasseModel> {

    public WrasseRenderer(EntityRendererProvider.Context context) {
        super(context, new WrasseModel(context.bakeLayer(ReefModelLayers.WRASSE)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Wrasse entity) {
        return entity.getVariantTexture();
    }
}
