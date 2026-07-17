package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class ReefMobRenderer<T extends ReefMob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    public ReefMobRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, Function<ModelPart, M> model, float shadowRadius) {
        super(context, model.apply(context.bakeLayer(layer)), shadowRadius);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getVariantTexture();
    }
}
