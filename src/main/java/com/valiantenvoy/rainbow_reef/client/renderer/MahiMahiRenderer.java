package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.MahiMahiModel;
import com.valiantenvoy.rainbow_reef.entity.MahiMahi;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MahiMahiRenderer extends MobRenderer<MahiMahi, MahiMahiModel> {

    public MahiMahiRenderer(EntityRendererProvider.Context context) {
        super(context, new MahiMahiModel(context.bakeLayer(ReefModelLayers.MAHI_MAHI)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(MahiMahi entity) {
        return entity.getVariantTexture();
    }
}