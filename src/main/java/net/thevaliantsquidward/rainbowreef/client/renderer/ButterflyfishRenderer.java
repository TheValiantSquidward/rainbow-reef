package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ButterflyfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Butterflyfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class ButterflyfishRenderer extends MobRenderer<Butterflyfish, ButterflyfishModel> {

    public ButterflyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyfishModel(context.bakeLayer(ReefModelLayers.BUTTERFLYFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Butterflyfish entity) {
        Butterflyfish.ButterflyfishVariant butterflyfishVariant = Butterflyfish.ButterflyfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/butterflyfish/" + butterflyfishVariant.getSerializedName() + ".png");
    }
}