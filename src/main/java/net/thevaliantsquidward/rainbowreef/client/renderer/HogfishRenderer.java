package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.HogfishModel;
import net.thevaliantsquidward.rainbowreef.entity.HogfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HogfishRenderer extends GeoEntityRenderer<HogfishEntity> {
    private static final ResourceLocation CUBAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/cuban.png");
    private static final ResourceLocation SPANISH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/spanish.png");
    private static final ResourceLocation CORAL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/coral.png");
    private static final ResourceLocation LYRETAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/lyretail.png");
    private static final ResourceLocation PEPPERMINT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/peppermint.png");


    public HogfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HogfishModel());
    }

    protected void scale(HogfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(HogfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> SPANISH;
            case 2 -> PEPPERMINT;
            case 3 -> LYRETAIL;
            case 4 -> CORAL;
            default -> CUBAN;
        };
    }
}