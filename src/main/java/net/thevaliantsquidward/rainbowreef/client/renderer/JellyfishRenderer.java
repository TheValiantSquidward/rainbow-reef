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
    protected void setupRotations(Jellyfish entity, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks) {
        float xRot = Mth.lerp(partialTicks, entity.xBodyRotO, entity.xBodyRot);
        float zRot = Mth.lerp(partialTicks, entity.zBodyRotO, entity.zBodyRot);
        poseStack.translate(0.0F, 0.5F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - rotationYaw));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        poseStack.mulPose(Axis.YP.rotationDegrees(zRot));
        poseStack.translate(0.0F, -1.2F, 0.0F);
    }

    public ResourceLocation getTextureLocation(Jellyfish entity) {
        Jellyfish.JellyfishVariant jellyfishVariant = Jellyfish.JellyfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/jellyfish/" + jellyfishVariant.getSerializedName() + ".png");
    }
}