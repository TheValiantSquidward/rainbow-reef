package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.BassletEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.BoxfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BassletRenderer extends GeoEntityRenderer<BassletEntity> {
    private static final ResourceLocation FAIRY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/fairy.png");
    private static final ResourceLocation BRAZILIAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/brazilian.png");
    private static final ResourceLocation ACCESSOR = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/accessor.png");
    private static final ResourceLocation BLACKCAP = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/blackcap.png");
    private static final ResourceLocation CANDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/candy.png");
    private static final ResourceLocation GOLD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/goldbasslet.png");
    private static final ResourceLocation GILDED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/gilded.png");

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
            default -> FAIRY;
        };
    }
}