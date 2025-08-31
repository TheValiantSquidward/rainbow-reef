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
import net.thevaliantsquidward.rainbowreef.client.models.entity.SmallSharkModel;
import net.thevaliantsquidward.rainbowreef.entity.SmallShark;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class SmallSharkRenderer extends MobRenderer<SmallShark, SmallSharkModel> {

    public SmallSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SmallSharkModel(context.bakeLayer(ReefModelLayers.SMALL_SHARK)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(SmallShark entity) {
        SmallShark.SmallSharkVariant smallSharkVariant = SmallShark.SmallSharkVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/small_shark/" + smallSharkVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(SmallShark entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTicks);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, -entity.prevTilt, -entity.tilt)));
    }
}