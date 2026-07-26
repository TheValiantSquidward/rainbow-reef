package com.valiantenvoy.rainbow_reef.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.valiantenvoy.rainbow_reef.blocks.PufferLanternBlock;
import com.valiantenvoy.rainbow_reef.blocks.block_entity.PufferLanternBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.neoforged.neoforge.client.model.data.ModelData;

public class PufferLanternBlockRenderer implements BlockEntityRenderer<PufferLanternBlockEntity> {

    private final BlockRenderDispatcher blockRender;

    public PufferLanternBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRender = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(PufferLanternBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        BlockState state = blockEntity.getBlockState();
        BlockPos pos = blockEntity.getBlockPos();
        float rotation = RotationSegment.convertToDegrees(blockEntity.getBlockState().getValue(PufferLanternBlock.ROTATION));
        if (level != null) {
            ModelBlockRenderer.enableCaching();
            poseStack.pushPose();
            poseStack.translate(0.5D, 0.0D, 0.5D);
            poseStack.mulPose(Axis.YP.rotationDegrees(-rotation));
            poseStack.translate(-0.5D, 0.0D, -0.5D);
            for (var renderType : this.blockRender.getBlockModel(state).getRenderTypes(state, RandomSource.create(state.getSeed(pos)), ModelData.EMPTY)) {
                VertexConsumer consumer = bufferSource.getBuffer(renderType);
                this.blockRender.getModelRenderer().tesselateBlock(level, this.blockRender.getBlockModel(state), state, pos, poseStack, consumer, false, RandomSource.create(), state.getSeed(pos), packedOverlay, ModelData.EMPTY, renderType);
            }
            poseStack.popPose();
            ModelBlockRenderer.clearCache();
        }
    }
}
