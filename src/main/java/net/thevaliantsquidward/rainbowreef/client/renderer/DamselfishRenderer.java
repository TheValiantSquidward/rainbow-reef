package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.DamselfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Damselfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class DamselfishRenderer extends MobRenderer<Damselfish, DamselfishModel> {

    public DamselfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DamselfishModel(context.bakeLayer(ReefModelLayers.DAMSELFISH)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Damselfish entity) {
        Damselfish.DamselfishVariant damselfishVariant = Damselfish.DamselfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/damselfish/" + damselfishVariant.getSerializedName() + ".png");
    }
}
