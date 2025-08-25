package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.HogfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Hogfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;


@OnlyIn(Dist.CLIENT)
public class HogfishRenderer extends MobRenderer<Hogfish, HogfishModel<Hogfish>> {
    private static final ResourceLocation CUBAN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/cuban.png");
    private static final ResourceLocation SPANISH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/spanish.png");
    private static final ResourceLocation CORAL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/coral.png");
    private static final ResourceLocation LYRETAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/lyretail.png");
    private static final ResourceLocation PEPPERMINT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/hogfish/peppermint.png");


    public HogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new HogfishModel<>(context.bakeLayer(ReefModelLayers.HOGFISH_LAYER)), 0.4F);
    }

    protected void scale(Hogfish entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(Hogfish entity) {
        return switch (entity.getVariant()) {
            case 1 -> SPANISH;
            case 2 -> PEPPERMINT;
            case 3 -> LYRETAIL;
            case 4 -> CORAL;
            default -> CUBAN;
        };
    }
}