package net.thevaliantsquidward.rainbowreef.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.*;
import net.thevaliantsquidward.rainbowreef.client.renderer.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItemProperties;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        event.enqueueWork(ReefItemProperties::registerItemProperties);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ReefEntities.ANGELFISH.get(), AngelfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.CRAB.get(), CrabRenderer::new);
        event.registerEntityRenderer(ReefEntities.PARROTFISH.get(), ParrotfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.PIPEFISH.get(), PipefishRenderer::new);
        event.registerEntityRenderer(ReefEntities.RAY.get(), EagleRayRenderer::new);
        event.registerEntityRenderer(ReefEntities.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(ReefEntities.SMALL_SHARK.get(), SmallSharkRenderer::new);
        event.registerEntityRenderer(ReefEntities.TANG.get(), TangRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ReefModelLayers.ANGELFISH_LAYER, AngelfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.CRAB_LAYER, CrabModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.PARROTFISH_LAYER, ParrotfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.PIPEFISH_LAYER, PipefishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.RAY_LAYER, EagleRayModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SEAHORSE_LAYER, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SMALL_SHARK_LAYER, SmallSharkModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.TANG_LAYER, TangModel::createBodyLayer);
    }
}
