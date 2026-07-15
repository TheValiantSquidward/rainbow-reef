package com.valiantenvoy.rainbow_reef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.SharkModel;
import com.valiantenvoy.rainbow_reef.entity.Shark;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SharkRenderer extends MobRenderer<Shark, SharkModel> {

    public SharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SharkModel(context.bakeLayer(ReefModelLayers.SHARK)), 0.7F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Shark entity) {
        Shark.SharkVariant sharkVariant = Shark.SharkVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/shark/" + sharkVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(@NotNull Shark entity, @NotNull PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks, scale);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}