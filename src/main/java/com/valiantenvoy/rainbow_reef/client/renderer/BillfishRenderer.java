package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.BillfishModel;
import com.valiantenvoy.rainbow_reef.entity.Billfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BillfishRenderer extends MobRenderer<Billfish, BillfishModel> {

    public BillfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BillfishModel(context.bakeLayer(ReefModelLayers.BILLFISH)), 0.6F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Billfish entity) {
        Billfish.BillfishVariant billfishVariant = Billfish.BillfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/billfish/" + billfishVariant.getSerializedName() + ".png");
    }
}