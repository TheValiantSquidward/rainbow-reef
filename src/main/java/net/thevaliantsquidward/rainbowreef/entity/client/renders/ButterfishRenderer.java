package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.ButterfishModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.ButterfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ButterfishRenderer extends GeoEntityRenderer<ButterfishEntity> {
    private static final ResourceLocation COPPERBAND = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/copperband.png");
    private static final ResourceLocation EASTERISLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/easterisle.png");
    private static final ResourceLocation THREADFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/threadfin.png");
    private static final ResourceLocation BANNER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/banner.png");
    private static final ResourceLocation BLUECHEEK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bluecheek.png");
    private static final ResourceLocation LONGNOSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/longnose.png");
    private static final ResourceLocation SPOTFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/spotfin.png");
    public ButterfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ButterfishModel());
    }

    protected void scale(ButterfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ButterfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> EASTERISLE;
            case 2 -> THREADFIN;
            case 3 -> BANNER;
            case 4 -> BLUECHEEK;
            case 5 -> LONGNOSE;
            case 6 -> SPOTFIN;
            default -> COPPERBAND;
        };
    }
}