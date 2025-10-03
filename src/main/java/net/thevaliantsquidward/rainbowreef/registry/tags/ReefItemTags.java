package net.thevaliantsquidward.rainbowreef.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public class ReefItemTags {

    public static final TagKey<Item> RAINBOW_REEF_CRABS = modItemTag("rainbow_reef_crabs");
    public static final TagKey<Item> CRABS = modItemTag("crabs");

    private static TagKey<Item> modItemTag(String name) {
        return itemTag(RainbowReef.MOD_ID, name);
    }

    private static TagKey<Item> forgeItemTag(String name) {
        return itemTag("forge", name);
    }

    public static TagKey<Item> itemTag(String modid, String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(modid, name));
    }
}
