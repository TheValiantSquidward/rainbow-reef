package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.blocks.AnemoneBlock;
import net.thevaliantsquidward.rainbowreef.registry.ReefPoiTypes;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Anemonefish extends VariantSchoolingFish {

    private static final EntityDataAccessor<BlockPos> HOME = SynchedEntityData.defineId(Anemonefish.class, EntityDataSerializers.BLOCK_POS);

    public int anemoneSearchCooldown = 0;
    int maxAnemoneSearchCooldown;
    int homeRadius;
    int restInterval;
    int restTime;

    public Anemonefish(EntityType<? extends VariantSchoolingFish> entityType, Level level, int searchCooldown, int homeRadius, int restInterval, int restTime) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        this.maxAnemoneSearchCooldown = searchCooldown;

        this.homeRadius = homeRadius;
        this.restInterval = restInterval;
        this.restTime = restTime;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME, new BlockPos(0,0,0));
    }

    @Override
    public void tick() {
        reviewAnemone();
        if (this.anemoneSearchCooldown > 0){
            this.anemoneSearchCooldown--;
        }
        super.tick();
    }

    public void reviewAnemone() {
        //checks if the nem is still there
        BlockPos nemPos = this.getAnemonePos();

        if (nemPos != null) {
            if (!(this.level().getBlockState(nemPos).getBlock() instanceof AnemoneBlock)) {
                this.setAnemonePos(null);
                this.anemoneSearchCooldown = 0;
            }
        }
    }


    public void setAnemonePos(BlockPos pos) {
        //sets the home position of the clownfish
        //NULL = REMOVE POS

        if (pos == null){
            //setting home position to null defaults the position TO THE WORLD ORIGIN
            //and sets hasnem to false
            this.entityData.set(HOME, BlockPos.ZERO);

        } else {
            //giving an actual blockpos sets the clownfish's home position to the given blockpos
            //and sets hasnem to true
            this.entityData.set(HOME, pos);
        }
    }

    public BlockPos getAnemonePos() {
        //returns the block position of the clown's home
        //NULL = NO POS

        if (this.entityData.get(HOME) == BlockPos.ZERO) {
            //if this clown does not have a home position return null
            return null;

        } else {
            //if this clown does have a home position return it as a blockpos
            return this.entityData.get(HOME);
        }
    }

    public boolean hasAnemone() {
        //GOALS MUST CHECK HASNEM IN CANUSE AND CANCONTINUETOUSE

        BlockPos nemPos = this.getAnemonePos();

        if (nemPos != null) {
            if (!(this.level().getBlockState(nemPos).getBlock() instanceof AnemoneBlock)) {
                this.setAnemonePos(null);
                this.findAndSetAnemone();
                return false;
            }
        }

        return !(this.entityData.get(HOME) == BlockPos.ZERO);
    }


    public boolean canSee(BlockPos pos) {
        Vec3 vec3 = new Vec3(this.getX(), this.getEyeY(), this.getZ());
        Vec3 vec31 = new Vec3(pos.getX(), pos.getY(), pos.getZ());
        if (vec31.distanceTo(vec3) > 20.0) {
            return false;
        } else {
            return this.level().clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, net.minecraft.world.level.ClipContext.Fluid.NONE, this)).getType() == HitResult.Type.MISS;
        }
    }

    public void findAndSetAnemone() {
        List<BlockPos> list = this.findAnemone();
        //generates list of every nem ever

        BlockPos closest = null;

        if (list.isEmpty()) {
            return;
        }

        for (BlockPos pos : list) {
            if (closest == null || this.distanceToSqr(closest.getX(), closest.getY(), closest.getZ()) > this.distanceToSqr(pos.getX(), pos.getY(), pos.getZ())) {
                if (this.canSee(pos)) {
                    closest = pos;
                }
            }
        }
        //check nems in render, finds the closest visible nem

        if (closest != null) {
            this.setAnemonePos(closest);
        }
        //sets home nem to destination
    }

    public List<BlockPos> findAnemone() {
        BlockPos pos = this.blockPosition();
        PoiManager poimanager = ((ServerLevel)this.level()).getPoiManager();
        Stream<PoiRecord> stream = poimanager.getInRange((poiTypeHolder) -> poiTypeHolder.is(ReefPoiTypes.GREEN_NEM.getKey()) || poiTypeHolder.is(ReefPoiTypes.ORANGE_NEM.getKey()) || poiTypeHolder.is(ReefPoiTypes.YELLOW_NEM.getKey()), pos, 32, PoiManager.Occupancy.ANY);
        return stream.map(PoiRecord::getPos).sorted(Comparator.comparingDouble((blockPos) -> blockPos.distSqr(pos))).collect(Collectors.toList());
    }
}

