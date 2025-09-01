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
public class PipefishRenderer extends MobRenderer<Pipefish, PipefishModel> {

    public PipefishRenderer(EntityRendererProvider.Context context) {
        super(context, new PipefishModel(context.bakeLayer(ReefModelLayers.PIPEFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Pipefish entity) {
        Pipefish.PipefishVariant parrotfishVariant = Pipefish.PipefishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/pipefish/" + parrotfishVariant.getSerializedName() + ".png");
    }
}