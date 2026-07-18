package com.valiantenvoy.rainbow_reef.datagen;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.RainbowReefTab;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class ReefLanguageProvider extends LanguageProvider {

    public ReefLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), RainbowReef.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addTab(RainbowReefTab.RAINBOW_REEF_TAB.get(), "Rainbow Reef");
        this.addTab(RainbowReefTab.RAINBOW_REEF_VARIANTS_TAB.get(), "Rainbow Reef Variants");

        ReefBlocks.BLOCK_TRANSLATIONS.forEach(this::forBlocks);
        ReefItems.ITEM_TRANSLATIONS.forEach(this::forItems);
        ReefEntities.ENTITY_TYPE.getEntries().forEach(this::forEntity);

        this.add("tooltip.rainbowreef.burrow_occupant", "%s %s");

        this.addItem(ReefItems.ANGELFISH_BUCKET, "Bucket of Angelfish");
        this.addItem(ReefItems.ARROW_CRAB_BUCKET, "Bucket of Arrow Crab");
        this.addItem(ReefItems.BASSLET_BUCKET, "Bucket of Basslet");
        this.addItem(ReefItems.BOXFISH_BUCKET, "Bucket of Boxfish");
        this.addItem(ReefItems.BUTTERFLYFISH_BUCKET, "Bucket of Butterflyfish");
        this.addItem(ReefItems.CLOWNFISH_BUCKET, "Bucket of Clownfish");
        this.addItem(ReefItems.CRAB_BUCKET, "Bucket of Crab");
        this.addItem(ReefItems.DWARF_ANGELFISH_BUCKET, "Bucket of Dwarf Angelfish");
        this.addItem(ReefItems.GOBY_BUCKET, "Bucket of Goby");
        this.addItem(ReefItems.HOGFISH_BUCKET, "Bucket of Hogfish");
        this.addItem(ReefItems.JELLYFISH_BUCKET, "Bucket of Jellyfish");
        this.addItem(ReefItems.MAHI_MAHI_BUCKET, "Bucket of Mahi Mahi");
        this.addItem(ReefItems.MOORISH_IDOL_BUCKET, "Bucket of Moorish Idol");
        this.addItem(ReefItems.PARROTFISH_BUCKET, "Bucket of Parrotfish");
        this.addItem(ReefItems.PIPEFISH_BUCKET, "Bucket of Pipefish");
        this.addItem(ReefItems.RAY_BUCKET, "Bucket of Ray");
        this.addItem(ReefItems.SEAHORSE_BUCKET, "Bucket of Seahorse");
        this.addItem(ReefItems.SMALL_SHARK_BUCKET, "Bucket of Small Shark");
        this.addItem(ReefItems.TANG_BUCKET, "Bucket of Tang");

        this.addItem(ReefItems.SURF_N_TURF, "Surf 'N' Turf");

        this.musicDisc();

        this.sound(ReefSoundEvents.FISH_DEATH, "Fish dies");
        this.sound(ReefSoundEvents.FISH_HURT, "Fish hurts");
        this.sound(ReefSoundEvents.FISH_FLOP, "Fish flops");
        this.sound(ReefSoundEvents.FISH_JUMP, "Fish jumps");

        this.sound(ReefSoundEvents.CRAB_DEATH, "Crab dies");
        this.sound(ReefSoundEvents.CRAB_HURT, "Crab hurts");
        this.sound(ReefSoundEvents.CRAB_FLOP, "Crab flops");

        this.sound(ReefSoundEvents.JELLYFISH_HURT, "Jellyfish hurts");
        this.sound(ReefSoundEvents.JELLYFISH_ZAP, "Jellyfish zaps");
        this.sound(ReefSoundEvents.JELLY_BLOCK_BOUNCE, "Jelly Block bounces");
        this.sound(ReefSoundEvents.CLAW_DISC, "ValiantEnvoy - Claw");
    }

    @Override
    public String getName() {
        return  RainbowReef.MOD_ID + " Languages: en_us";
    }

    private void forBlocks(Supplier<? extends Block> block) {
        this.addBlock(block, ReefTextUtils.createTranslation(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block.get())).getPath()));
    }

    private void forItems(Supplier<? extends Item> item) {
        this.addItem(item, ReefTextUtils.createTranslation(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get())).getPath()));
    }

    private void forEntity(Supplier<? extends EntityType<?>> entity) {
        this.addEntityType(entity, ReefTextUtils.createTranslation(Objects.requireNonNull(BuiltInRegistries.ENTITY_TYPE.getKey(entity.get())).getPath()));
    }

    public void sound(Supplier<? extends SoundEvent> key, String subtitle){
        this.add("subtitles.rainbowreef." + key.get().getLocation().getPath(), subtitle);
    }

    public void addTab(CreativeModeTab key, String name){
        add(key.getDisplayName().getString(), name);
    }

    public void addAdvancement(String key, String name) {
        this.add("advancement." + RainbowReef.MOD_ID + "." + key, name);
    }

    public void addAdvancementDesc(String key, String name) {
        this.add("advancement." + RainbowReef.MOD_ID + "." + key + ".desc", name);
    }

    protected void musicDisc() {
        String disc = ((Supplier<? extends Item>) ReefItems.CLAW_DISC).get().getDescriptionId();
        this.add(disc, "Music Disc");
        this.add("jukebox_song.rainbowreef." + BuiltInRegistries.ITEM.getKey(((Supplier<? extends Item>) ReefItems.CLAW_DISC).get()).getPath(), "ValiantEnvoy - Claw");
    }
}
