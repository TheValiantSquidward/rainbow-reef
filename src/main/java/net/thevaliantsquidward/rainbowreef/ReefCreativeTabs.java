package net.thevaliantsquidward.rainbowreef;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

public class ReefCreativeTabs {
public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RainbowReef.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReefItems.RAW_TANG.get()))
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

                        pOutput.accept(ReefItems.RAW_ANGELFISH.get());
                        pOutput.accept(ReefItems.RAW_ARROW_CRAB.get());
                        pOutput.accept(ReefItems.RAW_BASSLET.get());
                        pOutput.accept(ReefItems.RAW_BOXFISH.get());
                        pOutput.accept(ReefItems.RAW_BUTTERFISH.get());
                        pOutput.accept(ReefItems.RAW_CLOWNFISH.get());
                        pOutput.accept(ReefItems.RAW_CRAB_MEAT.get());
                        pOutput.accept(ReefItems.RAW_DWARF_ANGELFISH.get());
                        pOutput.accept(ReefItems.RAW_GOBY.get());
                        pOutput.accept(ReefItems.RAW_HOGFISH.get());
                        pOutput.accept(ReefItems.GLOB_OF_JELLY.get());
                        pOutput.accept(ReefItems.RAW_MOORISH_IDOL.get());
                        pOutput.accept(ReefItems.RAW_PARROTFISH.get());
                        pOutput.accept(ReefItems.RAW_PIPEFISH.get());
                        pOutput.accept(ReefItems.RAW_RAY.get());
                        pOutput.accept(ReefItems.RAW_SEAHORSE.get());
                        pOutput.accept(ReefItems.RAW_SMALL_SHARK.get());
                        pOutput.accept(ReefItems.RAW_TANG.get());

                        pOutput.accept(ReefItems.ANGELFISH_BUCKET.get());
                        pOutput.accept(ReefItems.ARROW_CRAB_BUCKET.get());
                        pOutput.accept(ReefItems.BASSLET_BUCKET.get());
                        pOutput.accept(ReefItems.BOXFISH_BUCKET.get());
                        pOutput.accept(ReefItems.BUTTERFISH_BUCKET.get());
                        pOutput.accept(ReefItems.CLOWNFISH_BUCKET.get());
                        pOutput.accept(ReefItems.CRAB_BUCKET.get());
                        pOutput.accept(ReefItems.DWARF_ANGELFISH_BUCKET.get());
                        pOutput.accept(ReefItems.GOBY_BUCKET.get());
                        pOutput.accept(ReefItems.HOGFISH_BUCKET.get());
                        pOutput.accept(ReefItems.JELLYFISH_BUCKET.get());
                        pOutput.accept(ReefItems.MOORISH_IDOL_BUCKET.get());
                        pOutput.accept(ReefItems.PARROTFISH_BUCKET.get());
                        pOutput.accept(ReefItems.PIPEFISH_BUCKET.get());
                        pOutput.accept(ReefItems.SEAHORSE_BUCKET.get());
                        pOutput.accept(ReefItems.TANG_BUCKET.get());
                        pOutput.accept(ReefItems.SHARK_BUCKET.get());
                        pOutput.accept(ReefItems.SPOTTED_EAGLE_RAY_BUCKET.get());


                        pOutput.accept(ReefItems.ANGELFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.ARROW_CRAB_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.BASSLET_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.BOXFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.BUTTERFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.CLOWNFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.CRAB_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.DWARF_ANGEL_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.GOBY_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.HOGFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.JELLYFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.MOORISH_IDOL_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.PARROTFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.PIPEFISH_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.RAY_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.SEAHORSE_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.TANG_SPAWN_EGG.get());
                        pOutput.accept(ReefItems.SMALL_SHARK_SPAWN_EGG.get());

                        pOutput.accept(ReefBlocks.ANGELFISH_CAKE.get());
                        pOutput.accept(ReefItems.BASSLET_COOKIE.get());
                        pOutput.accept(ReefItems.BOXFISH_BREAD.get());
                        pOutput.accept(ReefItems.BUTTERED_BUTTERFLYFISH_TOAST.get());
                        pOutput.accept(ReefItems.CLOWNFISH_CUPCAKE.get());
                        pOutput.accept(ReefItems.ROASTED_CRAB_MEAT.get());
                        pOutput.accept(ReefItems.CRAB_CAKE.get());
                        pOutput.accept(ReefItems.DWARF_ANGELFISH_TARTS.get());
                        pOutput.accept(ReefItems.GOBY_GUMMY.get());
                        pOutput.accept(ReefItems.HOGFISH_BACON.get());
                        pOutput.accept(ReefItems.COOKED_HOGFISH_BACON.get());
                        pOutput.accept(ReefItems.ULTRA_BACON_SANDWICH.get());
                        pOutput.accept(ReefItems.JELLYFISH_JELLY.get());
                        pOutput.accept(ReefItems.JELLY_SANDWICH.get());
                        pOutput.accept(ReefItems.JELLY_TART.get());
                        pOutput.accept(ReefItems.IDOL_COOKIE.get());
                        pOutput.accept(ReefItems.PARROTFISH_PUNCH.get());
                        pOutput.accept(ReefItems.PIPEFISH_SUSHI.get());
                        pOutput.accept(ReefItems.CHOCO_RAY_MUFFIN.get());
                        pOutput.accept(ReefItems.DRIED_SEAHORSE.get());
                        pOutput.accept(ReefItems.TANGY_SOUP.get());
                        pOutput.accept(ReefItems.SHARKBITE_SALAD.get());

                        pOutput.accept(ReefItems.SEASUGAR_SORBET.get());
                        pOutput.accept(ReefItems.ROCKFISH_CANDY.get());
                        pOutput.accept(ReefItems.FORBIDDEN_SOUP.get());
                        pOutput.accept(ReefItems.SWEET_TOOTH_SEABURGER.get());
                        pOutput.accept(ReefItems.HAWAIIAN_BARBEQUE.get());
                        pOutput.accept(ReefItems.TROPICAL_FISHSTICKS.get());
                        pOutput.accept(ReefItems.SURF_N_TURF.get());

                        pOutput.accept(ReefItems.CLAW_DISC.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_BLOCKS = CREATIVE_MODE_TABS.register("rainbow_reef_block_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReefBlocks.CORALSTONE.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_block_tab"))
                    .displayItems((pParameters, pOutput) -> {


                        pOutput.accept(ReefBlocks.CORALSTONE.get());
                        pOutput.accept(ReefBlocks.CORALSTONE_BRICKS.get());
                        pOutput.accept(ReefBlocks.POLISHED_CORALSTONE.get());
                        pOutput.accept(ReefBlocks.CHISELED_CORALSTONE.get());
                        pOutput.accept(ReefBlocks.BUBBLER.get());
                        pOutput.accept(ReefBlocks.RED_SAND_BUBBLER.get());
                        pOutput.accept(ReefBlocks.BLUE_PUFFER_LANTERN.get());
                        pOutput.accept(ReefBlocks.GREEN_PUFFER_LANTERN.get());
                        pOutput.accept(ReefBlocks.ORANGE_PUFFER_LANTERN.get());
                        pOutput.accept(ReefBlocks.JELLY_BLOCK.get());
                        pOutput.accept(ReefBlocks.YELLOW_SEA_ANEMONE.get());
                        pOutput.accept(ReefBlocks.GREEN_SEA_ANEMONE.get());
                        pOutput.accept(ReefBlocks.ORANGE_SEA_ANEMONE.get());

                        pOutput.accept(ReefBlocks.BARREL_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.CHIMNEY_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.HAND_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.SHELF_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.TOWER_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.ROSE_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.FLOWER_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.RING_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.BUSH_CORAL_BLOCK.get());

                        pOutput.accept(ReefBlocks.DEAD_BARREL_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_HAND_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_SHELF_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_TOWER_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_ROSE_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_FLOWER_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_RING_CORAL_BLOCK.get());
                        pOutput.accept(ReefBlocks.DEAD_BUSH_CORAL_BLOCK.get());

                        pOutput.accept(ReefBlocks.BARREL_CORAL.get());
                        pOutput.accept(ReefBlocks.CHIMNEY_CORAL.get());
                        pOutput.accept(ReefBlocks.HAND_CORAL.get());
                        pOutput.accept(ReefBlocks.SHELF_CORAL.get());
                        pOutput.accept(ReefBlocks.TOWER_CORAL.get());
                        pOutput.accept(ReefBlocks.ROSE_CORAL.get());
                        pOutput.accept(ReefBlocks.FLOWER_CORAL.get());
                        pOutput.accept(ReefBlocks.RING_CORAL.get());
                        pOutput.accept(ReefBlocks.BUSH_CORAL.get());

                        pOutput.accept(ReefBlocks.DEAD_BARREL_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_CHIMNEY_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_HAND_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_SHELF_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_TOWER_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_ROSE_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_FLOWER_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_RING_CORAL.get());
                        pOutput.accept(ReefBlocks.DEAD_BUSH_CORAL.get());

                        pOutput.accept(ReefBlocks.BARREL_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.CHIMNEY_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.HAND_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.SHELF_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.TOWER_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.ROSE_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.FLOWER_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.RING_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.BUSH_CORAL_FAN.get());

                        pOutput.accept(ReefBlocks.DEAD_BARREL_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_HAND_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_SHELF_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_TOWER_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_ROSE_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_FLOWER_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_RING_CORAL_FAN.get());
                        pOutput.accept(ReefBlocks.DEAD_BUSH_CORAL_FAN.get());

                        pOutput.accept(ReefBlocks.CARMINE_STARFISH.get());
                        pOutput.accept(ReefBlocks.CERULEAN_STARFISH.get());
                        pOutput.accept(ReefBlocks.CHARTREUSE_STARFISH.get());
                        pOutput.accept(ReefBlocks.FUCHSIA_STARFISH.get());
                        pOutput.accept(ReefBlocks.SAFFRON_STARFISH.get());
                        pOutput.accept(ReefBlocks.TANGERINE_STARFISH.get());
                        pOutput.accept(ReefBlocks.UMBER_STARFISH.get());
                        pOutput.accept(ReefBlocks.VIOLET_STARFISH.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
