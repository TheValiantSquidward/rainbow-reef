package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ReefMobRenderer<T extends ReefMob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    public ReefMobRenderer(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    public static <T extends ReefMob, M extends EntityModel<T>> EntityRendererProvider<T> createRenderer(ModelLayerLocation layer, Function<ModelPart, M> modelFactory, float shadowRadius) {
        return context -> new ReefMobRenderer<>(context, modelFactory.apply(context.bakeLayer(layer)), shadowRadius);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getVariantTexture();
    }

    @Nullable
    @Override
    protected RenderType getRenderType(T entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        ResourceLocation textureLocation = this.getTextureLocation(entity);
        if (translucent) {
            return RenderType.itemEntityTranslucentCull(textureLocation);
        }
        else if (bodyVisible) {
            return entity.getVariantRenderType().getRenderType(textureLocation);
        }
        else {
            return super.getRenderType(entity, false, false, glowing);
        }
    }
}
