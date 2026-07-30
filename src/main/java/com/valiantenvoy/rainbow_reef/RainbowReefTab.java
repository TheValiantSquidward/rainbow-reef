package com.valiantenvoy.rainbow_reef;

import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefMobVariants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Comparator;

public class RainbowReefTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RainbowReef.MOD_ID);

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

                        output.accept(ReefItems.ANGELFISH_BUCKET.get());
                        output.accept(ReefItems.ARROW_CRAB_BUCKET.get());
                        output.accept(ReefItems.BASSLET_BUCKET.get());
                        output.accept(ReefItems.BILLFISH_BUCKET.get());
                        output.accept(ReefItems.BOXFISH_BUCKET.get());
                        output.accept(ReefItems.BUTTERFLYFISH_BUCKET.get());
                        output.accept(ReefItems.CLOWNFISH_BUCKET.get());
                        output.accept(ReefItems.CRAB_BUCKET.get());
                        output.accept(ReefItems.DAMSELFISH_BUCKET.get());
                        output.accept(ReefItems.DOLPHIN_BUCKET.get());
                        output.accept(ReefItems.DWARF_ANGELFISH_BUCKET.get());
                        output.accept(ReefItems.FROGFISH_BUCKET.get());
                        output.accept(ReefItems.FUSILIER_BUCKET.get());
                        output.accept(ReefItems.GOBY_BUCKET.get());
                        output.accept(ReefItems.HOGFISH_BUCKET.get());
                        output.accept(ReefItems.JELLYFISH_BUCKET.get());
                        output.accept(ReefItems.LARGE_SHARK_BUCKET.get());
                        output.accept(ReefItems.LIONFISH_BUCKET.get());
                        output.accept(ReefItems.MAHI_MAHI_BUCKET.get());
                        output.accept(ReefItems.MAORI_WRASSE_BUCKET.get());
                        output.accept(ReefItems.MOORISH_IDOL_BUCKET.get());
                        output.accept(ReefItems.PARROTFISH_BUCKET.get());
                        output.accept(ReefItems.PIPEFISH_BUCKET.get());
                        output.accept(ReefItems.RABBITFISH_BUCKET.get());
                        output.accept(ReefItems.RAY_BUCKET.get());
                        output.accept(ReefItems.SEAHORSE_BUCKET.get());
                        output.accept(ReefItems.SHARK_BUCKET.get());
                        output.accept(ReefItems.SMALL_SHARK_BUCKET.get());
                        output.accept(ReefItems.TANG_BUCKET.get());
                        output.accept(ReefItems.TRIGGERFISH_BUCKET.get());
                        output.accept(ReefItems.WRASSE_BUCKET.get());

                        output.accept(ReefItems.RAW_ANGELFISH.get());
                        output.accept(ReefItems.RAW_ARROW_CRAB.get());
                        output.accept(ReefItems.RAW_BASSLET.get());
                        output.accept(ReefItems.RAW_BOXFISH.get());
                        output.accept(ReefItems.RAW_BUTTERFLYFISH.get());
                        output.accept(ReefItems.RAW_CLOWNFISH.get());
                        output.accept(ReefItems.RAW_CRAB.get());
                        output.accept(ReefItems.RAW_DAMSELFISH.get());
                        output.accept(ReefItems.RAW_DWARF_ANGELFISH.get());
                        output.accept(ReefItems.RAW_FROGFISH.get());
                        output.accept(ReefItems.RAW_FUSILIER.get());
                        output.accept(ReefItems.RAW_GOBY.get());
                        output.accept(ReefItems.RAW_HOGFISH.get());
                        output.accept(ReefItems.GLOB_OF_JELLY.get());
                        output.accept(ReefItems.RAW_LIONFISH.get());
                        output.accept(ReefItems.RAW_MAHI_MAHI.get());
                        output.accept(ReefItems.RAW_MOORISH_IDOL.get());
                        output.accept(ReefItems.RAW_PARROTFISH.get());
                        output.accept(ReefItems.RAW_PIPEFISH.get());
                        output.accept(ReefItems.RAW_RABBITFISH.get());
                        output.accept(ReefItems.RAW_RAY.get());
                        output.accept(ReefItems.RAW_SEAHORSE.get());
                        output.accept(ReefItems.RAW_SMALL_SHARK.get());
                        output.accept(ReefItems.RAW_TANG.get());
                        output.accept(ReefItems.RAW_TRIGGERFISH.get());
                        output.accept(ReefItems.RAW_WRASSE.get());

                        output.accept(ReefBlocks.ANGELFISH_CAKE.get());
                        output.accept(ReefItems.BASSLET_COOKIE.get());
                        output.accept(ReefItems.BOXFISH_BREAD.get());
                        output.accept(ReefItems.BUTTERED_TOAST.get());
                        output.accept(ReefItems.CLOWNFISH_CUPCAKE.get());
                        output.accept(ReefItems.ROASTED_CRAB.get());
                        output.accept(ReefItems.CRAB_CAKE.get());
                        output.accept(ReefItems.DWARF_ANGELFISH_TART.get());
                        output.accept(ReefItems.GOBY_GUMMY.get());
                        output.accept(ReefItems.HOGFISH_BACON.get());
                        output.accept(ReefItems.COOKED_HOGFISH_BACON.get());
                        output.accept(ReefItems.ULTRA_BACON_SANDWICH.get());
                        output.accept(ReefItems.JELLY_BOTTLE.get());
                        output.accept(ReefItems.JELLY_SANDWICH.get());
                        output.accept(ReefItems.JELLY_TART.get());
                        output.accept(ReefItems.IDOL_COOKIE.get());
                        output.accept(ReefItems.PARROTFISH_PUNCH.get());
                        output.accept(ReefItems.PIPEFISH_SUSHI.get());
                        output.accept(ReefItems.CHOCOLATE_RAY_MUFFIN.get());
                        output.accept(ReefItems.DRIED_SEAHORSE.get());
                        output.accept(ReefItems.SHARKBITE_SALAD.get());
                        output.accept(ReefItems.TANGY_SOUP.get());

                        output.accept(ReefItems.SEASUGAR_SORBET.get());
                        output.accept(ReefItems.ROCKFISH_CANDY.get());
                        output.accept(ReefItems.FORBIDDEN_SOUP.get());
                        output.accept(ReefItems.SWEET_TOOTH_SEABURGER.get());
                        output.accept(ReefItems.HAWAIIAN_BARBEQUE.get());
                        output.accept(ReefItems.TROPICAL_FISHSTICKS.get());
                        output.accept(ReefItems.SURF_N_TURF.get());

                        output.accept(ReefItems.CLAW_DISC.get());


                        output.accept(ReefBlocks.BROWN_SEA_ANEMONE.get());
                        output.accept(ReefBlocks.RED_SEA_ANEMONE.get());
                        output.accept(ReefBlocks.PURPLE_SEA_ANEMONE.get());
                        output.accept(ReefBlocks.MAGENTA_SEA_ANEMONE.get());
                        output.accept(ReefBlocks.PINK_SEA_ANEMONE.get());

                        output.accept(ReefBlocks.CARMINE_STARFISH.get());
                        output.accept(ReefBlocks.CERULEAN_STARFISH.get());
                        output.accept(ReefBlocks.CHARTREUSE_STARFISH.get());
                        output.accept(ReefBlocks.FUCHSIA_STARFISH.get());
                        output.accept(ReefBlocks.SAFFRON_STARFISH.get());
                        output.accept(ReefBlocks.TANGERINE_STARFISH.get());
                        output.accept(ReefBlocks.UMBER_STARFISH.get());
                        output.accept(ReefBlocks.VIOLET_STARFISH.get());

                        output.accept(ReefBlocks.JELLY_BLOCK.get());

                        output.accept(ReefBlocks.BUBBLER.get());

                        output.accept(ReefBlocks.PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.WHITE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.LIGHT_GRAY_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.GRAY_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.BLACK_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.BROWN_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.RED_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.ORANGE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.YELLOW_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.LIME_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.GREEN_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.CYAN_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.LIGHT_BLUE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.BLUE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.PURPLE_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.MAGENTA_PUFFER_LANTERN.get());
                        output.accept(ReefBlocks.PINK_PUFFER_LANTERN.get());

                        output.accept(ReefBlocks.WHITE_STAINED_SAND.get());
                        output.accept(ReefBlocks.LIGHT_GRAY_STAINED_SAND.get());
                        output.accept(ReefBlocks.GRAY_STAINED_SAND.get());
                        output.accept(ReefBlocks.BLACK_STAINED_SAND.get());
                        output.accept(ReefBlocks.BROWN_STAINED_SAND.get());
                        output.accept(ReefBlocks.RED_STAINED_SAND.get());
                        output.accept(ReefBlocks.ORANGE_STAINED_SAND.get());
                        output.accept(ReefBlocks.YELLOW_STAINED_SAND.get());
                        output.accept(ReefBlocks.LIME_STAINED_SAND.get());
                        output.accept(ReefBlocks.GREEN_STAINED_SAND.get());
                        output.accept(ReefBlocks.CYAN_STAINED_SAND.get());
                        output.accept(ReefBlocks.LIGHT_BLUE_STAINED_SAND.get());
                        output.accept(ReefBlocks.BLUE_STAINED_SAND.get());
                        output.accept(ReefBlocks.PURPLE_STAINED_SAND.get());
                        output.accept(ReefBlocks.MAGENTA_STAINED_SAND.get());
                        output.accept(ReefBlocks.PINK_STAINED_SAND.get());

                        output.accept(ReefBlocks.FINE_SAND.get());
                        output.accept(ReefBlocks.WHITE_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.LIGHT_GRAY_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.GRAY_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.BLACK_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.BROWN_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.RED_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.ORANGE_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.YELLOW_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.LIME_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.GREEN_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.CYAN_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.LIGHT_BLUE_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.BLUE_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.PURPLE_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.MAGENTA_STAINED_FINE_SAND.get());
                        output.accept(ReefBlocks.PINK_STAINED_FINE_SAND.get());
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
                        output.accept(ReefBlocks.BUSH_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.FLOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.HAND_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.RING_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.ROSE_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.SHELF_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.TOWER_CORAL_BLOCK.get());

                        output.accept(ReefBlocks.DEAD_BARREL_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL_BLOCK.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL_BLOCK.get());

                        output.accept(ReefBlocks.BARREL_CORAL.get());
                        output.accept(ReefBlocks.BUSH_CORAL.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.FLOWER_CORAL.get());
                        output.accept(ReefBlocks.HAND_CORAL.get());
                        output.accept(ReefBlocks.RING_CORAL.get());
                        output.accept(ReefBlocks.ROSE_CORAL.get());
                        output.accept(ReefBlocks.SHELF_CORAL.get());
                        output.accept(ReefBlocks.TOWER_CORAL.get());

                        output.accept(ReefBlocks.DEAD_BARREL_CORAL.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL.get());

                        output.accept(ReefBlocks.TALL_BARREL_CORAL.get());
                        output.accept(ReefBlocks.TALL_BUSH_CORAL.get());
                        output.accept(ReefBlocks.TALL_CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.TALL_FLOWER_CORAL.get());
                        output.accept(ReefBlocks.TALL_HAND_CORAL.get());
                        output.accept(ReefBlocks.TALL_RING_CORAL.get());
                        output.accept(ReefBlocks.TALL_ROSE_CORAL.get());
                        output.accept(ReefBlocks.TALL_SHELF_CORAL.get());
                        output.accept(ReefBlocks.TALL_TOWER_CORAL.get());

                        output.accept(ReefBlocks.DEAD_TALL_BARREL_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_BUSH_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_CHIMNEY_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_FLOWER_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_HAND_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_RING_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_ROSE_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_SHELF_CORAL.get());
                        output.accept(ReefBlocks.DEAD_TALL_TOWER_CORAL.get());

                        output.accept(ReefBlocks.BARREL_CORAL_FAN.get());
                        output.accept(ReefBlocks.BUSH_CORAL_FAN.get());
                        output.accept(ReefBlocks.CHIMNEY_CORAL_FAN.get());
                        output.accept(ReefBlocks.FLOWER_CORAL_FAN.get());
                        output.accept(ReefBlocks.HAND_CORAL_FAN.get());
                        output.accept(ReefBlocks.RING_CORAL_FAN.get());
                        output.accept(ReefBlocks.ROSE_CORAL_FAN.get());
                        output.accept(ReefBlocks.SHELF_CORAL_FAN.get());
                        output.accept(ReefBlocks.TOWER_CORAL_FAN.get());

                        output.accept(ReefBlocks.DEAD_BARREL_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_BUSH_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_CHIMNEY_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_FLOWER_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_HAND_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_RING_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_ROSE_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_SHELF_CORAL_FAN.get());
                        output.accept(ReefBlocks.DEAD_TOWER_CORAL_FAN.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RAINBOW_REEF_VARIANTS_TAB = CREATIVE_MODE_TABS.register("rainbow_reef_variants_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ReefItems.TANG_BUCKET.get()))
                    .withTabsBefore(RAINBOW_REEF_TAB.getId())
                    .title(Component.translatable("creativetab.rainbow_reef_variants_tab"))
                    .displayItems((parameters, output) -> {
                        variantsByRarity(parameters, output, ReefItems.ANGELFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.ANGELFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.ARROW_CRAB_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.ARROW_CRAB.get()));
                        variantsByRarity(parameters, output, ReefItems.BASSLET_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.BASSLET.get()));
                        variantsByRarity(parameters, output, ReefItems.BILLFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.BILLFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.BOXFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.BOXFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.BUTTERFLYFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.BUTTERFLYFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.CLOWNFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.CLOWNFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.CRAB_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.CRAB.get()));
                        variantsByRarity(parameters, output, ReefItems.DAMSELFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.DAMSELFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.DOLPHIN_BUCKET.get(), ReefMobVariants.registryFor(EntityType.DOLPHIN));
                        variantsByRarity(parameters, output, ReefItems.DWARF_ANGELFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.DWARF_ANGELFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.FROGFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.FROGFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.FUSILIER_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.FUSILIER.get()));
                        variantsByRarity(parameters, output, ReefItems.GOBY_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.GOBY.get()));
                        variantsByRarity(parameters, output, ReefItems.HOGFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.HOGFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.JELLYFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.JELLYFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.LARGE_SHARK_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.LARGE_SHARK.get()));
                        variantsByRarity(parameters, output, ReefItems.LIONFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.LIONFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.MAHI_MAHI_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.MAHI_MAHI.get()));
                        variantsByRarity(parameters, output, ReefItems.MAORI_WRASSE_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.MAORI_WRASSE.get()));
                        variantsByRarity(parameters, output, ReefItems.MOORISH_IDOL_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.MOORISH_IDOL.get()));
                        variantsByRarity(parameters, output, ReefItems.PARROTFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.PARROTFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.PIPEFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.PIPEFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.RABBITFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.RABBITFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.RAY_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.RAY.get()));
                        variantsByRarity(parameters, output, ReefItems.SEAHORSE_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.SEAHORSE.get()));
                        variantsByRarity(parameters, output, ReefItems.SHARK_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.SHARK.get()));
                        variantsByRarity(parameters, output, ReefItems.SMALL_SHARK_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.SMALL_SHARK.get()));
                        variantsByRarity(parameters, output, ReefItems.TANG_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.TANG.get()));
                        variantsByRarity(parameters, output, ReefItems.TRIGGERFISH_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.TRIGGERFISH.get()));
                        variantsByRarity(parameters, output, ReefItems.WRASSE_BUCKET.get(), ReefMobVariants.registryFor(ReefEntities.WRASSE.get()));
                    })
                    .build());

    private static void variantsByRarity(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output, Item bucket, ResourceKey<Registry<ReefMobVariant>> registryKey) {
        parameters.holders().lookupOrThrow(registryKey).listElements().sorted(Comparator.comparing((Holder.Reference<ReefMobVariant> holder) ->
                        holder.value().rarity()).thenComparing(holder ->
                        holder.key().location())).forEach(holder -> variantBucket(output, bucket, holder));
    }

    private static void variantBucket(CreativeModeTab.Output output, Item bucket, Holder.Reference<ReefMobVariant> variant) {
        ItemStack stack = new ItemStack(bucket);
        CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, tag -> tag.putString(ReefVariantMob.VARIANT_TAG, variant.key().location().toString()));
        output.accept(stack);
    }
}
