package net.thevaliantsquidward.rainbowreef.client.models.entity.base;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public abstract class ReefModel<E extends Entity> extends HierarchicalModel<E> {

    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    public ReefModel() {
        this(RenderType::entityCutoutNoCull);
    }

    public ReefModel(Function<ResourceLocation, RenderType> pRenderType) {
        super(pRenderType);
    }

    protected void animate(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks) {
        this.animate(animationState, animationDefinition, ageInTicks, 1.0F);
    }

    protected void animateWalk(AnimationDefinition animationDefinition, float limbSwing, float limbSwingAmount, float maxAnimationSpeed, float animationScaleFactor) {
        long i = (long) (limbSwing * 50.0F * maxAnimationSpeed);
        float j = Math.min(limbSwingAmount * animationScaleFactor, 1.0F);
        KeyframeAnimations.animate(this, animationDefinition, i, j, ANIMATION_VECTOR_CACHE);
    }

    protected void animateIdle(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks, float speed, float animationScaleFactor) {
        float scale = Math.max(0, Math.min(1 - Math.abs(speed), 1f));
        animationState.updateTime(ageInTicks, speed);
        animationState.ifStarted((state) -> KeyframeAnimations.animate(this, animationDefinition, state.getAccumulatedTime(), animationScaleFactor, ANIMATION_VECTOR_CACHE));
    }

    protected void animate(AnimationState animationState, AnimationDefinition animationDefinition, float ageInTicks, float speed) {
        animationState.updateTime(ageInTicks, speed);
        animationState.ifStarted((state) -> KeyframeAnimations.animate(this, animationDefinition, state.getAccumulatedTime(), 1.0F, ANIMATION_VECTOR_CACHE));
    }

    protected void applyStatic(AnimationDefinition pAnimationDefinition) {
        KeyframeAnimations.animate(this, pAnimationDefinition, 0L, 1.0F, ANIMATION_VECTOR_CACHE);
    }
}
