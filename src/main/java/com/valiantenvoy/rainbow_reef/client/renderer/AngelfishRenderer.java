package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.AngelfishModel;
import com.valiantenvoy.rainbow_reef.entity.Angelfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class AngelfishRenderer extends MobRenderer<Angelfish, AngelfishModel> {

    public AngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new AngelfishModel(context.bakeLayer(ReefModelLayers.ANGELFISH)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Angelfish entity) {
        Angelfish.AngelfishVariant angelfishVariant = Angelfish.AngelfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/angelfish/" + angelfishVariant.name().toLowerCase(Locale.ROOT) + ".png");
    }
}