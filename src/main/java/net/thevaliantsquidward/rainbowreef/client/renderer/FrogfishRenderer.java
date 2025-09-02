package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.FrogfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Frogfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class FrogfishRenderer extends MobRenderer<Frogfish, FrogfishModel> {

    public FrogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogfishModel(context.bakeLayer(ReefModelLayers.FROGFISH)), 0.4F);
    }

    public ResourceLocation getTextureLocation(Frogfish entity) {
        Frogfish.FrogfishVariant frogfishVariant = Frogfish.FrogfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/frogfish/" + frogfishVariant.getSerializedName() + ".png");
    }
}