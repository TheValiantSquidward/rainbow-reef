package net.thevaliantsquidward.rainbowreef.items;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class JellyBottleItem extends HoneyBottleItem {
    public JellyBottleItem(Properties pProperties) {
        super(pProperties);
    }

    public UseAnim getUseAnimation(ItemStack p_41358_) {
        return UseAnim.DRINK;
    }

    public SoundEvent getDrinkingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    public SoundEvent getEatingSound() {
        return SoundEvents.HONEY_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }
        if (!level.isClientSide && livingEntity instanceof ServerPlayer serverPlayer) {

            Player player = (Player) livingEntity;


            List<MobEffectInstance> effectsToRemove = new ArrayList<>();

            for (MobEffectInstance effect : player.getActiveEffects()) {
                if (!effect.isAmbient() && !effect.getEffect().isInstantenous() && !effect.getEffect().isBeneficial()) {
                    effectsToRemove.add(effect);
                }
            }

            effectsToRemove.forEach(effect -> player.removeEffect(effect.getEffect()));
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }

    private void applyRandomEffect(LivingEntity entity, Level level) {
        List<MobEffectInstance> effectsToAdd = new ArrayList<>();


    }

}

