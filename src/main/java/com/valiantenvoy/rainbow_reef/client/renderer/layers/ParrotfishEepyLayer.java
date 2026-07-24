package com.valiantenvoy.rainbow_reef.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valiantenvoy.rainbow_reef.client.models.entity.ParrotfishModel;
import com.valiantenvoy.rainbow_reef.entity.Parrotfish;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import com.valiantenvoy.rainbow_reef.utils.ReefColorUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class ParrotfishEepyLayer extends RenderLayer<Parrotfish, ParrotfishModel> {

    public ParrotfishEepyLayer(RenderLayerParent<Parrotfish, ParrotfishModel> renderer) {
        super(renderer);
    }

    public ResourceLocation getEepyTexture(Parrotfish entity) {
        return ReefMobVariant.fullTextureId(entity.getVariantTextureRaw().withPath(path -> path + "_sleepy"));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Parrotfish entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entity.isInvisible() && entity.isEepy()) {
            VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(this.getEepyTexture(entity)));
            this.getParentModel().renderToBuffer(poseStack, consumer, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), ReefColorUtils.packColor(1.0F, 1.0F, 1.0F, 1.0F));
        }
    }
}