package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.ReefDolphinModel;
import com.valiantenvoy.rainbow_reef.client.renderer.layers.ReefDolphinItemLayer;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Dolphin;

public class ReefDolphinRenderer extends MobRenderer<Dolphin, ReefDolphinModel> {

    private static final ResourceLocation DOLPHIN_LOCATION = RainbowReef.location("textures/entity/dolphin/dolphin_bottlenose.png");

    public ReefDolphinRenderer(EntityRendererProvider.Context context) {
        super(context, new ReefDolphinModel(context.bakeLayer(ReefModelLayers.DOLPHIN)), 0.7F);
        this.addLayer(new ReefDolphinItemLayer(this, context.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(Dolphin entity) {
        if (entity instanceof ReefVariantMob mob) {
            return mob.getVariantTexture();
        }
        return DOLPHIN_LOCATION;
    }
}