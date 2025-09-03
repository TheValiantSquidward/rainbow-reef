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
import net.thevaliantsquidward.rainbowreef.client.models.entity.JellyfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Jellyfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class JellyfishRenderer extends MobRenderer<Jellyfish, JellyfishModel> {

    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishModel(context.bakeLayer(ReefModelLayers.JELLYFISH)), 0.4F);
    }

    @Override
    protected float getFlipDegrees(Jellyfish entity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(Jellyfish entity) {
        Jellyfish.JellyfishVariant jellyfishVariant = Jellyfish.JellyfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/jellyfish/" + jellyfishVariant.getSerializedName() + ".png");
    }

    @Override
    protected void setupRotations(Jellyfish entity, PoseStack poseStack, float p_115319_, float p_115320_, float partialTicks) {
        if (entity.isInWaterOrBubble()) {
            if (isEntityUpsideDown(entity)) {
                poseStack.translate(0.0D, entity.getBbHeight() + 0.1F, 0.0D);
                poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F));
            }
            float translateY = entity.getBbHeight() * 0.5F;
            poseStack.translate(0.0F, translateY, 0.0F);
            poseStack.mulPose(Axis.YP.rotationDegrees(360.0F - Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees((Mth.rotLerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F) % 360.0F));
            poseStack.translate(0.0F, -translateY, 0.0F);
        } else {
            super.setupRotations(entity, poseStack, p_115319_, p_115320_, partialTicks);
        }
    }
}