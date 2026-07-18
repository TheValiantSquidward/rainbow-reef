package com.valiantenvoy.rainbow_reef.utils;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;

public class ColorNoise {

    public static final ColorNoise INSTANCE = new ColorNoise();
    private final PerlinSimplexNoise noise;

    public ColorNoise() {
        this.noise = new PerlinSimplexNoise(RandomSource.create(98756L), ImmutableList.of(-1, 0, 1));
    }

    public double getSample(double x, double z, float scale) {
        return this.noise.getValue(x / scale, z / scale, true);
    }

    public int applyNoise(BlockPos pos, int previousColor, float scale, float intensity){
        double noiseValue = this.getSample(pos.getX(),pos.getZ(),scale);
        double d1 = noiseValue * 16;
        d1 = Math.round(d1);
        noiseValue = d1 / 16;
        d1 = this.getSample(pos.getX(), pos.getZ(), 0.2F);
        int noiseValueInt = this.blend(previousColor,(float) noiseValue * intensity);
        return this.blend(noiseValueInt,(float) d1 * (intensity * 0.5F));
    }

    private float[] getAlphaColorArray(int hex) {
        return new float[]{((hex >> 24) & 255) / 255.0F, ((hex >> 16) & 255) / 255.0F, ((hex >> 8) & 255) / 255.0F, ((hex) & 255) / 255.0F};
    }

    private int toIntColor(float[] alphaColorArray) {
        return (((int) Math.floor(alphaColorArray[0] * 255) & 255) << 24) + (((int) Math.floor(alphaColorArray[1] * 255) & 255) << 16) + (((int) Math.floor(alphaColorArray[2] * 255) & 255) << 8) + ((int) Math.floor(alphaColorArray[3] * 255) & 255);
    }

    private int blend(int color1, float ratio) {
        final float[] rgb1 = this.getAlphaColorArray(color1);
        final float[] rgb2 = this.getAlphaColorArray(0);
        final float negative = 1-ratio;
        return this.toIntColor(new float[]{Mth.clamp(rgb2[0] * ratio + rgb1[0] * negative,0,1), Mth.clamp(rgb2[1] * ratio + rgb1[1] * negative,0,1), Mth.clamp(rgb2[2] * ratio + rgb1[2] * negative,0,1), Mth.clamp(rgb2[3] * ratio + rgb1[3] * negative,0,1)});
    }
}