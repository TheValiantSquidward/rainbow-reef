package com.valiantenvoy.rainbow_reef.items;

import com.valiantenvoy.rainbow_reef.registry.ReefDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BoxfishBreadItem extends Item {

    public BoxfishBreadItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level world, @NotNull LivingEntity entity) {
        super.finishUsingItem(stack, world, entity);


        if (entity instanceof Player player) {


            Random random = new Random();

    if (random.nextInt(100) == 0) {
            if (!world.isClientSide) {
                player.hurt(ReefDamageTypes.getDamageSource(world, ReefDamageTypes.ATE_BOXFISH), 10000000);
            }
     }

            }

            return stack;
    }
}