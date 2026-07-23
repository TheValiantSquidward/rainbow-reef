package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.JellyfishModel;
import com.valiantenvoy.rainbow_reef.client.renderer.layers.JellyfishOuterLayer;
import com.valiantenvoy.rainbow_reef.entity.Jellyfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class JellyfishRenderer extends ReefMobRenderer<Jellyfish, JellyfishModel> {

    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishModel(context.bakeLayer(ReefModelLayers.JELLYFISH)), 0.4F);
        this.addLayer(new JellyfishOuterLayer(this));
    }
}