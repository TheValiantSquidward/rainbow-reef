package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.BillfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Billfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class BillfishRenderer extends MobRenderer<Billfish, BillfishModel> {

    public BillfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BillfishModel(context.bakeLayer(ReefModelLayers.BILLFISH)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(Billfish entity) {
        Billfish.BillfishVariant billfishVariant = Billfish.BillfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/billfish/" + billfishVariant.getSerializedName() + ".png");
    }
}