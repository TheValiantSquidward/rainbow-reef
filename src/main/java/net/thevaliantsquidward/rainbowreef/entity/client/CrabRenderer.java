package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.CrabEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {
    private static final ResourceLocation VAMPIRE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/vampire.png");
    private static final ResourceLocation HALLOWEEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/halloween.png");
    private static final ResourceLocation GHOST = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/ghost.png");
    private static final ResourceLocation SALLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/sally.png");
    private static final ResourceLocation EMERALD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/emerald.png");
    private static final ResourceLocation BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bluecrab.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/purplecrab.png");
    private static final ResourceLocation CANDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/candycrab.png");


    public CrabRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new CrabModel());
    }

    protected void scale(CrabEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(CrabEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> HALLOWEEN;
            case 2 -> GHOST;
            case 3 -> SALLY;
            case 4 -> EMERALD;
            case 5 -> BLUE;
            case 6 -> PURPLE;
            case 7 -> CANDY;
            default -> VAMPIRE;
        };
    }
}