package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.BoxfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.JellyfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JellyfishRenderer extends GeoEntityRenderer<JellyfishEntity> {
    private static final ResourceLocation TEXTURE_PINK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_1.png");
    private static final ResourceLocation TEXTURE_ORANGE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_0.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_2.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_3.png");

    public JellyfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new JellyfishModel());
    }

    protected void scale(JellyfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(JellyfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_ORANGE;
            case 2 -> TEXTURE_WHITE;
            case 3 -> TEXTURE_YELLOW;
            default -> TEXTURE_PINK;
        };
    }
}