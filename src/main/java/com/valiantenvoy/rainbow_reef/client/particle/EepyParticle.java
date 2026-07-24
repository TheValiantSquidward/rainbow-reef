package com.valiantenvoy.rainbow_reef.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;

public class EepyParticle extends SimpleAnimatedParticle {

    private final float sinOffset;
    private final float cosOffset;
    private final float rotationDirection;
    private final float initialX;
    private final float initialZ;

    private EepyParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites, 0.0F);
        this.xd = (float) xSpeed;
        this.yd = (float) ySpeed;
        this.zd = (float) zSpeed;
        this.lifetime = 60 + this.random.nextInt(20);
        this.gravity = -0.065F;
        this.sinOffset = this.random.nextFloat();
        this.cosOffset = this.random.nextFloat();
        this.rotationDirection = this.random.nextBoolean() ? 1 : -1;
        this.initialX = (float) x;
        this.initialZ = (float) z;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        float radius = 0.2F;
        float speed = 0.1F;
        float xTarget = this.initialX + (float) Math.cos(this.age * speed + this.cosOffset * 4.0F) * radius * this.rotationDirection;
        float zTarget = this.initialZ + (float) Math.sin(this.age * speed + this.sinOffset * 4.0F) * radius * this.rotationDirection;
        this.xd = 0.25F * (xTarget - x);
        this.zd = 0.25F * (zTarget - z);
        this.setAlpha(1.0F - (this.age / (float) this.lifetime));
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp(1.0F - (this.age / (float) this.lifetime), 0.0F, 1.0F);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EepyParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
