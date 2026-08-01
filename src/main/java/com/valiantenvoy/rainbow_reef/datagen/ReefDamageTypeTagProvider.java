package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.registry.ReefDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ReefDamageTypeTagProvider extends TagsProvider<DamageType> {

    public ReefDamageTypeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, Registries.DAMAGE_TYPE, provider, RainbowReef.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
        this.tag(DamageTypeTags.BYPASSES_SHIELD).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
        this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
        this.tag(DamageTypeTags.BYPASSES_EFFECTS).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
        this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
        this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
        this.tag(DamageTypeTags.NO_KNOCKBACK).add(
                ReefDamageTypes.BOXFISH_BREAD
        );
    }
}