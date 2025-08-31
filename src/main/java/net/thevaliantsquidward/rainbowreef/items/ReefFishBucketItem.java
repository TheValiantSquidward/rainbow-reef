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
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.entity.*;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class ReefFishBucketItem extends MobBucketItem {

    public ReefFishBucketItem(Supplier<? extends EntityType<?>> fishType, Item.Properties properties) {
        super(fishType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, properties.stacksTo(1));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EntityType<?> fishType = getFishType();

        if (fishType == ReefEntities.ANGELFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Angelfish.AngelfishVariant angelfishVariant = Angelfish.AngelfishVariant.variantId(variant);
                String name = "entity.rainbowreef.angelfish.variant_" + angelfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.ARROW_CRAB.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.arrow_crab.variant_" + ArrowCrab.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BASSLET.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Basslet.BassletVariant bassletVariant = Basslet.BassletVariant.variantId(variant);
                String name = "entity.rainbowreef.basslet.variant_" + bassletVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BOXFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Boxfish.BoxfishVariant boxfishVariant = Boxfish.BoxfishVariant.variantId(variant);
                String name = "entity.rainbowreef.boxfish.variant_" + boxfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BUTTERFLYFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Butterflyfish.ButterflyfishVariant butterflyfishVariant = Butterflyfish.ButterflyfishVariant.variantId(variant);
                String name = "entity.rainbowreef.butterflyfish.variant_" + butterflyfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.CLOWNFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Clownfish.ClownfishVariant clownfishVariant = Clownfish.ClownfishVariant.variantId(variant);
                String name = "entity.rainbowreef.clownfish.variant_" + clownfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.CRAB.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.crab.variant_" + Crab.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.DWARFANGEL.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.dwarf_angelfish.variant_" + DwarfAngelfish.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.GOBY.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Goby.GobyVariant gobyVariant = Goby.GobyVariant.variantId(variant);
                String name = "entity.rainbowreef.goby.variant_" + gobyVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.HOGFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.hogfish.variant_" + Hogfish.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.JELLYFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.jellyfish.variant_" + Jellyfish.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.MOORISH_IDOL.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.moorish_idol.variant_" + MoorishIdol.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.PARROTFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.parrotfish.variant_" + Parrotfish.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.PIPEFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.pipefish.variant_" + Pipefish.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.RAY.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.ray.variant_" + Ray.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.SEAHORSE.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.seahorse.variant_" + Seahorse.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.SMALL_SHARK.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.small_shark.variant_" + SmallShark.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.TANG.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                String name = "entity.rainbowreef.tang.variant_" + Tang.getVariantName(variant);
                tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
}
