package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.LionfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Lionfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class LionfishRenderer extends MobRenderer<Lionfish, LionfishModel> {

    public LionfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LionfishModel(context.bakeLayer(ReefModelLayers.LIONFISH)), 0.4F);
    }

    public ResourceLocation getTextureLocation(Lionfish entity) {
        Lionfish.LionfishVariant lionfishVariant = Lionfish.LionfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/lionfish/" + lionfishVariant.getSerializedName() + ".png");
    }
}