package com.valiantenvoy.rainbow_reef.items;

import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariantUtils;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.registry.ReefMobVariants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class BurrowBlockItem extends BlockItem {

    public BurrowBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY).getUnsafe();
        ListTag occupants = compoundTag.getList("Occupants", Tag.TAG_COMPOUND);
        for (int i = 0; i < occupants.size(); i++) {
            Component line = describeOccupant(occupants.getCompound(i).getCompound("EntityData"), context);
            if (line != null) {
                tooltip.add(line);
            }
        }
    }

    @Nullable
    private static Component describeOccupant(CompoundTag entityData, TooltipContext context) {
        EntityType<?> fishType = EntityType.by(entityData).orElse(null);
        if (fishType == null) {
            return null;
        }
        ResourceLocation fishId = EntityType.getKey(fishType);
        ResourceLocation variantId = ResourceLocation.parse(entityData.getString(ReefVariantMob.VARIANT_TAG));

        Optional<Holder.Reference<ReefMobVariant>> variant = ReefMobVariantUtils.byId(context.registries(), ReefMobVariants.registryFor(fishType), variantId);
        UnaryOperator<Style> style = variant.map(holder -> holder.value().rarity().getStyle()).orElse(style1 -> style1.withColor(ChatFormatting.GRAY));

        String name = "entity." + fishId.getNamespace() + "." + fishId.getPath() + ".variant_" + variantId.getPath().toLowerCase(Locale.ROOT);
        MutableComponent line = Component.translatable("tooltip.rainbowreef.burrow_occupant", Component.translatable(name), fishType.getDescription());
        return line.withStyle(style).withStyle(ChatFormatting.ITALIC);
    }
}
