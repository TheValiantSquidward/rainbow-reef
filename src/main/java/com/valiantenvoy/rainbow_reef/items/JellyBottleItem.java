package com.valiantenvoy.rainbow_reef.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class JellyBottleItem extends HoneyBottleItem {

    public JellyBottleItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
            if (!level.isClientSide) {
                List<MobEffectInstance> effectsToRemove = new ArrayList<>();
                for (MobEffectInstance effect : serverPlayer.getActiveEffects()) {
                    if (!effect.isAmbient() && !effect.getEffect().value().isInstantenous() && !effect.getEffect().value().isBeneficial()) {
                        effectsToRemove.add(effect);
                    }
                }
                effectsToRemove.forEach(effect -> serverPlayer.removeEffect(effect.getEffect()));
            }
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}

