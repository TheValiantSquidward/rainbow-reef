package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.BassletModel;
import com.valiantenvoy.rainbow_reef.entity.Basslet;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BassletRenderer extends MobRenderer<Basslet, BassletModel> {

    public BassletRenderer(EntityRendererProvider.Context context) {
        super(context, new BassletModel(context.bakeLayer(ReefModelLayers.BASSLET)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Basslet entity) {
        return entity.getVariantTexture();
    }
}