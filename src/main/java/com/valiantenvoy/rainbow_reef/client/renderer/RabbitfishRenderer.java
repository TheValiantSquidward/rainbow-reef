package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.RabbitfishModel;
import com.valiantenvoy.rainbow_reef.entity.Rabbitfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RabbitfishRenderer extends MobRenderer<Rabbitfish, RabbitfishModel> {

    public RabbitfishRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitfishModel(context.bakeLayer(ReefModelLayers.RABBITFISH)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Rabbitfish entity) {
        Rabbitfish.RabbitfishVariant rabbitfishVariant = Rabbitfish.RabbitfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/rabbitfish/" + rabbitfishVariant.getSerializedName() + ".png");
    }
}
