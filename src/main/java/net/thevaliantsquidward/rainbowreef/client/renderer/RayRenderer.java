package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.RayModel;
import net.thevaliantsquidward.rainbowreef.entity.RayEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RayRenderer extends GeoEntityRenderer<RayEntity> {
    public RayRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RayModel());
    }

    private static final ResourceLocation SPOTTED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eagleray/spotted_eagle_ray.png");
    private static final ResourceLocation ORNATE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eagleray/ornate_eagle_ray.png");
    private static final ResourceLocation COWNOSE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eagleray/cownose.png");

    public ResourceLocation getTextureLocation(RayEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> ORNATE;
            case 2 -> COWNOSE;
            default -> SPOTTED;
        };
    }
}