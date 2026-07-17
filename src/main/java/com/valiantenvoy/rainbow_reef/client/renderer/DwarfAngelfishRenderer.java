package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.client.models.entity.DwarfAngelfishModel;
import com.valiantenvoy.rainbow_reef.entity.DwarfAngelfish;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DwarfAngelfishRenderer extends MobRenderer<DwarfAngelfish, DwarfAngelfishModel> {

    public DwarfAngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DwarfAngelfishModel(context.bakeLayer(ReefModelLayers.DWARF_ANGELFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfAngelfish entity) {
        return entity.getVariantTexture();
    }
}