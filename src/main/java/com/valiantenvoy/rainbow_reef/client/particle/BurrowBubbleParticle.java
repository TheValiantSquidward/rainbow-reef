package com.valiantenvoy.rainbow_reef.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BubbleColumnUpParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BurrowBubbleParticle extends BubbleColumnUpParticle {

    private static final int POP_SOUND_CHANCE = 2;
    private static final float POP_SOUND_VOLUME = 1.0F;

    protected BurrowBubbleParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.lifetime = (int) (32.0F / (Math.random() * 0.8F + 0.2F));
    }

    @Override
    public void remove() {
        if (!this.removed) {
            this.level.addParticle(ParticleTypes.BUBBLE_POP, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
            if (this.random.nextInt(POP_SOUND_CHANCE) == 0) {
                this.level.playLocalSound(this.x, this.y, this.z, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, POP_SOUND_VOLUME, 0.9F + this.random.nextFloat() * 0.4F, false);
            }
        }
        super.remove();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            BurrowBubbleParticle particle = new BurrowBubbleParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.sprites);
            return particle;
        }
    }
}
