package com.valiantenvoy.rainbow_reef.client.models.entity.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valiantenvoy.rainbow_reef.entity.utils.SmoothAnimationState;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import org.joml.Vector3f;

import java.util.function.Function;

public abstract class ReefModel<E extends Entity> extends HierarchicalModel<E> {

    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    public ReefModel() {
        this(RenderType::entityCutoutNoCull);
    }

    public ReefModel(Function<ResourceLocation, RenderType> renderType) {
        super(renderType);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        poseStack.pushPose();
        this.root().render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
        poseStack.popPose();
    }

    @Deprecated
    protected void animateIdle(AnimationState animationState, AnimationDefinition definition, float ageInTicks, float limbSwingAmount) {
        float scale = Math.clamp(1 - Math.abs(limbSwingAmount), 0, 1);
        animationState.updateTime(ageInTicks, (float) 0.8);
        animationState.ifStarted((state) -> KeyframeAnimations.animate(this, definition, state.getAccumulatedTime(), scale, ReefModel.ANIMATION_VECTOR_CACHE));
    }

    @Override
    @Deprecated
    protected void animate(AnimationState animationState, AnimationDefinition definition, float ageInTicks) {
        this.animate(animationState, definition, ageInTicks, 1.0F);
    }

    @Override
    protected void animateWalk(AnimationDefinition definition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor) {
        if (limbSwing != 0 && limbSwingAmount != 0) {
            long i = (long) (limbSwing * 50.0F * maxAnimationSpeed);
            float f = Math.min(limbSwingAmount * animationScaleFactor, 1.0F);
            KeyframeAnimations.animate(this, definition, i, f, ReefModel.ANIMATION_VECTOR_CACHE);
        }
    }

    @Override
    @Deprecated
    protected void animate(AnimationState animationState, AnimationDefinition definition, float ageInTicks, float speed) {
        animationState.updateTime(ageInTicks, speed);
        animationState.ifStarted((state) -> KeyframeAnimations.animate(this, definition, state.getAccumulatedTime(), 1.0F, ReefModel.ANIMATION_VECTOR_CACHE));
    }

    protected void animateIdleSmooth(SmoothAnimationState animationState, AnimationDefinition definition, float ageInTicks, float partialTicks, float limbSwingAmount) {
        if (!animationState.isActive(partialTicks)) {
            return;
        }
        animationState.animateIdle(this, definition, ageInTicks, partialTicks, limbSwingAmount, 1.5F, 1.0F);
    }

    protected void animateIdleSmooth(SmoothAnimationState animationState, AnimationDefinition definition, float ageInTicks, float partialTicks, float limbSwingAmount, float animationScaleFactor) {
        if (!animationState.isActive(partialTicks)) {
            return;
        }
        animationState.animateIdle(this, definition, ageInTicks, partialTicks, limbSwingAmount, animationScaleFactor, 1.0F);
    }

    protected void animateIdleSmooth(SmoothAnimationState animationState, AnimationDefinition definition, float ageInTicks, float partialTicks, float limbSwingAmount, float animationScaleFactor, float speed) {
        if (!animationState.isActive(partialTicks)) {
            return;
        }
        animationState.animateIdle(this, definition, ageInTicks, partialTicks, limbSwingAmount, animationScaleFactor, speed);
    }

    protected void animateSmooth(SmoothAnimationState animationState, AnimationDefinition definition, float ageInTicks, float partialTicks) {
        this.animateSmooth(animationState, definition, ageInTicks, partialTicks, 1.0F);
    }

    protected void animateSmooth(SmoothAnimationState animationState, AnimationDefinition definition, float ageInTicks, float partialTicks, float speed) {
        if (!animationState.isActive(partialTicks)) {
            return;
        }
        animationState.animate(this, definition, ageInTicks, partialTicks, speed);
    }

    @Override
    protected void applyStatic(AnimationDefinition definition) {
        KeyframeAnimations.animate(this, definition, 0L, 1.0F, ReefModel.ANIMATION_VECTOR_CACHE);
    }
}