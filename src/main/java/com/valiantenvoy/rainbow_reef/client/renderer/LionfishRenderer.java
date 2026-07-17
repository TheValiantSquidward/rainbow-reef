package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.LionfishModel;
import com.valiantenvoy.rainbow_reef.entity.Lionfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LionfishRenderer extends MobRenderer<Lionfish, LionfishModel> {

    public LionfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LionfishModel(context.bakeLayer(ReefModelLayers.LIONFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Lionfish entity) {
        return entity.getVariantTexture();
    }
}