package com.valiantenvoy.rainbow_reef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.valiantenvoy.rainbow_reef.client.models.entity.JellyfishModel;
import com.valiantenvoy.rainbow_reef.entity.Jellyfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JellyfishRenderer extends MobRenderer<Jellyfish, JellyfishModel> {

    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishModel(context.bakeLayer(ReefModelLayers.JELLYFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Jellyfish entity) {
        return entity.getVariantTexture();
    }

    @Override
    protected void setupRotations(Jellyfish entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
        if (entity.isRenderedInTooltip()) {
            return;
        }
        if (entity.isInWaterOrBubble()) {
            if (isEntityUpsideDown(entity)) {
                poseStack.translate(0.0D, entity.getBbHeight() + 0.1F, 0.0D);
                poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
            }
            float translateY = entity.getBbHeight() * 0.5F;
            poseStack.translate(0.0F, translateY, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(360.0F - Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees((Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F) % 360.0F));
            poseStack.translate(0.0F, -translateY, 0.0F);
        } else {
            super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks, scale);
        }
    }
}