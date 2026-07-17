package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.MoorishIdolModel;
import com.valiantenvoy.rainbow_reef.entity.MoorishIdol;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MoorishIdolRenderer extends MobRenderer<MoorishIdol, MoorishIdolModel> {

    public MoorishIdolRenderer(EntityRendererProvider.Context context) {
        super(context, new MoorishIdolModel(context.bakeLayer(ReefModelLayers.MOORISH_IDOL)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(MoorishIdol entity) {
        return entity.getVariantTexture();
    }
}