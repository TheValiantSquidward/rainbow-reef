package com.valiantenvoy.rainbow_reef.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.valiantenvoy.rainbow_reef.client.models.entity.ParrotfishModel;
import com.valiantenvoy.rainbow_reef.entity.Parrotfish;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import net.minecraft.client.renderer.MultiBufferSource;
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
        long roundedTime = entity.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;
        if (!entity.isInvisible() && night && entity.isInWaterOrBubble()) {
            ResourceLocation resourceLocation = this.getEepyTexture(entity);
            renderColoredCutoutModel(this.getParentModel(), resourceLocation, poseStack, buffer, packedLight, entity, -1);
        }
    }
}