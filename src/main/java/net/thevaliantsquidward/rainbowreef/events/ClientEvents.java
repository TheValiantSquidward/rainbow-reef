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
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ReefEntities.ANGELFISH.get(), AngelfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.CRAB.get(), CrabRenderer::new);
        event.registerEntityRenderer(ReefEntities.CLOWNFISH.get(), ClownfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.PARROTFISH.get(), ParrotfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.PIPEFISH.get(), PipefishRenderer::new);
        event.registerEntityRenderer(ReefEntities.RAY.get(), RayRenderer::new);
        event.registerEntityRenderer(ReefEntities.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(ReefEntities.SHARK.get(), SharkRenderer::new);
        event.registerEntityRenderer(ReefEntities.SMALL_SHARK.get(), SmallSharkRenderer::new);
        event.registerEntityRenderer(ReefEntities.TANG.get(), TangRenderer::new);
        event.registerEntityRenderer(ReefEntities.BUTTERFLYFISH.get(), ButterflyfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.ARROW_CRAB.get(), ArrowCrabRenderer::new);
        event.registerEntityRenderer(ReefEntities.DWARF_ANGELFISH.get(), DwarfAngelfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.BOXFISH.get(), BoxfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.BASSLET.get(), BassletRenderer::new);
        event.registerEntityRenderer(ReefEntities.MOORISH_IDOL.get(), MoorishIdolRenderer::new);
        event.registerEntityRenderer(ReefEntities.JELLYFISH.get(), JellyfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.HOGFISH.get(), HogfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.GOBY.get(), GobyRenderer::new);
        event.registerEntityRenderer(ReefEntities.LIONFISH.get(), LionfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.MAHI_MAHI.get(), MahiMahiRenderer::new);
        event.registerEntityRenderer(ReefEntities.MAORI_WRASSE.get(), MaoriWrasseRenderer::new);
        event.registerEntityRenderer(ReefEntities.BILLFISH.get(), BillfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.LARGE_SHARK.get(), LargeSharkRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ReefModelLayers.ANGELFISH, AngelfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.CRAB, CrabModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.CLOWNFISH, ClownfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.PARROTFISH, ParrotfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.PIPEFISH, PipefishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.RAY, RayModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SEAHORSE, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SMALL_SHARK, SmallSharkModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.TANG, TangModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BUTTERFLYFISH, ButterflyfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.ARROW_CRAB, ArrowCrabModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.DWARF_ANGELFISH, DwarfAngelfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BOXFISH, BoxfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BASSLET, BassletModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.MOORISH_IDOL, MoorishIdolModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.JELLYFISH, JellyfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.HOGFISH, HogfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.GOBY, GobyModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.LIONFISH, LionfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.MAHI_MAHI, MahiMahiModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BILLFISH, BillfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.MAORI_WRASSE, MaoriWrasseModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.FROGFISH, FrogfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SHARK, SharkModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.LARGE_SHARK, LargeSharkModel::createBodyLayer);
    }
}
