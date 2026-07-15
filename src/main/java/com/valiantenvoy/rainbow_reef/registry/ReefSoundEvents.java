package com.valiantenvoy.rainbow_reef.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.valiantenvoy.rainbow_reef.RainbowReef;

public class ReefSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, RainbowReef.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> FISH_HURT = registerSoundEvent("fish_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> FISH_DEATH = registerSoundEvent("fish_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> FISH_FLOP = registerSoundEvent("fish_flop");
    public static final DeferredHolder<SoundEvent, SoundEvent> FISH_JUMP = registerSoundEvent("fish_jump");

    // crab
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_HURT = registerSoundEvent("crab_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_DEATH = registerSoundEvent("crab_death");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_FLOP = registerSoundEvent("crab_flop");

    public static final DeferredHolder<SoundEvent, SoundEvent> JELLYFISH_ZAP = registerSoundEvent("jellyfish_zap");
    public static final DeferredHolder<SoundEvent, SoundEvent> JELLYFISH_HURT = registerSoundEvent("jellyfish_hurt");

    public static final DeferredHolder<SoundEvent, SoundEvent> JELLY_BLOCK_BOUNCE = registerSoundEvent("jelly_block_bounce");

    public static final DeferredHolder<SoundEvent, SoundEvent> CLAW_DISC = registerSoundEvent("claw_disc");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(RainbowReef.location(soundName)));
    }
}
