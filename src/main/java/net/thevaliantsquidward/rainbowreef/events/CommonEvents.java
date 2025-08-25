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
        entity.register(ReefEntities.GOBY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Goby::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.TANG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Tang::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BOXFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Boxfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.SMALL_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SmallShark::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.CLOWNFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Clownfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BUTTERFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Butterfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.SEAHORSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Seahorse::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.DWARFANGEL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, DwarfAngelfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.PARROTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Parrotfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.HOGFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Hogfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.PIPEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Pipefish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.RAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Ray::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BASSLET.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Basslet::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Crab::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.ARROW_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ArrowCrab::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.ANGELFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Angelfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.MOORISH_IDOL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, MoorishIdol::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, Jellyfish::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ReefEntities.GOBY.get(), Goby.setAttributes());
        event.put(ReefEntities.TANG.get(), Tang.setAttributes());
        event.put(ReefEntities.BOXFISH.get(), Boxfish.setAttributes());
        event.put(ReefEntities.SMALL_SHARK.get(), SmallShark.setAttributes());
        event.put(ReefEntities.CLOWNFISH.get(), Clownfish.setAttributes());
        event.put(ReefEntities.BUTTERFISH.get(), Butterfish.setAttributes());
        event.put(ReefEntities.SEAHORSE.get(), Seahorse.setAttributes());
        event.put(ReefEntities.DWARFANGEL.get(), DwarfAngelfish.setAttributes());
        event.put(ReefEntities.PARROTFISH.get(), Parrotfish.setAttributes());
        event.put(ReefEntities.HOGFISH.get(), Hogfish.setAttributes());
        event.put(ReefEntities.BASSLET.get(), Basslet.setAttributes());
        event.put(ReefEntities.PIPEFISH.get(), Pipefish.setAttributes());
        event.put(ReefEntities.RAY.get(), Ray.setAttributes());
        event.put(ReefEntities.CRAB.get(), Crab.setAttributes());
        event.put(ReefEntities.MOORISH_IDOL.get(), MoorishIdol.setAttributes());
        event.put(ReefEntities.ANGELFISH.get(), Angelfish.setAttributes());
        event.put(ReefEntities.ARROW_CRAB.get(), ArrowCrab.setAttributes());
        event.put(ReefEntities.JELLYFISH.get(), Jellyfish.setAttributes());
        event.put(ReefEntities.LIONFISH.get(), Lionfish.setAttributes());
        event.put(ReefEntities.MAHI_MAHI.get(), Mahi.setAttributes());
        event.put(ReefEntities.BILLFISH.get(), Billfish.setAttributes());
        event.put(ReefEntities.MAORI_WRASSE.get(), MaoriWrasse.setAttributes());
        event.put(ReefEntities.FROGFISH.get(), Frogfish.setAttributes());
    }
}