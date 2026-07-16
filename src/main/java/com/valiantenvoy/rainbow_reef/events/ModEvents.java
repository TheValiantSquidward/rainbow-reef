package com.valiantenvoy.rainbow_reef.events;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.*;
import com.valiantenvoy.rainbow_reef.registry.ReefBlocks;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

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

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent entity) {
        entity.register(ReefEntities.ANGELFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Angelfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.ARROW_CRAB.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ArrowCrab::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.BASSLET.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Basslet::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.BILLFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Billfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.BOXFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Boxfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.BUTTERFLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Butterflyfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.CLOWNFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Clownfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.CRAB.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Crab::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.DAMSELFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Damselfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.DWARF_ANGELFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DwarfAngelfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.FROGFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Frogfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.FUSILIER.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Fusilier::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.GOBY.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Goby::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.HOGFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Hogfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.JELLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Jellyfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.LARGE_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LargeShark::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.LIONFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lionfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.MAHI_MAHI.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MahiMahi::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.MAORI_WRASSE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MaoriWrasse::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.MOORISH_IDOL.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MoorishIdol::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.PARROTFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parrotfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.PIPEFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pipefish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.RABBITFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Rabbitfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.RAY.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Ray::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.SEAHORSE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Seahorse::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Shark::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.SMALL_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SmallShark::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.TANG.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Tang::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.TRIGGERFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Triggerfish::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
        entity.register(ReefEntities.WRASSE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Wrasse::canSpawn, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ReefEntities.GOBY.get(), Goby.createAttributes());
        event.put(ReefEntities.TANG.get(), Tang.createAttributes());
        event.put(ReefEntities.BOXFISH.get(), Boxfish.createAttributes());
        event.put(ReefEntities.SMALL_SHARK.get(), SmallShark.createAttributes());
        event.put(ReefEntities.CLOWNFISH.get(), Clownfish.createAttributes());
        event.put(ReefEntities.BUTTERFLYFISH.get(), Butterflyfish.createAttributes());
        event.put(ReefEntities.SEAHORSE.get(), Seahorse.createAttributes());
        event.put(ReefEntities.DWARF_ANGELFISH.get(), DwarfAngelfish.createAttributes());
        event.put(ReefEntities.PARROTFISH.get(), Parrotfish.createAttributes());
        event.put(ReefEntities.HOGFISH.get(), Hogfish.createAttributes());
        event.put(ReefEntities.BASSLET.get(), Basslet.createAttributes());
        event.put(ReefEntities.PIPEFISH.get(), Pipefish.createAttributes());
        event.put(ReefEntities.RAY.get(), Ray.createAttributes());
        event.put(ReefEntities.CRAB.get(), Crab.createAttributes());
        event.put(ReefEntities.MOORISH_IDOL.get(), MoorishIdol.createAttributes());
        event.put(ReefEntities.ANGELFISH.get(), Angelfish.createAttributes());
        event.put(ReefEntities.ARROW_CRAB.get(), ArrowCrab.setAttributes());
        event.put(ReefEntities.JELLYFISH.get(), Jellyfish.createAttributes());
        event.put(ReefEntities.LIONFISH.get(), Lionfish.createAttributes());
        event.put(ReefEntities.MAHI_MAHI.get(), MahiMahi.createAttributes());
        event.put(ReefEntities.BILLFISH.get(), Billfish.createAttributes());
        event.put(ReefEntities.MAORI_WRASSE.get(), MaoriWrasse.createAttributes());
        event.put(ReefEntities.FROGFISH.get(), Frogfish.createAttributes());
        event.put(ReefEntities.SHARK.get(), Shark.createAttributes());
        event.put(ReefEntities.LARGE_SHARK.get(), LargeShark.createAttributes());
        event.put(ReefEntities.WRASSE.get(), Wrasse.createAttributes());
        event.put(ReefEntities.TRIGGERFISH.get(), Triggerfish.createAttributes());
        event.put(ReefEntities.DAMSELFISH.get(), Damselfish.createAttributes());
        event.put(ReefEntities.RABBITFISH.get(), Rabbitfish.createAttributes());
        event.put(ReefEntities.FUSILIER.get(), Fusilier.createAttributes());
    }
}