package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.GobyModel;
import com.valiantenvoy.rainbow_reef.entity.Goby;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GobyRenderer extends MobRenderer<Goby, GobyModel> {

    public GobyRenderer(EntityRendererProvider.Context context) {
        super(context, new GobyModel(context.bakeLayer(ReefModelLayers.GOBY)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Goby entity) {
        Goby.GobyVariant gobyVariant = Goby.GobyVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/goby/" + gobyVariant.getSerializedName() + ".png");
    }
}