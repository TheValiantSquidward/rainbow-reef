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
import net.thevaliantsquidward.rainbowreef.client.models.entity.SharkModel;
import net.thevaliantsquidward.rainbowreef.entity.Shark;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class SharkRenderer extends MobRenderer<Shark, SharkModel> {

    public SharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SharkModel(context.bakeLayer(ReefModelLayers.SHARK)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(Shark entity) {
        Shark.SharkVariant sharkVariant = Shark.SharkVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/shark/" + sharkVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(Shark entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}