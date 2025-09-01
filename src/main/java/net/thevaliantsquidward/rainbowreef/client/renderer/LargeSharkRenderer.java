package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.LargeSharkModel;
import net.thevaliantsquidward.rainbowreef.entity.LargeShark;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class LargeSharkRenderer extends MobRenderer<LargeShark, LargeSharkModel> {

    public LargeSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new LargeSharkModel(context.bakeLayer(ReefModelLayers.LARGE_SHARK)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(LargeShark entity) {
        LargeShark.LargeSharkVariant largeSharkVariant = LargeShark.LargeSharkVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/large_shark/" + largeSharkVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(LargeShark entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}