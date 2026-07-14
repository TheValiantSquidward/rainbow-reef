package net.thevaliantsquidward.rainbowreef.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.ModList;
import net.thevaliantsquidward.rainbowreef.entity.*;
import net.thevaliantsquidward.rainbowreef.items.tooltip.ReefMobTooltipData;
import net.thevaliantsquidward.rainbowreef.registry.ReefEntities;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

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
    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        if (ModList.get().isLoaded("spawn")) {
            CompoundTag tag = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).copyTag();
            tag.putString("id", EntityType.getKey(this.getFishType()).toString());
            return Optional.of(new ReefMobTooltipData(tag));
        }
        return super.getTooltipImage(stack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull Item.TooltipContext context, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        EntityType<?> fishType = getFishType();

        CompoundTag compoundnbt = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY).getUnsafe();
        if (!compoundnbt.contains("BucketVariantTag", 3)) {
            return;
        }
        int variant = compoundnbt.getInt("BucketVariantTag");

        if (fishType == ReefEntities.ANGELFISH.get()) {
            Angelfish.AngelfishVariant angelfishVariant = Angelfish.AngelfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.angelfish.variant_" + angelfishVariant.name().toLowerCase(Locale.ROOT);
            tooltip.add((Component.translatable(name)).withStyle(angelfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.ARROW_CRAB.get()) {
            String name = "entity.rainbowreef.arrow_crab.variant_" + ArrowCrab.getVariantName(variant);
            tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.BASSLET.get()) {
            Basslet.BassletVariant bassletVariant = Basslet.BassletVariant.getVariantId(variant);
            String name = "entity.rainbowreef.basslet.variant_" + bassletVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(bassletVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.BOXFISH.get()) {
            Boxfish.BoxfishVariant boxfishVariant = Boxfish.BoxfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.boxfish.variant_" + boxfishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(boxfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.BUTTERFLYFISH.get()) {
            Butterflyfish.ButterflyfishVariant butterflyfishVariant = Butterflyfish.ButterflyfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.butterflyfish.variant_" + butterflyfishVariant.name().toLowerCase(Locale.ROOT);
            tooltip.add((Component.translatable(name)).withStyle(butterflyfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.CLOWNFISH.get()) {
            Clownfish.ClownfishVariant clownfishVariant = Clownfish.ClownfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.clownfish.variant_" + clownfishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(clownfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.CRAB.get()) {
            String name = "entity.rainbowreef.crab.variant_" + Crab.getVariantName(variant);
            tooltip.add((Component.translatable(name)).withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.DWARF_ANGELFISH.get()) {
            DwarfAngelfish.DwarfAngelfishVariant dwarfAngelfishVariant = DwarfAngelfish.DwarfAngelfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.dwarf_angelfish.variant_" + dwarfAngelfishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(dwarfAngelfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.GOBY.get()) {
            Goby.GobyVariant gobyVariant = Goby.GobyVariant.getVariantId(variant);
            String name = "entity.rainbowreef.goby.variant_" + gobyVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(gobyVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.HOGFISH.get()) {
            Hogfish.HogfishVariant hogfishVariant = Hogfish.HogfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.hogfish.variant_" + hogfishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(hogfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.JELLYFISH.get()) {
            Jellyfish.JellyfishVariant jellyfishVariant = Jellyfish.JellyfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.jellyfish.variant_" + jellyfishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(jellyfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.MOORISH_IDOL.get()) {
            MoorishIdol.MoorishIdolVariant moorishIdolVariant = MoorishIdol.MoorishIdolVariant.getVariantId(variant);
            String name = "entity.rainbowreef.moorish_idol.variant_" + moorishIdolVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(moorishIdolVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.PARROTFISH.get()) {
            Parrotfish.ParrotfishVariant parrotfishVariant = Parrotfish.ParrotfishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.parrotfish.variant_" + parrotfishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(parrotfishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.PIPEFISH.get()) {
            Pipefish.PipefishVariant pipefishVariant = Pipefish.PipefishVariant.getVariantId(variant);
            String name = "entity.rainbowreef.pipefish.variant_" + pipefishVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(pipefishVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.RAY.get()) {
            Ray.RayVariant rayVariant = Ray.RayVariant.getVariantId(variant);
            String name = "entity.rainbowreef.ray.variant_" + rayVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(rayVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.SEAHORSE.get()) {
            Seahorse.SeahorseVariant seahorseVariant = Seahorse.SeahorseVariant.getVariantId(variant);
            String name = "entity.rainbowreef.seahorse.variant_" + seahorseVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(seahorseVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.SMALL_SHARK.get()) {
            SmallShark.SmallSharkVariant smallSharkVariant = SmallShark.SmallSharkVariant.getVariantId(variant);
            String name = "entity.rainbowreef.small_shark.variant_" + smallSharkVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(smallSharkVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }

        if (fishType == ReefEntities.TANG.get()) {
            Tang.TangVariant tangVariant = Tang.TangVariant.getVariantId(variant);
            String name = "entity.rainbowreef.tang.variant_" + tangVariant.getSerializedName();
            tooltip.add((Component.translatable(name)).withStyle(tangVariant.getRarity().getStyle()).withStyle(ChatFormatting.ITALIC));
        }
    }
}
