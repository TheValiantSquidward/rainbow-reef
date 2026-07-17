package com.valiantenvoy.rainbow_reef.events;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.*;
import com.valiantenvoy.rainbow_reef.client.particle.BurrowBubbleParticle;
import com.valiantenvoy.rainbow_reef.client.renderer.JellyfishRenderer;
import com.valiantenvoy.rainbow_reef.client.renderer.ParrotfishRenderer;
import com.valiantenvoy.rainbow_reef.client.renderer.ReefMobRenderer;
import com.valiantenvoy.rainbow_reef.client.renderer.item.ReefMobTooltipRenderer;
import com.valiantenvoy.rainbow_reef.items.tooltip.ReefMobTooltipData;
import com.valiantenvoy.rainbow_reef.registry.ReefEntities;
import com.valiantenvoy.rainbow_reef.registry.ReefItemProperties;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import com.valiantenvoy.rainbow_reef.registry.ReefParticleTypes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

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

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ReefEntities.ANGELFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.ANGELFISH, AngelfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.ARROW_CRAB.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.ARROW_CRAB, ArrowCrabModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.BASSLET.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.BASSLET, BassletModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.BILLFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.BILLFISH, BillfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.BOXFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.BOXFISH, BoxfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.BUTTERFLYFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.BUTTERFLYFISH, ButterflyfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.CLOWNFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.CLOWNFISH, ClownfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.CRAB.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.CRAB, CrabModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.DAMSELFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.DAMSELFISH, DamselfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.DWARF_ANGELFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.DWARF_ANGELFISH, DwarfAngelfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.FROGFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.FROGFISH, FrogfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.FUSILIER.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.FUSILIER, FusilierModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.GOBY.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.GOBY, GobyModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.HOGFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.HOGFISH, HogfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.JELLYFISH.get(), JellyfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.LARGE_SHARK.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.LARGE_SHARK, LargeSharkModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.LIONFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.LIONFISH, LionfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.MAHI_MAHI.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.MAHI_MAHI, MahiMahiModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.MAORI_WRASSE.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.MAORI_WRASSE, MaoriWrasseModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.MOORISH_IDOL.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.MOORISH_IDOL, MoorishIdolModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.PARROTFISH.get(), ParrotfishRenderer::new);
        event.registerEntityRenderer(ReefEntities.RABBITFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.RABBITFISH, RabbitfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.RAY.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.RAY, RayModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.SEAHORSE.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.SEAHORSE, SeahorseModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.SHARK.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.SHARK, SharkModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.SMALL_SHARK.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.SMALL_SHARK, SmallSharkModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.TANG.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.TANG, TangModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.TRIGGERFISH.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.TRIGGERFISH, TriggerfishModel::new, 0.25F));
        event.registerEntityRenderer(ReefEntities.WRASSE.get(), context -> new ReefMobRenderer<>(context, ReefModelLayers.WRASSE, WrasseModel::new, 0.25F));
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
}
