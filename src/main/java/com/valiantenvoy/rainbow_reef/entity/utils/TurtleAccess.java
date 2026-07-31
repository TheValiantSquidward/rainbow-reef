package com.valiantenvoy.rainbow_reef.entity.utils;

import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;

public interface TurtleAccess {

    float getSwimRoll(float partialTicks);

    float getSwimPitch(float partialTicks);

    SmoothAnimationState getSwimAnimationState();
    SmoothAnimationState getSwimIdleAnimationState();
    SmoothAnimationState getIdleAnimationState();
    SmoothAnimationState getWalkAnimationState();
}
