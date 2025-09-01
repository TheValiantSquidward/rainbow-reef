package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

@Mod.EventBusSubscriber(modid = RainbowReef.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ReefSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RainbowReef.MOD_ID);

    // empty but still here for resource packs ig
    public static final RegistryObject<SoundEvent> FISH_IDLE = registerSoundEvent("fish_idle");

    public static final RegistryObject<SoundEvent> FISH_HURT = registerSoundEvent("fish_hurt");
    public static final RegistryObject<SoundEvent> FISH_DEATH = registerSoundEvent("fish_death");
    public static final RegistryObject<SoundEvent> FISH_FLOP = registerSoundEvent("fish_flop");

    // crab
    public static final RegistryObject<SoundEvent> CRAB_IDLE = registerSoundEvent("crab_idle");
    public static final RegistryObject<SoundEvent> CRAB_HURT = registerSoundEvent("crab_hurt");
    public static final RegistryObject<SoundEvent> CRAB_DEATH = registerSoundEvent("crab_death");
    public static final RegistryObject<SoundEvent> CRAB_FLOP = registerSoundEvent("crab_flop");

    public static final RegistryObject<SoundEvent> JELLYFISH_ZAP = registerSoundEvent("jellyfish_zap");
    public static final RegistryObject<SoundEvent> JELLYFISH_HURT = registerSoundEvent("jellyfish_hurt");

    public static final RegistryObject<SoundEvent> JELLY_BLOCK_BOUNCE = registerSoundEvent("jelly_block_bounce");

    public static final RegistryObject<SoundEvent> CLAW_DISC = registerSoundEvent("claw_disc");

    private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RainbowReef.MOD_ID, soundName)));
    }
}
