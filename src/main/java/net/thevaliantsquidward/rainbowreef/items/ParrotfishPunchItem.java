package net.thevaliantsquidward.rainbowreef.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.stats.Stats;
import org.jetbrains.annotations.NotNull;

public class ParrotfishPunchItem extends HoneyBottleItem {
    public ParrotfishPunchItem(Properties properties) {
        super(properties);
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }

    public @NotNull SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public @NotNull SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }


    public int getUseDuration(ItemStack stack) {
        return 80;
    }
}

