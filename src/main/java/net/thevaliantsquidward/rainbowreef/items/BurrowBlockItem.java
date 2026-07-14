package net.thevaliantsquidward.rainbowreef.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.Block;
import net.thevaliantsquidward.rainbowreef.entity.Basslet;
import net.thevaliantsquidward.rainbowreef.entity.Crab;
import net.thevaliantsquidward.rainbowreef.entity.Goby;
import net.thevaliantsquidward.rainbowreef.entity.base.ReefMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BurrowBlockItem extends BlockItem {

    public BurrowBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        CompoundTag tag = stack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY).getUnsafe();
        ListTag occupants = tag.getList("Occupants", Tag.TAG_COMPOUND);
        for (int i = 0; i < occupants.size(); i++) {
            Component line = describeOccupant(occupants.getCompound(i).getCompound("EntityData"));
            if (line != null) {
                tooltip.add(line);
            }
        }
    }

    @Nullable
    private static Component describeOccupant(CompoundTag entityData) {
        EntityType<?> type = EntityType.by(entityData).orElse(null);
        if (type == null) {
            return null;
        }
        int variant = entityData.getInt("Variant");
        String variantKey = null;
        ReefMob.ReefRarities rarity = ReefMob.ReefRarities.COMMON;

        if (type == ReefEntities.GOBY.get()) {
            Goby.GobyVariant gobyVariant = Goby.GobyVariant.getVariantId(variant);
            variantKey = "entity.rainbowreef.goby.variant_" + gobyVariant.getSerializedName();
            rarity = gobyVariant.getRarity();
        } else if (type == ReefEntities.BASSLET.get()) {
            Basslet.BassletVariant bassletVariant = Basslet.BassletVariant.getVariantId(variant);
            variantKey = "entity.rainbowreef.basslet.variant_" + bassletVariant.getSerializedName();
            rarity = bassletVariant.getRarity();
        } else if (type == ReefEntities.CRAB.get()) {
            variantKey = "entity.rainbowreef.crab.variant_" + Crab.getVariantName(variant);
        }

        MutableComponent line = variantKey == null
                ? Component.empty().append(type.getDescription())
                : Component.translatable("tooltip.rainbowreef.burrow_occupant", Component.translatable(variantKey), type.getDescription());
        return line.withStyle(rarity.getStyle()).withStyle(ChatFormatting.ITALIC);
    }
}
