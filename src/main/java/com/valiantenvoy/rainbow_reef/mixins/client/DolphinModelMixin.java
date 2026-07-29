package com.valiantenvoy.rainbow_reef.mixins.client;

import com.valiantenvoy.rainbow_reef.entity.utils.DolphinAccess;
import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DolphinModel.class)
public abstract class DolphinModelMixin<T extends Entity> {

    private @Shadow @Final ModelPart body;
    private @Shadow @Final ModelPart tail;
    private @Shadow @Final ModelPart tailFin;

    @Shadow
    public abstract ModelPart root();

    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    public void rainbowReef$tickDolphin(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        float partialTicks = ageInTicks - entity.tickCount;
        if (entity instanceof DolphinAccess dolphinAccess) {
            this.body.xRot += dolphinAccess.rainbowReef$dolphinGetSwimPitch(partialTicks) * Mth.DEG_TO_RAD;
            this.body.zRot += -dolphinAccess.rainbowReef$dolphinGetSwimRoll(partialTicks) * Mth.DEG_TO_RAD;
        }
        if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7) {
            this.body.xRot += -0.05F - 0.05F * Mth.cos(ageInTicks * 0.3F);
            this.tail.xRot = -0.25F * Mth.cos(ageInTicks * 0.3F);
            this.tailFin.xRot = -0.2F * Mth.cos(ageInTicks * 0.3F);
        }
        ci.cancel();
    }
}
