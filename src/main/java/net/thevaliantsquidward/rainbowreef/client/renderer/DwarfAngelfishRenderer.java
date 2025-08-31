package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.DwarfAngelfishModel;
import net.thevaliantsquidward.rainbowreef.entity.DwarfAngelfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class DwarfAngelfishRenderer extends MobRenderer<DwarfAngelfish, DwarfAngelfishModel> {

    public DwarfAngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new DwarfAngelfishModel(context.bakeLayer(ReefModelLayers.DWARF_ANGELFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(DwarfAngelfish entity) {
        DwarfAngelfish.DwarfAngelfishVariant dwarfAngelfishVariant = DwarfAngelfish.DwarfAngelfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/dwarf_angelfish/" + dwarfAngelfishVariant.getSerializedName() + ".png");
    }
}