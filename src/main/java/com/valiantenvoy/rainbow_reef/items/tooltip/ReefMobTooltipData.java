package com.valiantenvoy.rainbow_reef.items.tooltip;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record ReefMobTooltipData(CompoundTag entityTag) implements TooltipComponent {
}
