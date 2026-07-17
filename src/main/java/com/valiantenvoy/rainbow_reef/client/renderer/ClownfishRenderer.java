package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.ClownfishModel;
import com.valiantenvoy.rainbow_reef.entity.Clownfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ClownfishRenderer extends MobRenderer<Clownfish, ClownfishModel> {

    public ClownfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ClownfishModel(context.bakeLayer(ReefModelLayers.CLOWNFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Clownfish entity) {
        return entity.getVariantTexture();
    }
}