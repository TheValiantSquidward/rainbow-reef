package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MahiModel;
import net.thevaliantsquidward.rainbowreef.entity.Mahi;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class MahiRenderer extends MobRenderer<Mahi, MahiModel<Mahi>> {


    public MahiRenderer(EntityRendererProvider.Context context) {
        super(context, new MahiModel<>(context.bakeLayer(ReefModelLayers.MAHI_LAYER)), 0.4F);
    }


    protected void scale(Mahi entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(Mahi entity) {
        return  new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/mahi/mahi.png");
    }
}