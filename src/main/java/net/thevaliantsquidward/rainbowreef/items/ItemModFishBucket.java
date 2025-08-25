package net.thevaliantsquidward.rainbowreef.items;


import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.entity.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ItemModFishBucket extends MobBucketItem {
    public ItemModFishBucket(Supplier<? extends EntityType<?>> fishTypeIn, Fluid fluid, Item.Properties builder) {
        super(fishTypeIn, () -> fluid, () -> SoundEvents.BUCKET_EMPTY_FISH, builder.stacksTo(1));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EntityType fishType = getFishType();
        if (fishType == ReefEntities.GOBY.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.goby.variant_" + Goby.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.JELLYFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.jellyfish.variant_" + Jellyfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.CRAB.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.crab.variant_" + Crab.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.ARROW_CRAB.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.arrow_crab.variant_" + ArrowCrab.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.RAY.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.ray.variant_" + Ray.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.ANGELFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.angelfish.variant_" + Angelfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.MOORISH_IDOL.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.moorish_idol.variant_" + MoorishIdol.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.TANG.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.tang.variant_" + Tang.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }
        if (fishType == ReefEntities.CLOWNFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.clownfish.variant_" + Clownfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BOXFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.boxfish.variant_" + Boxfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }
        if (fishType == ReefEntities.SMALL_SHARK.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.small_shark.variant_" + SmallShark.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.SEAHORSE.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.seahorse.variant_" + Seahorse.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.PIPEFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.pipefish.variant_" + Pipefish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.DWARFANGEL.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.dwarf_angelfish.variant_" + DwarfAngelfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BUTTERFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.butterflyfish.variant_" + Butterfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.PARROTFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.parrotfish.variant_" + Parrotfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.HOGFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.hogfish.variant_" + Hogfish.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BASSLET.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int i = compoundnbt.getInt("BucketVariantTag");
                String s = "entity.rainbowreef.basslet.variant_" + Basslet.getVariantName(i);
                tooltip.add((Component.translatable(s)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }


    }
}
