package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.PipefishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PipefishRenderer extends GeoEntityRenderer<PipefishEntity> {
    private static final ResourceLocation GREEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/greenpipe.png");
    private static final ResourceLocation JANNS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/jannspipe.png");
    private static final ResourceLocation MULTIBANDED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/multibandedpipe.png");
    private static final ResourceLocation ORANGESTRIPED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/orangestripedpipe.png");
    private static final ResourceLocation BLUESTRIPED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/bluestripedpipe.png");
    private static final ResourceLocation PINK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/pink.png");


    public PipefishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new PipefishModel());
    }

    protected void scale(PipefishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(PipefishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> JANNS;
            case 2 -> MULTIBANDED;
            case 3 -> ORANGESTRIPED;
            case 4 -> BLUESTRIPED;
            case 5 -> PINK;
            default -> GREEN;
        };
    }
}