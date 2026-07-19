package com.valiantenvoy.rainbow_reef;

import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RainbowReefTab {
public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RainbowReef.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RAINBOW_REEF_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReefItems.RAW_TANG.get()))
                    .title(Component.translatable("creativetab.rainbow_reef_tab"))
                    .displayItems((parameters, output) -> {

                        // Spawn eggs
                        ReefItems.ITEMS.getEntries().forEach(spawnEgg -> {
                            if ((spawnEgg.get() instanceof DeferredSpawnEggItem)) {
                                output.accept(spawnEgg.get());
                            }
                        });

                        // angelfish
                        output.accept(ReefItems.ANGELFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_ANGELFISH.get());
                        output.accept(ReefBlocks.ANGELFISH_CAKE.get());

                        // arrow crab
                        output.accept(ReefItems.ARROW_CRAB_BUCKET.get());
                        output.accept(ReefItems.RAW_ARROW_CRAB.get());

                        // basslet
                        output.accept(ReefItems.BASSLET_BUCKET.get());
                        output.accept(ReefItems.RAW_BASSLET.get());
                        output.accept(ReefItems.BASSLET_COOKIE.get());

                        // billfish
                        output.accept(ReefItems.BILLFISH_BUCKET.get());

                        // boxfish
                        output.accept(ReefItems.BOXFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_BOXFISH.get());
                        output.accept(ReefItems.BOXFISH_BREAD.get());

                        // butterflyfish
                        output.accept(ReefItems.BUTTERFLYFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_BUTTERFLYFISH.get());
                        output.accept(ReefItems.BUTTERED_TOAST.get());

                        // clownfish
                        output.accept(ReefItems.CLOWNFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_CLOWNFISH.get());
                        output.accept(ReefItems.CLOWNFISH_CUPCAKE.get());

                        // crab
                        output.accept(ReefItems.CRAB_BUCKET.get());
                        output.accept(ReefItems.RAW_CRAB.get());
                        output.accept(ReefItems.ROASTED_CRAB.get());
                        output.accept(ReefItems.CRAB_CAKE.get());
                        output.accept(ReefItems.CRAB_ROE.get());

                        // damselfish

                        // dwarf angelfish
                        output.accept(ReefItems.DWARF_ANGELFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_DWARF_ANGELFISH.get());
                        output.accept(ReefItems.DWARF_ANGELFISH_TART.get());

                        // frogfish

                        // fusilier

                        // goby
                        output.accept(ReefItems.GOBY_BUCKET.get());
                        output.accept(ReefItems.RAW_GOBY.get());
                        output.accept(ReefItems.GOBY_GUMMY.get());

                        // hogfish
                        output.accept(ReefItems.HOGFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_HOGFISH.get());
                        output.accept(ReefItems.HOGFISH_BACON.get());
                        output.accept(ReefItems.COOKED_HOGFISH_BACON.get());
                        output.accept(ReefItems.ULTRA_BACON_SANDWICH.get());

                        // jellyfish
                        output.accept(ReefItems.JELLYFISH_BUCKET.get());
                        output.accept(ReefItems.GLOB_OF_JELLY.get());
                        output.accept(ReefItems.JELLY_BOTTLE.get());
                        output.accept(ReefItems.JELLY_SANDWICH.get());
                        output.accept(ReefItems.JELLY_TART.get());

                        // large shark

                        // lionfish

                        // mahi mahi
                        output.accept(ReefItems.MAHI_MAHI_BUCKET.get());
                        output.accept(ReefItems.RAW_MAHI_MAHI.get());

                        // maori wrasse

                        // moorish idol
                        output.accept(ReefItems.MOORISH_IDOL_BUCKET.get());
                        output.accept(ReefItems.RAW_MOORISH_IDOL.get());
                        output.accept(ReefItems.IDOL_COOKIE.get());

                        // parrotfish
                        output.accept(ReefItems.PARROTFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_PARROTFISH.get());
                        output.accept(ReefItems.PARROTFISH_PUNCH.get());

                        // pipefish
                        output.accept(ReefItems.PIPEFISH_BUCKET.get());
                        output.accept(ReefItems.RAW_PIPEFISH.get());
                        output.accept(ReefItems.PIPEFISH_SUSHI.get());

                        // rabbitfish

                        // ray
                        output.accept(ReefItems.RAY_BUCKET.get());
                        output.accept(ReefItems.RAW_RAY.get());
                        output.accept(ReefItems.CHOCOLATE_RAY_MUFFIN.get());

                        // seahorse
                        output.accept(ReefItems.SEAHORSE_BUCKET.get());
                        output.accept(ReefItems.RAW_SEAHORSE.get());
                        output.accept(ReefItems.DRIED_SEAHORSE.get());

                        // shark
                        output.accept(ReefItems.SHARK_BUCKET.get());

                        // small shark
                        output.accept(ReefItems.SMALL_SHARK_BUCKET.get());
                        output.accept(ReefItems.RAW_SMALL_SHARK.get());
                        output.accept(ReefItems.SHARKBITE_SALAD.get());

                        // tang
                        output.accept(ReefItems.TANG_BUCKET.get());
                        output.accept(ReefItems.RAW_TANG.get());
                        output.accept(ReefItems.TANGY_SOUP.get());

                        // triggerfish

                        // wrasse

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
                        output.accept(ReefBlocks.JELLY_BLOCK.get());
                        output.accept(ReefBlocks.FINE_GLASS.get());

                        output.accept(ReefBlocks.CORALSTONE.get());
                        output.accept(ReefBlocks.CORALSTONE_BRICKS.get());
                        output.accept(ReefBlocks.POLISHED_CORALSTONE.get());
                        output.accept(ReefBlocks.CHISELED_CORALSTONE.get());

                        output.accept(ReefBlocks.MUD_BURROW.get());
                        output.accept(ReefBlocks.SAND_BURROW.get());
                        output.accept(ReefBlocks.STONE_BURROW.get());
                        output.accept(ReefBlocks.CORALSTONE_BURROW.get());

                        output.accept(ReefBlocks.TALL_TUBE_CORAL.get());
                        output.accept(ReefBlocks.TALL_BRAIN_CORAL.get());
                        output.accept(ReefBlocks.TALL_BUBBLE_CORAL.get());
                        output.accept(ReefBlocks.TALL_FIRE_CORAL.get());
                        output.accept(ReefBlocks.TALL_HORN_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_TUBE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_BRAIN_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_BUBBLE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_FIRE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_HORN_CORAL.get());

                        output.accept(ReefBlocks.BARREL_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.BARREL_CORAL.get());
                        output.accept(ReefBlocks.TALL_BARREL_CORAL.get());
                        output.accept(ReefItems.BARREL_CORAL_FAN.get());
                        output.accept(ReefBlocks.BUSH_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.BUSH_CORAL.get());
                        output.accept(ReefBlocks.TALL_BUSH_CORAL.get());
                        output.accept(ReefItems.BUSH_CORAL_FAN.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.TALL_CHIMNEY_CORAL.get());
                        output.accept(ReefItems.CHIMNEY_CORAL_FAN.get());
                        output.accept(ReefBlocks.FLOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.FLOWER_CORAL.get());
                        output.accept(ReefBlocks.TALL_FLOWER_CORAL.get());
                        output.accept(ReefItems.FLOWER_CORAL_FAN.get());
                        output.accept(ReefBlocks.HAND_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.HAND_CORAL.get());
                        output.accept(ReefBlocks.TALL_HAND_CORAL.get());
                        output.accept(ReefItems.HAND_CORAL_FAN.get());
                        output.accept(ReefBlocks.RING_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.RING_CORAL.get());
                        output.accept(ReefBlocks.TALL_RING_CORAL.get());
                        output.accept(ReefItems.RING_CORAL_FAN.get());
                        output.accept(ReefBlocks.ROSE_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.ROSE_CORAL.get());
                        output.accept(ReefBlocks.TALL_ROSE_CORAL.get());
                        output.accept(ReefItems.ROSE_CORAL_FAN.get());
                        output.accept(ReefBlocks.SHELF_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.SHELF_CORAL.get());
                        output.accept(ReefBlocks.TALL_SHELF_CORAL.get());
                        output.accept(ReefItems.SHELF_CORAL_FAN.get());
                        output.accept(ReefBlocks.TOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.TOWER_CORAL.get());
                        output.accept(ReefBlocks.TALL_TOWER_CORAL.get());
                        output.accept(ReefItems.TOWER_CORAL_FAN.get());

                        output.accept(ReefBlocks.DEAD_BARREL_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_BARREL_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_BARREL_CORAL.get());
                        output.accept(ReefItems.DEAD_BARREL_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_BUSH_CORAL.get());
                        output.accept(ReefItems.DEAD_BUSH_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_CHIMNEY_CORAL.get());
                        output.accept(ReefItems.DEAD_CHIMNEY_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_FLOWER_CORAL.get());
                        output.accept(ReefItems.DEAD_FLOWER_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_HAND_CORAL.get());
                        output.accept(ReefItems.DEAD_HAND_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_RING_CORAL.get());
                        output.accept(ReefItems.DEAD_RING_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_ROSE_CORAL.get());
                        output.accept(ReefItems.DEAD_ROSE_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_SHELF_CORAL.get());
                        output.accept(ReefItems.DEAD_SHELF_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_TOWER_CORAL.get());
                        output.accept(ReefItems.DEAD_TOWER_CORAL_FAN.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RAINBOW_REEF_VARIANTS_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_variants_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReefItems.TANG_BUCKET.get()))
                    .withTabsBefore(RAINBOW_REEF_TAB.getId())
                    .title(Component.translatable("creativetab.rainbow_reef_variants_tab"))
                    .displayItems((parameters, output) -> {
                    })
                    .build());

    private static void variantBucket(CreativeModeTab.Output output, Item bucket, int variant) {
        ItemStack stack = new ItemStack(bucket);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, tag -> tag.putInt("BucketVariantTag", variant));
        output.accept(stack);
    }

//    private static <E extends Enum<E>> void variantsByRarity(CreativeModeTab.Output output, Item bucket, E[] values,
//                                                             ToIntFunction<E> id, Function<E, ReefMob.ReefRarities> rarity) {
//        Arrays.stream(values)
//                .sorted(Comparator.<E>comparingInt(e -> rarity.apply(e).ordinal()).thenComparing(Enum::name))
//                .forEach(e -> variantBucket(output, bucket, id.applyAsInt(e)));
//    }
//
//    private static void variantsByName(CreativeModeTab.Output output, Item bucket, int count, IntFunction<String> name) {
//        IntStream.rangeClosed(1, count)
//                .boxed()
//                .sorted(Comparator.comparing(name::apply))
//                .forEach(variant -> variantBucket(output, bucket, variant));
//    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
