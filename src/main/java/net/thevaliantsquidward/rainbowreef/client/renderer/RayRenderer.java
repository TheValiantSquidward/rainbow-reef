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
import net.thevaliantsquidward.rainbowreef.client.models.entity.RayModel;
import net.thevaliantsquidward.rainbowreef.entity.Ray;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class RayRenderer extends MobRenderer<Ray, RayModel> {

    public RayRenderer(EntityRendererProvider.Context context) {
        super(context, new RayModel(context.bakeLayer(ReefModelLayers.RAY)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(Ray entity) {
        Ray.RayVariant rayVariant = Ray.RayVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/ray/" + rayVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(Ray entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}