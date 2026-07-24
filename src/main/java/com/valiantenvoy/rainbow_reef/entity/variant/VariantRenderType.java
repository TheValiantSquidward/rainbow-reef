package com.valiantenvoy.rainbow_reef.entity.variant;

import com.mojang.serialization.Codec;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.neoforged.neoforge.client.NeoForgeRenderTypes;

import java.util.function.Function;

public enum VariantRenderType implements StringRepresentable {

    ENTITY_CUTOUT("entity_cutout", RenderType::entityCutout),
    ENTITY_CUTOUT_NO_CULL("entity_cutout_no_cull", RenderType::entityCutoutNoCull),
    ENTITY_CUTOUT_MIPPED("entity_cutout_mipped", NeoForgeRenderTypes::getEntityCutoutMipped),
    ENTITY_TRANSLUCENT("entity_translucent",  RenderType::entityTranslucent),
    ENTITY_TRANSLUCENT_CULL("entity_translucent_cull",  RenderType::entityTranslucentCull),
    ENTITY_TRANSLUCENT_EMISSIVE("entity_translucent_emissive",  RenderType::entityTranslucentEmissive);

    public static final Codec<VariantRenderType> CODEC = StringRepresentable.fromEnum(VariantRenderType::values);

    private final String name;
    private final Function<ResourceLocation, RenderType> renderType;

    VariantRenderType(String name, Function<ResourceLocation, RenderType> renderType) {
        this.name = name;
        this.renderType = renderType;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public RenderType getRenderType(ResourceLocation resourceLocation) {
        return this.renderType.apply(resourceLocation);
    }
}