package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.LionfishModel;
import com.valiantenvoy.rainbow_reef.entity.Lionfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LionfishRenderer extends MobRenderer<Lionfish, LionfishModel> {

    public LionfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LionfishModel(context.bakeLayer(ReefModelLayers.LIONFISH)), 0.4F);
    }

    public @NotNull ResourceLocation getTextureLocation(Lionfish entity) {
        Lionfish.LionfishVariant lionfishVariant = Lionfish.LionfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/lionfish/" + lionfishVariant.getSerializedName() + ".png");
    }
}