package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.FrogfishModel;
import com.valiantenvoy.rainbow_reef.entity.Frogfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FrogfishRenderer extends MobRenderer<Frogfish, FrogfishModel> {

    public FrogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogfishModel(context.bakeLayer(ReefModelLayers.FROGFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Frogfish entity) {
        return entity.getVariantTexture();
    }
}