package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.DwarfAngelfishModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.LionfishModel;
import net.thevaliantsquidward.rainbowreef.entity.LionfishEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class LionfishRenderer extends MobRenderer<LionfishEntity, LionfishModel<LionfishEntity>> {

    private static final ResourceLocation RED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/lionfish/red.png");
    private static final ResourceLocation CLEARFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/lionfish/clearfin.png");



    public LionfishRenderer(EntityRendererProvider.Context context) {
        super(context, new LionfishModel<>(context.bakeLayer(ReefModelLayers.LIONFISH_LAYER)), 0.4F);
    }


    protected void scale(LionfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(LionfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> CLEARFIN;
            default -> RED;
        };
    }
}