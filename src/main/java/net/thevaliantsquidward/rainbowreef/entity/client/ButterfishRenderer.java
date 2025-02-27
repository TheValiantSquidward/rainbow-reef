package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ButterfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ButterfishRenderer extends GeoEntityRenderer<ButterfishEntity> {
    private static final ResourceLocation COPPERBAND = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/copperband.png");
    private static final ResourceLocation EASTERISLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/easterisle.png");
    private static final ResourceLocation THREADFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/threadfin.png");
    private static final ResourceLocation BANNER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/banner.png");
    private static final ResourceLocation BLUECHEEK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/bluecheek.png");
    private static final ResourceLocation LONGNOSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/longnose.png");
    private static final ResourceLocation SPOTFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/spotfin.png");
    private static final ResourceLocation HOODED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/hooded.png");
    private static final ResourceLocation ARABIC = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/arabic.png");
    private static final ResourceLocation PYRAMID = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/pyramid.png");
    private static final ResourceLocation REDSEA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/redsea.png");
    private static final ResourceLocation SIXSPINED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/sixspined.png");
    private static final ResourceLocation DARKLONGNOSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/darklongnose.png");
    private static final ResourceLocation SADDLEBACK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/saddleback.png");
    private static final ResourceLocation AFRICAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/african.png");
    private static final ResourceLocation ERITREAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/eritrean.png");
    private static final ResourceLocation MARGINATED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/marginated.png");
    private static final ResourceLocation THOMPSON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/thompson.png");
    private static final ResourceLocation MULLERS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/mullers.png");
    private static final ResourceLocation WROUGHTIRON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/wroughtiron.png");
    private static final ResourceLocation FOUREYE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/butterflyfish/foureye.png");


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
            case 7 -> HOODED;
            case 8 -> ARABIC;
            case 9 -> PYRAMID;
            case 10 -> REDSEA;
            case 11 -> SIXSPINED;
            case 12 -> DARKLONGNOSE;
            case 13 -> SADDLEBACK;
            case 14 -> AFRICAN;
            case 15 -> ERITREAN;
            case 16 -> MARGINATED;
            case 17 -> THOMPSON;
            case 18 -> MULLERS;
            case 19 -> WROUGHTIRON;
            case 20 -> FOUREYE;

            default -> COPPERBAND;
        };
    }
}