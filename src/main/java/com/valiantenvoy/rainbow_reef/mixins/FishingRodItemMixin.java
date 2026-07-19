package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.entity.utils.FishingHookAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FishingRodItem.class)
public class FishingRodItemMixin {

    @ModifyArg(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private Entity rainbowReef$setFishingHookColor(Entity entity) {
        if (entity instanceof FishingHook hook) {
            Player player = hook.getPlayerOwner();
            if (player != null) {
                ItemStack rod = player.getItemInHand(player.getUsedItemHand());
                DyedItemColor color = rod.get(DataComponents.DYED_COLOR);
                ((FishingHookAccessor) hook).rainbowReef$setHasColor(color != null);
                ((FishingHookAccessor) hook).rainbowReef$setColor(color != null ? color.rgb() : 0x000000);
            }
        }
        return entity;
    }
}
