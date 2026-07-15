package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.DwarfAngelfishModel;
import com.valiantenvoy.rainbow_reef.entity.DwarfAngelfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DwarfAngelfishRenderer extends MobRenderer<DwarfAngelfish, DwarfAngelfishModel> {

    public DwarfAngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DwarfAngelfishModel(context.bakeLayer(ReefModelLayers.DWARF_ANGELFISH)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(DwarfAngelfish entity) {
        DwarfAngelfish.DwarfAngelfishVariant dwarfAngelfishVariant = DwarfAngelfish.DwarfAngelfishVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/dwarf_angelfish/" + dwarfAngelfishVariant.getSerializedName() + ".png");
    }
}