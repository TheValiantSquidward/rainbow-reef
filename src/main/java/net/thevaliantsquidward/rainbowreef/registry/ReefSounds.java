package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

public class ReefSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, RainbowReef.MOD_ID);




    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(RainbowReef.location(name)));
    }
    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}
