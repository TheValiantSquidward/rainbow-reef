package net.thevaliantsquidward.rainbowreef.item.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class CleansingSnackItem extends Item {
    public CleansingSnackItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack itemStack = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof ServerPlayer serverPlayer) {

            Player player = (Player) entity;


            List<MobEffectInstance> effectsToRemove = new ArrayList<>();

            for (MobEffectInstance effect : player.getActiveEffects()) {
                if (!effect.isAmbient() && !effect.getEffect().isInstantenous() && !effect.getEffect().isBeneficial()) {
                    effectsToRemove.add(effect);
                }
            }

            effectsToRemove.forEach(effect -> player.removeEffect(effect.getEffect()));
        }


        return itemStack;
    }
}
