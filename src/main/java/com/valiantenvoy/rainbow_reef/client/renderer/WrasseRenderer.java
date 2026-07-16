package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.WrasseModel;
import com.valiantenvoy.rainbow_reef.entity.Wrasse;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class WrasseRenderer extends MobRenderer<Wrasse, WrasseModel> {

    public WrasseRenderer(EntityRendererProvider.Context context) {
        super(context, new WrasseModel(context.bakeLayer(ReefModelLayers.WRASSE)), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(Wrasse entity) {
        Wrasse.WrasseVariant wrasseVariant = Wrasse.WrasseVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/wrasse/" + wrasseVariant.getSerializedName() + ".png");
    }
}
