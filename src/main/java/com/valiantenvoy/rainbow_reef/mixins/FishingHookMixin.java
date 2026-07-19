package com.valiantenvoy.rainbow_reef.mixins;

import com.valiantenvoy.rainbow_reef.entity.utils.FishingHookAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings({"WrongEntityDataParameterClass", "AddedMixinMembersNamePattern"})
@Mixin(FishingHook.class)
public abstract class FishingHookMixin extends Entity implements FishingHookAccessor {

    @Unique
    private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(FishingHook.class, EntityDataSerializers.INT);
    @Unique
    private static final EntityDataAccessor<Boolean> HAS_COLOR = SynchedEntityData.defineId(FishingHook.class, EntityDataSerializers.BOOLEAN);

    public FishingHookMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("TAIL"), method = "defineSynchedData")
    private void rainbowReef$addColorSynchedData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(COLOR, 0x000000);
        builder.define(HAS_COLOR, false);
    }

    @Inject(at = @At("TAIL"), method = "addAdditionalSaveData")
    private void rainbowReef$addColorSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putInt("Color", this.rainbowReef$getColor());
        compoundTag.putBoolean("HasColor", this.rainbowReef$hasColor());
    }

    @Inject(at = @At("TAIL"), method = "readAdditionalSaveData")
    private void rainbowReef$readColorSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.rainbowReef$setColor(compoundTag.getInt("Color"));
        this.rainbowReef$setHasColor(compoundTag.getBoolean("HasColor"));
    }

    @Override
    public int rainbowReef$getColor() {
        return this.entityData.get(COLOR);
    }
    @Override
    public void rainbowReef$setColor(int color) {
        this.entityData.set(COLOR, color);
    }

    @Override
    public boolean rainbowReef$hasColor() {
        return this.entityData.get(HAS_COLOR);
    }
    @Override
    public void rainbowReef$setHasColor(boolean color) {
        this.entityData.set(HAS_COLOR, color);
    }
}