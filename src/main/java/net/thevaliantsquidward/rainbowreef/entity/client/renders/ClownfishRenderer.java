package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.ClownfishModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.ClownfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ClownfishRenderer extends GeoEntityRenderer<ClownfishEntity> {
    private static final ResourceLocation CLOWNFISH_3 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_3.png");
    private static final ResourceLocation CLOWNFISH_0 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_0.png");
    private static final ResourceLocation CLOWNFISH_1 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_1.png");
    private static final ResourceLocation CLOWNFISH_2 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_2.png");
    private static final ResourceLocation CLOWNFISH_4 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_4.png");
    private static final ResourceLocation CLOWNFISH_5 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_5.png");
    private static final ResourceLocation CLOWNFISH_6 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_6.png");
    private static final ResourceLocation CLOWNFISH_7 = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clownfish_7.png");

    public ClownfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ClownfishModel());
    }

    protected void scale(ClownfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ClownfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> CLOWNFISH_0;
            case 2 -> CLOWNFISH_1;
            case 3 -> CLOWNFISH_2;
            case 4 -> CLOWNFISH_4;
            case 5 -> CLOWNFISH_5;
            case 6 -> CLOWNFISH_6;
            case 7 -> CLOWNFISH_7;
            default -> CLOWNFISH_3;
        };
    }
}