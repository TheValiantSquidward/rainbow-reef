package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.client.renderer.layer.ParrotfishEepyLayer;
import net.thevaliantsquidward.rainbowreef.entity.Parrotfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class ParrotfishRenderer extends MobRenderer<Parrotfish, ParrotfishModel> {

    public ParrotfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ParrotfishModel(context.bakeLayer(ReefModelLayers.PARROTFISH)), 0.4F);
        this.addLayer(new ParrotfishEepyLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Parrotfish entity) {
        Parrotfish.ParrotfishVariant parrotfishVariant = Parrotfish.ParrotfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/parrotfish/" + parrotfishVariant.getSerializedName() + ".png");
    }
}