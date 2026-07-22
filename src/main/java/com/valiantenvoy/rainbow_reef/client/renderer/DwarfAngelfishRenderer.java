package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.DwarfAngelfishModel;
import com.valiantenvoy.rainbow_reef.entity.DwarfAngelfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class DwarfAngelfishRenderer extends ReefMobRenderer<DwarfAngelfish, DwarfAngelfishModel> {

    public DwarfAngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DwarfAngelfishModel(context.bakeLayer(ReefModelLayers.DWARF_ANGELFISH)), 0.3F);
    }
}