package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.BoxfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Boxfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class BoxfishRenderer extends MobRenderer<Boxfish, BoxfishModel> {

    public BoxfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BoxfishModel(context.bakeLayer(ReefModelLayers.BOXFISH)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Boxfish entity) {
        Boxfish.BoxfishVariant boxfishVariant = Boxfish.BoxfishVariant.variantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/boxfish/" + boxfishVariant.getSerializedName() + ".png");
    }
}