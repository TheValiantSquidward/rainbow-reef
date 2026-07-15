package com.valiantenvoy.rainbow_reef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.TriggerfishModel;
import com.valiantenvoy.rainbow_reef.entity.Triggerfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class TriggerfishRenderer extends MobRenderer<Triggerfish, TriggerfishModel> {

    public TriggerfishRenderer(EntityRendererProvider.Context context) {
        super(context, new TriggerfishModel(context.bakeLayer(ReefModelLayers.TRIGGERFISH)), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(Triggerfish entity) {
        Triggerfish.TriggerfishVariant triggerfishVariant = Triggerfish.TriggerfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/triggerfish/" + triggerfishVariant.getSerializedName() + ".png");
    }
}
