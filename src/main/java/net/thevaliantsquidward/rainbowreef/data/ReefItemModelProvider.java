package net.thevaliantsquidward.rainbowreef.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

import java.util.Objects;

import static net.thevaliantsquidward.rainbowreef.registry.ReefItems.*;

public class ReefItemModelProvider extends ItemModelProvider {

    public ReefItemModelProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), RainbowReef.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerModels() {
        this.item(ANGELFISH_BUCKET);
        this.item(RAW_ANGELFISH);

        this.item(ARROW_CRAB_BUCKET);
        this.item(RAW_ARROW_CRAB);

        this.item(BASSLET_BUCKET);
        this.item(RAW_BASSLET);
        this.item(BASSLET_COOKIE);

        this.item(BOXFISH_BUCKET);
        this.item(RAW_BOXFISH);
        this.item(BOXFISH_BREAD);

        this.item(BUTTERFLYFISH_BUCKET);
        this.item(RAW_BUTTERFLYFISH);
        this.item(BUTTERED_TOAST);

        this.item(CLOWNFISH_BUCKET);
        this.item(RAW_CLOWNFISH);
        this.item(CLOWNFISH_CUPCAKE);

        this.item(CRAB_BUCKET);
        this.item(RAW_CRAB);
        this.item(ROASTED_CRAB);
        this.item(CRAB_CAKE);

        this.item(DWARF_ANGELFISH_BUCKET);
        this.item(RAW_DWARF_ANGELFISH);
        this.item(DWARF_ANGELFISH_TART);

        this.item(GOBY_BUCKET);
        this.item(RAW_GOBY);
        this.item(GOBY_GUMMY);

        this.item(HOGFISH_BUCKET);
        this.item(RAW_HOGFISH);
        this.item(HOGFISH_BACON);
        this.item(COOKED_HOGFISH_BACON);
        this.item(ULTRA_BACON_SANDWICH);

        this.item(JELLYFISH_BUCKET);
        this.item(GLOB_OF_JELLY);
        this.item(JELLY_BOTTLE);
        this.item(JELLY_SANDWICH);
        this.item(JELLY_TART);

        this.item(MOORISH_IDOL_BUCKET);
        this.item(RAW_MOORISH_IDOL);
        this.item(IDOL_COOKIE);

        this.item(PARROTFISH_BUCKET);
        this.item(RAW_PARROTFISH);
        this.item(PARROTFISH_PUNCH);

        this.item(PIPEFISH_BUCKET);
        this.item(RAW_PIPEFISH);
        this.item(PIPEFISH_SUSHI);

        this.item(RAY_BUCKET);
        this.item(RAW_RAY);
        this.item(CHOCOLATE_RAY_MUFFIN);

        this.item(SEAHORSE_BUCKET);
        this.item(RAW_SEAHORSE);
        this.item(DRIED_SEAHORSE);

        this.item(SMALL_SHARK_BUCKET);
        this.item(RAW_SMALL_SHARK);
        this.item(SHARKBITE_SALAD);

        this.item(TANG_BUCKET);
        this.item(RAW_TANG);
        this.item(TANGY_SOUP);

        this.item(ROCKFISH_CANDY);
        this.item(FORBIDDEN_SOUP);
        this.item(SWEET_TOOTH_SEABURGER);
        this.item(HAWAIIAN_BARBEQUE);
        this.item(TROPICAL_FISHSTICKS);
        this.item(SURF_N_TURF);
        this.item(SEASUGAR_SORBET);
        this.item(CLAW_DISC);

        // spawn eggs
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof SpawnEggItem && Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getNamespace().equals(RainbowReef.MOD_ID)) {
                getBuilder(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath()).parent(getExistingFile(new ResourceLocation("item/template_spawn_egg")));
            }
        }
    }

    // item
    private ItemModelBuilder item(RegistryObject<Item> item) {
        return generated(item.getId().getPath(), modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder dnaItem(RegistryObject<Item> item) {
        return generated(item.getId().getPath(), modLoc("item/dna/" + item.getId().getPath()));
    }

    // utils
    private ItemModelBuilder generated(String name, ResourceLocation... layers) {
        ItemModelBuilder builder = withExistingParent(name, "item/generated");
        for (int i = 0; i < layers.length; i++) {
            builder = builder.texture("layer" + i, layers[i]);
        }
        return builder;
    }
}
