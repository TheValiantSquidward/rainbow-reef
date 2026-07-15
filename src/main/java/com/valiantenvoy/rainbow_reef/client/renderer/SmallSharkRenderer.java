package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.SmallSharkModel;
import com.valiantenvoy.rainbow_reef.entity.SmallShark;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class SmallSharkRenderer extends MobRenderer<SmallShark, SmallSharkModel> {

    public SmallSharkRenderer(EntityRendererProvider.Context context) {
        super(context, new SmallSharkModel(context.bakeLayer(ReefModelLayers.SMALL_SHARK)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(SmallShark entity) {
        SmallShark.SmallSharkVariant smallSharkVariant = SmallShark.SmallSharkVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/small_shark/" + smallSharkVariant.getSerializedName() + ".png");
    }
}