package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.TriggerfishModel;
import com.valiantenvoy.rainbow_reef.entity.Triggerfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TriggerfishRenderer extends MobRenderer<Triggerfish, TriggerfishModel> {

    public TriggerfishRenderer(EntityRendererProvider.Context context) {
        super(context, new TriggerfishModel(context.bakeLayer(ReefModelLayers.TRIGGERFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Triggerfish entity) {
        return entity.getVariantTexture();
    }
}
