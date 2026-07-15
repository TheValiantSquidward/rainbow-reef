package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.FusilierModel;
import com.valiantenvoy.rainbow_reef.entity.Fusilier;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FusilierRenderer extends MobRenderer<Fusilier, FusilierModel> {

    public FusilierRenderer(EntityRendererProvider.Context context) {
        super(context, new FusilierModel(context.bakeLayer(ReefModelLayers.FUSILIER)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Fusilier entity) {
        Fusilier.FusilierVariant fusilierVariant = Fusilier.FusilierVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/fusilier/" + fusilierVariant.getSerializedName() + ".png");
    }
}
