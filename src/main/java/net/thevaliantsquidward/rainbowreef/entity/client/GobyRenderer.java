package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GobyRenderer extends GeoEntityRenderer<GobyEntity> {
    private static final ResourceLocation TEXTURE_FIRE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/firegoby.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/purplefiregoby.png");
    private static final ResourceLocation TEXTURE_CANDYCANE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/candycanegoby.png");
    private static final ResourceLocation TEXTURE_MANDARIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/mandaringoby.png");
    private static final ResourceLocation TEXTURE_YELLOWWATCH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellowwatchmangoby.png");
    private static final ResourceLocation TEXTURE_BLACK_RAY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/blackray.png");
    private static final ResourceLocation TEXTURE_HELFRICHI = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/helfrichi.png");
    private static final ResourceLocation TEXTURE_CATALINA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/catalinagoby.png");

    private static final ResourceLocation TEXTURE_BLUE_NEON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/blue_neon.png");
    private static final ResourceLocation TEXTURE_YELLOW_NEON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellow_neon_goby.png");
    private static final ResourceLocation TEXTURE_NEON_HYBRID = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/neon_hybrid.png");


    public GobyRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GobyModel());
    }

    protected void scale(GobyEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(GobyEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_PURPLE;
            case 2 -> TEXTURE_CANDYCANE;
            case 3 -> TEXTURE_MANDARIN;
            case 4 -> TEXTURE_YELLOWWATCH;
            case 5 -> TEXTURE_CATALINA;
            case 6 -> TEXTURE_BLACK_RAY;
            case 7 -> TEXTURE_HELFRICHI;
            case 8 -> TEXTURE_BLUE_NEON;
            case 9 -> TEXTURE_YELLOW_NEON;
            case 10 -> TEXTURE_NEON_HYBRID;
            default -> TEXTURE_FIRE;
        };
    }
}