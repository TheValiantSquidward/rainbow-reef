package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.ButterflyfishModel;
import com.valiantenvoy.rainbow_reef.entity.Butterflyfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ButterflyfishRenderer extends MobRenderer<Butterflyfish, ButterflyfishModel> {

    public ButterflyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ButterflyfishModel(context.bakeLayer(ReefModelLayers.BUTTERFLYFISH)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Butterflyfish entity) {
        Butterflyfish.ButterflyfishVariant butterflyfishVariant = Butterflyfish.ButterflyfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/butterflyfish/" + butterflyfishVariant.getSerializedName() + ".png");
    }
}