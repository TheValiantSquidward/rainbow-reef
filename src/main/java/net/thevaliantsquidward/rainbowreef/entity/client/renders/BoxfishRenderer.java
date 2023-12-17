package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.BoxfishModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.BoxfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoxfishRenderer extends GeoEntityRenderer<BoxfishEntity> {
    private static final ResourceLocation TEXTURE_GOLD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellowboxfish.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/whitespottedboxfish.png");
    private static final ResourceLocation TEXTURE_STRIPE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/whitebarredboxfish.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hornnosedboxfish.png");

    public BoxfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BoxfishModel());
    }

    protected void scale(BoxfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(BoxfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_PURPLE;
            case 2 -> TEXTURE_STRIPE;
            case 3 -> TEXTURE_WHITE;
            default -> TEXTURE_GOLD;
        };
    }
}