package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.RayEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RayRenderer extends GeoEntityRenderer<RayEntity> {
    public RayRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RayModel());
    }

    @Override
    public ResourceLocation getTextureLocation(RayEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/spotted_eagle_ray.png");
    }

    @Override
    public void render(RayEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}