package net.thevaliantsquidward.rainbowreef.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.ModEntities;
import net.thevaliantsquidward.rainbowreef.entity.custom.*;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntities.GOBY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, GobyEntity::canSpawn);
            SpawnPlacements.register(ModEntities.TANG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, TangEntity::canSpawn);
            SpawnPlacements.register(ModEntities.BOXFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BoxfishEntity::canSpawn);
            SpawnPlacements.register(ModEntities.SMALL_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SmallSharkEntity::canSpawn);
            SpawnPlacements.register(ModEntities.CLOWNFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ClownfishEntity::canSpawn);
            SpawnPlacements.register(ModEntities.BUTTERFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ButterfishEntity::canSpawn);
            SpawnPlacements.register(ModEntities.SEAHORSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SeahorseEntity::canSpawn);
            SpawnPlacements.register(ModEntities.DWARFANGEL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, DwarfAngelfishEntity::canSpawn);
            SpawnPlacements.register(ModEntities.PARROTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ParrotfishEntity::canSpawn);
            SpawnPlacements.register(ModEntities.HOGFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, HogfishEntity::canSpawn);
            SpawnPlacements.register(ModEntities.BASSLET.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BassletEntity::canSpawn);
            SpawnPlacements.register(ModEntities.PIPEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, PipefishEntity::canSpawn);

        });

    }
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GOBY.get(), GobyEntity.setAttributes());
        event.put(ModEntities.TANG.get(), TangEntity.setAttributes());
        event.put(ModEntities.BOXFISH.get(), BoxfishEntity.setAttributes());
        event.put(ModEntities.SMALL_SHARK.get(), SmallSharkEntity.setAttributes());
        event.put(ModEntities.CLOWNFISH.get(), ClownfishEntity.setAttributes());
        event.put(ModEntities.BUTTERFISH.get(), ClownfishEntity.setAttributes());
        event.put(ModEntities.SEAHORSE.get(), SeahorseEntity.setAttributes());
        event.put(ModEntities.DWARFANGEL.get(), DwarfAngelfishEntity.setAttributes());
        event.put(ModEntities.PARROTFISH.get(), ParrotfishEntity.setAttributes());
        event.put(ModEntities.HOGFISH.get(), HogfishEntity.setAttributes());
        event.put(ModEntities.BASSLET.get(), BassletEntity.setAttributes());
        event.put(ModEntities.PIPEFISH.get(), PipefishEntity.setAttributes());
        event.put(ModEntities.RAY.get(), RayEntity.setAttributes());
    }

}