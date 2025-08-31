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
import net.thevaliantsquidward.rainbowreef.client.models.entity.MaoriWrasseModel;
import net.thevaliantsquidward.rainbowreef.entity.MaoriWrasse;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class MaoriWrasseRenderer extends MobRenderer<MaoriWrasse, MaoriWrasseModel> {

    public MaoriWrasseRenderer(EntityRendererProvider.Context context) {
        super(context, new MaoriWrasseModel(context.bakeLayer(ReefModelLayers.MAORI_WRASSE)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(MaoriWrasse entity) {
        MaoriWrasse.MaoriWrasseVariant maoriWrasseVariant = MaoriWrasse.MaoriWrasseVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/maori_wrasse/" + maoriWrasseVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(MaoriWrasse entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.setupRotations(entity, poseStack, ageInTicks, rotationYaw, partialTick);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -entity.prevTilt, -entity.tilt)));
    }
}