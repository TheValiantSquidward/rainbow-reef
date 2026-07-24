package com.valiantenvoy.rainbow_reef.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valiantenvoy.rainbow_reef.client.models.entity.JellyfishModel;
import com.valiantenvoy.rainbow_reef.entity.Jellyfish;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class JellyfishOuterLayer extends RenderLayer<Jellyfish, JellyfishModel> {

    public JellyfishOuterLayer(RenderLayerParent<Jellyfish, JellyfishModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, Jellyfish entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isInvisible()) {
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityTranslucent(entity.getVariantTexture()));
            this.getParentModel().renderOuterBody(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F));
        }
    }
}