package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.items.*;

public class ReefItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RainbowReef.MOD_ID);

    public static final RegistryObject<Item> GOBY_SPAWN_EGG = ITEMS.register("goby_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.GOBY, 0xffffff, 0xdb3f1f, new Item.Properties()));

    public static final RegistryObject<Item> RAY_SPAWN_EGG = ITEMS.register("ray_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.RAY, 0x150a11, 0xd4d4e0, new Item.Properties()));

    public static final RegistryObject<Item> PIPEFISH_SPAWN_EGG = ITEMS.register("pipefish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.PIPEFISH, 0x7c7f16, 0x3c3112, new Item.Properties()));


    public static final RegistryObject<Item> TANG_SPAWN_EGG = ITEMS.register("tang_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.TANG, 0x445bca, 0xefb032, new Item.Properties()));

    public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = ITEMS.register("seahorse_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.SEAHORSE, 0xd6d126, 0x7a8e1e, new Item.Properties()));

    public static final RegistryObject<Item> BOXFISH_SPAWN_EGG = ITEMS.register("boxfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.BOXFISH, 0xfeda47, 0x62260a, new Item.Properties()));

    public static final RegistryObject<Item> PARROTFISH_SPAWN_EGG = ITEMS.register("parrotfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.PARROTFISH, 0x3998e7, 0x7351ff, new Item.Properties()));


    public static final RegistryObject<Item> DWARF_ANGEL_SPAWN_EGG = ITEMS.register("dwarf_angelfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.DWARF_ANGELFISH, 0xfed638, 0x294cc7, new Item.Properties()));


    public static final RegistryObject<Item> SMALL_SHARK_SPAWN_EGG = ITEMS.register("small_shark_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.SMALL_SHARK, 0xe0a33b, 0xa35a1d, new Item.Properties()));

    public static final RegistryObject<Item> SHARK_SPAWN_EGG = ITEMS.register("shark_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.SHARK, 0x686963, 0xb0a5a2, new Item.Properties()));

    public static final RegistryObject<Item> LARGE_SHARK_SPAWN_EGG = ITEMS.register("large_shark_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.LARGE_SHARK, 0x564637, 0xacaba3, new Item.Properties()));

    public static final RegistryObject<Item> CLOWNFISH_SPAWN_EGG = ITEMS.register("clownfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.CLOWNFISH, 0xe55500, 0xe69d7d, new Item.Properties()));

    public static final RegistryObject<Item> BASSLET_SPAWN_EGG = ITEMS.register("basslet_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.BASSLET, 0xcb28f9, 0xf9b628, new Item.Properties()));

    public static final RegistryObject<Item> BUTTERFISH_SPAWN_EGG = ITEMS.register("butterflyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.BUTTERFLYFISH, 0xdb8f1a, 0xfffdf6, new Item.Properties()));

    public static final RegistryObject<Item> HOGFISH_SPAWN_EGG = ITEMS.register("hogfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.HOGFISH, 0xca2418, 0xf3bc15, new Item.Properties()));

    public static final RegistryObject<Item> ANGELFISH_SPAWN_EGG = ITEMS.register("angelfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.ANGELFISH, 0x2e4284, 0xcfc24b, new Item.Properties()));

    public static final RegistryObject<Item> CRAB_SPAWN_EGG = ITEMS.register("crab_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.CRAB, 0x261f4e, 0xf78b21, new Item.Properties()));

    public static final RegistryObject<Item> ARROW_CRAB_SPAWN_EGG = ITEMS.register("arrow_crab_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.ARROW_CRAB, 0x4b3827, 0xd94b19, new Item.Properties()));

    public static final RegistryObject<Item> MOORISH_IDOL_SPAWN_EGG = ITEMS.register("moorish_idol_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.MOORISH_IDOL, 0x322b2f, 0xf2c447, new Item.Properties()));

    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.JELLYFISH, 0xf6b7dd, 0xef8298, new Item.Properties()));

    public static final RegistryObject<Item> BILLFISH_SPAWN_EGG = ITEMS.register("billfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.BILLFISH, 0x1750ca, 0xe9ce85, new Item.Properties()));

    public static final RegistryObject<Item> MAORI_WRASSE_SPAWN_EGG = ITEMS.register("maori_wrasse_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.MAORI_WRASSE, 0x1a7e89, 0x0d6c52, new Item.Properties()));

    public static final RegistryObject<Item> MAHI_MAHI_SPAWN_EGG = ITEMS.register("mahi_mahi_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.MAHI_MAHI, 0x4b922b, 0xdcd700, new Item.Properties()));

    public static final RegistryObject<Item> LIONFISH_SPAWN_EGG = ITEMS.register("lionfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.LIONFISH, 0x862a34, 0xd2d6dc, new Item.Properties()));

    public static final RegistryObject<Item> FROGFISH_SPAWN_EGG = ITEMS.register("frogfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.FROGFISH, 0xe4eef7, 0xbc4911, new Item.Properties()));

    public static final RegistryObject<Item> WRASSE_SPAWN_EGG = ITEMS.register("wrasse_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.WRASSE, 0xde608d, 0x1481bc, new Item.Properties()));

    public static final RegistryObject<Item> TRIGGERFISH_SPAWN_EGG = ITEMS.register("triggerfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.TRIGGERFISH, 0x121418, 0xaa8e23, new Item.Properties()));

    public static final RegistryObject<Item> DAMSELFISH_SPAWN_EGG = ITEMS.register("damselfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.DAMSELFISH, 0x15f578, 0x4ba2c4, new Item.Properties()));

    public static final RegistryObject<Item> RABBITFISH_SPAWN_EGG = ITEMS.register("rabbitfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ReefEntities.RABBITFISH, 0xe8c417, 0x282339, new Item.Properties()));

    public static final RegistryObject<Item> CLAW_DISC = ITEMS.register("claw_disc",
            () -> new RecordItem(2, ReefSoundEvents.CLAW_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2240));

    // Buckets
    public static final RegistryObject<Item> GOBY_BUCKET = ITEMS.register("goby_bucket", () -> new ReefFishBucketItem(ReefEntities.GOBY, new Item.Properties()));

    public static final RegistryObject<Item> ANGELFISH_BUCKET = ITEMS.register("angelfish_bucket", () -> new ReefFishBucketItem(ReefEntities.ANGELFISH, new Item.Properties()));

    public static final RegistryObject<Item> CRAB_BUCKET = ITEMS.register("crab_bucket", () -> new ReefFishBucketItem(ReefEntities.CRAB, new Item.Properties()));

    public static final RegistryObject<Item> PIPEFISH_BUCKET = ITEMS.register("pipefish_bucket", () -> new ReefFishBucketItem(ReefEntities.PIPEFISH, new Item.Properties()));

    public static final RegistryObject<Item> DWARF_ANGELFISH_BUCKET = ITEMS.register("dwarf_angelfish_bucket", () -> new ReefFishBucketItem(ReefEntities.DWARF_ANGELFISH, new Item.Properties()));

    public static final RegistryObject<Item> TANG_BUCKET = ITEMS.register("tang_bucket", () -> new ReefFishBucketItem(ReefEntities.TANG, new Item.Properties()));

    public static final RegistryObject<Item> BASSLET_BUCKET = ITEMS.register("basslet_bucket", () -> new ReefFishBucketItem(ReefEntities.BASSLET, new Item.Properties()));

    public static final RegistryObject<Item> CLOWNFISH_BUCKET = ITEMS.register("clownfish_bucket", () -> new ReefFishBucketItem(ReefEntities.CLOWNFISH, new Item.Properties()));

    public static final RegistryObject<Item> BOXFISH_BUCKET = ITEMS.register("boxfish_bucket", () -> new ReefFishBucketItem(ReefEntities.BOXFISH, new Item.Properties()));

    public static final RegistryObject<Item> ARROW_CRAB_BUCKET = ITEMS.register("arrow_crab_bucket", () -> new ReefFishBucketItem(ReefEntities.ARROW_CRAB, new Item.Properties()));

    public static final RegistryObject<Item> BUTTERFISH_BUCKET = ITEMS.register("butterflyfish_bucket", () -> new ReefFishBucketItem(ReefEntities.BUTTERFLYFISH, new Item.Properties()));

    public static final RegistryObject<Item> SMALL_SHARK_BUCKET = ITEMS.register("small_shark_bucket", () -> new ReefFishBucketItem(ReefEntities.SMALL_SHARK, new Item.Properties()));

    public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new ReefFishBucketItem(ReefEntities.SEAHORSE, new Item.Properties()));

    public static final RegistryObject<Item> HOGFISH_BUCKET = ITEMS.register("hogfish_bucket", () -> new ReefFishBucketItem(ReefEntities.HOGFISH, new Item.Properties()));

    public static final RegistryObject<Item> JELLYFISH_BUCKET = ITEMS.register("jellyfish_bucket", () -> new ReefFishBucketItem(ReefEntities.JELLYFISH, new Item.Properties()));

    public static final RegistryObject<Item> PARROTFISH_BUCKET = ITEMS.register("parrotfish_bucket", () -> new ReefFishBucketItem(ReefEntities.PARROTFISH, new Item.Properties()));

    public static final RegistryObject<Item> MOORISH_IDOL_BUCKET = ITEMS.register("moorish_idol_bucket", () -> new ReefFishBucketItem(ReefEntities.MOORISH_IDOL, new Item.Properties()));

    public static final RegistryObject<Item> RAY_BUCKET = ITEMS.register("ray_bucket", () -> new ReefFishBucketItem(ReefEntities.RAY, new Item.Properties()));

    // Raw
    public static final RegistryObject<Item> RAW_GOBY = ITEMS.register("raw_goby", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_RAY = ITEMS.register("raw_ray", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).meat().build())));

    public static final RegistryObject<Item> RAW_SMALL_SHARK = ITEMS.register("raw_small_shark", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).meat().build())));

    public static final RegistryObject<Item> RAW_ANGELFISH = ITEMS.register("raw_angelfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_MOORISH_IDOL = ITEMS.register("raw_moorish_idol", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_CRAB_MEAT = ITEMS.register("crab_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_ARROW_CRAB = ITEMS.register("arrow_crab", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_TANG = ITEMS.register("raw_tang", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_HOGFISH = ITEMS.register("raw_hogfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_BOXFISH = ITEMS.register("raw_boxfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(0.4F).meat().effect(new MobEffectInstance(MobEffects.WITHER, 140, 2), 1F).build())));

    public static final RegistryObject<Item> RAW_CLOWNFISH = ITEMS.register("raw_clownfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_BUTTERFISH = ITEMS.register("raw_butterflyfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_SEAHORSE = ITEMS.register("raw_seahorse", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_BASSLET = ITEMS.register("raw_basslet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_PIPEFISH = ITEMS.register("raw_pipefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> RAW_PARROTFISH = ITEMS.register("raw_parrotfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).meat().build())));

    public static final RegistryObject<Item> RAW_DWARF_ANGELFISH = ITEMS.register("raw_dwarf_angelfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    public static final RegistryObject<Item> GLOB_OF_JELLY = ITEMS.register("glob_of_jelly", () -> new Item(new Item.Properties()));

    // Meals
    public static final RegistryObject<Item> BASSLET_COOKIE = ITEMS.register("basslet_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));

    public static final RegistryObject<Item> BOXFISH_BREAD = ITEMS.register("boxfish_bread", () -> new BoxfishBreadItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.4F).build())));

    public static final RegistryObject<Item> BUTTERED_BUTTERFLYFISH_TOAST = ITEMS.register("buttered_butterflyfish", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(8).saturationMod(0F).build())));

    public static final RegistryObject<Item> CRAB_CAKE = ITEMS.register("crabcake", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).meat().build())));

    public static final RegistryObject<Item> CLOWNFISH_CUPCAKE = ITEMS.register("clownfish_cupcake", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(1F).build())));

    public static final RegistryObject<Item> CHOCO_RAY_MUFFIN = ITEMS.register("choco_ray_muffin", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(7).saturationMod(0.3F).build())));

    public static final RegistryObject<Item> COOKED_HOGFISH_BACON = ITEMS.register("cooked_hogfish_bacon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.4F).build())));

    public static final RegistryObject<Item> DRIED_SEAHORSE = ITEMS.register("dried_seahorse", () -> new CleansingSnackItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().fast().alwaysEat().build())));

    public static final RegistryObject<Item> DWARF_ANGELFISH_TARTS = ITEMS.register("dwarf_angelfish_tarts", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5F).build())));

    public static final RegistryObject<Item> GOBY_GUMMY = ITEMS.register("goby_gummy", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).fast().build())));

    public static final RegistryObject<Item> JELLY_TART = ITEMS.register("jelly_tart", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(0.5F).build())));

    public static final RegistryObject<Item> JELLY_SANDWICH = ITEMS.register("jelly_sandwich", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(8).saturationMod(0.5F).build())));

    public static final RegistryObject<Item> JELLYFISH_JELLY = ITEMS.register("jellyfish_jelly", () -> new JellyBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5F).alwaysEat().build())));

    public static final RegistryObject<Item> HOGFISH_BACON = ITEMS.register("hogfish_bacon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5F).build())));

    public static final RegistryObject<Item> IDOL_COOKIE = ITEMS.register("idol_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));

    public static final RegistryObject<Item> PARROTFISH_PUNCH = ITEMS.register("parrotfish_punch", () -> new ParrotfishPunchItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationMod(1.2F).alwaysEat().effect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1), 1F).effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1F).build())));

    public static final RegistryObject<Item> PIPEFISH_SUSHI = ITEMS.register("pipefish_sushi", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(0).saturationMod(0F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 1, 1), 1F).build())));

    public static final RegistryObject<Item> ROASTED_CRAB_MEAT = ITEMS.register("roasted_crab_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).meat().build())));

    public static final RegistryObject<Item> SHARKBITE_SALAD = ITEMS.register("sharkbite_salad", () -> new BowlFoodItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).build())));

    public static final RegistryObject<Item> TANGY_SOUP = ITEMS.register("tangy_soup", () -> new BowlFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(12).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.GLOWING, 600, 1), 1.0F).build())));

    public static final RegistryObject<Item> ULTRA_BACON_SANDWICH = ITEMS.register("bacon_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(14).saturationMod(0.7F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 0), 1.0F).build())));

    // Special Meals

    public static final RegistryObject<Item> ROCKFISH_CANDY = ITEMS.register("rockfish_candy", () -> new StickFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).fast().build())));

    public static final RegistryObject<Item> FORBIDDEN_SOUP = ITEMS.register("forbidden_soup", () -> new BowlFoodItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.35F).build())));

    public static final RegistryObject<Item> SWEET_TOOTH_SEABURGER = ITEMS.register("sweet_tooth_seaburger", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F).build())));

    public static final RegistryObject<Item> HAWAIIAN_BARBEQUE = ITEMS.register("hawaiian_barbeque", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(9).saturationMod(0.7F).effect(new MobEffectInstance(MobEffects.SATURATION, 600, 0), 1.0F).build())));

    public static final RegistryObject<Item> TROPICAL_FISHSTICKS = ITEMS.register("tropical_fishsticks", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).effect(new MobEffectInstance(MobEffects.JUMP, 600, 1), 0.7F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 1), 0.7F).build())));

    public static final RegistryObject<Item> SURF_N_TURF = ITEMS.register("surf_n_turf", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.7F).build())));

    public static final RegistryObject<Item> SEASUGAR_SORBET = ITEMS.register("seasugar_sorbet", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(7).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1.0F).build())));

    // Corals
    public static final RegistryObject<Item> BARREL_CORAL_FAN = ITEMS.register("barrel_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.BARREL_CORAL_FAN.get(), ReefBlocks.BARREL_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_BARREL_CORAL_FAN = ITEMS.register("dead_barrel_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_BARREL_CORAL_FAN.get(), ReefBlocks.DEAD_BARREL_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> CHIMNEY_CORAL_FAN = ITEMS.register("chimney_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.CHIMNEY_CORAL_FAN.get(), ReefBlocks.CHIMNEY_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_CHIMNEY_CORAL_FAN = ITEMS.register("dead_chimney_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_CHIMNEY_CORAL_FAN.get(), ReefBlocks.DEAD_CHIMNEY_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> SHELF_CORAL_FAN = ITEMS.register("shelf_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.SHELF_CORAL_FAN.get(), ReefBlocks.SHELF_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_SHELF_CORAL_FAN = ITEMS.register("dead_shelf_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_SHELF_CORAL_FAN.get(), ReefBlocks.DEAD_SHELF_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> HAND_CORAL_FAN = ITEMS.register("hand_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.HAND_CORAL_FAN.get(), ReefBlocks.HAND_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_HAND_CORAL_FAN = ITEMS.register("dead_hand_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_HAND_CORAL_FAN.get(), ReefBlocks.DEAD_HAND_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> TOWER_CORAL_FAN = ITEMS.register("tower_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.TOWER_CORAL_FAN.get(), ReefBlocks.TOWER_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_TOWER_CORAL_FAN = ITEMS.register("dead_tower_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_TOWER_CORAL_FAN.get(), ReefBlocks.DEAD_TOWER_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> ROSE_CORAL_FAN = ITEMS.register("rose_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.ROSE_CORAL_FAN.get(), ReefBlocks.ROSE_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_ROSE_CORAL_FAN = ITEMS.register("dead_rose_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_ROSE_CORAL_FAN.get(), ReefBlocks.DEAD_ROSE_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> FLOWER_CORAL_FAN = ITEMS.register("flower_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.FLOWER_CORAL_FAN.get(), ReefBlocks.FLOWER_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_FLOWER_CORAL_FAN = ITEMS.register("dead_flower_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_FLOWER_CORAL_FAN.get(), ReefBlocks.DEAD_FLOWER_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> RING_CORAL_FAN = ITEMS.register("ring_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.RING_CORAL_FAN.get(), ReefBlocks.RING_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_RING_CORAL_FAN = ITEMS.register("dead_ring_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_RING_CORAL_FAN.get(), ReefBlocks.DEAD_RING_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> BUSH_CORAL_FAN = ITEMS.register("bush_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.BUSH_CORAL_FAN.get(), ReefBlocks.BUSH_CORAL_WALL_FAN.get(), new Item.Properties(), Direction.DOWN));

    public static final RegistryObject<Item> DEAD_BUSH_CORAL_FAN = ITEMS.register("dead_bush_coral_fan",
            () -> new StandingAndWallBlockItem(ReefBlocks.DEAD_BUSH_CORAL_FAN.get(), ReefBlocks.DEAD_BUSH_CORAL_WALL_FAN.get(),
                    new Item.Properties(), Direction.DOWN));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}