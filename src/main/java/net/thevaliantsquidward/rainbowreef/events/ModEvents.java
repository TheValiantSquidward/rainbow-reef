package net.thevaliantsquidward.rainbowreef.events;



import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FISHERMAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack emeraldsExpensive = new ItemStack(Items.EMERALD, 64);
            ItemStack fishBucket = new ItemStack(ReefItems.TANG_BUCKET.get());

            CompoundTag nbt = fishBucket.getOrCreateTag();
            nbt.putInt("BucketVariantTag", 10);
            fishBucket.setTag(nbt);

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