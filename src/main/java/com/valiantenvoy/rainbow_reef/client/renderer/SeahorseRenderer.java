package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.SeahorseModel;
import com.valiantenvoy.rainbow_reef.entity.Seahorse;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SeahorseRenderer extends MobRenderer<Seahorse, SeahorseModel> {

    public SeahorseRenderer(EntityRendererProvider.Context context) {
        super(context, new SeahorseModel(context.bakeLayer(ReefModelLayers.SEAHORSE)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Seahorse entity) {
        Seahorse.SeahorseVariant seahorseVariant = Seahorse.SeahorseVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/seahorse/" + seahorseVariant.getSerializedName() + ".png");
    }
}