package com.valiantenvoy.rainbow_reef.blocks;

import com.valiantenvoy.rainbow_reef.registry.ReefBlockEntities;
import com.valiantenvoy.rainbow_reef.registry.ReefSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class BurrowBlockEntity extends BlockEntity {

    public static final int MAX_OCCUPANTS = 3;
    private static final int MIN_STAY_TICKS = 400;
    private static final int MAX_STAY_TICKS = 1200;
    public static final String COOLDOWN_TAG = "BurrowCooldown";

    private final List<Occupant> occupants = new ArrayList<>();
    private boolean keepOccupantsOnBreak;

    public BurrowBlockEntity(BlockPos pos, BlockState state) {
        super(ReefBlockEntities.BURROW.get(), pos, state);
    }

    public boolean isEmpty() {
        return this.occupants.isEmpty();
    }

    public boolean isFull() {
        return this.occupants.size() >= MAX_OCCUPANTS;
    }

    public boolean keepOccupantsOnBreak() {
        return this.keepOccupantsOnBreak;
    }

    public void setKeepOccupantsOnBreak(boolean keepOccupantsOnBreak) {
        this.keepOccupantsOnBreak = keepOccupantsOnBreak;
    }

    public boolean canEnter(Mob mob) {
        if (this.isFull()) {
            return false;
        }
        return this.occupants.isEmpty()
                || EntityType.by(this.occupants.getFirst().entityData).filter(type -> type == mob.getType()).isPresent();
    }

    public void tryEnter(Mob mob) {
        if (this.level == null || this.level.isClientSide || !mob.isAlive() || !this.canEnter(mob)) {
            return;
        }
        mob.stopRiding();
        mob.ejectPassengers();
        CompoundTag tag = new CompoundTag();
        if (!mob.save(tag)) {
            return;
        }
        tag.remove("UUID");
        tag.remove("Passengers");
        tag.remove("Leash");
        tag.remove("leash");
        int stay = MIN_STAY_TICKS + this.level.random.nextInt(MAX_STAY_TICKS - MIN_STAY_TICKS);
        this.occupants.add(new Occupant(tag, 0, stay));
        this.level.playSound(null, this.worldPosition, ReefSoundEvents.ENTER_BURROW.get(), SoundSource.BLOCKS, 1.0F, 1.5F);
        mob.discard();
        this.setChanged();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, BurrowBlockEntity burrow) {
        boolean changed = false;
        Iterator<Occupant> iterator = burrow.occupants.iterator();
        while (iterator.hasNext()) {
            Occupant occupant = iterator.next();
            occupant.ticksInBurrow++;
            if (occupant.ticksInBurrow > occupant.minOccupationTicks) {
                if (burrow.releaseOccupant(level, pos, state, occupant)) {
                    iterator.remove();
                    changed = true;
                } else {
                    occupant.minOccupationTicks += 100;
                }
            }
        }
        if (changed) {
            burrow.setChanged();
        }
    }

    private boolean releaseOccupant(Level level, BlockPos pos, BlockState state, Occupant occupant) {
        BlockPos exit = findExitPos(level, pos, state);
        if (exit == null) {
            return false;
        }
        Entity entity = EntityType.loadEntityRecursive(occupant.entityData.copy(), level, e -> e);
        if (entity == null) {
            return true;
        }
        entity.moveTo(exit.getX() + 0.5D, exit.getY() + 0.1D, exit.getZ() + 0.5D, level.random.nextFloat() * 360.0F, 0.0F);
        applyCooldown(level, entity);
        level.playSound(null, pos, ReefSoundEvents.EXIT_BURROW.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return level.addFreshEntity(entity);
    }

    public void evacuate() {
        if (this.level == null || this.level.isClientSide) {
            return;
        }
        for (Occupant occupant : this.occupants) {
            Entity entity = EntityType.loadEntityRecursive(occupant.entityData.copy(), this.level, e -> e);
            if (entity != null) {
                BlockPos pos = this.worldPosition;
                entity.moveTo(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, this.level.random.nextFloat() * 360.0F, 0.0F);
                applyCooldown(this.level, entity);
                this.level.addFreshEntity(entity);
            }
        }
        this.occupants.clear();
        this.setChanged();
    }

    private static void applyCooldown(Level level, Entity entity) {
        if (entity instanceof Mob mob) {
            mob.getPersistentData().putLong(COOLDOWN_TAG, level.getGameTime() + 600 + level.random.nextInt(1200));
        }
    }

    @Nullable
    private static BlockPos findExitPos(Level level, BlockPos pos, BlockState state) {
        if (state.getBlock() instanceof BurrowBlock burrow && burrow.isGround()) {
            BlockPos above = pos.above();
            return level.getBlockState(above).isSolid() ? null : above;
        }
        BlockPos fallback = null;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos side = pos.relative(direction);
            if (!level.getBlockState(side).isSolid()) {
                if (level.getFluidState(side).is(FluidTags.WATER)) {
                    return side;
                }
                if (fallback == null) {
                    fallback = side;
                }
            }
        }
        return fallback;
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.occupants.clear();
        ListTag list = tag.getList("Occupants", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            this.occupants.add(new Occupant(entry.getCompound("EntityData"), entry.getInt("TicksInBurrow"), entry.getInt("MinOccupationTicks")));
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        this.saveOccupants(tag);
    }

    private void saveOccupants(CompoundTag tag) {
        ListTag list = new ListTag();
        for (Occupant occupant : this.occupants) {
            CompoundTag entry = new CompoundTag();
            entry.put("EntityData", occupant.entityData.copy());
            entry.putInt("TicksInBurrow", occupant.ticksInBurrow);
            entry.putInt("MinOccupationTicks", occupant.minOccupationTicks);
            list.add(entry);
        }
        tag.put("Occupants", list);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder components) {
        super.collectImplicitComponents(components);
        if (!this.occupants.isEmpty()) {
            CompoundTag tag = new CompoundTag();
            tag.putString("id", Objects.requireNonNull(BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(this.getType())).toString());
            this.saveOccupants(tag);
            components.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(tag));
        }
    }

    private static class Occupant {
        final CompoundTag entityData;
        int ticksInBurrow;
        int minOccupationTicks;

        Occupant(CompoundTag entityData, int ticksInBurrow, int minOccupationTicks) {
            this.entityData = entityData;
            this.ticksInBurrow = ticksInBurrow;
            this.minOccupationTicks = minOccupationTicks;
        }
    }
}
