package net.thevaliantsquidward.rainbowreef.client.renderer;

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
public class HogfishRenderer extends MobRenderer<Hogfish, HogfishModel> {

    public HogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new HogfishModel(context.bakeLayer(ReefModelLayers.HOGFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Hogfish entity) {
        Hogfish.HogfishVariant hogfishVariant = Hogfish.HogfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/hogfish/" + hogfishVariant.getSerializedName() + ".png");
    }
}