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

    public ReefFishBucketItem(Supplier<? extends EntityType<?>> fishType) {
        super(fishType, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, new Item.Properties().stacksTo(1));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        EntityType<?> fishType = getFishType();

        if (fishType == ReefEntities.ANGELFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Angelfish.AngelfishVariant angelfishVariant = Angelfish.AngelfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.angelfish.variant_" + angelfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(angelfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
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
                Basslet.BassletVariant bassletVariant = Basslet.BassletVariant.getVariantId(variant);
                String name = "entity.rainbowreef.basslet.variant_" + bassletVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(bassletVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BOXFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Boxfish.BoxfishVariant boxfishVariant = Boxfish.BoxfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.boxfish.variant_" + boxfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(boxfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.BUTTERFLYFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Butterflyfish.ButterflyfishVariant butterflyfishVariant = Butterflyfish.ButterflyfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.butterflyfish.variant_" + butterflyfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(butterflyfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.CLOWNFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Clownfish.ClownfishVariant clownfishVariant = Clownfish.ClownfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.clownfish.variant_" + clownfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(clownfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
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

        if (fishType == ReefEntities.DWARF_ANGELFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                DwarfAngelfish.DwarfAngelfishVariant dwarfAngelfishVariant = DwarfAngelfish.DwarfAngelfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.dwarf_angelfish.variant_" + dwarfAngelfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(dwarfAngelfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.GOBY.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Goby.GobyVariant gobyVariant = Goby.GobyVariant.getVariantId(variant);
                String name = "entity.rainbowreef.goby.variant_" + gobyVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(gobyVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.HOGFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Hogfish.HogfishVariant hogfishVariant = Hogfish.HogfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.hogfish.variant_" + hogfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(hogfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.JELLYFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Jellyfish.JellyfishVariant jellyfishVariant = Jellyfish.JellyfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.jellyfish.variant_" + jellyfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(jellyfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.MOORISH_IDOL.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                MoorishIdol.MoorishIdolVariant moorishIdolVariant = MoorishIdol.MoorishIdolVariant.getVariantId(variant);
                String name = "entity.rainbowreef.moorish_idol.variant_" + moorishIdolVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(moorishIdolVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.PARROTFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Parrotfish.ParrotfishVariant parrotfishVariant = Parrotfish.ParrotfishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.parrotfish.variant_" + parrotfishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(parrotfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.PIPEFISH.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Pipefish.PipefishVariant pipefishVariant = Pipefish.PipefishVariant.getVariantId(variant);
                String name = "entity.rainbowreef.pipefish.variant_" + pipefishVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(pipefishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.RAY.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Ray.RayVariant rayVariant = Ray.RayVariant.getVariantId(variant);
                String name = "entity.rainbowreef.ray.variant_" + rayVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(rayVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.SEAHORSE.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Seahorse.SeahorseVariant seahorseVariant = Seahorse.SeahorseVariant.getVariantId(variant);
                String name = "entity.rainbowreef.seahorse.variant_" + seahorseVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(seahorseVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.SMALL_SHARK.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                SmallShark.SmallSharkVariant smallSharkVariant = SmallShark.SmallSharkVariant.getVariantId(variant);
                String name = "entity.rainbowreef.small_shark.variant_" + smallSharkVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(smallSharkVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }

        if (fishType == ReefEntities.TANG.get()) {
            CompoundTag compoundnbt = stack.getTag();
            if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
                int variant = compoundnbt.getInt("BucketVariantTag");
                Tang.TangVariant tangVariant = Tang.TangVariant.getVariantId(variant);
                String name = "entity.rainbowreef.tang.variant_" + tangVariant.getSerializedName();
                tooltip.add((Component.translatable(name)).withStyle(tangVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
            }
        }
    }
}
