package net.thevaliantsquidward.rainbowreef.datagen;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.RainbowReefTab;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Objects;
import java.util.function.Supplier;

public class ReefLanguageProvider extends LanguageProvider {

    public ReefLanguageProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), RainbowReef.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.addTab(RainbowReefTab.RAINBOW_REEF_TAB.get(), "Rainbow Reef");

        ReefBlocks.AUTO_TRANSLATE.forEach(this::forBlocks);
        ReefItems.AUTO_TRANSLATE.forEach(this::forItems);

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
        this.addItem(ReefItems.MOORISH_IDOL_BUCKET, "Bucket of Moorish Idol");
        this.addItem(ReefItems.PARROTFISH_BUCKET, "Bucket of Parrotfish");
        this.addItem(ReefItems.PIPEFISH_BUCKET, "Bucket of Pipefish");
        this.addItem(ReefItems.RAY_BUCKET, "Bucket of Ray");
        this.addItem(ReefItems.SEAHORSE_BUCKET, "Bucket of Seahorse");
        this.addItem(ReefItems.SMALL_SHARK_BUCKET, "Bucket of Small Shark");
        this.addItem(ReefItems.TANG_BUCKET, "Bucket of Tang");

        this.addItem(ReefItems.SURF_N_TURF, "Surf 'N' Turf");

        this.musicDisc(ReefItems.CLAW_DISC, "TheValiantSquidward - Claw");

        this.sound(ReefSoundEvents.FISH_IDLE, "Fish gurgles");
        this.sound(ReefSoundEvents.FISH_DEATH, "Fish dies");
        this.sound(ReefSoundEvents.FISH_HURT, "Fish hurts");
        this.sound(ReefSoundEvents.FISH_FLOP, "Fish flops");
        this.sound(ReefSoundEvents.FISH_JUMP, "Fish jumps");
    }

    @Override
    public String getName() {
        return  RainbowReef.MOD_ID + " Languages: en_us";
    }

    private void forBlocks(Supplier<? extends Block> block) {
        addBlock(block, ReefTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath()));
    }

    private void forItems(Supplier<? extends Item> item) {
        addItem(item, ReefTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item.get())).getPath()));
    }

    private void forEntity(Supplier<? extends EntityType<?>> entity) {
        addEntityType(entity, ReefTextUtils.createTranslation(Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(entity.get())).getPath()));
    }

    public void sound(Supplier<? extends SoundEvent> key, String subtitle){
        add("subtitles.rainbowreef." + key.get().getLocation().getPath(), subtitle);
    }

    private void addEnchantmentWithDesc(Enchantment enchantment, String description) {
        String name = ForgeRegistries.ENCHANTMENTS.getKey(enchantment).getPath();
        this.add(enchantment, formatEnchantment(name));
        this.add(enchantment.getDescriptionId() + ".desc", description);
    }

    private String formatEnchantment(String path) {
        return WordUtils.capitalizeFully(path.replace("_", " ")).replace("Of ", "of ");
    }

    public void potion(Supplier<? extends Potion> key, String name, String regName) {
        potions(key.get(), name, regName);
    }

    public void potions(Potion key, String name, String regName) {
        add("item.minecraft.potion.effect." + regName, "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + regName, "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + regName, "Lingering Potion of " + name);
        add("item.minecraft.tipped_arrow.effect." + regName, "Arrow of " + name);
    }

    protected void painting(String name, String author) {
        add("painting." + RainbowReef.MOD_ID + "." + name + ".title",  ReefTextUtils.createTranslation(name));
        add("painting." + RainbowReef.MOD_ID + "." + name + ".author",  author);
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

    protected void musicDisc(Supplier<? extends Item> item, String description) {
        String disc = item.get().getDescriptionId();
        add(disc, "Music Disc");
        add(disc + ".desc", description);
    }
}
