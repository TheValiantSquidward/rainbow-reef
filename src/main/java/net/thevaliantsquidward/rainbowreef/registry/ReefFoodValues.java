package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ReefFoodValues {

    public static final FoodProperties RAW_GOBY = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1F)
            .build();

    public static final FoodProperties RAW_RAY = (new FoodProperties.Builder())
            .nutrition(3).saturationMod(0.3F)
            .build();

    public static final FoodProperties RAW_BOXFISH = (new FoodProperties.Builder())
            .nutrition(1).saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 2), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 2), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1F)
            .build();

}
