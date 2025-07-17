package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ArrowCrabModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ButterfishModel;
import net.thevaliantsquidward.rainbowreef.entity.ArrowCrabEntity;
import net.thevaliantsquidward.rainbowreef.entity.ButterfishEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class ArrowCrabRenderer extends MobRenderer<ArrowCrabEntity, ArrowCrabModel<ArrowCrabEntity>> {

    private static final ResourceLocation RED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/arrowcrab/redarrowcrab.png");
    private static final ResourceLocation YELLOWLINED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/arrowcrab/yellowlinearrowcrab.png");


    public ArrowCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowCrabModel<>(context.bakeLayer(ReefModelLayers.ARROWCRAB_LAYER)), 0.4F);
    }

    protected void scale(ArrowCrabEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(ArrowCrabEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> RED;
            default -> YELLOWLINED;
        };
    }
}