package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.AngelfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AngelfishRenderer extends GeoEntityRenderer<AngelfishEntity> {
    private static final ResourceLocation EMPEROR = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/emperorangel.png");
    private static final ResourceLocation FRENCH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/frenchangel.png");
    private static final ResourceLocation QUEEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/queenangel.png");
    private static final ResourceLocation YELLOWBAND = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/yellowbandangel.png");
    private static final ResourceLocation BLUERING = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/blueringangel.png");
    private static final ResourceLocation ROCKBEAUTY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/rockbeautyangel.png");
    private static final ResourceLocation BLUEQUEEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/bluequeenangel.png");
    private static final ResourceLocation MAJESTIC = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/majestic.png");
    private static final ResourceLocation KING = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/king.png");
    private static final ResourceLocation SEMICIRCLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/semicircle.png");
    private static final ResourceLocation BANDED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/banded.png");
    private static final ResourceLocation GRAY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/gray.png");
    private static final ResourceLocation OLDWOMAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/oldwoman.png");
    private static final ResourceLocation GUINEAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/guinean.png");
    private static final ResourceLocation QUEENSLANDYELLOWTAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/queenslandyellowtail.png");
    private static final ResourceLocation CLARION = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/angelfish/clarion.png");


    public AngelfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new AngelfishModel());
    }

    protected void scale(AngelfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(AngelfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> FRENCH;
            case 2 -> EMPEROR;
            case 3 -> YELLOWBAND;
            case 4 -> BLUERING;
            case 5 -> ROCKBEAUTY;
            case 6 -> BLUEQUEEN;
            case 7 -> MAJESTIC;
            case 8 -> KING;
            case 9 -> SEMICIRCLE;
            case 10 -> BANDED;
            case 11 -> GRAY;
            case 12 -> OLDWOMAN;
            case 13 -> GUINEAN;
            case 14 -> QUEENSLANDYELLOWTAIL;
            case 15 -> CLARION;

            default -> QUEEN;
        };
    }
}