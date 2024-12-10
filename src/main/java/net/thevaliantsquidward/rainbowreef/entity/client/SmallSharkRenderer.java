package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.SmallSharkEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SmallSharkRenderer extends GeoEntityRenderer<SmallSharkEntity> {
    private static final ResourceLocation TEXTURE_EPAULETTE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/epauletteshark.png");
    private static final ResourceLocation TEXTURE_PAJAMA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/pajama.png");
    private static final ResourceLocation TEXTURE_NURSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/nurse.png");
    private static final ResourceLocation TEXTURE_HORNED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/horned.png");
    private static final ResourceLocation TEXTURE_ZEBRA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/zebra.png");
    private static final ResourceLocation TEXTURE_ALBINO = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/albino.png");
    private static final ResourceLocation TEXTURE_PIEBALD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/piebald_horned.png");

    public SmallSharkRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SmallSharkModel());
    }

    protected void scale(SmallSharkEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(SmallSharkEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_PAJAMA;
            case 2 -> TEXTURE_HORNED;
            case 3 -> TEXTURE_NURSE;
            case 4 -> TEXTURE_ZEBRA;
            case 5 -> TEXTURE_ALBINO;
            case 6 -> TEXTURE_PIEBALD;
            default -> TEXTURE_EPAULETTE;
        };
    }
}