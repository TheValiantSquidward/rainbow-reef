package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.BassletModel;
import com.valiantenvoy.rainbow_reef.entity.Basslet;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BassletRenderer extends MobRenderer<Basslet, BassletModel> {

    public BassletRenderer(EntityRendererProvider.Context context) {
        super(context, new BassletModel(context.bakeLayer(ReefModelLayers.BASSLET)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Basslet entity) {
        Basslet.BassletVariant bassletVariant = Basslet.BassletVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/basslet/" + bassletVariant.getSerializedName() + ".png");
    }
}