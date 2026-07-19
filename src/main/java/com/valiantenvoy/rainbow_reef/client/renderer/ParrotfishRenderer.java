package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.ParrotfishModel;
import com.valiantenvoy.rainbow_reef.client.renderer.layers.ParrotfishEepyLayer;
import com.valiantenvoy.rainbow_reef.entity.Parrotfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ParrotfishRenderer extends MobRenderer<Parrotfish, ParrotfishModel> {

    public ParrotfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ParrotfishModel(context.bakeLayer(ReefModelLayers.PARROTFISH)), 0.4F);
        this.addLayer(new ParrotfishEepyLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Parrotfish entity) {
        return entity.getVariantTexture();
    }
}