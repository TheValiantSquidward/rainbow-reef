package net.thevaliantsquidward.rainbowreef.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.ParrotfishEntity;
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
    private static final ResourceLocation SLEEPY_RED_SEA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_5.png");
    private static final ResourceLocation SLEEPY_PRINCESS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_6.png");
    private static final ResourceLocation SLEEPY_YELLOWTAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_7.png");
    private static final ResourceLocation SLEEPY_BLUEBUMPHEAD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_8.png");
    private static final ResourceLocation SLEEPY_RED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_9.png");
    private static final ResourceLocation SLEEPY_YELLOWBAND = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_10.png");
    private static final ResourceLocation SLEEPY_OBISHIME = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_11.png");


    public ParrotfishEepyLayer(GeoRenderer<ParrotfishEntity> entityRendererIn) {
        super(entityRendererIn);

    }

    public ResourceLocation getOverlay(ParrotfishEntity entityLivingBaseIn) {
        return switch (entityLivingBaseIn.getVariant()) {
            case 1 -> SLEEPY_HUMPHEAD;
            case 2 -> SLEEPY_RAINBOW;
            case 3 -> SLEEPY_MIDNIGHT;
            case 4 -> SLEEPY_STOPLIGHT;
            case 5 -> SLEEPY_RED_SEA;
            case 6 -> SLEEPY_PRINCESS;
            case 7 -> SLEEPY_YELLOWTAIL;
            case 8 -> SLEEPY_BLUEBUMPHEAD;
            case 9 -> SLEEPY_RED;
            case 10 -> SLEEPY_YELLOWBAND;
            case 11 -> SLEEPY_OBISHIME;

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