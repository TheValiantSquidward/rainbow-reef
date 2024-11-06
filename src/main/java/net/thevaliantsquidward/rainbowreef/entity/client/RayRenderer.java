package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.MoorishIdolEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.RayEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RayRenderer extends GeoEntityRenderer<RayEntity> {
    public RayRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RayModel());
    }

    private static final ResourceLocation DEFAULT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eagleray/spotted_eagle_ray.png");
    private static final ResourceLocation ORNATE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eagleray/ornate_eagle_ray.png");

    public ResourceLocation getTextureLocation(RayEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> ORNATE;
            default -> DEFAULT;
        };
    }
}