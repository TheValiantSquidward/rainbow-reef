package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.ReefTurtleModel;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Turtle;

public class ReefTurtleRenderer extends MobRenderer<Turtle, ReefTurtleModel> {

    private static final ResourceLocation TURTLE_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/turtle/big_sea_turtle.png");

    public ReefTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new ReefTurtleModel(context.bakeLayer(ReefModelLayers.TURTLE)), 0.7F);
    }

    @Override
    protected float getShadowRadius(Turtle entity) {
        float shadowRadius = super.getShadowRadius(entity);
        return entity.isBaby() ? shadowRadius * 0.83F : shadowRadius;
    }

    @Override
    public ResourceLocation getTextureLocation(Turtle entity) {
        if (entity instanceof ReefVariantMob mob) {
            return mob.getVariantTexture();
        }
        return TURTLE_LOCATION;
    }
}