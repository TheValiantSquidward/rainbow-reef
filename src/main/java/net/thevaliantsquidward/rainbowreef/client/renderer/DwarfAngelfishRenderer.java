package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.DwarfAngelfishModel;
import net.thevaliantsquidward.rainbowreef.entity.DwarfAngelfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfAngelfishRenderer extends GeoEntityRenderer<DwarfAngelfishEntity> {
    private static final ResourceLocation BICOLOR = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/bicolor.png");
    private static final ResourceLocation CORAL_BEAUTY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/coralbeauty.png");
    private static final ResourceLocation PEPPERMINT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/peppermint.png");
    private static final ResourceLocation FLAME = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/flame.png");
    private static final ResourceLocation KEYHOLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/keyhole.png");
    private static final ResourceLocation MASKED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/masked.png");
    private static final ResourceLocation CHERUB = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/cherubfish.png");
    private static final ResourceLocation JAPANESE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/japanese.png");
    private static final ResourceLocation BLACKNOX = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/blacknox.png");
    private static final ResourceLocation LAMARCK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/lamarck.png");
    private static final ResourceLocation LEMONPEEL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/lemonpeel.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/yellow.png");
    private static final ResourceLocation ORANGEPEEL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/orangepeel.png");
    private static final ResourceLocation PEARLSCALE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/pearlscale.png");
    private static final ResourceLocation RESPLENDENT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/resplendent.png");
    private static final ResourceLocation YELLOWTAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/dwarfangelfish/yellowtail.png");


    public DwarfAngelfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new DwarfAngelfishModel());
    }

    protected void scale(DwarfAngelfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(DwarfAngelfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> CORAL_BEAUTY;
            case 2 -> PEPPERMINT;
            case 3 -> FLAME;
            case 4 -> KEYHOLE;
            case 5 -> MASKED;
            case 6 -> CHERUB;
            case 7 -> JAPANESE;
            case 8 -> BLACKNOX;
            case 9 -> LAMARCK;
            case 10 -> LEMONPEEL;
            case 11 -> YELLOW;
            case 12 -> ORANGEPEEL;
            case 13 -> PEARLSCALE;
            case 14 -> RESPLENDENT;
            case 15 -> YELLOWTAIL;
            default -> BICOLOR;
        };
    }
}