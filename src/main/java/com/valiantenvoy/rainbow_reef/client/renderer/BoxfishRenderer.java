package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.BoxfishModel;
import com.valiantenvoy.rainbow_reef.entity.Boxfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BoxfishRenderer extends MobRenderer<Boxfish, BoxfishModel> {

    public BoxfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BoxfishModel(context.bakeLayer(ReefModelLayers.BOXFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Boxfish entity) {
        return entity.getVariantTexture();
    }
}