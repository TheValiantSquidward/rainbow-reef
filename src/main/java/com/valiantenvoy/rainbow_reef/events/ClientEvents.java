package com.valiantenvoy.rainbow_reef.events;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.RainbowReefConfig;
import com.valiantenvoy.rainbow_reef.client.models.entity.*;
import com.valiantenvoy.rainbow_reef.client.particle.BurrowBubbleParticle;
import com.valiantenvoy.rainbow_reef.client.renderer.*;
import com.valiantenvoy.rainbow_reef.client.renderer.item.ReefMobTooltipRenderer;
import com.valiantenvoy.rainbow_reef.items.tooltip.ReefMobTooltipData;
import com.valiantenvoy.rainbow_reef.registry.*;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

import static com.valiantenvoy.rainbow_reef.client.renderer.ReefMobRenderer.createRenderer;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = RainbowReef.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        event.enqueueWork(ReefItemProperties::registerItemProperties);
    }

    @SubscribeEvent
    public static void registerTooltipComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ReefMobTooltipData.class, ReefMobTooltipRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ReefParticleTypes.BURROW_BUBBLE.get(), BurrowBubbleParticle.Provider::new);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerVanillaEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        if (RainbowReefConfig.DYEABLE_FISHING_RODS.getAsBoolean()) {
            event.registerEntityRenderer(EntityType.FISHING_BOBBER, ReefFishingHookRenderer::new);
        }
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
        event.registerEntityRenderer(ReefEntities.DWARF_ANGELFISH.get(), createRenderer(ReefModelLayers.DWARF_ANGELFISH, DwarfAngelfishModel::new, 0.3F));
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
        event.registerEntityRenderer(ReefEntities.WRASSE.get(), WrasseRenderer::new);
        event.registerEntityRenderer(ReefEntities.TRIGGERFISH.get(), TriggerfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.DAMSELFISH.get(), DamselfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.RABBITFISH.get(), RabbitfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.FROGFISH.get(), FrogfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.FUSILIER.get(), FusilierRenderer::new);
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
        event.registerLayerDefinition(ReefModelLayers.WRASSE, WrasseModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.TRIGGERFISH, TriggerfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.DAMSELFISH, DamselfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.RABBITFISH, RabbitfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.FUSILIER, FusilierModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> {
                    if (level == null || pos == null) {
                        return 0x1787d4;
                    }
                    return BiomeColors.getAverageWaterColor(level, pos);
                },
                ReefBlocks.FINE_GLASS.get()
        );
    }
}
