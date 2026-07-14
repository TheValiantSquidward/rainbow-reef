package net.thevaliantsquidward.rainbowreef.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.items.tooltip.ReefMobTooltipData;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ReefMobTooltipRenderer implements ClientTooltipComponent {
    private static final int CELL_SIZE = 16;

    private final CompoundTag entityTag;

    public ReefMobTooltipRenderer(ReefMobTooltipData data) {
        this.entityTag = data.entityTag();
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return CELL_SIZE + 8;
    }

    @Override
    public int getHeight() {
        return CELL_SIZE + 8;
    }

    @Override
    public void renderImage(@NotNull Font font, int x, int y, @NotNull GuiGraphics graphics) {
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
        }
        entity.setYHeadRot(0);

        float maxSize = Math.max(entity.getDimensions(entity.getPose()).width(), entity.getDimensions(entity.getPose()).height());
        float scale = Math.min((CELL_SIZE * 0.8F) / maxSize, 32.0F);

        float renderX = x + (this.getWidth(font) / 2F);
        float renderY = y + (this.getHeight() / 2F);

        float time = (mc.level.getGameTime() + mc.getTimer().getGameTimeDeltaPartialTick(false)) / 20.0F;
        float bob = (float) Math.sin(time * Math.PI * 0.5F) * 0.05F;

        PoseStack stack = graphics.pose();
        stack.pushPose();
        stack.translate(renderX, renderY + bob * scale, 50.0F);
        stack.scale(scale, -scale, scale);
        stack.mulPose(Axis.YP.rotationDegrees(45));
        stack.mulPose(Axis.XP.rotationDegrees(-7.5F));

        mc.getEntityRenderDispatcher().setRenderShadow(false);
        mc.getEntityRenderDispatcher().render(living, 0.0, 0.0, 0.0, 0.0F, 1.0F, stack, graphics.bufferSource(), LightTexture.pack(15, 15));
        mc.getEntityRenderDispatcher().setRenderShadow(true);

        stack.popPose();
    }
}
