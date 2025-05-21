package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.BassletModel;
import net.thevaliantsquidward.rainbowreef.entity.BassletEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BassletRenderer extends GeoEntityRenderer<BassletEntity> {
    private static final ResourceLocation FAIRY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/fairy.png");
    private static final ResourceLocation BRAZILIAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/brazilian.png");
    private static final ResourceLocation ACCESSOR = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/accessor.png");
    private static final ResourceLocation BLACKCAP = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/blackcap.png");
    private static final ResourceLocation CANDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/candy.png");
    private static final ResourceLocation GOLD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/goldbasslet.png");
    private static final ResourceLocation GILDED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/gilded.png");
    private static final ResourceLocation SWISSGUARD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/swissguard.png");
    private static final ResourceLocation YELLOWSCISSORTAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/yellow_scissor_tail.png");
    private static final ResourceLocation MIDNIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/basslet/midnight.png");

    public BassletRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BassletModel());
    }

    protected void scale(BassletEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(BassletEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> BRAZILIAN;
            case 2 -> ACCESSOR;
            case 3 -> BLACKCAP;
            case 4 -> CANDY;
            case 5 -> GOLD;
            case 6 -> GILDED;
            case 7 -> SWISSGUARD;
            case 8 -> YELLOWSCISSORTAIL;
            case 9 -> MIDNIGHT;
            default -> FAIRY;
        };
    }
}