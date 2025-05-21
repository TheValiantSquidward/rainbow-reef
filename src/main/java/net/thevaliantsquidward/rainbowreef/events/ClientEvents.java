package net.thevaliantsquidward.rainbowreef.events;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;

@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID, bus = Bus.MOD)
public final class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        ItemProperties.register(ReefItems.SHARK_BUCKET.get(), new ResourceLocation(RainbowReef.MOD_ID, "variant"), (stack, world, player, i) -> stack.hasTag() ? stack.getOrCreateTag().getInt("BucketVariantTag") : 0);
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent entity) {
        entity.register(ModEntities.GOBY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, GobyEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.TANG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, TangEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.BOXFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BoxfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.SMALL_SHARK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SmallSharkEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.CLOWNFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ClownfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.BUTTERFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ButterfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.SEAHORSE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, SeahorseEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.DWARFANGEL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, DwarfAngelfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.PARROTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, ParrotfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.HOGFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, HogfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.PIPEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, PipefishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.RAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, RayEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.BASSLET.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, BassletEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrabEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.ARROW_CRAB.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ArrowCrabEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.ANGELFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, AngelfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.MOORISH_IDOL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, MoorishIdolEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
        entity.register(ModEntities.JELLYFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.WORLD_SURFACE, JellyfishEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GOBY.get(), GobyEntity.setAttributes());
        event.put(ModEntities.TANG.get(), TangEntity.setAttributes());
        event.put(ModEntities.BOXFISH.get(), BoxfishEntity.setAttributes());
        event.put(ModEntities.SMALL_SHARK.get(), SmallSharkEntity.setAttributes());
        event.put(ModEntities.CLOWNFISH.get(), ClownfishEntity.setAttributes());
        event.put(ModEntities.BUTTERFISH.get(), ButterfishEntity.setAttributes());
        event.put(ModEntities.SEAHORSE.get(), SeahorseEntity.setAttributes());
        event.put(ModEntities.DWARFANGEL.get(), DwarfAngelfishEntity.setAttributes());
        event.put(ModEntities.PARROTFISH.get(), ParrotfishEntity.setAttributes());
        event.put(ModEntities.HOGFISH.get(), HogfishEntity.setAttributes());
        event.put(ModEntities.BASSLET.get(), BassletEntity.setAttributes());
        event.put(ModEntities.PIPEFISH.get(), PipefishEntity.setAttributes());
        event.put(ModEntities.RAY.get(), RayEntity.setAttributes());
        event.put(ModEntities.CRAB.get(), CrabEntity.setAttributes());
        event.put(ModEntities.MOORISH_IDOL.get(), MoorishIdolEntity.setAttributes());
        event.put(ModEntities.ANGELFISH.get(), AngelfishEntity.setAttributes());
        event.put(ModEntities.ARROW_CRAB.get(), ArrowCrabEntity.setAttributes());
        event.put(ModEntities.JELLYFISH.get(), JellyfishEntity.setAttributes());
    }

}