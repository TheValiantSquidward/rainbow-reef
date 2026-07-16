package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.FrogfishModel;
import com.valiantenvoy.rainbow_reef.entity.Frogfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FrogfishRenderer extends MobRenderer<Frogfish, FrogfishModel> {

    public FrogfishRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogfishModel(context.bakeLayer(ReefModelLayers.FROGFISH)), 0.4F);
    }

    public @NotNull ResourceLocation getTextureLocation(Frogfish entity) {
        Frogfish.FrogfishVariant frogfishVariant = Frogfish.FrogfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/frogfish/" + frogfishVariant.getSerializedName() + ".png");
    }
}