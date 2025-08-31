package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ClownfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Clownfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class ClownfishRenderer extends MobRenderer<Clownfish, ClownfishModel> {

    public ClownfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ClownfishModel(context.bakeLayer(ReefModelLayers.CLOWNFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Clownfish entity) {
        Clownfish.ClownfishVariant clownfishVariant = Clownfish.ClownfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/clownfish/" + clownfishVariant.getSerializedName() + ".png");
    }
}