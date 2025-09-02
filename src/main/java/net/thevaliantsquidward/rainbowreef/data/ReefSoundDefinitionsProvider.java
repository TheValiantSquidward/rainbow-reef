package net.thevaliantsquidward.rainbowreef.data;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class ReefSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public ReefSoundDefinitionsProvider(PackOutput packOutput, ExistingFileHelper helper) {
        super(packOutput, RainbowReef.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        this.sound(ReefSoundEvents.FISH_HURT,
                sound("entity/fish/hurt1"),
                sound("entity/fish/hurt2"),
                sound("entity/fish/hurt3"),
                sound("entity/fish/hurt4")
        );
        this.sound(ReefSoundEvents.FISH_DEATH,
                sound("entity/fish/hurt1"),
                sound("entity/fish/hurt2"),
                sound("entity/fish/hurt3"),
                sound("entity/fish/hurt4")
        );
        this.sound(ReefSoundEvents.FISH_FLOP,
                sound("entity/fish/flop1").volume(0.3F),
                sound("entity/fish/flop2").volume(0.3F),
                sound("entity/fish/flop3").volume(0.3F),
                sound("entity/fish/flop4").volume(0.3F)
        );
        this.sound(ReefSoundEvents.FISH_JUMP,
                sound("mob/dolphin/jump1").volume(0.75F),
                sound("mob/dolphin/jump2").volume(0.75F),
                sound("mob/dolphin/jump3").volume(0.75F)
        );

        this.sound(ReefSoundEvents.CRAB_HURT,
                sound("entity/fish/hurt1"),
                sound("entity/fish/hurt2"),
                sound("entity/fish/hurt3"),
                sound("entity/fish/hurt4")
        );
        this.sound(ReefSoundEvents.CRAB_DEATH,
                sound("entity/fish/hurt1"),
                sound("entity/fish/hurt2"),
                sound("entity/fish/hurt3"),
                sound("entity/fish/hurt4")
        );
        // what.
        this.sound(ReefSoundEvents.CRAB_FLOP,
                sound("entity/fish/flop1").volume(0.3F),
                sound("entity/fish/flop2").volume(0.3F),
                sound("entity/fish/flop3").volume(0.3F),
                sound("entity/fish/flop4").volume(0.3F)
        );

        this.sound(ReefSoundEvents.JELLYFISH_HURT,
                sound(RainbowReef.modPrefix("entity/jellyfish/hurt1"))
        );
        this.sound(ReefSoundEvents.JELLYFISH_ZAP,
                sound(RainbowReef.modPrefix("entity/jellyfish/zap1")),
                sound(RainbowReef.modPrefix("entity/jellyfish/zap2"))
        );

        this.sound(ReefSoundEvents.JELLY_BLOCK_BOUNCE,
                sound(RainbowReef.modPrefix("block/jelly_block/bounce1"))
        );

        this.sound(ReefSoundEvents.CLAW_DISC,
                sound(RainbowReef.modPrefix("music/disc/claw")).stream()
        );
    }

    private void soundDefinition(Supplier<SoundEvent> soundEvent, String subtitle, SoundDefinition.Sound... sounds) {
        this.add(soundEvent.get(), SoundDefinition.definition().subtitle("subtitles.rainbowreef." + subtitle).with(sounds));
    }

    private void sound(Supplier<SoundEvent> soundEvent, SoundDefinition.Sound... sounds){
        this.soundDefinition(soundEvent, soundEvent.get().getLocation().getPath(), sounds);
    }
}
