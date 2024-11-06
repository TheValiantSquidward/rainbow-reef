package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.GobyEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.SeahorseEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeahorseRenderer extends GeoEntityRenderer<SeahorseEntity> {
    private static final ResourceLocation KELPY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/kelpy.png");
    private static final ResourceLocation COBALT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/cobalt.png");
    private static final ResourceLocation GOLD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/gold.png");
    private static final ResourceLocation AMBER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/amber.png");
    private static final ResourceLocation SILVER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/silver.png");
    private static final ResourceLocation GARNET = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/garnet.png");
    private static final ResourceLocation RUBY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/ruby.png");
    private static final ResourceLocation SPINEL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/spinel.png");
    private static final ResourceLocation CHERT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/chert.png");
    private static final ResourceLocation ONYX = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/seahorse/onyx.png");



    public SeahorseRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SeahorseModel());
    }

    protected void scale(SeahorseEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(SeahorseEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> COBALT;
            case 2 -> GOLD;
            case 3 -> AMBER;
            case 4 -> SILVER;
            case 5 -> GARNET;
            case 6 -> RUBY;
            case 7 -> SPINEL;
            case 8 -> CHERT;
            case 9 -> ONYX;
            default -> KELPY;
        };
    }
}