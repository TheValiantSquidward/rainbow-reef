package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.BassletModel;
import net.thevaliantsquidward.rainbowreef.entity.Basslet;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class BassletRenderer extends MobRenderer<Basslet, BassletModel> {

    public BassletRenderer(EntityRendererProvider.Context context) {
        super(context, new BassletModel(context.bakeLayer(ReefModelLayers.BASSLET)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(Basslet entity) {
        Basslet.BassletVariant bassletVariant = Basslet.BassletVariant.variantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/basslet/" + bassletVariant.getSerializedName() + ".png");
    }
}