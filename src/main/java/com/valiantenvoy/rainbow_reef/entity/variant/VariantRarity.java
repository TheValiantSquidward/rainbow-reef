package com.valiantenvoy.rainbow_reef.entity.variant;

import com.mojang.serialization.Codec;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.util.StringRepresentable;

import java.util.function.UnaryOperator;

public enum VariantRarity implements StringRepresentable {

    COMMON("common", style -> style.withColor(ChatFormatting.GRAY), 60),
    UNCOMMON("uncommon", style -> style.withColor(ChatFormatting.YELLOW), 30),
    RARE("rare", style -> style.withColor(ChatFormatting.AQUA), 15),
    EPIC("epic", style -> style.withColor(ChatFormatting.LIGHT_PURPLE), 5),
    ABERRANT("aberrant", style -> style.withColor(15933054), 1);

    public static final Codec<VariantRarity> CODEC = StringRepresentable.fromEnum(VariantRarity::values);

    private final String name;
    private final UnaryOperator<Style> style;
    private final int weight;

    VariantRarity(String name, UnaryOperator<Style> style, int weight) {
        this.name = name;
        this.style = style;
        this.weight = weight;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getWeight() {
        return this.weight;
    }

    public UnaryOperator<Style> getStyle() {
        return this.style;
    }
}