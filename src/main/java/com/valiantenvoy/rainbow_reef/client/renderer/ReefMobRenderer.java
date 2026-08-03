package com.valiantenvoy.rainbow_reef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.entity.utils.BodyChainMob;
import com.valiantenvoy.rainbow_reef.entity.variant.VariantRenderType;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.NeoForgeRenderTypes;

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
            return this.getVariantRenderType(entity.getVariantRenderType(), textureLocation);
        }
        else {
            return super.getRenderType(entity, false, false, glowing);
        }
    }

    public RenderType getVariantRenderType(VariantRenderType type, ResourceLocation texture) {
        return switch (type) {
            case ENTITY_CUTOUT -> RenderType.entityCutout(texture);
            case ENTITY_CUTOUT_NO_CULL -> RenderType.entityCutoutNoCull(texture);
            case ENTITY_CUTOUT_MIPPED -> NeoForgeRenderTypes.getEntityCutoutMipped(texture);
            case ENTITY_TRANSLUCENT -> RenderType.entityTranslucent(texture);
            case ENTITY_TRANSLUCENT_CULL -> RenderType.entityTranslucentCull(texture);
            case ENTITY_TRANSLUCENT_EMISSIVE -> RenderType.entityTranslucentEmissive(texture);
        };
    }

    @Override
    protected void setupRotations(T entity, PoseStack poseStack, float bob, float yBodyRot, float partialTicks, float scale) {
        if (entity instanceof BodyChainMob bodyChainMob) {
            super.setupRotations(entity, poseStack, bob, bodyChainMob.getRenderYaw(partialTicks), partialTicks, scale);
        }
        else {
            super.setupRotations(entity, poseStack, bob, yBodyRot, partialTicks, scale);
        }
    }
}
