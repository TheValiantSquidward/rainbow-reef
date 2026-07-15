package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ArrowCrabModel;
import net.thevaliantsquidward.rainbowreef.entity.ArrowCrab;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ArrowCrabRenderer extends MobRenderer<ArrowCrab, ArrowCrabModel> {

    private static final ResourceLocation RED = RainbowReef.location("textures/entity/arrowcrab/redarrowcrab.png");
    private static final ResourceLocation YELLOWLINED = RainbowReef.location("textures/entity/arrowcrab/yellowlinearrowcrab.png");


    public ArrowCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new ArrowCrabModel(context.bakeLayer(ReefModelLayers.ARROW_CRAB)), 0.4F);
    }

    protected void scale(@NotNull ArrowCrab entitylivingbaseIn, @NotNull PoseStack matrixStackIn, float partialTickTime) {
    }


    public @NotNull ResourceLocation getTextureLocation(ArrowCrab entity) {
        return switch (entity.getVariant()) {
            case 1 -> RED;
            default -> YELLOWLINED;
        };
    }
}