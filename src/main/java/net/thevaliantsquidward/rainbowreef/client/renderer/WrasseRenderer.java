package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.WrasseModel;
import net.thevaliantsquidward.rainbowreef.entity.Wrasse;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class WrasseRenderer extends MobRenderer<Wrasse, WrasseModel> {

    public WrasseRenderer(EntityRendererProvider.Context context) {
        super(context, new WrasseModel(context.bakeLayer(ReefModelLayers.WRASSE)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Wrasse entity) {
        Wrasse.WrasseVariant wrasseVariant = Wrasse.WrasseVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/wrasse/" + wrasseVariant.getSerializedName() + ".png");
    }
}
