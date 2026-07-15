package com.valiantenvoy.rainbow_reef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.LargeSharkModel;
import com.valiantenvoy.rainbow_reef.entity.LargeShark;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LargeSharkRenderer extends MobRenderer<LargeShark, LargeSharkModel> {

    public LargeSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new LargeSharkModel(context.bakeLayer(ReefModelLayers.LARGE_SHARK)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LargeShark entity) {
        LargeShark.LargeSharkVariant largeSharkVariant = LargeShark.LargeSharkVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/large_shark/" + largeSharkVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(@NotNull LargeShark entity, @NotNull PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks, scale);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}