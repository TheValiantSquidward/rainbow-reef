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

        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "bicolor");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "black_nox");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "cherub");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "coral_beauty");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "flame");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "keyhole");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "lamarck");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "lemonpeel");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "masked");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "pearlscale");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "resplendent");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "yellow");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "japanese");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "multibar");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "yellowtail");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "blackspot");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "joculator");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "peppermint");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "orangepeel");
        this.entityVariant(ReefEntities.DWARF_ANGELFISH, "white_bicolor");

        this.entityVariant(ReefEntities.FROGFISH, "clown");
        this.entityVariant(ReefEntities.FROGFISH, "orange_ocellated");
        this.entityVariant(ReefEntities.FROGFISH, "pink_ocellated");
        this.entityVariant(ReefEntities.FROGFISH, "psychedelic");
        this.entityVariant(ReefEntities.FROGFISH, "red_longlure");
        this.entityVariant(ReefEntities.FROGFISH, "sargassum");
        this.entityVariant(ReefEntities.FROGFISH, "warty");
        this.entityVariant(ReefEntities.FROGFISH, "yellow_longlure");

        this.entityVariant(ReefEntities.FUSILIER, "dark_banded");
        this.entityVariant(ReefEntities.FUSILIER, "lunar");
        this.entityVariant(ReefEntities.FUSILIER, "redbelly_yellowtail");
        this.entityVariant(ReefEntities.FUSILIER, "striped");
        this.entityVariant(ReefEntities.FUSILIER, "yellow_and_blue_back", "Yellow and Blue Back");
        this.entityVariant(ReefEntities.FUSILIER, "yellowback");

        this.entityVariant(ReefEntities.GOBY, "black_ray");
        this.entityVariant(ReefEntities.GOBY, "blackfin");
        this.entityVariant(ReefEntities.GOBY, "bluestreak");
        this.entityVariant(ReefEntities.GOBY, "catalina");
        this.entityVariant(ReefEntities.GOBY, "fire");
        this.entityVariant(ReefEntities.GOBY, "helfrichi");
        this.entityVariant(ReefEntities.GOBY, "leopard_spotted");
        this.entityVariant(ReefEntities.GOBY, "mandarin");
        this.entityVariant(ReefEntities.GOBY, "yellow_clown");
        this.entityVariant(ReefEntities.GOBY, "yellow_watchman");
        this.entityVariant(ReefEntities.GOBY, "candycane");
        this.entityVariant(ReefEntities.GOBY, "neon_blue");
        this.entityVariant(ReefEntities.GOBY, "neon_yellow");
        this.entityVariant(ReefEntities.GOBY, "purple_fire");
        this.entityVariant(ReefEntities.GOBY, "dracula");
        this.entityVariant(ReefEntities.GOBY, "neon_hybrid");

        this.entityVariant(ReefEntities.HOGFISH, "cuban");
        this.entityVariant(ReefEntities.HOGFISH, "spanish");
        this.entityVariant(ReefEntities.HOGFISH, "coral");
        this.entityVariant(ReefEntities.HOGFISH, "lyretail");
        this.entityVariant(ReefEntities.HOGFISH, "peppermint");
        this.entityVariant(ReefEntities.HOGFISH, "blue_spanish");

        this.entityVariant(ReefEntities.JELLYFISH, "orange");
        this.entityVariant(ReefEntities.JELLYFISH, "pink");
        this.entityVariant(ReefEntities.JELLYFISH, "white");
        this.entityVariant(ReefEntities.JELLYFISH, "yellow");
        this.entityVariant(ReefEntities.JELLYFISH, "minty");
        this.entityVariant(ReefEntities.JELLYFISH, "muddy");
        this.entityVariant(ReefEntities.JELLYFISH, "azure");
        this.entityVariant(ReefEntities.JELLYFISH, "red");
        this.entityVariant(ReefEntities.JELLYFISH, "abyssal");

        this.entityVariant(ReefEntities.LARGE_SHARK, "bull");
        this.entityVariant(ReefEntities.LARGE_SHARK, "tiger");

        this.entityVariant(ReefEntities.LIONFISH, "red");
        this.entityVariant(ReefEntities.LIONFISH, "clearfin");

        this.entityVariant(ReefEntities.MAHI_MAHI, "mahi_mahi", "Mahi-mahi");

        this.entityVariant(ReefEntities.MAORI_WRASSE, "maori_wrasse");

        this.entityVariant(ReefEntities.MOORISH_IDOL, "zanclus");
        this.entityVariant(ReefEntities.MOORISH_IDOL, "silver");

        this.entityVariant(ReefEntities.PARROTFISH, "blue");
        this.entityVariant(ReefEntities.PARROTFISH, "humphead");
        this.entityVariant(ReefEntities.PARROTFISH, "midnight");
        this.entityVariant(ReefEntities.PARROTFISH, "rainbow");
        this.entityVariant(ReefEntities.PARROTFISH, "stoplight");
        this.entityVariant(ReefEntities.PARROTFISH, "yellowtail");
        this.entityVariant(ReefEntities.PARROTFISH, "mediterranean");
        this.entityVariant(ReefEntities.PARROTFISH, "princess");
        this.entityVariant(ReefEntities.PARROTFISH, "red");
        this.entityVariant(ReefEntities.PARROTFISH, "yellowband");
        this.entityVariant(ReefEntities.PARROTFISH, "blue_bumphead");
        this.entityVariant(ReefEntities.PARROTFISH, "obishime");

        this.entityVariant(ReefEntities.PIPEFISH, "blue_striped");
        this.entityVariant(ReefEntities.PIPEFISH, "green");
        this.entityVariant(ReefEntities.PIPEFISH, "orange_striped");
        this.entityVariant(ReefEntities.PIPEFISH, "multibanded");
        this.entityVariant(ReefEntities.PIPEFISH, "pink");
        this.entityVariant(ReefEntities.PIPEFISH, "janss", "Janss'");

        this.entityVariant(ReefEntities.RABBITFISH, "bicolor_foxface");
        this.entityVariant(ReefEntities.RABBITFISH, "black_foxface");
        this.entityVariant(ReefEntities.RABBITFISH, "foxface");
        this.entityVariant(ReefEntities.RABBITFISH, "magnificent_foxface");

        this.entityVariant(ReefEntities.RAY, "cownose");
        this.entityVariant(ReefEntities.RAY, "spotted");
        this.entityVariant(ReefEntities.RAY, "ornate");

        this.entityVariant(ReefEntities.SEAHORSE, "barbours", "Barbour's");
        this.entityVariant(ReefEntities.SEAHORSE, "common");
        this.entityVariant(ReefEntities.SEAHORSE, "west_african");
        this.entityVariant(ReefEntities.SEAHORSE, "big_belly", "Big-belly");
        this.entityVariant(ReefEntities.SEAHORSE, "tiger_tail");
        this.entityVariant(ReefEntities.SEAHORSE, "western_spiny");

        this.entityVariant(ReefEntities.SHARK, "blacktip_reef");
        this.entityVariant(ReefEntities.SHARK, "whitetip_reef");
        this.entityVariant(ReefEntities.SHARK, "lemon");

        this.entityVariant(ReefEntities.SMALL_SHARK, "epaulette");
        this.entityVariant(ReefEntities.SMALL_SHARK, "horned");
        this.entityVariant(ReefEntities.SMALL_SHARK, "nurse");
        this.entityVariant(ReefEntities.SMALL_SHARK, "zebra");
        this.entityVariant(ReefEntities.SMALL_SHARK, "pajama");
        this.entityVariant(ReefEntities.SMALL_SHARK, "port_jackson");
        this.entityVariant(ReefEntities.SMALL_SHARK, "albino");
        this.entityVariant(ReefEntities.SMALL_SHARK, "piebald_horned");

        this.entityVariant(ReefEntities.TANG, "achilles");
        this.entityVariant(ReefEntities.TANG, "atlantic_blue");
        this.entityVariant(ReefEntities.TANG, "blue");
        this.entityVariant(ReefEntities.TANG, "clown");
        this.entityVariant(ReefEntities.TANG, "convict");
        this.entityVariant(ReefEntities.TANG, "eyestripe");
        this.entityVariant(ReefEntities.TANG, "powder_blue");
        this.entityVariant(ReefEntities.TANG, "sailfin");
        this.entityVariant(ReefEntities.TANG, "scopas");
        this.entityVariant(ReefEntities.TANG, "yellow");
        this.entityVariant(ReefEntities.TANG, "chocolate");
        this.entityVariant(ReefEntities.TANG, "orangeband");
        this.entityVariant(ReefEntities.TANG, "purple");
        this.entityVariant(ReefEntities.TANG, "unicorn");
        this.entityVariant(ReefEntities.TANG, "white_cheek");
        this.entityVariant(ReefEntities.TANG, "bristletooth");
        this.entityVariant(ReefEntities.TANG, "black");
        this.entityVariant(ReefEntities.TANG, "black_surgeon");
        this.entityVariant(ReefEntities.TANG, "blonde_lipstick");
        this.entityVariant(ReefEntities.TANG, "zebra");
        this.entityVariant(ReefEntities.TANG, "gem");
        this.entityVariant(ReefEntities.TANG, "regal_blue");
        this.entityVariant(ReefEntities.TANG, "goth");
        this.entityVariant(ReefEntities.TANG, "green_spot");
        this.entityVariant(ReefEntities.TANG, "muddy");
        this.entityVariant(ReefEntities.TANG, "pastel_blue");
        this.entityVariant(ReefEntities.TANG, "pearly");
        this.entityVariant(ReefEntities.TANG, "penguin");
        this.entityVariant(ReefEntities.TANG, "powder_blue_hybrid");
        this.entityVariant(ReefEntities.TANG, "rusty");
        this.entityVariant(ReefEntities.TANG, "yellowbelly_blue");
        this.entityVariant(ReefEntities.TANG, "yellowstrike");

        this.entityVariant(ReefEntities.TRIGGERFISH, "black");
        this.entityVariant(ReefEntities.TRIGGERFISH, "blue");
        this.entityVariant(ReefEntities.TRIGGERFISH, "clown");
        this.entityVariant(ReefEntities.TRIGGERFISH, "picasso");
        this.entityVariant(ReefEntities.TRIGGERFISH, "queen");
        this.entityVariant(ReefEntities.TRIGGERFISH, "rough");

        this.entityVariant(ReefEntities.WRASSE, "blackear");
        this.entityVariant(ReefEntities.WRASSE, "bluehead");
        this.entityVariant(ReefEntities.WRASSE, "bluestreak_cleaner");
        this.entityVariant(ReefEntities.WRASSE, "cortez_rainbow");
        this.entityVariant(ReefEntities.WRASSE, "creole");
        this.entityVariant(ReefEntities.WRASSE, "greenbird");
        this.entityVariant(ReefEntities.WRASSE, "jansens", "Jansen's");
        this.entityVariant(ReefEntities.WRASSE, "orange_dotted");
        this.entityVariant(ReefEntities.WRASSE, "peacock");
        this.entityVariant(ReefEntities.WRASSE, "rainbow_mediterranean");
        this.entityVariant(ReefEntities.WRASSE, "sixbar");
        this.entityVariant(ReefEntities.WRASSE, "surge");
        this.entityVariant(ReefEntities.WRASSE, "yellowhead");
        this.entityVariant(ReefEntities.WRASSE, "yellowtail");
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
