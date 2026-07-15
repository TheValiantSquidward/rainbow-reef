package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MahiMahiModel;
import net.thevaliantsquidward.rainbowreef.entity.MahiMahi;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class MahiMahiRenderer extends MobRenderer<MahiMahi, MahiMahiModel> {

    public MahiMahiRenderer(EntityRendererProvider.Context context) {
        super(context, new MahiMahiModel(context.bakeLayer(ReefModelLayers.MAHI_MAHI)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MahiMahi entity) {
        MahiMahi.MahiMahiVariant mahiMahiVariant = MahiMahi.MahiMahiVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/mahi_mahi/" + mahiMahiVariant.getSerializedName() + ".png");
    }
}