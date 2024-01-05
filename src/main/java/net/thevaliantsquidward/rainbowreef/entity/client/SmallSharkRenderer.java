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
    private static final ResourceLocation TEXTURE_EPAULETTE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/epauletteshark.png");
    private static final ResourceLocation TEXTURE_PAJAMA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pajama.png");
    private static final ResourceLocation TEXTURE_NURSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/nurse.png");
    private static final ResourceLocation TEXTURE_HORNED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/horned.png");

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
            default -> TEXTURE_EPAULETTE;
        };
    }
}