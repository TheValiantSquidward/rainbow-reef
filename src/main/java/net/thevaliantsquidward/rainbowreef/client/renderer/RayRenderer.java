package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.RayModel;
import net.thevaliantsquidward.rainbowreef.entity.Ray;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RayRenderer extends MobRenderer<Ray, RayModel> {

    public RayRenderer(EntityRendererProvider.Context context) {
        super(context, new RayModel(context.bakeLayer(ReefModelLayers.RAY)), 0.6F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Ray entity) {
        Ray.RayVariant rayVariant = Ray.RayVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/ray/" + rayVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(@NotNull Ray entity, @NotNull PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks, scale);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}