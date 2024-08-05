package net.thevaliantsquidward.rainbowreef.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RainbowReef.MOD_ID);


    public static final RegistryObject<SoundEvent> JELLYZAP = registerSoundEvents("jellyzap");
    public static final RegistryObject<SoundEvent> JELLYHIT = registerSoundEvents("jellyhit");
    public static final RegistryObject<SoundEvent> JELLYBOUNCE = registerSoundEvents("jellybounce");

    public static final RegistryObject<SoundEvent> CLAW = registerSoundEvents("claw");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RainbowReef.MOD_ID, name)));
    }
    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}
