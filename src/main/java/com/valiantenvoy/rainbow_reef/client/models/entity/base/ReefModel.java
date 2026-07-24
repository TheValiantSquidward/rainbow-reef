package com.valiantenvoy.rainbow_reef.client.models.entity.base;

import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.joml.Vector3f;

public abstract class ReefModel<E extends Entity> extends HierarchicalModel<E> {

    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();
    protected static final float IDLE_FADE_SCALE = 2.5F;
    protected static final float ACTIVE_THRESHOLD = 0.05F;

    public ReefModel() {
        super();
    }

    @Override
    public void setupAnim(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.setupAnimations(entity, limbSwing, limbSwingAmount, ageInTicks, ageInTicks - entity.tickCount, netHeadYaw, headPitch);
    }

    protected abstract void setupAnimations(E entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch);

    @Override
    protected void applyStatic(AnimationDefinition definition) {
        KeyframeAnimations.animate(this, definition, 0L, 1.0F, ANIMATION_VECTOR_CACHE);
    }

    protected void animateSmooth(SmoothAnimationState state, AnimationDefinition definition, float ageInTicks, float partialTicks) {
        this.animateSmooth(state, definition, ageInTicks, partialTicks, 1.0F);
    }

    protected void animateSmooth(SmoothAnimationState state, AnimationDefinition definition, float ageInTicks, float partialTicks, float speed) {
        float factor = state.factor(partialTicks);
        if (factor <= ACTIVE_THRESHOLD) {
            return;
        }
        state.updateTime(ageInTicks, speed);
        KeyframeAnimations.animate(this, definition, state.getAccumulatedTime(), factor, ANIMATION_VECTOR_CACHE);
    }

    protected void animateWalkSmooth(SmoothAnimationState state, AnimationDefinition definition, float limbSwing, float limbSwingAmount, float partialTicks) {
        this.animateWalkSmooth(state, definition, limbSwing, limbSwingAmount, 1.5F, 2.5F, partialTicks);
    }

    protected void animateWalkSmooth(SmoothAnimationState state, AnimationDefinition definition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor, float partialTicks) {
        float factor = state.factor(partialTicks);
        if (factor <= ACTIVE_THRESHOLD || limbSwingAmount <= 0.0F) {
            return;
        }
        long time = (long) (limbSwing * 50.0F * maxAnimationSpeed);
        float scale = Math.min(limbSwingAmount * animationScaleFactor, 1.0F) * factor;
        KeyframeAnimations.animate(this, definition, time, scale, ANIMATION_VECTOR_CACHE);
    }

    protected void animateIdleSmooth(SmoothAnimationState state, AnimationDefinition definition, float ageInTicks, float partialTicks, float limbSwingAmount) {
        this.animateIdleSmooth(state, definition, ageInTicks, partialTicks, limbSwingAmount, IDLE_FADE_SCALE, 1.0F);
    }

    protected void animateIdleSmooth(SmoothAnimationState state, AnimationDefinition definition, float ageInTicks, float partialTicks, float limbSwingAmount, float animationScaleFactor, float speed) {
        float factor = state.factor(partialTicks) * (1.0F - Math.min(limbSwingAmount * animationScaleFactor, 1.0F));
        if (factor <= ACTIVE_THRESHOLD) {
            return;
        }
        state.updateTime(ageInTicks, speed);
        KeyframeAnimations.animate(this, definition, state.getAccumulatedTime(), factor, ANIMATION_VECTOR_CACHE);
    }

    protected void applyPitchAndRoll(ReefMob entity, ModelPart modelPart, float partialTicks) {
        modelPart.xRot += entity.getSwimPitch(partialTicks) * Mth.DEG_TO_RAD;
        modelPart.zRot += -entity.getSwimRoll(partialTicks) * Mth.DEG_TO_RAD;
    }
}