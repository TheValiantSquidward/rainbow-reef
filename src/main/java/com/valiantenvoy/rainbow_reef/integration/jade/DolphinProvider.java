package com.valiantenvoy.rainbow_reef.integration.jade;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariantUtils;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefMobVariants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Dolphin;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.TooltipPosition;
import snownee.jade.api.config.IPluginConfig;

import java.util.Locale;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class DolphinProvider implements IEntityComponentProvider {

    @Override
    public ResourceLocation getUid() {
        return RainbowReef.location("dolphin");
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        Dolphin dolphin = (Dolphin) accessor.getEntity();
        if (dolphin instanceof ReefVariantMob variantMob) {
            RegistryAccess registries = accessor.getLevel().registryAccess();
            if (ReefMobVariantUtils.variantCount(registries, ReefMobVariants.registryFor(dolphin.getType())) <= 1) {
                return;
            }
            ResourceLocation fishId = EntityType.getKey(dolphin.getType());
            ResourceLocation variantId = variantMob.getVariantId();
            Optional<Holder.Reference<ReefMobVariant>> variant = ReefMobVariantUtils.byId(registries, ReefMobVariants.registryFor(dolphin.getType()), variantId);
            UnaryOperator<Style> style = variant.map(holder -> holder.value().rarity().getStyle()).orElse(style1 -> style1.withColor(ChatFormatting.GRAY));
            String name = "entity." + fishId.getNamespace() + "." + fishId.getPath() + ".variant_" + variantId.getPath().toLowerCase(Locale.ROOT);
            tooltip.add((Component.translatable(name)).withStyle(style).withStyle(ChatFormatting.ITALIC));
        }
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 100;
    }
}
