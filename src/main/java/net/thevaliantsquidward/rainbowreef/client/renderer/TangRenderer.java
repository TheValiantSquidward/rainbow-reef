package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.TangModel;
import net.thevaliantsquidward.rainbowreef.entity.Tang;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class TangRenderer extends MobRenderer<Tang, TangModel> {

    public TangRenderer(EntityRendererProvider.Context context) {
        super(context, new TangModel(context.bakeLayer(ReefModelLayers.TANG)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Tang entity) {
        Tang.TangVariant tangVariant = Tang.TangVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/tang/" + tangVariant.getSerializedName() + ".png");
    }
}
