package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.TangEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TangRenderer extends GeoEntityRenderer<TangEntity> {
    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_0.png");
    private static final ResourceLocation TEXTURE_POWDERBLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_1.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_3.png");
    private static final ResourceLocation TEXTURE_UNICORN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_4.png");
    private static final ResourceLocation TEXTURE_CONVICT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_5.png");
    private static final ResourceLocation TEXTURE_CLOWN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_6.png");
    private static final ResourceLocation TEXTURE_ACHILLES = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_7.png");
    private static final ResourceLocation TEXTURE_YELLOW_BELLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_8.png");
    private static final ResourceLocation TEXTURE_MUDDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_9.png");
    private static final ResourceLocation TEXTURE_PEARLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_10.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_11.png");
    private static final ResourceLocation TEXTURE_BLACK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_12.png");
    private static final ResourceLocation TEXTURE_GEM = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_13.png");
    private static final ResourceLocation TEXTURE_REGAL_BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_14.png");
    private static final ResourceLocation TEXTURE_PENGUIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_15.png");
    private static final ResourceLocation TEXTURE_GREEN_SPOT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_16.png");
    private static final ResourceLocation TEXTURE_RUSTY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_17.png");
    private static final ResourceLocation TEXTURE_CHOCOLATE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_18.png");
    private static final ResourceLocation TEXTURE_SAILFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_19.png");
    private static final ResourceLocation TEXTURE_ATLANTIC = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/tangfish_20.png");


    public TangRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TangModel());
    }

    protected void scale(TangEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(TangEntity entity) {
        return switch (entity.getVariant()) {
            case 1 ->  TEXTURE_POWDERBLUE;
            case 2 ->  TEXTURE_YELLOW;
            case 3 ->  TEXTURE_UNICORN;
            case 4 ->  TEXTURE_CONVICT;
            case 5 ->  TEXTURE_CLOWN;
            case 6 ->  TEXTURE_ACHILLES;
            case 7 ->  TEXTURE_PURPLE;
            case 8 ->  TEXTURE_BLACK;
            case 9 ->  TEXTURE_REGAL_BLUE;
            case 10 -> TEXTURE_GEM;
            case 11 -> TEXTURE_PENGUIN;
            case 12 -> TEXTURE_GREEN_SPOT;
            case 13 -> TEXTURE_RUSTY;
            case 14 -> TEXTURE_PEARLY;
            case 15 -> TEXTURE_YELLOW_BELLY;
            case 16 -> TEXTURE_MUDDY;
            case 17 -> TEXTURE_CHOCOLATE;
            case 18 -> TEXTURE_SAILFIN;
            case 19 -> TEXTURE_ATLANTIC;
            default -> TEXTURE_BLUE;
        };
    }
}
