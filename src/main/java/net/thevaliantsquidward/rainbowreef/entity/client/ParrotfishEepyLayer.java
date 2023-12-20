package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.ParrotfishEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ParrotfishEepyLayer extends GeoRenderLayer<ParrotfishEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation(RainbowReef.MOD_ID, "geo/parrotfish.geo.json");
    private static final ResourceLocation SLEEPY_BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_0.png");
    private static final ResourceLocation SLEEPY_STOPLIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_3.png");
    private static final ResourceLocation SLEEPY_HUMPHEAD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_1.png");
    private static final ResourceLocation SLEEPY_MIDNIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_4.png");
    private static final ResourceLocation SLEEPY_RAINBOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_2.png");


    public ParrotfishEepyLayer(GeoRenderer<ParrotfishEntity> entityRendererIn) {
        super(entityRendererIn);

    }

    public ResourceLocation getOverlay(ParrotfishEntity entityLivingBaseIn) {
        return switch (entityLivingBaseIn.getVariant()) {
            case 1 -> SLEEPY_HUMPHEAD;
            case 2 -> SLEEPY_RAINBOW;
            case 3 -> SLEEPY_MIDNIGHT;
            case 4 -> SLEEPY_STOPLIGHT;
            default -> SLEEPY_BLUE;
        };
    }


    public void render(PoseStack poseStack, ParrotfishEntity entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        long roundedTime = entityLivingBaseIn.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;
        if (night) {
            ResourceLocation overlayTexture = getOverlay(entityLivingBaseIn);
            RenderType cameo = RenderType.entityCutout(overlayTexture);
            getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, entityLivingBaseIn, renderType,
                    bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY,
                    1, 1, 1, 1);
        }
    }
}