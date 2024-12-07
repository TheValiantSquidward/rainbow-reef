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


    public AngelfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new AngelfishModel());
    }

    protected void scale(AngelfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(AngelfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> FRENCH;
            case 2 -> QUEEN;
            case 3 -> YELLOWBAND;
            case 4 -> BLUERING;
            case 5 -> ROCKBEAUTY;
            case 6 -> BLUEQUEEN;
            case 7 -> MAJESTIC;

            default -> EMPEROR;
        };
    }
}