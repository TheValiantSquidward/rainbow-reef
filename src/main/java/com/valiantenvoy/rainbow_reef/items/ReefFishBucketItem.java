package com.valiantenvoy.rainbow_reef.items;

import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariantUtils;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefVariantMob;
import com.valiantenvoy.rainbow_reef.items.tooltip.ReefMobTooltipData;
import com.valiantenvoy.rainbow_reef.registry.ReefMobVariants;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.ModList;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ReefFishBucketItem extends MobBucketItem {

    private final Supplier<? extends EntityType<?>> fishTypeSupplier;

    public ReefFishBucketItem(Supplier<? extends EntityType<?>> fishType) {
        super(fishType.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1));
        this.fishTypeSupplier = fishType;
    }

    public EntityType<?> getFishType() {
        return this.fishTypeSupplier.get();
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        if (ModList.get().isLoaded("teallib")) {
            CompoundTag compoundTag = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).copyTag();
            compoundTag.putString("id", EntityType.getKey(this.getFishType()).toString());
            return Optional.of(new ReefMobTooltipData(compoundTag));
        }
        return super.getTooltipImage(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        EntityType<?> fishType = this.getFishType();
        ResourceLocation fishId = EntityType.getKey(fishType);
        CompoundTag compoundTag = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).copyTag();
        if (!compoundTag.contains(ReefVariantMob.VARIANT_TAG, 8)) {
            return;
        }
        ResourceLocation variantId = ResourceLocation.parse(compoundTag.getString(ReefVariantMob.VARIANT_TAG));

        Optional<Holder.Reference<ReefMobVariant>> variant = ReefMobVariantUtils.byId(context.registries(), ReefMobVariants.registryFor(fishType), variantId);
        UnaryOperator<Style> style = variant.map(holder -> holder.value().rarity().getStyle()).orElse(style1 -> style1.withColor(ChatFormatting.GRAY));

        String name = "entity." + fishId.getNamespace() + "." + fishId.getPath() + ".variant_" + variantId.getPath().toLowerCase(Locale.ROOT);
        tooltip.add((Component.translatable(name)).withStyle(style).withStyle(ChatFormatting.ITALIC));
    }
}
