package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.MaoriWrasseModel;
import com.valiantenvoy.rainbow_reef.entity.MaoriWrasse;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MaoriWrasseRenderer extends MobRenderer<MaoriWrasse, MaoriWrasseModel> {

    public MaoriWrasseRenderer(EntityRendererProvider.Context context) {
        super(context, new MaoriWrasseModel(context.bakeLayer(ReefModelLayers.MAORI_WRASSE)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(MaoriWrasse entity) {
        return entity.getVariantTexture();
    }
}