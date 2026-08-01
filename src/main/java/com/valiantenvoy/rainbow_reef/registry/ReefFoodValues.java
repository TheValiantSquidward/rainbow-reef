package com.valiantenvoy.rainbow_reef.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ReefFoodValues {

    public static final FoodProperties RAW_TROPICAL_FISH = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1F)
            .build();

    public static final FoodProperties RAW_LARGE_FISH = (new FoodProperties.Builder())
            .nutrition(3).saturationModifier(0.3F)
            .build();

    public static final FoodProperties RAW_BOXFISH = (new FoodProperties.Builder())
            .nutrition(1).saturationModifier(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 600, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0F)
            .build();

    public static final FoodProperties BOXFISH_BREAD = (new FoodProperties.Builder())
            .nutrition(8).saturationModifier(0.4F)
            .build();

    public static final FoodProperties PIPEFISH_SUSHI = (new FoodProperties.Builder())
            .nutrition(4).saturationModifier(0.5F)
            .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F)
            .build();

    public static final FoodProperties BUTTERED_TOAST = (new FoodProperties.Builder())
            .nutrition(8).saturationModifier(0.6F)
            .build();

    public static final FoodProperties COOKIE = (new FoodProperties.Builder())
            .nutrition(2).saturationModifier(0.1F)
            .fast()
            .build();

}
