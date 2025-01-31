package net.thevaliantsquidward.rainbowreef.event;



import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.block.ModBlocks;
import net.thevaliantsquidward.rainbowreef.entity.ModEntities;
import net.thevaliantsquidward.rainbowreef.entity.custom.*;
import net.thevaliantsquidward.rainbowreef.item.ModItems;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerProfession.FISHERMAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            ItemStack emeraldsExpensive = new ItemStack(Items.EMERALD, 64);
            ItemStack fishBucket = new ItemStack(ModItems.TANG_BUCKET.get());

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
            ItemStack coralStone = new ItemStack(ModBlocks.POLISHED_CORALSTONE.get(), 4);

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