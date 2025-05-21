package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.client.renderer.layer.ParrotfishEepyLayer;
import net.thevaliantsquidward.rainbowreef.entity.ParrotfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ParrotfishRenderer extends GeoEntityRenderer<ParrotfishEntity> {
    private static final ResourceLocation BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/blue.png");
    private static final ResourceLocation HUMPHEAD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/humphead.png");
    private static final ResourceLocation RAINBOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/rainbow.png");
    private static final ResourceLocation MIDNIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/midnight.png");
    private static final ResourceLocation STOPLIGHT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/stoplight.png");
    private static final ResourceLocation MEDITERRANEAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/redseaparrotfish.png");
    private static final ResourceLocation PRINCESS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/princess.png");
    private static final ResourceLocation YELLOWTAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/yellowtail.png");
    private static final ResourceLocation BLUEBUMPHEAD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/bluebumphead.png");
    private static final ResourceLocation RED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/red.png");
    private static final ResourceLocation YELLOWBAND = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/yellowband.png");
    private static final ResourceLocation OBISHIME = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parrotfish/obishime.png");

    public ParrotfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ParrotfishModel());
        this.addRenderLayer(new ParrotfishEepyLayer(this));
    }

    protected void scale(ParrotfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ParrotfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> HUMPHEAD;
            case 2 -> RAINBOW;
            case 3 -> MIDNIGHT;
            case 4 -> STOPLIGHT;
            case 5 -> MEDITERRANEAN;
            case 6 -> PRINCESS;
            case 7 -> YELLOWTAIL;
            case 8 -> BLUEBUMPHEAD;
            case 9 -> RED;
            case 10 -> YELLOWBAND;
            case 11 -> OBISHIME;

            default -> BLUE;
        };
    }
}