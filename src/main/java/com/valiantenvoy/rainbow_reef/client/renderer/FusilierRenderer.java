package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.FusilierModel;
import com.valiantenvoy.rainbow_reef.entity.Fusilier;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FusilierRenderer extends MobRenderer<Fusilier, FusilierModel> {

    public FusilierRenderer(EntityRendererProvider.Context context) {
        super(context, new FusilierModel(context.bakeLayer(ReefModelLayers.FUSILIER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Fusilier entity) {
        return entity.getVariantTexture();
    }
}
