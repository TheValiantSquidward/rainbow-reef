package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.DamselfishModel;
import com.valiantenvoy.rainbow_reef.entity.Damselfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DamselfishRenderer extends MobRenderer<Damselfish, DamselfishModel> {

    public DamselfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DamselfishModel(context.bakeLayer(ReefModelLayers.DAMSELFISH)), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(Damselfish entity) {
        Damselfish.DamselfishVariant damselfishVariant = Damselfish.DamselfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/damselfish/" + damselfishVariant.getSerializedName() + ".png");
    }
}
