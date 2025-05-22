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
import net.thevaliantsquidward.rainbowreef.entity.SmallSharkEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class SmallSharkRenderer extends MobRenderer<SmallSharkEntity, SmallSharkModel<SmallSharkEntity>> {

    private static final ResourceLocation TEXTURE_EPAULETTE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/epauletteshark.png");
    private static final ResourceLocation TEXTURE_PAJAMA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/pajama.png");
    private static final ResourceLocation TEXTURE_NURSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/nurse.png");
    private static final ResourceLocation TEXTURE_HORNED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/horned.png");
    private static final ResourceLocation TEXTURE_ZEBRA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/zebra.png");
    private static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/albino.png");
    private static final ResourceLocation TEXTURE_PIEBALD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/piebald_horned.png");
    private static final ResourceLocation TEXTURE_PORTJACKSON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/portjackson.png");

    public SmallSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SmallSharkModel<>(context.bakeLayer(ReefModelLayers.SMALL_SHARK_LAYER)), 0.4F);
    }

    public ResourceLocation getTextureLocation(SmallSharkEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_PAJAMA;
            case 2 -> TEXTURE_HORNED;
            case 3 -> TEXTURE_NURSE;
            case 4 -> TEXTURE_ZEBRA;
            case 5 -> TEXTURE_ALBINO;
            case 6 -> TEXTURE_PIEBALD;
            case 7 -> TEXTURE_PORTJACKSON;
            default -> TEXTURE_EPAULETTE;
        };
    }

    @Override
    protected void setupRotations(SmallSharkEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.setupRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
    }
}