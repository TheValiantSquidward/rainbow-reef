package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.FusilierModel;
import net.thevaliantsquidward.rainbowreef.entity.Fusilier;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class FusilierRenderer extends MobRenderer<Fusilier, FusilierModel> {

    public FusilierRenderer(EntityRendererProvider.Context context) {
        super(context, new FusilierModel(context.bakeLayer(ReefModelLayers.FUSILIER)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Fusilier entity) {
        Fusilier.FusilierVariant fusilierVariant = Fusilier.FusilierVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/fusilier/" + fusilierVariant.getSerializedName() + ".png");
    }
}
