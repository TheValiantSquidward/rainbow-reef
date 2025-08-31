package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.SeahorseModel;
import net.thevaliantsquidward.rainbowreef.entity.Seahorse;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class SeahorseRenderer extends MobRenderer<Seahorse, SeahorseModel> {

    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super(context, new SeahorseModel(context.bakeLayer(ReefModelLayers.SEAHORSE)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Seahorse entity) {
        Seahorse.SeahorseVariant seahorseVariant = Seahorse.SeahorseVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/seahorse/" + seahorseVariant.getSerializedName() + ".png");
    }
}