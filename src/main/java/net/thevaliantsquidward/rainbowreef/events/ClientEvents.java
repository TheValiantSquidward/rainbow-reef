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
        event.registerEntityRenderer(ReefEntities.CLOWNFISH.get(), ClownfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.PARROTFISH.get(), ParrotfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.PIPEFISH.get(), PipefishRenderer::new);
        event.registerEntityRenderer(ReefEntities.RAY.get(), EagleRayRenderer::new);
        event.registerEntityRenderer(ReefEntities.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(ReefEntities.SMALL_SHARK.get(), SmallSharkRenderer::new);
        event.registerEntityRenderer(ReefEntities.TANG.get(), TangRenderer::new);
        event.registerEntityRenderer(ReefEntities.BUTTERFISH.get(), ButterfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.ARROW_CRAB.get(), ArrowCrabRenderer::new);
        event.registerEntityRenderer(ReefEntities.DWARFANGEL.get(), DwarfAngelfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.BOXFISH.get(), BoxfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.BASSLET.get(), BassletRenderer::new);
        event.registerEntityRenderer(ReefEntities.MOORISH_IDOL.get(), MoorishIdolRenderer::new);
        event.registerEntityRenderer(ReefEntities.JELLYFISH.get(), JellyfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.HOGFISH.get(), HogfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.GOBY.get(), GobyRenderer::new);
        event.registerEntityRenderer(ReefEntities.LIONFISH.get(), LionfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.MAHI_MAHI.get(), MahiRenderer::new);
        event.registerEntityRenderer(ReefEntities.BILLFISH.get(), BillfishRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ReefModelLayers.ANGELFISH_LAYER, AngelfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.CRAB_LAYER, CrabModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.CLOWNFISH_LAYER, ClownfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.PARROTFISH_LAYER, ParrotfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.PIPEFISH_LAYER, PipefishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.RAY_LAYER, EagleRayModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SEAHORSE_LAYER, SeahorseModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.SMALL_SHARK_LAYER, SmallSharkModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.TANG_LAYER, TangModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BUTTERFISH_LAYER, ButterfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.ARROWCRAB_LAYER, ArrowCrabModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.DWARF_ANGELFISH_LAYER, DwarfAngelfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BOXFISH_LAYER, BoxfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BASSLET_LAYER, BassletModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.IDOL_LAYER, MoorishIdolModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.JELLYFISH_LAYER, JellyfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.HOGFISH_LAYER, HogfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.GOBY_LAYER, GobyModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.LIONFISH_LAYER, LionfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.MAHI_LAYER, MahiModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.BILLFISH_LAYER, BillfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.MAORI_WRASSE_LAYER, MaoriWrasseModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.FROGFISH_LAYER, FrogfishModel::createBodyLayer);
    }
}
