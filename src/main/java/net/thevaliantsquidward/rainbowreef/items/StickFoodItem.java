package net.thevaliantsquidward.rainbowreef.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class StickFoodItem extends Item {
    public StickFoodItem(Item.Properties properties) {
        super(properties);
    }
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entityLiving) {
        ItemStack itemstack = super.finishUsingItem(stack, level, entityLiving);
        return entityLiving instanceof Player && ((Player)entityLiving).getAbilities().instabuild ? itemstack : new ItemStack(Items.STICK);
    }
}