package net.thevaliantsquidward.rainbowreef.events;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;

@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent entity) {
        entity.register(ReefEntities.GOBY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Goby::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.TANG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Tang::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BOXFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Boxfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.SMALL_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SmallShark::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.CLOWNFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Clownfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BUTTERFLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Butterflyfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.SEAHORSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Seahorse::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.DWARFANGEL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DwarfAngelfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.PARROTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parrotfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.HOGFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Hogfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.PIPEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pipefish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.RAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Ray::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BASSLET.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Basslet::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Crab::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.ARROW_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ArrowCrab::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.ANGELFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Angelfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.MOORISH_IDOL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MoorishIdol::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Jellyfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BILLFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Billfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.FROGFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Frogfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.LIONFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Lionfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.MAHI_MAHI.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mahi::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.MAORI_WRASSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MaoriWrasse::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ReefEntities.GOBY.get(), Goby.createAttributes());
        event.put(ReefEntities.TANG.get(), Tang.setAttributes());
        event.put(ReefEntities.BOXFISH.get(), Boxfish.createAttributes());
        event.put(ReefEntities.SMALL_SHARK.get(), SmallShark.setAttributes());
        event.put(ReefEntities.CLOWNFISH.get(), Clownfish.setAttributes());
        event.put(ReefEntities.BUTTERFLYFISH.get(), Butterflyfish.setAttributes());
        event.put(ReefEntities.SEAHORSE.get(), Seahorse.setAttributes());
        event.put(ReefEntities.DWARFANGEL.get(), DwarfAngelfish.setAttributes());
        event.put(ReefEntities.PARROTFISH.get(), Parrotfish.setAttributes());
        event.put(ReefEntities.HOGFISH.get(), Hogfish.setAttributes());
        event.put(ReefEntities.BASSLET.get(), Basslet.createAttributes());
        event.put(ReefEntities.PIPEFISH.get(), Pipefish.setAttributes());
        event.put(ReefEntities.RAY.get(), Ray.setAttributes());
        event.put(ReefEntities.CRAB.get(), Crab.setAttributes());
        event.put(ReefEntities.MOORISH_IDOL.get(), MoorishIdol.setAttributes());
        event.put(ReefEntities.ANGELFISH.get(), Angelfish.createAttributes());
        event.put(ReefEntities.ARROW_CRAB.get(), ArrowCrab.setAttributes());
        event.put(ReefEntities.JELLYFISH.get(), Jellyfish.setAttributes());
        event.put(ReefEntities.LIONFISH.get(), Lionfish.setAttributes());
        event.put(ReefEntities.MAHI_MAHI.get(), Mahi.setAttributes());
        event.put(ReefEntities.BILLFISH.get(), Billfish.createAttributes());
        event.put(ReefEntities.MAORI_WRASSE.get(), MaoriWrasse.setAttributes());
        event.put(ReefEntities.FROGFISH.get(), Frogfish.setAttributes());
    }
}