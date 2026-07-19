package com.valiantenvoy.rainbow_reef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.utils.FishingHookAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;

public class ReefFishingHookRenderer extends EntityRenderer<FishingHook> {

    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.withDefaultNamespace("textures/entity/fishing_hook.png");
    private static final ResourceLocation DYE_TEXTURE_LOCATION = RainbowReef.location("textures/entity/fishing_hook_color.png");

    private static final RenderType RENDER_TYPE = RenderType.entityCutout(TEXTURE_LOCATION);
    private static final RenderType DYE_RENDER_TYPE = RenderType.entityTranslucent(DYE_TEXTURE_LOCATION);

    private static final double VIEW_BOBBING_SCALE = 960.0F;

    public ReefFishingHookRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(FishingHook entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        Player player = entity.getPlayerOwner();
        if (player != null) {
            poseStack.pushPose();
            poseStack.pushPose();
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            PoseStack.Pose lastPose = poseStack.last();

            VertexConsumer consumer = buffer.getBuffer(RENDER_TYPE);
            vertex(consumer, lastPose, packedLight, 0.0F, 0, 0, 1, 1, 1, 1);
            vertex(consumer, lastPose, packedLight, 1.0F, 0, 1, 1, 1, 1, 1);
            vertex(consumer, lastPose, packedLight, 1.0F, 1, 1, 0, 1, 1, 1);
            vertex(consumer, lastPose, packedLight, 0.0F, 1, 0, 0, 1, 1, 1);

            // render the dyed overlay if the rod is dyed
            if (((FishingHookAccessor) entity).rainbowReef$hasColor()) {
                int color = ((FishingHookAccessor) entity).rainbowReef$getColor();
                float r = ((color >> 16) & 255) / 255.0F;
                float g = ((color >> 8) & 255) / 255.0F;
                float b = (color & 255) / 255.0F;
                VertexConsumer dyeConsumer = buffer.getBuffer(DYE_RENDER_TYPE);
                vertex(dyeConsumer, lastPose, packedLight, 0.0F, 0, 0, 1, r, g, b);
                vertex(dyeConsumer, lastPose, packedLight, 1.0F, 0, 1, 1, r, g, b);
                vertex(dyeConsumer, lastPose, packedLight, 1.0F, 1, 1, 0, r, g, b);
                vertex(dyeConsumer, lastPose, packedLight, 0.0F, 1, 0, 0, r, g, b);
            }

            poseStack.popPose();
            float f = player.getAttackAnim(partialTicks);
            float f1 = Mth.sin(Mth.sqrt(f) * (float) Math.PI);
            Vec3 vec3 = this.getPlayerHandPos(player, f1, partialTicks);
            Vec3 vec31 = entity.getPosition(partialTicks).add(0.0F, 0.25F, 0.0F);
            float f2 = (float) (vec3.x - vec31.x);
            float f3 = (float) (vec3.y - vec31.y);
            float f4 = (float) (vec3.z - vec31.z);
            VertexConsumer consumer1 = buffer.getBuffer(RenderType.lineStrip());
            PoseStack.Pose lastPose1 = poseStack.last();

            for (int j = 0; j <= 16; ++j) {
                stringVertex(entity, f2, f3, f4, consumer1, lastPose1, fraction(j), fraction(j + 1));
            }

            poseStack.popPose();
            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }
    }

    private Vec3 getPlayerHandPos(Player player, float yaw, float partialTicks) {
        int i = player.getMainArm() == HumanoidArm.RIGHT ? 1 : -1;
        ItemStack itemstack = player.getMainHandItem();
        if (!itemstack.canPerformAction(ItemAbilities.FISHING_ROD_CAST)) {
            i = -i;
        }
        if (this.entityRenderDispatcher.options.getCameraType().isFirstPerson() && player == Minecraft.getInstance().player) {
            double d4 = VIEW_BOBBING_SCALE / (double) this.entityRenderDispatcher.options.fov().get();
            Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane((float) i * 0.525F, -0.1F).scale(d4).yRot(yaw * 0.5F).xRot(-yaw * 0.7F);
            return player.getEyePosition(partialTicks).add(vec3);
        }
        else {
            float f = Mth.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * ((float) Math.PI / 180F);
            double d0 = Mth.sin(f);
            double d1 = Mth.cos(f);
            float f1 = player.getScale();
            double d2 = (double) i * 0.35D * (double) f1;
            double d3 = 0.8D * (double) f1;
            float f2 = player.isCrouching() ? -0.1875F : 0.0F;
            return player.getEyePosition(partialTicks).add(-d1 * d2 - d0 * d3, (double) f2 - 0.45D * (double) f1, -d0 * d2 + d1 * d3);
        }
    }

    private static float fraction(int numerator) {
        return (float) numerator / 16;
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int packedLight, float x, int y, int u, int v, float r, float g, float b) {
        consumer.addVertex(pose, x - 0.5F, (float) y - 0.5F, 0.0F)
                .setColor(r, g, b, 1.0F)
                .setUv((float) u, (float ) v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    private static void stringVertex(FishingHook entity, float x, float y, float z, VertexConsumer consumer, PoseStack.Pose pose, float stringFraction, float nextStringFraction) {
        float f = x * stringFraction;
        float f1 = y * (stringFraction * stringFraction + stringFraction) * 0.5F + 0.25F;
        float f2 = z * stringFraction;
        float f3 = x * nextStringFraction - f;
        float f4 = y * (nextStringFraction * nextStringFraction + nextStringFraction) * 0.5F + 0.25F - f1;
        float f5 = z * nextStringFraction - f2;
        float f6 = Mth.sqrt(f3 * f3 + f4 * f4 + f5 * f5);
        f3 /= f6;
        f4 /= f6;
        f5 /= f6;
        int color = ((FishingHookAccessor) entity).rainbowReef$getColor();
        float r = ((color >> 16) & 255) / 255.0F;
        float g = ((color >> 8) & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        consumer.addVertex(pose, f, f1, f2).setColor(r, g, b, 1.0F).setNormal(pose, f3, f4, f5);
    }

    @Override
    public ResourceLocation getTextureLocation(FishingHook entity) {
        return TEXTURE_LOCATION;
    }
}
