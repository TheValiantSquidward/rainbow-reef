package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.ClownfishModel;
import net.thevaliantsquidward.rainbowreef.entity.ClownfishEntity;
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
    private static final ResourceLocation ALLARD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/allard.png");
    private static final ResourceLocation MOCHA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/mocha.png");
    private static final ResourceLocation WHITESNOUT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/whitesnout.png");
    private static final ResourceLocation GOLDNUGGET = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/goldnugget.png");
    private static final ResourceLocation REDSADDLEBACK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/redsaddleback.png");
    private static final ResourceLocation SNOWSTORM = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/snowstorm.png");
    private static final ResourceLocation ORANGESKUNK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/orangeskunk.png");
    private static final ResourceLocation DOMINO = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/domino.png");
    private static final ResourceLocation YELLOWCLARKII = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/yellowclarkii.png");
    private static final ResourceLocation NAKED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish/naked.png");


    public ClownfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ClownfishModel());
    }

    protected void scale(ClownfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ClownfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> BLACKANDWHITE;
            case 2 -> MAROON;
            case 3 -> PINKSKUNK;
            case 4 -> CLARKII;
            case 5 -> BLIZZARD;
            case 6 -> TOMATO;
            case 7 -> BLUESTRAIN;
            case 8 -> MADAGASCAR;
            case 9 -> OMAN;
            case 10 -> ALLARD;
            case 11 -> MOCHA;
            case 12 -> WHITESNOUT;
            case 13 -> GOLDNUGGET;
            case 14 -> REDSADDLEBACK;
            case 15 -> SNOWSTORM;
            case 16 -> ORANGESKUNK;
            case 17 -> DOMINO;
            case 18 -> YELLOWCLARKII;
            case 19 -> NAKED;

            default -> OCELLARIS;
        };
    }
}