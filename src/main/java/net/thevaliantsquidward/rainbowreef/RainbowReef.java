package net.thevaliantsquidward.rainbowreef;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;
import net.thevaliantsquidward.rainbowreef.client.renderer.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefLootModifiers;
import net.thevaliantsquidward.rainbowreef.registry.ReefSounds;
import net.thevaliantsquidward.rainbowreef.util.RRPOI;
import net.thevaliantsquidward.rainbowreef.registry.ReefFeatures;

import java.util.Locale;

@Mod(RainbowReef.MOD_ID)
public class RainbowReef {
    public static final String MOD_ID = "rainbowreef";

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
    }

    public RainbowReef() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ReefCreativeTabs.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

        ReefEntities.register(modEventBus);
        ReefItems.register(modEventBus);
        ReefSounds.register(modEventBus);
        ReefLootModifiers.register(modEventBus);
        ReefFeatures.FEATURES.register(modEventBus);
        RRPOI.DEF_REG.register(modEventBus);
        ReefBlocks.BLOCKS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ReefEntities.GOBY.get(), GobyRenderer:: new);
            EntityRenderers.register(ReefEntities.BOXFISH.get(), BoxfishRenderer:: new);
            EntityRenderers.register(ReefEntities.CLOWNFISH.get(), ClownfishRenderer:: new);
            EntityRenderers.register(ReefEntities.BUTTERFISH.get(), ButterfishRenderer:: new);
            EntityRenderers.register(ReefEntities.DWARFANGEL.get(), DwarfAngelfishRenderer:: new);
            EntityRenderers.register(ReefEntities.HOGFISH.get(), HogfishRenderer:: new);
            EntityRenderers.register(ReefEntities.BASSLET.get(), BassletRenderer:: new);
            EntityRenderers.register(ReefEntities.MOORISH_IDOL.get(), MoorishIdolRenderer:: new);
            EntityRenderers.register(ReefEntities.ARROW_CRAB.get(), ArrowCrabRenderer:: new);
            EntityRenderers.register(ReefEntities.JELLYFISH.get(), JellyfishRenderer:: new);
            EntityRenderers.register(ReefEntities.LIONFISH.get(), LionfishRenderer:: new);
        }
    }
}
