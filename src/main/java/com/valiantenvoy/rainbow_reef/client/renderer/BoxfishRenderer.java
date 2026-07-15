package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.BoxfishModel;
import com.valiantenvoy.rainbow_reef.entity.Boxfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BoxfishRenderer extends MobRenderer<Boxfish, BoxfishModel> {

    public BoxfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BoxfishModel(context.bakeLayer(ReefModelLayers.BOXFISH)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Boxfish entity) {
        Boxfish.BoxfishVariant boxfishVariant = Boxfish.BoxfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/boxfish/" + boxfishVariant.getSerializedName() + ".png");
    }
}