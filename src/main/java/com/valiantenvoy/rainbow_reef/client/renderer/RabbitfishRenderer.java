package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.RabbitfishModel;
import com.valiantenvoy.rainbow_reef.entity.Rabbitfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RabbitfishRenderer extends MobRenderer<Rabbitfish, RabbitfishModel> {

    public RabbitfishRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitfishModel(context.bakeLayer(ReefModelLayers.RABBITFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Rabbitfish entity) {
        return entity.getVariantTexture();
    }
}
