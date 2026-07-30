package com.valiantenvoy.rainbow_reef.entity.utils;

import com.valiantenvoy.rainbow_reef.entity.animation.SmoothAnimationState;
import net.minecraft.world.entity.animal.Dolphin;

import java.util.stream.Stream;

public interface DolphinAccess {

    float getSwimRoll(float partialTicks);

    float getSwimPitch(float partialTicks);

    void setLeaping(boolean leaping);

    boolean isLeaping();

    SmoothAnimationState getSwimAnimationState();
    SmoothAnimationState getIdleAnimationState();
    SmoothAnimationState getFlopAnimationState();
    SmoothAnimationState getJumpAnimationState();

    boolean isFollower();

    boolean hasFollowers();

    boolean canBeFollowed();

    boolean inRangeOfLeader();

    void pathToLeader();

    void stopFollowing();

    void startFollowing(Dolphin leader);

    void addFollowers(Stream<Dolphin> dolphin);

    void addFollower();

    void removeFollower();
}
