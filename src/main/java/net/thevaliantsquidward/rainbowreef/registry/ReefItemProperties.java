package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.items.ReefFishBucketItem;

public class ReefItemProperties {

    public static void registerItemProperties() {
        for (RegistryObject<Item> item : ReefItems.ITEMS.getEntries()) {
            if (item.get() instanceof ReefFishBucketItem) {
                registerVariantProperties(item.get());
            }
        }
    }

    public static void registerVariantProperties(Item item) {
        ItemProperties.register(item, new ResourceLocation(RainbowReef.MOD_ID, "variant"), (stack, world, player, i) -> stack.hasTag() ? stack.getOrCreateTag().getInt("BucketVariantTag") : 0);
    }
}
