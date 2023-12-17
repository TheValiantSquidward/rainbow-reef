package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.ParrotfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ParrotfishRenderer extends GeoEntityRenderer<ParrotfishEntity> {
    private static final ResourceLocation BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/blue.png");
    private static final ResourceLocation HUMPHEAD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/humphead.png");
    private static final ResourceLocation RAINBOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/rainbow.png");
    private static final ResourceLocation MIDNIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/midnight.png");
    private static final ResourceLocation STOPLIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/stoplight.png");


    public ParrotfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ParrotfishModel());
    }

    protected void scale(ParrotfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ParrotfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> HUMPHEAD;
            case 2 -> RAINBOW;
            case 3 -> MIDNIGHT;
            case 4 -> STOPLIGHT;
            default -> BLUE;
        };
    }
}