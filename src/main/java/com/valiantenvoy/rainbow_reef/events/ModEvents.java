package com.valiantenvoy.rainbow_reef.events;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.RainbowReefConfig;
import com.valiantenvoy.rainbow_reef.entity.*;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = RainbowReef.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onInteractEntity(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        ItemStack stack = event.getItemStack();
        InteractionHand hand = event.getHand();

        if (stack.is(Items.WATER_BUCKET) && target.isAlive() && target.getType() == EntityType.TURTLE && RainbowReefConfig.BUCKETABLE_TURTLES.get()) {
            target.playSound(((Bucketable) target).getPickupSound(), 1.0F, 1.0F);
            ItemStack bucketStack = ((Bucketable) target).getBucketItemStack();
            ((Bucketable) target).saveToBucketTag(bucketStack);
            ItemStack filledResult = ItemUtils.createFilledResult(stack, player, bucketStack, false);
            player.setItemInHand(hand, filledResult);
            if (!target.level().isClientSide) {
                CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) player, bucketStack);
            }
            target.discard();
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide()));
        }
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent entity) {
        entity.register(ReefEntities.ANGELFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.ARROW_CRAB.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.OCEAN_FLOOR, Crab::checkSpawnRules, Operation.AND);
        entity.register(ReefEntities.BASSLET.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.BILLFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.BOXFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.BUTTERFLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.CLOWNFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.CRAB.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.OCEAN_FLOOR, Crab::checkSpawnRules, Operation.AND);
        entity.register(ReefEntities.DAMSELFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.DWARF_ANGELFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.FROGFISH.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.OCEAN_FLOOR, Frogfish::checkSpawnRules, Operation.AND);
        entity.register(ReefEntities.FUSILIER.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.GOBY.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.HOGFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.JELLYFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.LARGE_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.LIONFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.MAHI_MAHI.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.MAORI_WRASSE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.MOORISH_IDOL.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.PARROTFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.PIPEFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.RABBITFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.RAY.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.SEAHORSE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.SMALL_SHARK.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.TANG.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.TRIGGERFISH.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
        entity.register(ReefEntities.WRASSE.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ReefMob::canSpawn, Operation.AND);
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
        event.put(ReefEntities.ARROW_CRAB.get(), ArrowCrab.createAttributes());
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