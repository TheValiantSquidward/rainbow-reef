package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MoorishIdolModel;
import net.thevaliantsquidward.rainbowreef.entity.MoorishIdolEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MoorishIdolRenderer extends GeoEntityRenderer<MoorishIdolEntity> {
    private static final ResourceLocation DEFAULT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/moorishidol/defaultmoorishidol.png");
    private static final ResourceLocation SILVER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/moorishidol/silvermoorishidol.png");



    public MoorishIdolRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MoorishIdolModel());
    }

    protected void scale(MoorishIdolEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(MoorishIdolEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> SILVER;
            default -> DEFAULT;
        };
    }
}