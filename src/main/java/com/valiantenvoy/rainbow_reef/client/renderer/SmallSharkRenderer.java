package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.SmallSharkModel;
import com.valiantenvoy.rainbow_reef.entity.SmallShark;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SmallSharkRenderer extends MobRenderer<SmallShark, SmallSharkModel> {

    public SmallSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SmallSharkModel(context.bakeLayer(ReefModelLayers.SMALL_SHARK)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(SmallShark entity) {
        return entity.getVariantTexture();
    }
}