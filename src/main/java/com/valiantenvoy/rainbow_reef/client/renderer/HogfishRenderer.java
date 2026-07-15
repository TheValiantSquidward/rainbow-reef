package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.HogfishModel;
import com.valiantenvoy.rainbow_reef.entity.Hogfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class HogfishRenderer extends MobRenderer<Hogfish, HogfishModel> {

    public HogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new HogfishModel(context.bakeLayer(ReefModelLayers.HOGFISH)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Hogfish entity) {
        Hogfish.HogfishVariant hogfishVariant = Hogfish.HogfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/hogfish/" + hogfishVariant.getSerializedName() + ".png");
    }
}