package com.valiantenvoy.rainbow_reef.items;

import com.valiantenvoy.rainbow_reef.registry.ReefDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BoxfishBreadItem extends Item {

    public BoxfishBreadItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player && level.getRandom().nextInt(100) == 0 && !level.isClientSide) {
            player.hurt(ReefDamageTypes.getDamageSource(level, ReefDamageTypes.ATE_BOXFISH), 1000);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}