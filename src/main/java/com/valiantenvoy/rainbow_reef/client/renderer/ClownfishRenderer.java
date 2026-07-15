package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.ClownfishModel;
import com.valiantenvoy.rainbow_reef.entity.Clownfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ClownfishRenderer extends MobRenderer<Clownfish, ClownfishModel> {

    public ClownfishRenderer(EntityRendererProvider.Context context) {
        super(context, new ClownfishModel(context.bakeLayer(ReefModelLayers.CLOWNFISH)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Clownfish entity) {
        Clownfish.ClownfishVariant clownfishVariant = Clownfish.ClownfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/clownfish/" + clownfishVariant.getSerializedName() + ".png");
    }
}