package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.BillfishModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MaoriWrasseModel;
import net.thevaliantsquidward.rainbowreef.entity.BillfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.MaoriWrasseEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class MaoriWrasseRenderer extends MobRenderer<MaoriWrasseEntity, MaoriWrasseModel<MaoriWrasseEntity>> {

    public MaoriWrasseRenderer(EntityRendererProvider.Context context) {
        super(context, new MaoriWrasseModel<>(context.bakeLayer(ReefModelLayers.MAORI_WRASSE_LAYER)), 0.6F);
    }

    private static final ResourceLocation MAORI = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/maoriwrasse/maori_wrasse.png");

    public ResourceLocation getTextureLocation(MaoriWrasseEntity entity) {
        return switch (entity.getVariant()) {
            default -> MAORI;
        };
    }

    @Override
    protected void setupRotations(MaoriWrasseEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.setupRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
    }
}