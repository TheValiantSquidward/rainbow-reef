package net.thevaliantsquidward.rainbowreef.client.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SharkAnimations {

	public static final AnimationDefinition SWIM = AnimationDefinition.Builder.withLength(8.0F).looping()
			.addAnimation("core", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-1.25F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-2.17F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-2.17F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-1.25F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(1.25F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(2.17F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(2.17F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(1.25F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(-1.25F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(-2.17F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(-2.17F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(-1.25F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(1.25F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(2.17F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(2.17F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(1.25F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("core", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(-0.43F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.posVec(-0.43F, 0.13F, 0.26F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.43F, 1.0F, 0.71F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.posVec(0.43F, 1.5F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.posVec(0.0F, 1.87F, 0.97F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.posVec(-0.43F, 2.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.posVec(-0.43F, 1.87F, 0.97F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.posVec(0.0F, 1.5F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.43F, 1.0F, 0.71F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.posVec(0.43F, 0.5F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.posVec(0.0F, 0.13F, 0.26F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.posVec(-0.43F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.posVec(-0.43F, 0.13F, -0.26F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.posVec(0.0F, 0.5F, -0.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.posVec(0.43F, 1.0F, -0.71F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.posVec(0.43F, 1.5F, -0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.posVec(0.0F, 1.87F, -0.97F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.posVec(-0.43F, 2.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.posVec(-0.43F, 1.87F, -0.97F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.posVec(0.0F, 1.5F, -0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.posVec(0.43F, 1.0F, -0.71F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.posVec(0.43F, 0.5F, -0.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.posVec(0.0F, 0.13F, -0.26F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.posVec(-0.43F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-1.25F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(1.25F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(2.17F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(2.5F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(1.25F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-2.17F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-2.5F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(-1.25F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(0.0F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(1.25F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(2.17F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(2.5F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(1.25F, 2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(0.0F, 2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(-2.17F, -2.17F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(-2.5F, -2.17F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.1F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.33F, -5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(5.0F, -2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(4.33F, -2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, -5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, -7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-2.5F, -7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-4.33F, -5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-5.0F, -2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-4.33F, -2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-2.5F, -5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, -7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(2.5F, -7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.33F, -5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(5.0F, -2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(4.33F, -2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(2.5F, -5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(0.0F, -7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(-2.5F, -7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-4.33F, -5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(-5.0F, -2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(-4.33F, -2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-2.5F, -5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(0.0F, -7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(2.5F, -7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(4.33F, -5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.33F, 5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(5.0F, 7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(4.33F, 7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-2.5F, 2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-4.33F, 5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-5.0F, 7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-4.33F, 7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-2.5F, 5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, 2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(2.5F, 2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.33F, 5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(5.0F, 7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(4.33F, 7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(2.5F, 5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(0.0F, 2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(-2.5F, 2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-4.33F, 5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(-5.0F, 7.17F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(-4.33F, 7.17F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-2.5F, 5.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(0.0F, 2.83F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(2.5F, 2.83F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(4.33F, 5.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.17F, 8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(1.25F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, -8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-1.25F, -8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-2.5F, 8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-2.17F, 8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, -8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(1.25F, -8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(2.5F, 8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.17F, 8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(1.25F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(0.0F, -8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(-1.25F, -8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(-2.5F, 8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-2.17F, 8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(0.0F, -8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(1.25F, -8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(2.5F, 8.66F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(2.17F, 8.66F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("caudal_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.17F, 17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(2.5F, 17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(2.17F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(1.25F, -17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, -17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-2.17F, 17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-2.5F, 17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-1.25F, -17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, -17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(1.25F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.17F, 17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(2.5F, 17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(2.17F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(1.25F, -17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(0.0F, -17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-2.17F, 17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(-2.5F, 17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-1.25F, -17.32F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(0.0F, -17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(1.25F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(2.17F, 17.32F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_pelvic_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_pelvic_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.125F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("upper_jaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.375F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition FLOP = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("core", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-1.3F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.22F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(1.08F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.87F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.22F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-1.08F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-1.3F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.22F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(1.08F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.87F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.22F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-1.08F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-1.3F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.75F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("jaw", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(8.1757F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("dorsal_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 112.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_fin", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -67.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_fin", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-14.81F, -25.7154F, 10.0954F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("caudal_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 17.5F, -2.5F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("left_pelvic_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 35.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("right_pelvic_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-25.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}