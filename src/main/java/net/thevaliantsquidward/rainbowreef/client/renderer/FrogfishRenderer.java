package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.FrogfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Frogfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;


@OnlyIn(Dist.CLIENT)
public class FrogfishRenderer extends MobRenderer<Frogfish, FrogfishModel<Frogfish>> {
    private static final ResourceLocation CLOWN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/clown.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/orangeocellated.png");
    private static final ResourceLocation PINK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/pinkocellated.png");
    private static final ResourceLocation PSYCHEDELIC = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/psychedelic.png");
    private static final ResourceLocation RED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/redlonglure.png");
    private static final ResourceLocation SARGASSUM = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/sargassum.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/frogfish/yellowlonglure.png");


    public FrogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogfishModel<>(context.bakeLayer(ReefModelLayers.FROGFISH)), 0.4F);
    }

    protected void scale(Frogfish entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(Frogfish entity) {
        return switch (entity.getVariant()) {
            case 1 -> ORANGE;
            case 2 -> PINK;
            case 3 -> PSYCHEDELIC;
            case 4 -> RED;
            case 5 -> SARGASSUM;
            case 6 -> YELLOW;
            default -> CLOWN;
        };
    }
}