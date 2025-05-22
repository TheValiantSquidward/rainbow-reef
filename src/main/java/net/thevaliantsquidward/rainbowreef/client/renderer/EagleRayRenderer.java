package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.EagleRayModel;
import net.thevaliantsquidward.rainbowreef.entity.RayEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

public class EagleRayRenderer extends MobRenderer<RayEntity, EagleRayModel<RayEntity>> {

    public EagleRayRenderer(EntityRendererProvider.Context context) {
        super(context, new EagleRayModel<>(context.bakeLayer(ReefModelLayers.RAY_LAYER)), 0.6F);
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