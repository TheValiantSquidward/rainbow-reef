package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.PipefishModel;
import net.thevaliantsquidward.rainbowreef.entity.Pipefish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class PipefishRenderer extends MobRenderer<Pipefish, PipefishModel<Pipefish>> {

    private static final ResourceLocation GREEN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/greenpipe.png");
    private static final ResourceLocation JANNS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/jannspipe.png");
    private static final ResourceLocation MULTIBANDED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/multibandedpipe.png");
    private static final ResourceLocation ORANGESTRIPED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/orangestripedpipe.png");
    private static final ResourceLocation BLUESTRIPED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/bluestripedpipe.png");
    private static final ResourceLocation PINK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/pipefish/pink.png");

    public PipefishRenderer(EntityRendererProvider.Context context) {
        super(context, new PipefishModel<>(context.bakeLayer(ReefModelLayers.PIPEFISH)), 0.4F);
    }

    public ResourceLocation getTextureLocation(Pipefish entity) {
        return switch (entity.getVariant()) {
            case 1 -> JANNS;
            case 2 -> MULTIBANDED;
            case 3 -> ORANGESTRIPED;
            case 4 -> BLUESTRIPED;
            case 5 -> PINK;
            default -> GREEN;
        };
    }
}