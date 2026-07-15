package com.valiantenvoy.rainbow_reef.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.ParrotfishModel;
import com.valiantenvoy.rainbow_reef.entity.Parrotfish;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ParrotfishEepyLayer extends RenderLayer<Parrotfish, ParrotfishModel> {

    public ParrotfishEepyLayer(RenderLayerParent<Parrotfish, ParrotfishModel> renderer) {
        super(renderer);
    }

    public ResourceLocation eepyTextures(Parrotfish entity) {
        Parrotfish.ParrotfishVariant parrotfishVariant = Parrotfish.ParrotfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/parrotfish/sleepy_" + parrotfishVariant.getSerializedName() + ".png");
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, Parrotfish entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        long roundedTime = entity.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;
        if (!entity.isInvisible() && night && entity.isInWaterOrBubble()) {
            ResourceLocation resourceLocation = eepyTextures(entity);
            renderColoredCutoutModel(this.getParentModel(), resourceLocation, poseStack, buffer, packedLight, entity, -1);
        }
    }
}