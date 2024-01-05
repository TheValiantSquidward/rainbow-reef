package net.thevaliantsquidward.rainbowreef.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.HoneyBottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.ModEntities;
import net.thevaliantsquidward.rainbowreef.item.custom.CleansingSnackItem;
import net.thevaliantsquidward.rainbowreef.item.custom.ItemModFishBucket;
import net.thevaliantsquidward.rainbowreef.item.custom.ParrotfishPunchItem;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RainbowReef.MOD_ID);

    public static Item.Properties drinkItem() {
        return new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16);
    }

    public static Item.Properties soupItem() {
        return new Item.Properties().craftRemainder(Items.BOWL).stacksTo(8);
    }
    public static final RegistryObject<Item> GOBY_SPAWN_EGG = ITEMS.register("goby_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GOBY, 0xffffff, 0xdb3f1f, new Item.Properties()));

    public static final RegistryObject<Item> RAY_SPAWN_EGG = ITEMS.register("ray_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.RAY, 0x150a11, 0xd4d4e0, new Item.Properties()));

    public static final RegistryObject<Item> PIPEFISH_SPAWN_EGG = ITEMS.register("pipefish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PIPEFISH, 0x7c7f16, 0x3c3112, new Item.Properties()));


    public static final RegistryObject<Item> TANG_SPAWN_EGG = ITEMS.register("tang_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TANG, 0x445bca, 0xefb032, new Item.Properties()));

    public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = ITEMS.register("seahorse_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SEAHORSE, 0xd6d126, 0x7a8e1e, new Item.Properties()));

    public static final RegistryObject<Item> BOXFISH_SPAWN_EGG = ITEMS.register("boxfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BOXFISH, 0xfeda47, 0x62260a, new Item.Properties()));

    public static final RegistryObject<Item> PARROTFISH_SPAWN_EGG = ITEMS.register("parrotfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PARROTFISH, 0x3998e7, 0x7351ff, new Item.Properties()));


    public static final RegistryObject<Item> DWARF_ANGEL_SPAWN_EGG = ITEMS.register("dwarf_angelfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DWARFANGEL, 0xfed638, 0x294cc7, new Item.Properties()));


    public static final RegistryObject<Item> SMALL_SHARK_SPAWN_EGG = ITEMS.register("small_shark_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SMALL_SHARK, 0xe0a33b, 0xa35a1d, new Item.Properties()));

    public static final RegistryObject<Item> CLOWNFISH_SPAWN_EGG = ITEMS.register("clownfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CLOWNFISH, 0xe55500, 0xe69d7d, new Item.Properties()));

    public static final RegistryObject<Item> BASSLET_SPAWN_EGG = ITEMS.register("basslet_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BASSLET, 0xcb28f9, 0xf9b628, new Item.Properties()));

    public static final RegistryObject<Item> BUTTERFISH_SPAWN_EGG = ITEMS.register("butterflyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.BUTTERFISH, 0xdb8f1a, 0xfffdf6, new Item.Properties()));

    public static final RegistryObject<Item> HOGFISH_SPAWN_EGG = ITEMS.register("hogfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.HOGFISH, 0xca2418, 0xf3bc15, new Item.Properties()));

    public static final RegistryObject<Item> GOBY_BUCKET = ITEMS.register("goby_bucket", () -> new ItemModFishBucket(ModEntities.GOBY, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> PIPEFISH_BUCKET = ITEMS.register("pipefish_bucket", () -> new ItemModFishBucket(ModEntities.PIPEFISH, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> DWARF_ANGELFISH_BUCKET = ITEMS.register("dwarf_angelfish_bucket", () -> new ItemModFishBucket(ModEntities.DWARFANGEL, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> TANG_BUCKET = ITEMS.register("tang_bucket", () -> new ItemModFishBucket(ModEntities.TANG, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> BASSLET_BUCKET = ITEMS.register("basslet_bucket", () -> new ItemModFishBucket(ModEntities.BASSLET, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> CLOWNFISH_BUCKET = ITEMS.register("clownfish_bucket", () -> new ItemModFishBucket(ModEntities.CLOWNFISH, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> BOXFISH_BUCKET = ITEMS.register("boxfish_bucket", () -> new ItemModFishBucket(ModEntities.BOXFISH, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> BUTTERFISH_BUCKET = ITEMS.register("butterflyfish_bucket", () -> new ItemModFishBucket(ModEntities.BUTTERFISH, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> SHARK_BUCKET = ITEMS.register("smallshark_bucket", () -> new ItemModFishBucket(ModEntities.SMALL_SHARK, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new ItemModFishBucket(ModEntities.SEAHORSE, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> HOGFISH_BUCKET = ITEMS.register("hogfish_bucket", () -> new ItemModFishBucket(ModEntities.HOGFISH, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> RAW_GOBY = ITEMS.register("raw_goby", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_TANG = ITEMS.register("raw_tang", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_HOGFISH = ITEMS.register("raw_hogfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_BOXFISH = ITEMS.register("raw_boxfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(0.4F).meat().effect(new MobEffectInstance(MobEffects.WITHER, 140, 2), 1F).build())));

    public static final RegistryObject<Item> RAW_CLOWNFISH = ITEMS.register("raw_clownfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_BUTTERFISH = ITEMS.register("raw_butterflyfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_SEAHORSE = ITEMS.register("raw_seahorse", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_BASSLET = ITEMS.register("raw_basslet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_PIPEFISH = ITEMS.register("raw_pipefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_PARROTFISH = ITEMS.register("raw_parrotfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> PARROTFISH_BUCKET = ITEMS.register("parrotfish_bucket", () -> new ItemModFishBucket(ModEntities.PARROTFISH, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> SPOTTED_EAGLE_RAY_BUCKET = ITEMS.register("spotted_eagle_ray_bucket", () -> new ItemModFishBucket(ModEntities.RAY, Fluids.WATER, new Item.Properties()));

    public static final RegistryObject<Item> RAW_DWARF_ANGELFISH = ITEMS.register("raw_dwarf_angelfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> GOBY_GUMMY = ITEMS.register("goby_gummy", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(2).saturationMod(1.6F).fast().build())));

    public static final RegistryObject<Item> BOXFISH_BREAD = ITEMS.register("boxfish_bread", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(9).saturationMod(1.8F).effect(new MobEffectInstance(MobEffects.WITHER, 140, 224), 0.01F).build())));

    public static final RegistryObject<Item> CLOWNFISH_CUPCAKE = ITEMS.register("clownfish_cupcake", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2F).effect(new MobEffectInstance(MobEffects.POISON, 200, 0), 1).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 2), 1F).build())));

    public static final RegistryObject<Item> DRIED_SEAHORSE = ITEMS.register("dried_seahorse", () -> new CleansingSnackItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().fast().alwaysEat().build())));

    public static final RegistryObject<Item> TANGY_SOUP = ITEMS.register("tangy_soup", () -> new BowlFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(10).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.GLOWING, 600, 1), 1.0F).build())));

    public static final RegistryObject<Item> PARROTFISH_PUNCH = ITEMS.register("parrotfish_punch", () -> new ParrotfishPunchItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationMod(1.2F).alwaysEat().effect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1), 1F).effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1F).build())));

    public static final RegistryObject<Item> DWARF_ANGELFISH_TARTS = ITEMS.register("dwarf_angelfish_tarts", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(1).saturationMod(1.6F).build())));

    public static final RegistryObject<Item> BASSLET_COOKIE = ITEMS.register("basslet_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(0).saturationMod(0.5F).build())));

    public static final RegistryObject<Item> BUTTERED_BUTTERFLYFISH_TOAST = ITEMS.register("buttered_butterflyfish", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(20).saturationMod(0F).build())));

    public static final RegistryObject<Item> HOGFISH_BACON = ITEMS.register("hogfish_bacon", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(8).saturationMod(1.2F).build())));

    public static final RegistryObject<Item> PIPEFISH_SUSHI = ITEMS.register("pipefish_sushi", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(0).saturationMod(0F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 1, 1), 1F).build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
