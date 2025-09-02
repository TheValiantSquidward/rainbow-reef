package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ReefItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RainbowReef.MOD_ID);
    public static List<RegistryObject<? extends Item>> AUTO_TRANSLATE = new ArrayList<>();

    // angelfish
    public static final RegistryObject<Item> ANGELFISH_SPAWN_EGG = registerSpawnEggItem("angelfish", ReefEntities.ANGELFISH, 0x2e4284, 0xcfc24b);
    public static final RegistryObject<Item> ANGELFISH_BUCKET = registerMobBucketItem("angelfish", ReefEntities.ANGELFISH);
    public static final RegistryObject<Item> RAW_ANGELFISH = registerItem("angelfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    // arrow crab
    public static final RegistryObject<Item> ARROW_CRAB_SPAWN_EGG = registerSpawnEggItem("arrow_crab", ReefEntities.ARROW_CRAB, 0x4b3827, 0xd94b19);
    public static final RegistryObject<Item> ARROW_CRAB_BUCKET = registerMobBucketItem("arrow_crab", ReefEntities.ARROW_CRAB);
    public static final RegistryObject<Item> RAW_ARROW_CRAB = registerItem("arrow_crab", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));

    // basslet
    public static final RegistryObject<Item> BASSLET_SPAWN_EGG = registerSpawnEggItem("basslet", ReefEntities.BASSLET, 0xcb28f9, 0xf9b628);
    public static final RegistryObject<Item> BASSLET_BUCKET = registerMobBucketItem("basslet", ReefEntities.BASSLET);
    public static final RegistryObject<Item> RAW_BASSLET = registerItem("basslet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> BASSLET_COOKIE = registerItem("basslet_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));

    // billfish
    public static final RegistryObject<Item> BILLFISH_SPAWN_EGG = registerSpawnEggItem("billfish", ReefEntities.BILLFISH, 0x1750ca, 0xe9ce85);

    // boxfish
    public static final RegistryObject<Item> BOXFISH_SPAWN_EGG = registerSpawnEggItem("boxfish", ReefEntities.BOXFISH, 0xfeda47, 0x62260a);
    public static final RegistryObject<Item> BOXFISH_BUCKET = registerMobBucketItem("boxfish", ReefEntities.BOXFISH);
    public static final RegistryObject<Item> RAW_BOXFISH = registerItem("boxfish", () -> new Item(foodItem(ReefFoodValues.RAW_BOXFISH)));
    public static final RegistryObject<Item> BOXFISH_BREAD = registerItem("boxfish_bread", () -> new BoxfishBreadItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.4F).build())));

    // butterflyfish
    public static final RegistryObject<Item> BUTTERFLYFISH_SPAWN_EGG = registerSpawnEggItem("butterflyfish", ReefEntities.BUTTERFLYFISH, 0xdb8f1a, 0xfffdf6);
    public static final RegistryObject<Item> BUTTERFLYFISH_BUCKET = registerMobBucketItem("butterflyfish", ReefEntities.BUTTERFLYFISH);
    public static final RegistryObject<Item> RAW_BUTTERFLYFISH = registerItem("butterflyfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> BUTTERED_TOAST = registerItem("buttered_toast", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(8).saturationMod(0F).build())));

    // clownfish
    public static final RegistryObject<Item> CLOWNFISH_SPAWN_EGG = registerSpawnEggItem("clownfish", ReefEntities.CLOWNFISH, 0xe55500, 0xe69d7d);
    public static final RegistryObject<Item> CLOWNFISH_BUCKET = registerMobBucketItem("clownfish", ReefEntities.CLOWNFISH);
    public static final RegistryObject<Item> RAW_CLOWNFISH = registerItem("clownfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> CLOWNFISH_CUPCAKE = registerItem("clownfish_cupcake", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(1F).build())));

    // crab
    public static final RegistryObject<Item> CRAB_SPAWN_EGG = registerSpawnEggItem("crab", ReefEntities.CRAB, 0x261f4e, 0xf78b21);
    public static final RegistryObject<Item> CRAB_BUCKET = registerMobBucketItem("crab", ReefEntities.CRAB);
    public static final RegistryObject<Item> RAW_CRAB = registerItem("crab", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> ROASTED_CRAB = registerItem("roasted_crab", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).meat().build())));
    public static final RegistryObject<Item> CRAB_CAKE = registerItem("crab_cake", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.5F).meat().build())));

    // damselfish
    public static final RegistryObject<Item> DAMSELFISH_SPAWN_EGG = registerSpawnEggItem("damselfish", ReefEntities.DAMSELFISH, 0x15f578, 0x4ba2c4);

    // dwarf angelfish
    public static final RegistryObject<Item> DWARF_ANGELFISH_SPAWN_EGG = registerSpawnEggItem("dwarf_angelfish", ReefEntities.DWARF_ANGELFISH, 0xfed638, 0x294cc7);
    public static final RegistryObject<Item> DWARF_ANGELFISH_BUCKET = registerMobBucketItem("dwarf_angelfish", ReefEntities.DWARF_ANGELFISH);
    public static final RegistryObject<Item> RAW_DWARF_ANGELFISH = registerItem("dwarf_angelfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> DWARF_ANGELFISH_TARTS = registerItem("dwarf_angelfish_tarts", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5F).build())));

    // frogfish
    public static final RegistryObject<Item> FROGFISH_SPAWN_EGG = registerSpawnEggItem("frogfish", ReefEntities.FROGFISH, 0xe4eef7, 0xbc4911);

    // goby
    public static final RegistryObject<Item> GOBY_SPAWN_EGG = registerSpawnEggItem("goby", ReefEntities.GOBY, 0xffffff, 0xdb3f1f);
    public static final RegistryObject<Item> GOBY_BUCKET = registerMobBucketItem("goby", ReefEntities.GOBY);
    public static final RegistryObject<Item> RAW_GOBY = registerItem("goby", () -> new Item(foodItem(ReefFoodValues.RAW_GOBY)));
    public static final RegistryObject<Item> GOBY_GUMMY = registerItem("goby_gummy", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5F).fast().build())));

    // hogfish
    public static final RegistryObject<Item> HOGFISH_SPAWN_EGG = registerSpawnEggItem("hogfish", ReefEntities.HOGFISH, 0xca2418, 0xf3bc15);
    public static final RegistryObject<Item> HOGFISH_BUCKET = registerMobBucketItem("hogfish", ReefEntities.HOGFISH);
    public static final RegistryObject<Item> RAW_HOGFISH = registerItem("hogfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> HOGFISH_BACON = registerItem("hogfish_bacon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5F).build())));
    public static final RegistryObject<Item> COOKED_HOGFISH_BACON = registerItem("cooked_hogfish_bacon", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationMod(0.4F).build())));
    public static final RegistryObject<Item> ULTRA_BACON_SANDWICH = registerItem("ultra_bacon_sandwich", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(14).saturationMod(0.7F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 0), 1.0F).build())));

    // jellyfish
    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = registerSpawnEggItem("jellyfish", ReefEntities.JELLYFISH, 0xf6b7dd, 0xef8298);
    public static final RegistryObject<Item> JELLYFISH_BUCKET = registerMobBucketItem("jellyfish", ReefEntities.JELLYFISH);
    public static final RegistryObject<Item> GLOB_OF_JELLY = registerItem("glob_of_jelly", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JELLYFISH_JELLY = registerItem("jellyfish_jelly", () -> new JellyBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.5F).alwaysEat().build())));
    public static final RegistryObject<Item> JELLY_TART = registerItem("jelly_tart", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(0.5F).build())));
    public static final RegistryObject<Item> JELLY_SANDWICH = registerItem("jelly_sandwich", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(8).saturationMod(0.5F).build())));

    // large shark
    public static final RegistryObject<Item> LARGE_SHARK_SPAWN_EGG = registerSpawnEggItem("large_shark", ReefEntities.LARGE_SHARK, 0x564637, 0xacaba3);

    // lionfish
    public static final RegistryObject<Item> LIONFISH_SPAWN_EGG = registerSpawnEggItem("lionfish", ReefEntities.LIONFISH, 0x862a34, 0xd2d6dc);

    // mahi mahi
    public static final RegistryObject<Item> MAHI_MAHI_SPAWN_EGG = registerSpawnEggItem("mahi_mahi", ReefEntities.MAHI_MAHI, 0x4b922b, 0xdcd700);

    // maori wrasse
    public static final RegistryObject<Item> MAORI_WRASSE_SPAWN_EGG = registerSpawnEggItem("maori_wrasse", ReefEntities.MAORI_WRASSE, 0x1a7e89, 0x0d6c52);

    // moorish idol
    public static final RegistryObject<Item> MOORISH_IDOL_SPAWN_EGG = registerSpawnEggItem("moorish_idol", ReefEntities.MOORISH_IDOL, 0x322b2f, 0xf2c447);
    public static final RegistryObject<Item> MOORISH_IDOL_BUCKET = registerMobBucketItem("moorish_idol", ReefEntities.MOORISH_IDOL);
    public static final RegistryObject<Item> RAW_MOORISH_IDOL = registerItem("moorish_idol", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> IDOL_COOKIE = registerItem("idol_cookie", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));

    // parrotfish
    public static final RegistryObject<Item> PARROTFISH_SPAWN_EGG = registerSpawnEggItem("parrotfish", ReefEntities.PARROTFISH, 0x3998e7, 0x7351ff);
    public static final RegistryObject<Item> PARROTFISH_BUCKET = registerMobBucketItem("parrotfish", ReefEntities.PARROTFISH);
    public static final RegistryObject<Item> RAW_PARROTFISH = registerItem("parrotfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).meat().build())));
    public static final RegistryObject<Item> PARROTFISH_PUNCH = registerItem("parrotfish_punch", () -> new ParrotfishPunchItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationMod(1.2F).alwaysEat().effect(new MobEffectInstance(MobEffects.ABSORPTION, 300, 1), 1F).effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1F).build())));

    // pipefish
    public static final RegistryObject<Item> PIPEFISH_SPAWN_EGG = registerSpawnEggItem("pipefish", ReefEntities.PIPEFISH, 0x7c7f16, 0x3c3112);
    public static final RegistryObject<Item> PIPEFISH_BUCKET = registerMobBucketItem("pipefish", ReefEntities.PIPEFISH);
    public static final RegistryObject<Item> RAW_PIPEFISH = registerItem("pipefish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> PIPEFISH_SUSHI = registerItem("pipefish_sushi", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(0).saturationMod(0F).alwaysEat().effect(new MobEffectInstance(MobEffects.HEAL, 1, 1), 1F).build())));

    // rabbitfish
    public static final RegistryObject<Item> RABBITFISH_SPAWN_EGG = registerSpawnEggItem("rabbitfish", ReefEntities.RABBITFISH, 0xe8c417, 0x282339);

    // ray
    public static final RegistryObject<Item> RAY_SPAWN_EGG = registerSpawnEggItem("ray", ReefEntities.RAY, 0x150a11, 0xd4d4e0);
    public static final RegistryObject<Item> RAY_BUCKET = registerMobBucketItem("ray", ReefEntities.RAY);
    public static final RegistryObject<Item> RAW_RAY = registerItem("ray", () -> new Item(foodItem(ReefFoodValues.RAW_RAY)));
    public static final RegistryObject<Item> CHOCOLATE_RAY_MUFFIN = registerItem("chocolate_ray_muffin", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().alwaysEat().nutrition(7).saturationMod(0.3F).build())));

    // seahorse
    public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = registerSpawnEggItem("seahorse", ReefEntities.SEAHORSE, 0xd6d126, 0x7a8e1e);
    public static final RegistryObject<Item> SEAHORSE_BUCKET = registerMobBucketItem("seahorse", ReefEntities.SEAHORSE);
    public static final RegistryObject<Item> RAW_SEAHORSE = registerItem("seahorse", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> DRIED_SEAHORSE = registerItem("dried_seahorse", () -> new CleansingSnackItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().fast().alwaysEat().build())));

    // shark
    public static final RegistryObject<Item> SHARK_SPAWN_EGG = registerSpawnEggItem("shark", ReefEntities.SHARK, 0x686963, 0xb0a5a2);

    // small shark
    public static final RegistryObject<Item> SMALL_SHARK_SPAWN_EGG = registerSpawnEggItem("small_shark", ReefEntities.SMALL_SHARK, 0xe0a33b, 0xa35a1d);
    public static final RegistryObject<Item> SMALL_SHARK_BUCKET = registerMobBucketItem("small_shark", ReefEntities.SMALL_SHARK);
    public static final RegistryObject<Item> RAW_SMALL_SHARK = registerItem("small_shark", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.8F).meat().build())));
    public static final RegistryObject<Item> SHARKBITE_SALAD = registerItem("sharkbite_salad", () -> new BowlFoodItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).build())));

    // tang
    public static final RegistryObject<Item> TANG_SPAWN_EGG = registerSpawnEggItem("tang", ReefEntities.TANG, 0x445bca, 0xefb032);
    public static final RegistryObject<Item> TANG_BUCKET = registerMobBucketItem("tang", ReefEntities.TANG);
    public static final RegistryObject<Item> RAW_TANG = registerItem("tang", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> TANGY_SOUP = registerItem("tangy_soup", () -> new BowlFoodItem(new Item.Properties().stacksTo(1).craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(12).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.GLOWING, 600, 1), 1.0F).build())));

    // triggerfish
    public static final RegistryObject<Item> TRIGGERFISH_SPAWN_EGG = registerSpawnEggItem("triggerfish", ReefEntities.TRIGGERFISH, 0x121418, 0xaa8e23);

    // wrasse
    public static final RegistryObject<Item> WRASSE_SPAWN_EGG = registerSpawnEggItem("wrasse", ReefEntities.WRASSE, 0xde608d, 0x1481bc);

    // special meals
    public static final RegistryObject<Item> ROCKFISH_CANDY = registerItem("rockfish_candy", () -> new StickFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.8F).fast().build())));
    public static final RegistryObject<Item> FORBIDDEN_SOUP = registerItem("forbidden_soup", () -> new BowlFoodItem(new Item.Properties().stacksTo(16).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.35F).build())));
    public static final RegistryObject<Item> SWEET_TOOTH_SEABURGER = registerItem("sweet_tooth_seaburger", () -> new Item(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 1), 1.0F).build())));
    public static final RegistryObject<Item> HAWAIIAN_BARBEQUE = registerItem("hawaiian_barbeque", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(9).saturationMod(0.7F).effect(new MobEffectInstance(MobEffects.SATURATION, 600, 0), 1.0F).build())));
    public static final RegistryObject<Item> TROPICAL_FISHSTICKS = registerItem("tropical_fishsticks", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.5F).effect(new MobEffectInstance(MobEffects.JUMP, 600, 1), 0.7F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 1), 0.7F).build())));
    public static final RegistryObject<Item> SURF_N_TURF = registerItemNoLang("surf_n_turf", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.BUCKET).food(new FoodProperties.Builder().nutrition(10).saturationMod(0.7F).build())));
    public static final RegistryObject<Item> SEASUGAR_SORBET = registerItem("seasugar_sorbet", () -> new BowlFoodItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).food(new FoodProperties.Builder().nutrition(7).saturationMod(0.3F).effect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1.0F).build())));

    // discs
    public static final RegistryObject<Item> CLAW_DISC = registerItemNoLang("claw_disc", () -> new RecordItem(2, ReefSoundEvents.CLAW_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2240));

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

    private static <I extends Item> RegistryObject<I> registerItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        AUTO_TRANSLATE.add(item);
        return item;
    }

    private static <I extends Item> RegistryObject<I> registerItemNoLang(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = ITEMS.register(name, supplier);
        return item;
    }

    private static RegistryObject<Item> registerSpawnEggItem(String name, RegistryObject type, int baseColor, int spotColor) {
        return registerItem(name + "_spawn_egg", () -> new ForgeSpawnEggItem(type, baseColor, spotColor, new Item.Properties()));
    }

    private static RegistryObject<Item> registerMobBucketItem(String name, RegistryObject type) {
        return registerItemNoLang(name + "_bucket", () -> new ReefFishBucketItem(type));
    }

    public static Item.Properties foodItem(FoodProperties food) {
        return new Item.Properties().food(food);
    }
}