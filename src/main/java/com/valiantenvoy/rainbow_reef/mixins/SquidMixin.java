package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.entity.ai.goals.SquidPanicGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SquidWanderGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Squid.class)
public class SquidMixin extends WaterAnimal {

    protected SquidMixin(EntityType<? extends WaterAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "registerGoals", at = @At("HEAD"), cancellable = true)
    protected void registerGoals(CallbackInfo ci) {
        Squid squid = (Squid) (Object) this;
        this.goalSelector.addGoal(0, new SquidWanderGoal(squid));
        this.goalSelector.addGoal(1, new SquidPanicGoal(squid));
        ci.cancel();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
            this.move(MoverType.SELF, this.getDeltaMovement());
        }
    }
}
