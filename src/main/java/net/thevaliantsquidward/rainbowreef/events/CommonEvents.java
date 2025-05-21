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
        entity.register(ReefEntities.GOBY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, GobyEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.TANG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, TangEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BOXFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BoxfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.SMALL_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SmallSharkEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.CLOWNFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ClownfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BUTTERFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ButterfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.SEAHORSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SeahorseEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.DWARFANGEL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, DwarfAngelfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.PARROTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ParrotfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.HOGFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, HogfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.PIPEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, PipefishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.RAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, RayEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.BASSLET.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BassletEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.ARROW_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ArrowCrabEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.ANGELFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, AngelfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.MOORISH_IDOL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, MoorishIdolEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ReefEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, JellyfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ReefEntities.GOBY.get(), GobyEntity.setAttributes());
        event.put(ReefEntities.TANG.get(), TangEntity.setAttributes());
        event.put(ReefEntities.BOXFISH.get(), BoxfishEntity.setAttributes());
        event.put(ReefEntities.SMALL_SHARK.get(), SmallSharkEntity.setAttributes());
        event.put(ReefEntities.CLOWNFISH.get(), ClownfishEntity.setAttributes());
        event.put(ReefEntities.BUTTERFISH.get(), ButterfishEntity.setAttributes());
        event.put(ReefEntities.SEAHORSE.get(), SeahorseEntity.setAttributes());
        event.put(ReefEntities.DWARFANGEL.get(), DwarfAngelfishEntity.setAttributes());
        event.put(ReefEntities.PARROTFISH.get(), ParrotfishEntity.setAttributes());
        event.put(ReefEntities.HOGFISH.get(), HogfishEntity.setAttributes());
        event.put(ReefEntities.BASSLET.get(), BassletEntity.setAttributes());
        event.put(ReefEntities.PIPEFISH.get(), PipefishEntity.setAttributes());
        event.put(ReefEntities.RAY.get(), RayEntity.setAttributes());
        event.put(ReefEntities.CRAB.get(), CrabEntity.setAttributes());
        event.put(ReefEntities.MOORISH_IDOL.get(), MoorishIdolEntity.setAttributes());
        event.put(ReefEntities.ANGELFISH.get(), AngelfishEntity.setAttributes());
        event.put(ReefEntities.ARROW_CRAB.get(), ArrowCrabEntity.setAttributes());
        event.put(ReefEntities.JELLYFISH.get(), JellyfishEntity.setAttributes());
    }
}