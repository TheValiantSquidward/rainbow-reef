package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.AngelfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Angelfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class AngelfishRenderer extends MobRenderer<Angelfish, AngelfishModel> {

    public AngelfishRenderer(EntityRendererProvider.Context context) {
        super(context, new AngelfishModel(context.bakeLayer(ReefModelLayers.ANGELFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(Angelfish entity) {
        Angelfish.AngelfishVariant angelfishVariant = Angelfish.AngelfishVariant.variantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/angelfish/" + angelfishVariant.getSerializedName() + ".png");
    }
}