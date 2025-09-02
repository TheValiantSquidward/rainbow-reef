package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.RabbitfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Rabbitfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class RabbitfishRenderer extends MobRenderer<Rabbitfish, RabbitfishModel> {

    public RabbitfishRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitfishModel(context.bakeLayer(ReefModelLayers.RABBITFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Rabbitfish entity) {
        Rabbitfish.RabbitfishVariant rabbitfishVariant = Rabbitfish.RabbitfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/rabbitfish/" + rabbitfishVariant.getSerializedName() + ".png");
    }
}
