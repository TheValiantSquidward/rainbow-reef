package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.BillfishModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.EagleRayModel;
import net.thevaliantsquidward.rainbowreef.entity.BillfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.RayEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class BillfishRenderer extends MobRenderer<BillfishEntity, BillfishModel<BillfishEntity>> {

    public BillfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BillfishModel<>(context.bakeLayer(ReefModelLayers.BILLFISH_LAYER)), 0.6F);
    }

    private static final ResourceLocation SAILFISH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/billfish/sailfish.png");

    public ResourceLocation getTextureLocation(BillfishEntity entity) {
        return switch (entity.getVariant()) {
            default -> SAILFISH;
        };
    }

    @Override
    protected void setupRotations(BillfishEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.setupRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
    }
}