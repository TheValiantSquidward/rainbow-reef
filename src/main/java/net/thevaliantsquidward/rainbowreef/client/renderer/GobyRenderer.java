package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.GobyModel;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class GobyRenderer extends MobRenderer<Goby, GobyModel> {

    public GobyRenderer(EntityRendererProvider.Context context) {
        super(context, new GobyModel(context.bakeLayer(ReefModelLayers.GOBY)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Goby entity) {
        Goby.GobyVariant gobyVariant = Goby.GobyVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/goby/" + gobyVariant.getSerializedName() + ".png");
    }
}