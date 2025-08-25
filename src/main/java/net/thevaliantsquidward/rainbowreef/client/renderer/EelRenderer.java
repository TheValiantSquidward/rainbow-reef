package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.EelModel;
import net.thevaliantsquidward.rainbowreef.entity.Eel;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class EelRenderer extends MobRenderer<Eel, EelModel<Eel>> {

    private static final ResourceLocation TEXTURE_GREEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/green.png");
    private static final ResourceLocation TEXTURE_DRAGON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/dragon.png");
    private static final ResourceLocation TEXTURE_LACED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/laced.png");
    private static final ResourceLocation TEXTURE_FIMBRIATED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/fimbriated.png");
    private static final ResourceLocation TEXTURE_JAPANESE_DRAGON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/japanese_dragon.png");
    private static final ResourceLocation TEXTURE_MUD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/mud.png");
    private static final ResourceLocation TEXTURE_FRESHWATER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eel/fresh.png");

    public EelRenderer(EntityRendererProvider.Context context) {
        super(context, new EelModel<>(context.bakeLayer(ReefModelLayers.EEL_LAYER)), 0.4F);
    }

    public ResourceLocation getTextureLocation(Eel entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_GREEN;
            case 2 -> TEXTURE_DRAGON;
            case 3 -> TEXTURE_LACED;
            case 4 -> TEXTURE_FIMBRIATED;
            case 5 -> TEXTURE_JAPANESE_DRAGON;
            case 6 -> TEXTURE_MUD;
            case 7 -> TEXTURE_FRESHWATER;
            default -> TEXTURE_GREEN;
        };
    }

    @Override
    protected void setupRotations(Eel animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.setupRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
    }
}