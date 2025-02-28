package net.thevaliantsquidward.rainbowreef.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.block.ModBlocks;

public class ModCreativeModeTabs {
public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RainbowReef.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAW_TANG.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        //order:
                        //angelfish
                        //arrow crab
                        //basslet
                        //boxfish
                        //butterfish
                        //clownfish
                        //crab
                        //dwarf angelfish
                        //goby
                        //hogfish
                        //jellyfish
                        //moorish idol
                        //parrotfish
                        //pipefish
                        //ray
                        //seahorse
                        //small shark
                        //tang

                        pOutput.accept(ModItems.RAW_ANGELFISH.get());
                        pOutput.accept(ModItems.RAW_ARROW_CRAB.get());
                        pOutput.accept(ModItems.RAW_BASSLET.get());
                        pOutput.accept(ModItems.RAW_BOXFISH.get());
                        pOutput.accept(ModItems.RAW_BUTTERFISH.get());
                        pOutput.accept(ModItems.RAW_CLOWNFISH.get());
                        pOutput.accept(ModItems.RAW_CRAB_MEAT.get());
                        pOutput.accept(ModItems.RAW_DWARF_ANGELFISH.get());
                        pOutput.accept(ModItems.RAW_GOBY.get());
                        pOutput.accept(ModItems.RAW_HOGFISH.get());
                        pOutput.accept(ModItems.GLOB_OF_JELLY.get());
                        pOutput.accept(ModItems.RAW_MOORISH_IDOL.get());
                        pOutput.accept(ModItems.RAW_PARROTFISH.get());
                        pOutput.accept(ModItems.RAW_PIPEFISH.get());
                        pOutput.accept(ModItems.RAW_RAY.get());
                        pOutput.accept(ModItems.RAW_SEAHORSE.get());
                        pOutput.accept(ModItems.RAW_SMALL_SHARK.get());
                        pOutput.accept(ModItems.RAW_TANG.get());

                        pOutput.accept(ModItems.ANGELFISH_BUCKET.get());
                        pOutput.accept(ModItems.ARROW_CRAB_BUCKET.get());
                        pOutput.accept(ModItems.BASSLET_BUCKET.get());
                        pOutput.accept(ModItems.BOXFISH_BUCKET.get());
                        pOutput.accept(ModItems.BUTTERFISH_BUCKET.get());
                        pOutput.accept(ModItems.CLOWNFISH_BUCKET.get());
                        pOutput.accept(ModItems.CRAB_BUCKET.get());
                        pOutput.accept(ModItems.DWARF_ANGELFISH_BUCKET.get());
                        pOutput.accept(ModItems.GOBY_BUCKET.get());
                        pOutput.accept(ModItems.HOGFISH_BUCKET.get());
                        pOutput.accept(ModItems.JELLYFISH_BUCKET.get());
                        pOutput.accept(ModItems.MOORISH_IDOL_BUCKET.get());
                        pOutput.accept(ModItems.PARROTFISH_BUCKET.get());
                        pOutput.accept(ModItems.PIPEFISH_BUCKET.get());
                        pOutput.accept(ModItems.SEAHORSE_BUCKET.get());
                        pOutput.accept(ModItems.TANG_BUCKET.get());
                        pOutput.accept(ModItems.SHARK_BUCKET.get());
                        pOutput.accept(ModItems.SPOTTED_EAGLE_RAY_BUCKET.get());


                        pOutput.accept(ModItems.ANGELFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ARROW_CRAB_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BASSLET_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BOXFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.BUTTERFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CLOWNFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CRAB_SPAWN_EGG.get());
                        pOutput.accept(ModItems.DWARF_ANGEL_SPAWN_EGG.get());
                        pOutput.accept(ModItems.GOBY_SPAWN_EGG.get());
                        pOutput.accept(ModItems.HOGFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.JELLYFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MOORISH_IDOL_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PARROTFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.PIPEFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.RAY_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SEAHORSE_SPAWN_EGG.get());
                        pOutput.accept(ModItems.TANG_SPAWN_EGG.get());
                        pOutput.accept(ModItems.SMALL_SHARK_SPAWN_EGG.get());


                        pOutput.accept(ModItems.BASSLET_COOKIE.get());
                        pOutput.accept(ModItems.BOXFISH_BREAD.get());
                        pOutput.accept(ModItems.BUTTERED_BUTTERFLYFISH_TOAST.get());
                        pOutput.accept(ModItems.CLOWNFISH_CUPCAKE.get());
                        pOutput.accept(ModItems.ROASTED_CRAB_MEAT.get());
                        pOutput.accept(ModItems.CRAB_CAKE.get());
                        pOutput.accept(ModItems.DWARF_ANGELFISH_TARTS.get());
                        pOutput.accept(ModItems.GOBY_GUMMY.get());
                        pOutput.accept(ModItems.HOGFISH_BACON.get());
                        pOutput.accept(ModItems.COOKED_HOGFISH_BACON.get());
                        pOutput.accept(ModItems.ULTRA_BACON_SANDWICH.get());
                        pOutput.accept(ModItems.JELLYFISH_JELLY.get());
                        pOutput.accept(ModItems.JELLY_SANDWICH.get());
                        pOutput.accept(ModItems.JELLY_TART.get());
                        pOutput.accept(ModItems.IDOL_COOKIE.get());
                        pOutput.accept(ModItems.PARROTFISH_PUNCH.get());
                        pOutput.accept(ModItems.PIPEFISH_SUSHI.get());
                        pOutput.accept(ModItems.CHOCO_RAY_MUFFIN.get());
                        pOutput.accept(ModItems.DRIED_SEAHORSE.get());
                        pOutput.accept(ModItems.SHARKBITE_SALAD.get());
                        pOutput.accept(ModItems.TANGY_SOUP.get());
                        pOutput.accept(ModBlocks.ANGELFISH_CAKE.get());

                        pOutput.accept(ModItems.CLAW_DISC.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_BLOCKS = CREATIVE_MODE_TABS.register("rainbow_reef_block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CORALSTONE.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_block_tab"))
                    .displayItems((pParameters, pOutput) -> {


                        pOutput.accept(ModBlocks.CORALSTONE.get());
                        pOutput.accept(ModBlocks.CORALSTONE_BRICKS.get());
                        pOutput.accept(ModBlocks.POLISHED_CORALSTONE.get());
                        pOutput.accept(ModBlocks.CHISELED_CORALSTONE.get());
                        pOutput.accept(ModBlocks.BUBBLER.get());
                        pOutput.accept(ModBlocks.RED_SAND_BUBBLER.get());
                        pOutput.accept(ModBlocks.BLUE_PUFFER_LANTERN.get());
                        pOutput.accept(ModBlocks.GREEN_PUFFER_LANTERN.get());
                        pOutput.accept(ModBlocks.ORANGE_PUFFER_LANTERN.get());
                        pOutput.accept(ModBlocks.JELLY_BLOCK.get());
                        pOutput.accept(ModBlocks.YELLOW_SEA_ANEMONE.get());
                        pOutput.accept(ModBlocks.GREEN_SEA_ANEMONE.get());
                        pOutput.accept(ModBlocks.ORANGE_SEA_ANEMONE.get());

                        pOutput.accept(ModBlocks.BARREL_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.CHIMNEY_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.HAND_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.SHELF_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.TOWER_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.ROSE_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.FLOWER_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.RING_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.BUSH_CORAL_BLOCK.get());

                        pOutput.accept(ModBlocks.DEAD_BARREL_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_CHIMNEY_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_HAND_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_SHELF_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_TOWER_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_ROSE_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_FLOWER_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_RING_CORAL_BLOCK.get());
                        pOutput.accept(ModBlocks.DEAD_BUSH_CORAL_BLOCK.get());

                        pOutput.accept(ModBlocks.BARREL_CORAL.get());
                        pOutput.accept(ModBlocks.CHIMNEY_CORAL.get());
                        pOutput.accept(ModBlocks.HAND_CORAL.get());
                        pOutput.accept(ModBlocks.SHELF_CORAL.get());
                        pOutput.accept(ModBlocks.TOWER_CORAL.get());
                        pOutput.accept(ModBlocks.ROSE_CORAL.get());
                        pOutput.accept(ModBlocks.FLOWER_CORAL.get());
                        pOutput.accept(ModBlocks.RING_CORAL.get());
                        pOutput.accept(ModBlocks.BUSH_CORAL.get());

                        pOutput.accept(ModBlocks.DEAD_BARREL_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_CHIMNEY_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_HAND_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_SHELF_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_TOWER_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_ROSE_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_FLOWER_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_RING_CORAL.get());
                        pOutput.accept(ModBlocks.DEAD_BUSH_CORAL.get());

                        pOutput.accept(ModBlocks.BARREL_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.CHIMNEY_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.HAND_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.SHELF_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.TOWER_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.ROSE_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.FLOWER_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.RING_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.BUSH_CORAL_FAN.get());

                        pOutput.accept(ModBlocks.DEAD_BARREL_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_CHIMNEY_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_HAND_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_SHELF_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_TOWER_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_ROSE_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_FLOWER_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_RING_CORAL_FAN.get());
                        pOutput.accept(ModBlocks.DEAD_BUSH_CORAL_FAN.get());

                        pOutput.accept(ModBlocks.CARMINE_STARFISH.get());
                        pOutput.accept(ModBlocks.CERULEAN_STARFISH.get());
                        pOutput.accept(ModBlocks.CHARTREUSE_STARFISH.get());
                        pOutput.accept(ModBlocks.FUCHSIA_STARFISH.get());
                        pOutput.accept(ModBlocks.SAFFRON_STARFISH.get());
                        pOutput.accept(ModBlocks.TANGERINE_STARFISH.get());
                        pOutput.accept(ModBlocks.UMBER_STARFISH.get());
                        pOutput.accept(ModBlocks.VIOLET_STARFISH.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
