package com.valiantenvoy.rainbow_reef.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.items.tooltip.ReefMobTooltipData;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.component.CustomData;

// Copied from Teal Lib https://github.com/N1nn1/TealLib/blob/main/src/main/java/com/ninni/teallib/api/client/renderer/item/CapturedMobsTooltipRenderer.java
public class ReefMobTooltipRenderer implements ClientTooltipComponent {

    private static final int CELL_SIZE = 16;

    private final CompoundTag entityTag;

    public ReefMobTooltipRenderer(ReefMobTooltipData data) {
        this.entityTag = data.entityTag();
    }

    @Override
    public int getWidth(Font font) {
        return CELL_SIZE + 8;
    }

    @Override
    public int getHeight() {
        return CELL_SIZE + 8;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        Minecraft instance = Minecraft.getInstance();
        if (instance.level == null) {
            return;
        }

        EntityType<?> type = EntityType.byString(this.entityTag.getString("id")).orElse(null);
        if (type == null) {
            return;
        }

        Entity entity = type.create(instance.level);
        if (!(entity instanceof LivingEntity living)) {
            return;
        }

        CompoundTag fishTag = this.entityTag.copy();
        fishTag.remove("id");
        if (!fishTag.isEmpty()) {
            EntityType.updateCustomEntityTag(instance.level, null, entity, CustomData.of(fishTag));
        }
        if (entity instanceof Bucketable bucketable) {
            bucketable.loadFromBucketTag(fishTag);
        }
        if (entity instanceof ReefMob reefMob) {
            reefMob.setTooltipWaterState();
            reefMob.setRenderedInTooltip(true);
        }
        entity.setYHeadRot(0);

        float maxSize = Math.max(entity.getDimensions(entity.getPose()).width(), entity.getDimensions(entity.getPose()).height());
        float scale = Math.min((CELL_SIZE * 0.8F) / maxSize, 32.0F);

        float renderX = x + (this.getWidth(font) / 2.0F);
        float renderY = y + (this.getHeight() / 2.0F);
        boolean shouldBob = true;

        if (type == ReefEntities.ANGELFISH.get()) {
            renderY += 2;
        }
        if (type == ReefEntities.ARROW_CRAB.get()) {
            renderX += 10;
            renderY += 3;
            shouldBob = false;
        }
        if (type == ReefEntities.BASSLET.get()) {
            renderY += 2;
        }
        if (type == ReefEntities.BILLFISH.get()) {
            renderX += 5;
            renderY += 0.2F;
        }
        if (type == ReefEntities.BUTTERFLYFISH.get()) {
            renderY += 2;
        }
        if (type == ReefEntities.CRAB.get()) {
            renderX += 2;
            renderY += 3;
            shouldBob = false;
        }
        if (type == EntityType.DOLPHIN) {
            renderX += 10;
        }
        if (type == ReefEntities.FROGFISH.get()) {
            renderY += 5.5f;
        }
        if (type == ReefEntities.GOBY.get()) {
            renderY += 2;
        }
        if (type == ReefEntities.HOGFISH.get()) {
            renderY += 2;
            renderX += 3;
        }
        if (type == ReefEntities.JELLYFISH.get()) {
            renderY -= 4;
            renderX += 5;
        }
        if (type == ReefEntities.LARGE_SHARK.get()) {
            renderX += 3;
            renderY += 4;
        }
        if (type == ReefEntities.LIONFISH.get()) {
            renderY += 3;
        }
        if (type == ReefEntities.MAHI_MAHI.get()) {
            renderX += 1;
            renderY += 3;
        }
        if (type == ReefEntities.MAORI_WRASSE.get()) {
            renderY += 2.2F;
        }
        if (type == ReefEntities.MOORISH_IDOL.get()) {
            renderX += 1;
            renderY += 2;
        }
        if (type == ReefEntities.PARROTFISH.get()) {
            renderX += 2;
            renderY += 2;
        }
        if (type == ReefEntities.PIPEFISH.get()) {
            renderX += 7;
            renderY += 0.5F;
        }
        if (type == ReefEntities.RAY.get()) {
            renderX += 17;
            renderY += 0.25F;
        }
        if (type == ReefEntities.SEAHORSE.get()) {
            renderX -= 4;
            renderY += 7;
        }
        if (type == ReefEntities.SHARK.get()) {
            renderX += 3;
            renderY += 2;
        }
        if (type == ReefEntities.SMALL_SHARK.get()) {
            renderX += 9;
            renderY += 2;
        }
        if (type == ReefEntities.TANG.get()) {
            renderX += 3;
            renderY += 3;
        }
        if (type == ReefEntities.TRIGGERFISH.get()) {
            renderY += 2.25F;
        }
        if (type == ReefEntities.WRASSE.get()) {
            renderX += 1.5F;
            renderY += 3.5F;
        }

        float time = (instance.level.getGameTime() + instance.getTimer().getGameTimeDeltaPartialTick(false)) / 20.0F;
        float bob = shouldBob ? (float) Math.sin(time * Math.PI * 0.5F) * 0.05F : 0.0F;

        PoseStack stack = graphics.pose();
        stack.pushPose();
        stack.translate(renderX, renderY + bob * scale, 50.0F);
        stack.scale(scale, -scale, scale);
        stack.mulPose(Axis.YP.rotationDegrees(45));
        if (type == ReefEntities.JELLYFISH.get()) {
            stack.mulPose(Axis.XP.rotationDegrees(90.0F));
        }
        if (type == ReefEntities.RAY.get()) {
            stack.mulPose(Axis.XP.rotationDegrees(20.0F));
            stack.mulPose(Axis.ZP.rotationDegrees(20.0F));
        }
        if (type == ReefEntities.BILLFISH.get()) {
            stack.scale(0.75F, 0.75F, 0.75F);
        }
        if (type == ReefEntities.SHARK.get()) {
            stack.scale(1.25F, 1.25F, 1.25F);
        }
        if (type == ReefEntities.TRIGGERFISH.get()) {
            stack.scale(0.8F, 0.8F, 0.8F);
        }
        stack.mulPose(Axis.XP.rotationDegrees(-7.5F));

        instance.getEntityRenderDispatcher().setRenderShadow(false);
        instance.getEntityRenderDispatcher().render(living, 0.0, 0.0, 0.0, 0.0F, 1.0F, stack, graphics.bufferSource(), LightTexture.pack(15, 15));
        instance.getEntityRenderDispatcher().setRenderShadow(true);

        stack.popPose();
    }
}
