package net.thevaliantsquidward.rainbowreef.events;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.AngelfishModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.TangModel;
import net.thevaliantsquidward.rainbowreef.client.renderer.AngelfishRenderer;
import net.thevaliantsquidward.rainbowreef.client.renderer.TangRenderer;
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
        event.registerEntityRenderer(ReefEntities.TANG.get(), TangRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ReefModelLayers.ANGELFISH_LAYER, AngelfishModel::createBodyLayer);
        event.registerLayerDefinition(ReefModelLayers.TANG_LAYER, TangModel::createBodyLayer);
    }
}
