package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.BoxfishModel;
import net.thevaliantsquidward.rainbowreef.entity.BoxfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BoxfishRenderer extends GeoEntityRenderer<BoxfishEntity> {
    private static final ResourceLocation TEXTURE_GOLD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/yellowboxfish.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/whitespottedboxfish.png");
    private static final ResourceLocation TEXTURE_STRIPE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/whitebarredboxfish.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/hornnosedboxfish.png");
    private static final ResourceLocation TEXTURE_BLUETAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/bluetail.png");
    private static final ResourceLocation TEXTURE_LONGHORN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/longhorn.png");
    private static final ResourceLocation TEXTURE_WHITLEYS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/whitleys.png");
    private static final ResourceLocation TEXTURE_SPOTTED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/spotted.png");


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
            case 4 -> TEXTURE_BLUETAIL;
            case 5 -> TEXTURE_LONGHORN;
            case 6 -> TEXTURE_WHITLEYS;
            case 7 -> TEXTURE_SPOTTED;
            default -> TEXTURE_GOLD;
        };
    }
}