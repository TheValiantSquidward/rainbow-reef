package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.client.renderer.layer.ParrotfishEepyLayer;
import net.thevaliantsquidward.rainbowreef.entity.ParrotfishEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class ParrotfishRenderer extends MobRenderer<ParrotfishEntity, ParrotfishModel<ParrotfishEntity>> {

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

    public ParrotfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ParrotfishModel<>(context.bakeLayer(ReefModelLayers.PARROTFISH_LAYER)), 0.4F);
        this.addLayer(new ParrotfishEepyLayer(this));
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