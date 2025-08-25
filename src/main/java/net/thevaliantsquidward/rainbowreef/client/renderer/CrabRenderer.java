package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.CrabModel;
import net.thevaliantsquidward.rainbowreef.entity.Crab;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class CrabRenderer extends MobRenderer<Crab, CrabModel<Crab>> {

    private static final ResourceLocation VAMPIRE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/vampire.png");
    private static final ResourceLocation HALLOWEEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/halloween.png");
    private static final ResourceLocation GHOST = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/ghost.png");
    private static final ResourceLocation SALLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/sally.png");
    private static final ResourceLocation EMERALD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/emerald.png");
    private static final ResourceLocation BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/bluecrab.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/purplecrab.png");
    private static final ResourceLocation CANDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/candycrab.png");
    private static final ResourceLocation REDGHOST = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/crab/redghost.png");

    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CrabModel<>(context.bakeLayer(ReefModelLayers.CRAB_LAYER)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Crab entity) {
        return switch (entity.getVariant()) {
            case 1 -> HALLOWEEN;
            case 2 -> GHOST;
            case 3 -> SALLY;
            case 4 -> EMERALD;
            case 5 -> BLUE;
            case 6 -> PURPLE;
            case 7 -> CANDY;
            case 8 -> REDGHOST;
            default -> VAMPIRE;
        };
    }
}