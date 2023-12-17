package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.TangModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.TangEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TangRenderer extends GeoEntityRenderer<TangEntity> {
    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bluetang.png");
    private static final ResourceLocation TEXTURE_POWDERBLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/powderbluetang.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/yellowtang.png");
    private static final ResourceLocation TEXTURE_UNICORN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/unicorntang.png");
    private static final ResourceLocation TEXTURE_CONVICT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/convicttang.png");
    private static final ResourceLocation TEXTURE_CLOWN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/clowntang.png");
    private static final ResourceLocation TEXTURE_ACHILLES = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/achillestang.png");

    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/purpletang.png");

    private static final ResourceLocation TEXTURE_MESSY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/messy.png");

    private static final ResourceLocation TEXTURE_DISTORTED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/distorted.png");

    private static final ResourceLocation TEXTURE_PEARLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pearly.png");

    public TangRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TangModel());
    }

    protected void scale(TangEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(TangEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_POWDERBLUE;
            case 2 -> TEXTURE_YELLOW;
            case 3 -> TEXTURE_UNICORN;
            case 4 -> TEXTURE_CONVICT;
            case 5 -> TEXTURE_CLOWN;
            case 6 -> TEXTURE_ACHILLES;
            case 7 -> TEXTURE_PURPLE;
            case 8 -> TEXTURE_MESSY;
            case 9 -> TEXTURE_DISTORTED;
            case 10 -> TEXTURE_PEARLY;
            default -> TEXTURE_BLUE;
        };
    }
}
