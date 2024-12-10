package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ClownfishRenderer extends GeoEntityRenderer<ClownfishEntity> {
    private static final ResourceLocation BLACKANDWHITE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/blackandwhite.png");
    private static final ResourceLocation OCELLARIS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/ocellaris.png");
    private static final ResourceLocation MAROON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/maroon.png");
    private static final ResourceLocation PINKSKUNK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/pinkscunk.png");
    private static final ResourceLocation CLARKII = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/clarkii.png");
    private static final ResourceLocation BLIZZARD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/blizzard.png");
    private static final ResourceLocation TOMATO = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/tomato.png");
    private static final ResourceLocation BLUESTRAIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/bluestrain.png");
    private static final ResourceLocation MADAGASCAR = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/madagascar.png");
    private static final ResourceLocation OMAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/oman.png");


    public ClownfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ClownfishModel());
    }

    protected void scale(ClownfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ClownfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> OCELLARIS;
            case 2 -> MAROON;
            case 3 -> PINKSKUNK;
            case 4 -> CLARKII;
            case 5 -> BLIZZARD;
            case 6 -> TOMATO;
            case 7 -> BLUESTRAIN;
            case 8 -> MADAGASCAR;
            case 9 -> OMAN;
            default -> BLACKANDWHITE;
        };
    }
}