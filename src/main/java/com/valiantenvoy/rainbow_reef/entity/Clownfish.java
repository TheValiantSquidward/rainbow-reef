package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.blocks.SeaAnemoneBlock;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.*;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import com.valiantenvoy.rainbow_reef.registry.ReefPoiTypes;
import com.valiantenvoy.rainbow_reef.tags.ReefTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Clownfish extends VariantSchoolingFish {

    private static final EntityDataAccessor<BlockPos> ANEMONE_POS = SynchedEntityData.defineId(Clownfish.class, EntityDataSerializers.BLOCK_POS);

    public int anemoneSearchCooldown = 0;

    public Clownfish(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANEMONE_POS, BlockPos.ZERO);
    }

    @Nullable
    public BlockPos getAnemonePos() {
        if (this.entityData.get(ANEMONE_POS) == BlockPos.ZERO) {
            return null;
        } else {
            return this.entityData.get(ANEMONE_POS);
        }
    }
    public void setAnemonePos(BlockPos pos) {
        this.entityData.set(ANEMONE_POS, pos);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.75F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new RestInAnemoneGoal(this, 1.1D, 300, 200));
        this.goalSelector.addGoal(4, new MoveToAnemoneGoal(this, 1.2D, 16.0D));
        this.goalSelector.addGoal(5, new LocateAnemoneGoal(this, 200));
        this.goalSelector.addGoal(6, new FishNibbleBlockGoal(this, 10, ReefTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(6, new SwimWanderGoal(this, 1.0D, 60));
        this.goalSelector.addGoal(7, new FollowVariantLeaderGoal(this));
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (this.getRandom().nextFloat() < 0.33F) {
            return super.getWalkTargetValue(pos, level);
        }
        return this.getDepthPathfindingFavor(pos, level);
    }

    public boolean hasAnemone() {
        BlockPos anemonePos = this.getAnemonePos();
        if (anemonePos == null) {
            return false;
        }
        if (!(this.level().getBlockState(anemonePos).getBlock() instanceof SeaAnemoneBlock)) {
            this.setAnemonePos(BlockPos.ZERO);
            this.findAndSetAnemone();
            return false;
        }
        return true;
    }

    public boolean canSeeAnemone(BlockPos pos) {
        Vec3 vec3 = new Vec3(this.getX(), this.getEyeY(), this.getZ());
        Vec3 vec31 = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        if (vec31.distanceTo(vec3) > 20.0D) {
            return false;
        } else {
            return this.level().clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)).getType() == HitResult.Type.MISS;
        }
    }

    public void findAndSetAnemone() {
        List<BlockPos> posList = this.findAnemone();
        BlockPos blockPos = null;
        if (posList.isEmpty()) {
            return;
        }
        for (BlockPos pos : posList) {
            if (blockPos == null || this.distanceToSqr(blockPos.getX(), blockPos.getY(), blockPos.getZ()) > this.distanceToSqr(pos.getX(), pos.getY(), pos.getZ())) {
                if (this.canSeeAnemone(pos)) {
                    blockPos = pos;
                }
            }
        }
        if (blockPos != null) {
            this.setAnemonePos(blockPos);
        }
    }

    public List<BlockPos> findAnemone() {
        BlockPos pos = this.blockPosition();
        PoiManager poimanager = ((ServerLevel) this.level()).getPoiManager();
        Stream<PoiRecord> stream = poimanager.getInRange((poiTypeHolder) -> poiTypeHolder.is(ReefPoiTypes.ANEMONE.getKey()), pos, 16, PoiManager.Occupancy.ANY);
        return stream.map(PoiRecord::getPos).sorted(Comparator.comparingDouble((blockPos) -> blockPos.distSqr(pos))).collect(Collectors.toList());
    }

    public void checkAnemonePos() {
        BlockPos anemonePos = this.getAnemonePos();
        if (anemonePos != null) {
            if (!(this.level().getBlockState(anemonePos).getBlock() instanceof SeaAnemoneBlock)) {
                this.setAnemonePos(BlockPos.ZERO);
                this.anemoneSearchCooldown = 0;
            }
        }
    }

    @Override
    protected void doPush(Entity entity) {
        if (entity instanceof Clownfish clownfish && !clownfish.hasAnemone()) {
            super.doPush(entity);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            if (this.tickCount % 20 == 0) {
                this.checkAnemonePos();
            }
            if (this.anemoneSearchCooldown > 0) {
                this.anemoneSearchCooldown--;
            }
        }
    }

    @Override
    public int getMaxSchoolSize() {
        return 6;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CLOWNFISH_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/clownfish/clownfish_ocellaris.png");
    }
}