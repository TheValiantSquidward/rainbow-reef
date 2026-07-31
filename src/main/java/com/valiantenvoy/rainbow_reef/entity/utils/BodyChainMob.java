package com.valiantenvoy.rainbow_reef.entity.utils;

import com.valiantenvoy.rainbow_reef.entity.animation.BodyChain;

public interface BodyChainMob {

    BodyChain getBodyChain();

    float getRenderYaw(float partialTicks);

    float getSegmentYawOffset(int index, float partialTicks);

    float getSegmentPitchOffset(int index, float partialTicks);
}
