package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.ParrotfishModel;
import com.valiantenvoy.rainbow_reef.client.renderer.layer.ParrotfishEepyLayer;
import com.valiantenvoy.rainbow_reef.entity.Parrotfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ParrotfishRenderer extends MobRenderer<Parrotfish, ParrotfishModel> {

    public ParrotfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ParrotfishModel(context.bakeLayer(ReefModelLayers.PARROTFISH)), 0.4F);
        this.addLayer(new ParrotfishEepyLayer(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Parrotfish entity) {
        Parrotfish.ParrotfishVariant parrotfishVariant = Parrotfish.ParrotfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/parrotfish/" + parrotfishVariant.getSerializedName() + ".png");
    }
}