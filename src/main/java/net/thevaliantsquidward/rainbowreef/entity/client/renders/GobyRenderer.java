package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.GobyModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GobyRenderer extends GeoEntityRenderer<GobyEntity> {
    private static final ResourceLocation TEXTURE_FIRE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/firegoby.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/purplefiregoby.png");
    private static final ResourceLocation TEXTURE_CANDYCANE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/candycanegoby.png");
    private static final ResourceLocation TEXTURE_MANDARIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/mandaringoby.png");
    private static final ResourceLocation TEXTURE_YELLOWWATCH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellowwatchmangoby.png");

    private static final ResourceLocation TEXTURE_CATALINA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/catalinagoby.png");


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
            default -> TEXTURE_FIRE;
        };
    }
}