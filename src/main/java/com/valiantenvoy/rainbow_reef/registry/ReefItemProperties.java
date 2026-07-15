package com.valiantenvoy.rainbow_reef.registry;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.items.ReefFishBucketItem;

public class ReefItemProperties {

    public static void registerItemProperties() {
        for (DeferredHolder<Item, ? extends Item> item : ReefItems.ITEMS.getEntries()) {
            if (item.get() instanceof ReefFishBucketItem) {
                registerVariantProperties(item.get());
            }
        }
    }

    public static void registerVariantProperties(Item item) {
        ItemProperties.register(item, RainbowReef.location("variant"), (stack, level, entity, seed) -> stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).getUnsafe().getInt("BucketVariantTag"));
    }
}
