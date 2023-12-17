package net.thevaliantsquidward.rainbowreef;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thevaliantsquidward.rainbowreef.entity.ModEntities;
import net.thevaliantsquidward.rainbowreef.entity.client.renders.*;
import net.thevaliantsquidward.rainbowreef.item.ModCreativeModeTabs;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import org.slf4j.Logger;


@Mod(RainbowReef.MOD_ID)
public class RainbowReef
{
    public static final String MOD_ID = "rainbowreef";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RainbowReef()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus);

        ModEntities.register(modEventBus);

        ModItems.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register
                    (ModEntities.TANG.get(), TangRenderer::new);
            EntityRenderers.register
                    (ModEntities.GOBY.get(), GobyRenderer::new);
            EntityRenderers.register
                    (ModEntities.BOXFISH.get(), BoxfishRenderer::new);
            EntityRenderers.register
                    (ModEntities.SMALL_SHARK.get(), SmallSharkRenderer::new);
            EntityRenderers.register
                    (ModEntities.CLOWNFISH.get(), ClownfishRenderer::new);
            EntityRenderers.register
                    (ModEntities.BUTTERFISH.get(), ButterfishRenderer::new);
            EntityRenderers.register
                    (ModEntities.SEAHORSE.get(), SeahorseRenderer::new);
            EntityRenderers.register
                    (ModEntities.DWARFANGEL.get(), DwarfAngelfishRenderer::new);
            EntityRenderers.register
                    (ModEntities.PARROTFISH.get(), ParrotfishRenderer:: new);
            EntityRenderers.register
                    (ModEntities.HOGFISH.get(), HogfishRenderer::new);
        }
    }
}
