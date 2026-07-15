package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MoorishIdolModel;
import net.thevaliantsquidward.rainbowreef.entity.MoorishIdol;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class MoorishIdolRenderer extends MobRenderer<MoorishIdol, MoorishIdolModel> {

    public MoorishIdolRenderer(EntityRendererProvider.Context context) {
        super(context, new MoorishIdolModel(context.bakeLayer(ReefModelLayers.MOORISH_IDOL)), 0.4F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(MoorishIdol entity) {
        MoorishIdol.MoorishIdolVariant moorishIdolVariant = MoorishIdol.MoorishIdolVariant.getVariantId(entity.getVariant());
        return RainbowReef.location("textures/entity/moorish_idol/" + moorishIdolVariant.getSerializedName() + ".png");
    }
}