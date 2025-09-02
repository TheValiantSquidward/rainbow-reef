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
import net.thevaliantsquidward.rainbowreef.client.models.entity.BillfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Billfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class BillfishRenderer extends MobRenderer<Billfish, BillfishModel> {

    public BillfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BillfishModel(context.bakeLayer(ReefModelLayers.BILLFISH)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(Billfish entity) {
        Billfish.BillfishVariant billfishVariant = Billfish.BillfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/billfish/" + billfishVariant.getSerializedName() + ".png");
    }

//    @Override
//    protected void setupRotations(Billfish animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
//        super.setupRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
//        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
//    }
}