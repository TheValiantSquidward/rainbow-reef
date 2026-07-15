package com.valiantenvoy.rainbow_reef.items;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CleansingSnackItem extends Item {
    public CleansingSnackItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack itemStack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof ServerPlayer serverPlayer) {


            List<MobEffectInstance> effectsToRemove = new ArrayList<>();

            for (MobEffectInstance effect : serverPlayer.getActiveEffects()) {
                if (!effect.isAmbient() && !effect.getEffect().value().isInstantenous() && !effect.getEffect().value().isBeneficial()) {
                    effectsToRemove.add(effect);
                }
            }

            effectsToRemove.forEach(effect -> serverPlayer.removeEffect(effect.getEffect()));
        }


        return itemStack;
    }
}
