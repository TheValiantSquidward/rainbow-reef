package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.entity.utils.DolphinAccess;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dolphin.class)
public abstract class DolphinMixin extends Mob implements DolphinAccess {

    private static final @Unique double PITCH_MIN = 0.01D;
    private static final @Unique double PITCH_MAX = 0.05D;
    private static final @Unique float PITCH_LERP = 0.2F;
    private static final @Unique float PITCH_CLAMP = 85.0F;

    private static final @Unique float ROLL_DECAY = 1.9F;
    private static final @Unique float ROLL_CLAMP = 20.0F;

    private @Unique float rainbowReef$dolphinPrevSwimRoll;
    private @Unique float rainbowReef$dolphinSwimRoll;

    private @Unique float rainbowReef$dolphinPrevSwimPitch;
    private @Unique float rainbowReef$dolphinSwimPitch;

    protected DolphinMixin(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends Dolphin> entityType, Level level, CallbackInfo ci) {
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 6, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 6);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void rainbowReef$tickDolphin(CallbackInfo ci) {
        Dolphin dolphin = (Dolphin) (Object) this;
        if (dolphin.level().isClientSide) {
            this.rainbowReef$dolphinUpdateSwimRoll();
            this.rainbowReef$dolphinUpdateSwimPitch();
        }
        // fix death by drowning by simply not drowning!
        if (dolphin.getAirSupply() <= 10) {
            dolphin.setAirSupply(dolphin.getMaxAirSupply());
        }
    }

    @Unique
    protected void rainbowReef$dolphinUpdateSwimRoll() {
        Dolphin dolphin = (Dolphin) (Object) this;
        this.rainbowReef$dolphinPrevSwimRoll = this.rainbowReef$dolphinSwimRoll;
        if (dolphin.isInWater()) {
            float turn = Mth.degreesDifference(dolphin.getYRot(), dolphin.yRotO);
            if (Math.abs(turn) > 1.0F) {
                if (Math.abs(this.rainbowReef$dolphinSwimRoll) < ROLL_CLAMP) {
                    this.rainbowReef$dolphinSwimRoll -= Math.signum(turn);
                }
            } else if (this.rainbowReef$dolphinSwimRoll != 0.0F) {
                float sign = Math.signum(this.rainbowReef$dolphinSwimRoll);
                this.rainbowReef$dolphinSwimRoll -= sign * ROLL_DECAY;
                if (this.rainbowReef$dolphinSwimRoll * sign < 0.0F) {
                    this.rainbowReef$dolphinSwimRoll = 0.0F;
                }
            }
        } else {
            this.rainbowReef$dolphinSwimRoll = 0.0F;
        }
    }

    @Unique
    protected void rainbowReef$dolphinUpdateSwimPitch() {
        Dolphin dolphin = (Dolphin) (Object) this;
        this.rainbowReef$dolphinPrevSwimPitch = this.rainbowReef$dolphinSwimPitch;
        float target = 0.0F;
        double dx = dolphin.getX() - dolphin.xo;
        double dy = dolphin.getY() - dolphin.yo;
        double dz = dolphin.getZ() - dolphin.zo;
        double horizontal = Math.sqrt(dx * dx + dz * dz);
        double speed = Math.sqrt(horizontal * horizontal + dy * dy);
        float speedFactor = (float) Mth.clamp((speed - PITCH_MIN) / (PITCH_MAX - PITCH_MIN), 0.0D, 1.0D);
        if (speedFactor > 0.0F) {
            float angle = (float) (-(Mth.atan2(dy, horizontal) * (180.0D / Math.PI)));
            target = Mth.clamp(angle, -PITCH_CLAMP, PITCH_CLAMP) * speedFactor;
        }
        this.rainbowReef$dolphinSwimPitch += (target - this.rainbowReef$dolphinSwimPitch) * PITCH_LERP;
    }

    @Override
    public float rainbowReef$dolphinGetSwimRoll(float partialTicks) {
        return Mth.lerp(partialTicks, this.rainbowReef$dolphinPrevSwimRoll, this.rainbowReef$dolphinSwimRoll);
    }

    @Override
    public float rainbowReef$dolphinGetSwimPitch(float partialTicks) {
        return Mth.lerp(partialTicks, this.rainbowReef$dolphinPrevSwimPitch, this.rainbowReef$dolphinSwimPitch);
    }
}
