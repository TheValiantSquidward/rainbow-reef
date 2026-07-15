package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.TangModel;
import com.valiantenvoy.rainbow_reef.entity.Tang;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class TangRenderer extends MobRenderer<Tang, TangModel> {

    public TangRenderer(EntityRendererProvider.Context context) {
        super(context, new TangModel(context.bakeLayer(ReefModelLayers.TANG)), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(Tang entity) {
        Tang.TangVariant tangVariant = Tang.TangVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/tang/" + tangVariant.getSerializedName() + ".png");
    }
}
