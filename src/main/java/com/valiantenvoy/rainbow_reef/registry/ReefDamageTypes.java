package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ReefDamageTypes {

    public static final ResourceKey<DamageType> BOXFISH_BREAD = register("boxfish_bread");

    public static DamageSource boxfishBread(Level level, Entity source, @Nullable Entity causingEntity) {
        return level.damageSources().source(BOXFISH_BREAD, source, causingEntity);
    }

    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, RainbowReef.location(name));
    }
}
