package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MoorishIdolModel;
import net.thevaliantsquidward.rainbowreef.entity.MoorishIdol;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class MoorishIdolRenderer extends MobRenderer<MoorishIdol, MoorishIdolModel> {

    public MoorishIdolRenderer(EntityRendererProvider.Context context) {
        super(context, new MoorishIdolModel(context.bakeLayer(ReefModelLayers.MOORISH_IDOL)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(MoorishIdol entity) {
        MoorishIdol.MoorishIdolVariant moorishIdolVariant = MoorishIdol.MoorishIdolVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/moorish_idol/" + moorishIdolVariant.getSerializedName() + ".png");
    }
}