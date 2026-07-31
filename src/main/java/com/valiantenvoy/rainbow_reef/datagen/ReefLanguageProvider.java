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
        this.addItem(ReefItems.BILLFISH_BUCKET, "Bucket of Billfish");
        this.addItem(ReefItems.BUTTERFLYFISH_BUCKET, "Bucket of Butterflyfish");
        this.addItem(ReefItems.CLOWNFISH_BUCKET, "Bucket of Clownfish");
        this.addItem(ReefItems.CRAB_BUCKET, "Bucket of Crab");
        this.addItem(ReefItems.DAMSELFISH_BUCKET, "Bucket of Damselfish");
        this.addItem(ReefItems.DWARF_ANGELFISH_BUCKET, "Bucket of Dwarf Angelfish");
        this.addItem(ReefItems.FROGFISH_BUCKET, "Bucket of Frogfish");
        this.addItem(ReefItems.FUSILIER_BUCKET, "Bucket of Fusilier");
        this.addItem(ReefItems.GOBY_BUCKET, "Bucket of Goby");
        this.addItem(ReefItems.HOGFISH_BUCKET, "Bucket of Hogfish");
        this.addItem(ReefItems.JELLYFISH_BUCKET, "Bucket of Jellyfish");
        this.addItem(ReefItems.MAHI_MAHI_BUCKET, "Bucket of Mahi-mahi");
        this.addItem(ReefItems.MAORI_WRASSE_BUCKET, "Bucket of Maori Wrasse");
        this.addItem(ReefItems.MOORISH_IDOL_BUCKET, "Bucket of Moorish Idol");
        this.addItem(ReefItems.PARROTFISH_BUCKET, "Bucket of Parrotfish");
        this.addItem(ReefItems.PIPEFISH_BUCKET, "Bucket of Pipefish");
        this.addItem(ReefItems.RABBITFISH_BUCKET, "Bucket of Rabbitfish");
        this.addItem(ReefItems.RAY_BUCKET, "Bucket of Ray");
        this.addItem(ReefItems.SEAHORSE_BUCKET, "Bucket of Seahorse");
        this.addItem(ReefItems.SHARK_BUCKET, "Bucket of Shark");
        this.addItem(ReefItems.SMALL_SHARK_BUCKET, "Bucket of Small Shark");
        this.addItem(ReefItems.TANG_BUCKET, "Bucket of Tang");
        this.addItem(ReefItems.TRIGGERFISH_BUCKET, "Bucket of Triggerfish");
        this.addItem(ReefItems.LIONFISH_BUCKET, "Bucket of Lionfish");
        this.addItem(ReefItems.LARGE_SHARK_BUCKET, "Bucket of Large Shark");
        this.addItem(ReefItems.WRASSE_BUCKET, "Bucket of Wrasse");
        this.addItem(ReefItems.DOLPHIN_BUCKET, "Bucket of Dolphin");

        this.addItem(ReefItems.SURF_N_TURF, "Surf 'N' Turf");

        this.addItem(ReefItems.MAHI_MAHI_SPAWN_EGG, "Mahi-mahi Spawn Egg");
        this.addItem(ReefItems.RAW_MAHI_MAHI, "Mahi-mahi");

        this.musicDisc();

        this.sound(ReefSoundEvents.FISH_DEATH, "Fish dies");
        this.sound(ReefSoundEvents.FISH_HURT, "Fish hurts");
        this.sound(ReefSoundEvents.FISH_FLOP, "Fish flops");
        this.sound(ReefSoundEvents.FISH_JUMP, "Fish jumps");

        this.sound(ReefSoundEvents.CRAB_DEATH, "Crab dies");
        this.sound(ReefSoundEvents.CRAB_HURT, "Crab hurts");
        this.sound(ReefSoundEvents.CRAB_FLOP, "Crab flops");

        this.sound(ReefSoundEvents.SHARK_WARN, "Shark warns");
        this.sound(ReefSoundEvents.SHARK_ATTACK, "Shark attacks");

        this.sound(ReefSoundEvents.JELLYFISH_HURT, "Jellyfish hurts");
        this.sound(ReefSoundEvents.JELLYFISH_ZAP, "Jellyfish zaps");
        this.sound(ReefSoundEvents.JELLY_BLOCK_BOUNCE, "Jelly Block bounces");
        this.sound(ReefSoundEvents.CLAW_DISC, "ValiantEnvoy - Claw");

        this.entityVariant(ReefEntities.ANGELFISH, "banded");
        this.entityVariant(ReefEntities.ANGELFISH, "bluering");
        this.entityVariant(ReefEntities.ANGELFISH, "gray");
        this.entityVariant(ReefEntities.ANGELFISH, "guinean");
        this.entityVariant(ReefEntities.ANGELFISH, "old_woman");
        this.entityVariant(ReefEntities.ANGELFISH, "queen");
        this.entityVariant(ReefEntities.ANGELFISH, "queensland_yellowtail");
        this.entityVariant(ReefEntities.ANGELFISH, "semicircle");
        this.entityVariant(ReefEntities.ANGELFISH, "yellowband");
        this.entityVariant(ReefEntities.ANGELFISH, "clarion");
        this.entityVariant(ReefEntities.ANGELFISH, "emperor");
        this.entityVariant(ReefEntities.ANGELFISH, "french");
        this.entityVariant(ReefEntities.ANGELFISH, "majestic");
        this.entityVariant(ReefEntities.ANGELFISH, "king");
        this.entityVariant(ReefEntities.ANGELFISH, "rock_beauty");
        this.entityVariant(ReefEntities.ANGELFISH, "blue_queen");

        this.entityVariant(ReefEntities.ARROW_CRAB, "red");
        this.entityVariant(ReefEntities.ARROW_CRAB, "yellowline");

        this.entityVariant(ReefEntities.BASSLET, "blackcap");
        this.entityVariant(ReefEntities.BASSLET, "brazilian");
        this.entityVariant(ReefEntities.BASSLET, "fairy");
        this.entityVariant(ReefEntities.BASSLET, "accessor");
        this.entityVariant(ReefEntities.BASSLET, "midnight");
        this.entityVariant(ReefEntities.BASSLET, "swissguard");
        this.entityVariant(ReefEntities.BASSLET, "yellow_scissortail");
        this.entityVariant(ReefEntities.BASSLET, "candy");
        this.entityVariant(ReefEntities.BASSLET, "gold");
        this.entityVariant(ReefEntities.BASSLET, "gilded");

        this.entityVariant(ReefEntities.BILLFISH, "sailfish");
        this.entityVariant(ReefEntities.BILLFISH, "swordfish");

        this.entityVariant(ReefEntities.BOXFISH, "spotted");
        this.entityVariant(ReefEntities.BOXFISH, "white_barred");
        this.entityVariant(ReefEntities.BOXFISH, "whitleys", "Whitley's");
        this.entityVariant(ReefEntities.BOXFISH, "yellow");
        this.entityVariant(ReefEntities.BOXFISH, "bluetail");
        this.entityVariant(ReefEntities.BOXFISH, "longhorn");
        this.entityVariant(ReefEntities.BOXFISH, "white_spotted");

        this.entityVariant(ReefEntities.BUTTERFLYFISH, "banner");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "bluecheek");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "copperband");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "foureye");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "longnose");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "spotfin");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "threadfin");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "african");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "arabic");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "colorful_blue_botch");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "hooded");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "lined");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "marginated");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "mertensii");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "mullers", "Muller's");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "ornate");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "pyramid");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "saddleback");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "six_spined");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "thompsons", "Thompson's");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "easter_island");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "eritrean");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "red_sea");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "teardrop");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "vagabond");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "wrought_iron");
        this.entityVariant(ReefEntities.BUTTERFLYFISH, "dark_longnose");

        this.entityVariant(ReefEntities.CLOWNFISH, "allard");
        this.entityVariant(ReefEntities.CLOWNFISH, "black_and_white", "Black and White");
        this.entityVariant(ReefEntities.CLOWNFISH, "clarks", "Clark's");
        this.entityVariant(ReefEntities.CLOWNFISH, "madagascar");
        this.entityVariant(ReefEntities.CLOWNFISH, "maroon");
        this.entityVariant(ReefEntities.CLOWNFISH, "ocellaris");
        this.entityVariant(ReefEntities.CLOWNFISH, "pink_skunk");
        this.entityVariant(ReefEntities.CLOWNFISH, "red_saddleback");
        this.entityVariant(ReefEntities.CLOWNFISH, "tomato");
        this.entityVariant(ReefEntities.CLOWNFISH, "bluestrain");
        this.entityVariant(ReefEntities.CLOWNFISH, "mocha");
        this.entityVariant(ReefEntities.CLOWNFISH, "oman");
        this.entityVariant(ReefEntities.CLOWNFISH, "snowstorm");
        this.entityVariant(ReefEntities.CLOWNFISH, "yellow_clarks", "Yellow Clark's");
        this.entityVariant(ReefEntities.CLOWNFISH, "blizzard");
        this.entityVariant(ReefEntities.CLOWNFISH, "naked");
        this.entityVariant(ReefEntities.CLOWNFISH, "orange_skunk");
        this.entityVariant(ReefEntities.CLOWNFISH, "whitesnout");
        this.entityVariant(ReefEntities.CLOWNFISH, "domino");
        this.entityVariant(ReefEntities.CLOWNFISH, "gold_nugget");

        this.entityVariant(ReefEntities.CRAB, "blue");
        this.entityVariant(ReefEntities.CRAB, "ghost");
        this.entityVariant(ReefEntities.CRAB, "sally_lightfoot");
        this.entityVariant(ReefEntities.CRAB, "vampire");
        this.entityVariant(ReefEntities.CRAB, "purple_shore");
        this.entityVariant(ReefEntities.CRAB, "emerald");
        this.entityVariant(ReefEntities.CRAB, "red_ghost");
        this.entityVariant(ReefEntities.CRAB, "candy");

        this.entityVariant(ReefEntities.DAMSELFISH, "azure");
        this.entityVariant(ReefEntities.DAMSELFISH, "barrier_reef");
        this.entityVariant(ReefEntities.DAMSELFISH, "beaugregory");
        this.entityVariant(ReefEntities.DAMSELFISH, "bicolor");
        this.entityVariant(ReefEntities.DAMSELFISH, "blue");
        this.entityVariant(ReefEntities.DAMSELFISH, "bluefin");
        this.entityVariant(ReefEntities.DAMSELFISH, "brown");
        this.entityVariant(ReefEntities.DAMSELFISH, "canary");
        this.entityVariant(ReefEntities.DAMSELFISH, "chinese_scissortail");
        this.entityVariant(ReefEntities.DAMSELFISH, "domino");
        this.entityVariant(ReefEntities.DAMSELFISH, "green");
        this.entityVariant(ReefEntities.DAMSELFISH, "indigo");
        this.entityVariant(ReefEntities.DAMSELFISH, "jewel");
        this.entityVariant(ReefEntities.DAMSELFISH, "sergeant_major");
        this.entityVariant(ReefEntities.DAMSELFISH, "scissortail_sergeant_major");
        this.entityVariant(ReefEntities.DAMSELFISH, "sunshine");
        this.entityVariant(ReefEntities.DAMSELFISH, "three_stripe");
        this.entityVariant(ReefEntities.DAMSELFISH, "two_tone");

        this.entityVariant(() -> EntityType.DOLPHIN, "beluga");
        this.entityVariant(() -> EntityType.DOLPHIN, "boto");
        this.entityVariant(() -> EntityType.DOLPHIN, "bottlenose");
        this.entityVariant(() -> EntityType.DOLPHIN, "chilean");
        this.entityVariant(() -> EntityType.DOLPHIN, "common");
        this.entityVariant(() -> EntityType.DOLPHIN, "frasers", "Fraser's");
        this.entityVariant(() -> EntityType.DOLPHIN, "hourglass");
        this.entityVariant(() -> EntityType.DOLPHIN, "spinner");
        this.entityVariant(() -> EntityType.DOLPHIN, "spotted");
    }

    @Override
    public String getName() {
        return  RainbowReef.MOD_ID + " Languages: en_us";
    }

    private void forBlocks(Supplier<? extends Block> block) {
        this.addBlock(block, createTranslation(Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block.get())).getPath()));
    }

    private void forItems(Supplier<? extends Item> item) {
        this.addItem(item, createTranslation(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get())).getPath()));
    }

    private void forEntity(Supplier<? extends EntityType<?>> entity) {
        this.addEntityType(entity, createTranslation(Objects.requireNonNull(BuiltInRegistries.ENTITY_TYPE.getKey(entity.get())).getPath()));
    }

    public void entityVariant(Supplier<? extends EntityType<?>> key, String variantName, String name) {
        this.add(key.get() + ".variant_" + variantName, name);
    }

    public void entityVariant(Supplier<? extends EntityType<?>> key, String variantName) {
        this.add(key.get() + ".variant_" + variantName, createTranslation(variantName));
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

    public static String createTranslation(String path) {
        final StringBuilder builder = new StringBuilder();

        for (String part : path.split("_")) {
            if (!builder.isEmpty()) {
                builder.append(" ");
            }
            builder.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1));
        }
        return builder.toString();
    }
}
