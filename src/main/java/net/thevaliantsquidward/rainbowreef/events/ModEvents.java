package net.thevaliantsquidward.rainbowreef.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import java.util.List;

@EventBusSubscriber(modid = RainbowReef.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FISHERMAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack emeraldsExpensive = new ItemStack(Items.EMERALD, 64);
            ItemStack fishBucket = new ItemStack(ReefItems.TANG_BUCKET.get());

            CustomData.update(DataComponents.BUCKET_ENTITY_DATA, fishBucket, tag -> tag.putInt("BucketVariantTag", 10));

            trades.get(3).add(new BasicItemListing(
                    emeraldsExpensive,
                    fishBucket,
                    1,
                    30,
                    0.2f
            ));


        }

        if(event.getType() == VillagerProfession.MASON) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack emeralds = new ItemStack(Items.EMERALD);
            ItemStack coralStone = new ItemStack(ReefBlocks.POLISHED_CORALSTONE.get(), 4);

            trades.get(2).add(new BasicItemListing(
                    emeralds,
                    coralStone,
                    16,
                    10,
                    0.05f
            ));


        }
    }



}