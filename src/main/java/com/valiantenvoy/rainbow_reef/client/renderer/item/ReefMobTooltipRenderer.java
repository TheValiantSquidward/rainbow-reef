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
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        EntityType<?> type = EntityType.byString(this.entityTag.getString("id")).orElse(null);
        if (type == null) return;

        Entity entity = type.create(mc.level);
        if (!(entity instanceof LivingEntity living)) return;

        CompoundTag fishTag = this.entityTag.copy();
        fishTag.remove("id");
        if (!fishTag.isEmpty()) {
            EntityType.updateCustomEntityTag(mc.level, null, entity, CustomData.of(fishTag));
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

        float renderX = x + (this.getWidth(font) / 2F);
        float renderY = y + (this.getHeight() / 2F);
        if (type == ReefEntities.ARROW_CRAB.get()) {
            renderX += 10;
            renderY += 3;
        }
        if (type == ReefEntities.PIPEFISH.get()) {
            renderX += 10;
            renderY += 0;
        }
        if (type == ReefEntities.JELLYFISH.get()) {
            renderX += 3;
            renderY -= 3;
        }
        if (type == ReefEntities.HOGFISH.get()) {
            renderX += 3;
            renderY += 0;
        }

        float time = (mc.level.getGameTime() + mc.getTimer().getGameTimeDeltaPartialTick(false)) / 20.0F;
        float bob = (float) Math.sin(time * Math.PI * 0.5F) * 0.05F;

        PoseStack stack = graphics.pose();
        stack.pushPose();
        stack.translate(renderX, renderY + bob * scale, 50.0F);
        stack.scale(scale, -scale, scale);
        stack.mulPose(Axis.YP.rotationDegrees(45));
        if (type == ReefEntities.JELLYFISH.get()) {
            stack.mulPose(Axis.XP.rotationDegrees(90.0F));
        }
        stack.mulPose(Axis.XP.rotationDegrees(-7.5F));

        mc.getEntityRenderDispatcher().setRenderShadow(false);
        mc.getEntityRenderDispatcher().render(living, 0.0, 0.0, 0.0, 0.0F, 1.0F, stack, graphics.bufferSource(), LightTexture.pack(15, 15));
        mc.getEntityRenderDispatcher().setRenderShadow(true);

        stack.popPose();
    }
}
