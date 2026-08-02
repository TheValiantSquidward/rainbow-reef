package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.RainbowReefConfig;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public abstract class AnimalMixin extends Mob implements Bucketable {

    protected AnimalMixin(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "mobInteract", at = @At("RETURN"), cancellable = true)
    public void rainbowReef$bucketTurtle(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (RainbowReefConfig.BUCKETABLE_TURTLES.get()) {
            Animal animal = (Animal) (Object) this;
            if (animal instanceof Turtle turtle && turtle instanceof Bucketable) {
                cir.setReturnValue(Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand)));
            }
        }
    }
}
