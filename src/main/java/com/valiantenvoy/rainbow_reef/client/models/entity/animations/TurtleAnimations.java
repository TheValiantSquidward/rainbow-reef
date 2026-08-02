package com.valiantenvoy.rainbow_reef.client.models.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class TurtleAnimations {

	public static final AnimationDefinition SWIM = AnimationDefinition.Builder.withLength(4.0F).looping()
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-14.33F, -0.67F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-5.67F, -9.33F, -7.83F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-5.67F, -9.33F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-14.33F, -0.67F, -8.75F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-14.33F, -0.67F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-5.67F, -9.33F, -12.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-5.67F, -9.33F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-14.33F, -0.67F, -11.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-14.33F, -0.67F, -10.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-14.33F, 0.67F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-5.67F, 9.33F, 7.83F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-5.67F, 9.33F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-14.33F, 0.67F, 8.75F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-14.33F, 0.67F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(-5.67F, 9.33F, 12.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-5.67F, 9.33F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-14.33F, 0.67F, 11.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-14.33F, 0.67F, 10.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, -10.0F, -40.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(22.5F, 15.98F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(7.5F, 15.98F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -10.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(7.5F, -35.98F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(22.5F, -35.98F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(30.0F, -10.0F, -40.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(22.5F, 15.98F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(7.5F, 15.98F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, -10.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(7.5F, -35.98F, -25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(22.5F, -35.98F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(30.0F, -10.0F, -40.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(30.0F, 10.0F, 40.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(22.5F, -15.98F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(7.5F, -15.98F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(7.5F, 35.98F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(22.5F, 35.98F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(30.0F, 10.0F, 40.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(22.5F, -15.98F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(7.5F, -15.98F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(7.5F, 35.98F, 25.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(22.5F, 35.98F, 35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(30.0F, 10.0F, 40.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(2.17F, 0.0F, 2.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 2.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(2.17F, 0.0F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(2.17F, 0.0F, -2.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, -2.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(-2.17F, 0.0F, -1.25F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 1.3F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.3F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, -1.3F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 1.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.posVec(0.0F, 1.3F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, -1.3F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.posVec(0.0F, -1.3F, -1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 1.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition SWIM_IDLE = AnimationDefinition.Builder.withLength(8.0F).looping()
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(3.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(6.25F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(3.75F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-14.33F, -15.67F, -14.33F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-5.67F, -20.0F, -14.33F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.67F, -22.5F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-14.33F, -25.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-14.33F, -24.33F, -5.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(-5.67F, -20.0F, -5.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-5.67F, -17.5F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(-14.33F, -15.0F, -12.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(-15.0F, -15.17F, -13.54F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(-14.33F, -15.67F, -14.33F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-14.33F, 15.67F, 14.33F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-5.67F, 20.0F, 14.33F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.67F, 22.5F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-14.33F, 25.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(-14.33F, 24.33F, 5.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(-5.67F, 20.0F, 5.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(-5.67F, 17.5F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(-14.33F, 15.0F, 12.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(-15.0F, 15.17F, 13.54F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(-14.33F, 15.67F, 14.33F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.33F, -14.33F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(4.33F, -10.0F, -29.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, -5.67F, -28.66F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-4.33F, -5.67F, -18.96F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-4.33F, -10.0F, -9.51F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, -14.33F, -9.01F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.33F, -14.33F, -17.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(4.33F, -10.0F, -26.33F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, -5.67F, -26.83F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-4.33F, -5.67F, -18.96F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-4.33F, -10.0F, -11.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, -14.33F, -12.55F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.33F, -14.33F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(4.33F, -10.0F, -32.45F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(0.0F, -5.67F, -33.66F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(-4.33F, -5.67F, -26.04F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(-4.33F, -10.0F, -18.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(0.0F, -14.33F, -18.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(4.33F, -14.33F, -27.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(4.33F, -10.0F, -35.99F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(0.0F, -5.67F, -35.49F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-4.33F, -5.67F, -26.04F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(-4.33F, -10.0F, -16.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(0.0F, -14.33F, -15.13F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(4.33F, -14.33F, -22.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.33F, 14.33F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(4.33F, 10.0F, 29.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 5.67F, 28.66F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-4.33F, 5.67F, 18.96F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.degreeVec(-4.33F, 10.0F, 9.51F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 14.33F, 9.01F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(4.33F, 14.33F, 17.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(4.33F, 10.0F, 26.33F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 5.67F, 26.83F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-4.33F, 5.67F, 18.96F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-4.33F, 10.0F, 11.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, 14.33F, 12.55F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.33F, 14.33F, 22.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.degreeVec(4.33F, 10.0F, 32.45F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.degreeVec(0.0F, 5.67F, 33.66F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(-4.33F, 5.67F, 26.04F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.degreeVec(-4.33F, 10.0F, 18.17F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(0.0F, 14.33F, 18.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(4.33F, 14.33F, 27.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.degreeVec(4.33F, 10.0F, 35.99F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.degreeVec(0.0F, 5.67F, 35.49F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-4.33F, 5.67F, 26.04F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.degreeVec(-4.33F, 10.0F, 16.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.degreeVec(0.0F, 14.33F, 15.13F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(4.33F, 14.33F, 22.5F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 3.18F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-6.25F, 0.0F, 3.7F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.degreeVec(-7.17F, 0.0F, 1.67F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, -1.77F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.degreeVec(-3.75F, 0.0F, -2.09F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -1.06F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, -0.87F, 0.52F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, -0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.41F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, 0.5F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.6667F, KeyframeAnimations.posVec(0.0F, 0.87F, 1.93F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 1.0F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.posVec(0.0F, 0.87F, 1.93F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.posVec(0.0F, 0.5F, 1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 1.41F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.3333F, KeyframeAnimations.posVec(0.0F, -0.5F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.6667F, KeyframeAnimations.posVec(0.0F, -0.87F, 0.52F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.3333F, KeyframeAnimations.posVec(0.0F, -0.87F, -0.52F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.6667F, KeyframeAnimations.posVec(0.0F, -0.5F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.41F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.3333F, KeyframeAnimations.posVec(0.0F, 0.5F, -1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.6667F, KeyframeAnimations.posVec(0.0F, 0.87F, -1.93F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.3333F, KeyframeAnimations.posVec(0.0F, 0.87F, -1.93F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.6667F, KeyframeAnimations.posVec(0.0F, 0.5F, -1.73F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.posVec(0.0F, 0.0F, -1.41F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.3333F, KeyframeAnimations.posVec(0.0F, -0.5F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.6667F, KeyframeAnimations.posVec(0.0F, -0.87F, -0.52F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(3.0F).looping()
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(-3.46F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1667F, KeyframeAnimations.degreeVec(3.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8333F, KeyframeAnimations.degreeVec(1.37F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-2.57F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-3.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-3.46F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, -65.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(5.0F, -60.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(5.0F, -50.0F, -7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(2.5F, -40.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, -30.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(2.5F, -20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.5F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4167F, KeyframeAnimations.degreeVec(5.0F, -15.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.5833F, KeyframeAnimations.degreeVec(5.0F, -35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.75F, KeyframeAnimations.degreeVec(7.5F, -55.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.9167F, KeyframeAnimations.degreeVec(7.5F, -65.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_right", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_back_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(7.5F, 65.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(5.0F, 60.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(5.0F, 50.0F, 7.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(2.5F, 40.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.5F, 30.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(2.5F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(2.5F, -10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.4167F, KeyframeAnimations.degreeVec(5.0F, 15.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.5833F, KeyframeAnimations.degreeVec(5.0F, 35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.75F, KeyframeAnimations.degreeVec(7.5F, 55.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.9167F, KeyframeAnimations.degreeVec(7.5F, 65.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_left", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(10.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0F, -35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(10.0F, -25.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, 15.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.25F, KeyframeAnimations.degreeVec(-10.0F, 25.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, -35.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.9167F, KeyframeAnimations.degreeVec(10.0F, -50.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(10.0F, -55.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_right", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(10.0F, 55.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(10.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(10.0F, 35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(10.0F, 25.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.75F, KeyframeAnimations.degreeVec(0.0F, -15.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, -35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.25F, KeyframeAnimations.degreeVec(-10.0F, -25.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.5F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.75F, KeyframeAnimations.degreeVec(0.0F, 35.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.9167F, KeyframeAnimations.degreeVec(10.0F, 50.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(10.0F, 55.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_left", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.64F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.98F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.98F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.64F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.64F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.1667F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.98F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.98F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.5F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.87F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6667F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.64F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8333F, KeyframeAnimations.posVec(0.0F, 0.0F, -0.34F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(8.0F).looping()
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(2.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.0F, KeyframeAnimations.degreeVec(-2.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, -1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(5.0F, KeyframeAnimations.degreeVec(2.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(7.0F, KeyframeAnimations.degreeVec(-2.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, -1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("fin_back_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_back_right", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_back_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 45.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_back_left", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_right", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_right", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_left", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("fin_left", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();
}