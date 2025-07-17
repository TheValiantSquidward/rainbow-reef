package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.EelModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.JellyfishModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MoorishIdolModel;
import net.thevaliantsquidward.rainbowreef.entity.EelEntity;
import net.thevaliantsquidward.rainbowreef.entity.JellyfishEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class JellyfishRenderer extends MobRenderer<JellyfishEntity, JellyfishModel<JellyfishEntity>> {
    private static final ResourceLocation TEXTURE_PINK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_1.png");
    private static final ResourceLocation TEXTURE_ORANGE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_0.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_2.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_3.png");
    private static final ResourceLocation TEXTURE_MUDDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_4.png");
    private static final ResourceLocation TEXTURE_ABYSSAL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_5.png");
    private static final ResourceLocation TEXTURE_CHERRY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_6.png");
    private static final ResourceLocation TEXTURE_MINTY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_7.png");
    private static final ResourceLocation TEXTURE_AZURE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_8.png");
    private static final ResourceLocation TEXTURE_RED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/jellyfish/jellyfish_9.png");

    public JellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishModel<>(context.bakeLayer(ReefModelLayers.JELLYFISH_LAYER)), 0.4F);
    }

    protected void scale(JellyfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(JellyfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_ORANGE;
            case 2 -> TEXTURE_WHITE;
            case 3 -> TEXTURE_YELLOW;
            case 4 -> TEXTURE_MUDDY;
            case 5 -> TEXTURE_ABYSSAL;
            case 6 -> TEXTURE_CHERRY;
            case 7 -> TEXTURE_MINTY;
            case 8 -> TEXTURE_AZURE;
            case 9 -> TEXTURE_RED;
            default -> TEXTURE_PINK;
        };
    }
}