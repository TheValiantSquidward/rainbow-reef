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

public class RainbowReefTab {
public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RainbowReef.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RAINBOW_REEF_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReefItems.RAW_TANG.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_tab"))
                    .displayItems((pParameters, output) -> {
                        // angelfish
                        output.accept(ReefItems.ANGELFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.ANGELFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_ANGELFISH.get());
                        output.accept(ReefBlocks.ANGELFISH_CAKE.get());

                        // arrow crab
                        output.accept(ReefItems.ARROW_CRAB_SPAWN_EGG.get());
                        output.accept(ReefItems.ARROW_CRAB_BUCKET.get());
                        output.accept(ReefItems.RAW_ARROW_CRAB.get());

                        // basslet
                        output.accept(ReefItems.BASSLET_SPAWN_EGG.get());
                        output.accept(ReefItems.BASSLET_BUCKET.get());

                        // billfish
                        output.accept(ReefItems.BILLFISH_SPAWN_EGG.get());

                        // boxfish
                        output.accept(ReefItems.BOXFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.BOXFISH_BUCKET.get());

                        // butterflyfish
                        output.accept(ReefItems.BUTTERFLYFISH_SPAWN_EGG.get());

                        // clownfish
                        output.accept(ReefItems.CLOWNFISH_SPAWN_EGG.get());

                        // crab
                        output.accept(ReefItems.CRAB_SPAWN_EGG.get());

                        output.accept(ReefItems.DAMSELFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.DWARF_ANGELFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.FROGFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.GOBY_SPAWN_EGG.get());
                        output.accept(ReefItems.HOGFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.JELLYFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.LARGE_SHARK_SPAWN_EGG.get());
                        output.accept(ReefItems.LIONFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.MAHI_MAHI_SPAWN_EGG.get());
                        output.accept(ReefItems.MAORI_WRASSE_SPAWN_EGG.get());
                        output.accept(ReefItems.MOORISH_IDOL_SPAWN_EGG.get());
                        output.accept(ReefItems.PARROTFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.PIPEFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.RABBITFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.RAY_SPAWN_EGG.get());
                        output.accept(ReefItems.SEAHORSE_SPAWN_EGG.get());
                        output.accept(ReefItems.SHARK_SPAWN_EGG.get());
                        output.accept(ReefItems.SMALL_SHARK_SPAWN_EGG.get());
                        output.accept(ReefItems.TANG_SPAWN_EGG.get());
                        output.accept(ReefItems.TRIGGERFISH_SPAWN_EGG.get());
                        output.accept(ReefItems.WRASSE_SPAWN_EGG.get());

                        output.accept(ReefItems.RAW_BASSLET.get());
                        output.accept(ReefItems.RAW_BOXFISH.get());
                        output.accept(ReefItems.RAW_BUTTERFLYFISH.get());
                        output.accept(ReefItems.RAW_CLOWNFISH.get());
                        output.accept(ReefItems.RAW_CRAB.get());
                        output.accept(ReefItems.RAW_DWARF_ANGELFISH.get());
                        output.accept(ReefItems.RAW_GOBY.get());
                        output.accept(ReefItems.RAW_HOGFISH.get());
                        output.accept(ReefItems.GLOB_OF_JELLY.get());
                        output.accept(ReefItems.RAW_MOORISH_IDOL.get());
                        output.accept(ReefItems.RAW_PARROTFISH.get());
                        output.accept(ReefItems.RAW_PIPEFISH.get());
                        output.accept(ReefItems.RAW_RAY.get());
                        output.accept(ReefItems.RAW_SEAHORSE.get());
                        output.accept(ReefItems.RAW_SMALL_SHARK.get());
                        output.accept(ReefItems.RAW_TANG.get());

                        output.accept(ReefItems.BUTTERFLYFISH_BUCKET.get());
                        output.accept(ReefItems.CLOWNFISH_BUCKET.get());
                        output.accept(ReefItems.CRAB_BUCKET.get());
                        output.accept(ReefItems.DWARF_ANGELFISH_BUCKET.get());
                        output.accept(ReefItems.GOBY_BUCKET.get());
                        output.accept(ReefItems.HOGFISH_BUCKET.get());
                        output.accept(ReefItems.JELLYFISH_BUCKET.get());
                        output.accept(ReefItems.MOORISH_IDOL_BUCKET.get());
                        output.accept(ReefItems.PARROTFISH_BUCKET.get());
                        output.accept(ReefItems.PIPEFISH_BUCKET.get());
                        output.accept(ReefItems.SEAHORSE_BUCKET.get());
                        output.accept(ReefItems.TANG_BUCKET.get());
                        output.accept(ReefItems.SMALL_SHARK_BUCKET.get());
                        output.accept(ReefItems.RAY_BUCKET.get());

                        output.accept(ReefItems.BASSLET_COOKIE.get());
                        output.accept(ReefItems.BOXFISH_BREAD.get());
                        output.accept(ReefItems.BUTTERED_TOAST.get());
                        output.accept(ReefItems.CLOWNFISH_CUPCAKE.get());
                        output.accept(ReefItems.ROASTED_CRAB.get());
                        output.accept(ReefItems.CRAB_CAKE.get());
                        output.accept(ReefItems.DWARF_ANGELFISH_TARTS.get());
                        output.accept(ReefItems.GOBY_GUMMY.get());
                        output.accept(ReefItems.HOGFISH_BACON.get());
                        output.accept(ReefItems.COOKED_HOGFISH_BACON.get());
                        output.accept(ReefItems.ULTRA_BACON_SANDWICH.get());
                        output.accept(ReefItems.JELLYFISH_JELLY.get());
                        output.accept(ReefItems.JELLY_SANDWICH.get());
                        output.accept(ReefItems.JELLY_TART.get());
                        output.accept(ReefItems.IDOL_COOKIE.get());
                        output.accept(ReefItems.PARROTFISH_PUNCH.get());
                        output.accept(ReefItems.PIPEFISH_SUSHI.get());
                        output.accept(ReefItems.CHOCOLATE_RAY_MUFFIN.get());
                        output.accept(ReefItems.DRIED_SEAHORSE.get());
                        output.accept(ReefItems.TANGY_SOUP.get());
                        output.accept(ReefItems.SHARKBITE_SALAD.get());

                        output.accept(ReefItems.SEASUGAR_SORBET.get());
                        output.accept(ReefItems.ROCKFISH_CANDY.get());
                        output.accept(ReefItems.FORBIDDEN_SOUP.get());
                        output.accept(ReefItems.SWEET_TOOTH_SEABURGER.get());
                        output.accept(ReefItems.HAWAIIAN_BARBEQUE.get());
                        output.accept(ReefItems.TROPICAL_FISHSTICKS.get());
                        output.accept(ReefItems.SURF_N_TURF.get());

                        output.accept(ReefItems.CLAW_DISC.get());

                        output.accept(ReefBlocks.ORANGE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.GREEN_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.BLUE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.ORANGE_SEA_ANEMONE.get());
                        output.accept(ReefBlocks.YELLOW_SEA_ANEMONE.get());
                        output.accept(ReefBlocks.GREEN_SEA_ANEMONE.get());

                        output.accept(ReefBlocks.CARMINE_STARFISH.get());
                        output.accept(ReefBlocks.CERULEAN_STARFISH.get());
                        output.accept(ReefBlocks.CHARTREUSE_STARFISH.get());
                        output.accept(ReefBlocks.FUCHSIA_STARFISH.get());
                        output.accept(ReefBlocks.SAFFRON_STARFISH.get());
                        output.accept(ReefBlocks.TANGERINE_STARFISH.get());
                        output.accept(ReefBlocks.UMBER_STARFISH.get());
                        output.accept(ReefBlocks.VIOLET_STARFISH.get());

                        output.accept(ReefBlocks.BUBBLER.get());
                        output.accept(ReefBlocks.RED_SAND_BUBBLER.get());
                        output.accept(ReefBlocks.JELLY_BLOCK.get());

                        output.accept(ReefBlocks.CORALSTONE.get());
                        output.accept(ReefBlocks.CORALSTONE_BRICKS.get());
                        output.accept(ReefBlocks.POLISHED_CORALSTONE.get());
                        output.accept(ReefBlocks.CHISELED_CORALSTONE.get());

                        output.accept(ReefBlocks.BARREL_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.BARREL_CORAL.get());
                        output.accept(ReefBlocks.BARREL_CORAL_FAN.get());
                        output.accept(ReefBlocks.BUSH_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.BUSH_CORAL.get());
                        output.accept(ReefBlocks.BUSH_CORAL_FAN.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL_FAN.get());
                        output.accept(ReefBlocks.FLOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.FLOWER_CORAL.get());
                        output.accept(ReefBlocks.FLOWER_CORAL_FAN.get());
                        output.accept(ReefBlocks.HAND_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.HAND_CORAL.get());
                        output.accept(ReefBlocks.HAND_CORAL_FAN.get());
                        output.accept(ReefBlocks.RING_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.RING_CORAL.get());
                        output.accept(ReefBlocks.RING_CORAL_FAN.get());
                        output.accept(ReefBlocks.ROSE_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.ROSE_CORAL.get());
                        output.accept(ReefBlocks.ROSE_CORAL_FAN.get());
                        output.accept(ReefBlocks.SHELF_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.SHELF_CORAL.get());
                        output.accept(ReefBlocks.SHELF_CORAL_FAN.get());
                        output.accept(ReefBlocks.TOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.TOWER_CORAL.get());
                        output.accept(ReefBlocks.TOWER_CORAL_FAN.get());

                        output.accept(ReefBlocks.DEAD_BARREL_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_BARREL_CORAL.get());
                        output.accept(ReefBlocks.DEAD_BARREL_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL_FAN.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
