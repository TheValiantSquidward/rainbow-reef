package net.thevaliantsquidward.rainbowreef.entity.base.NemHoster;

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
import net.thevaliantsquidward.rainbowreef.entity.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.util.RRPOI;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NemHoster extends VariantSchoolingFish {
    private static final EntityDataAccessor<BlockPos> HOME = SynchedEntityData.defineId(NemHoster.class, EntityDataSerializers.BLOCK_POS);


    int nemSearchCooldown = 0;
    int maxNemSearchCD;
    int homeRadius;
    int restInterval;
    int restTime;


    public NemHoster(EntityType<? extends VariantSchoolingFish> pEntityType, Level pLevel, int searchCD, int homeRadius, int restInterval, int restTime) {
        super(pEntityType, pLevel, 200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);

        this.maxNemSearchCD = searchCD;

        this.homeRadius = homeRadius;
        this.restInterval = restInterval;
        this.restTime = restTime;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME, new BlockPos(0,0,0));
    }

    public void tick() {
        reviewNem();
        if (this.nemSearchCooldown > 0){
            this.nemSearchCooldown--;
        }

        super.tick();
    }

    public void reviewNem() {
        //checks if the nem is still there
        BlockPos nemPos = this.getNemPos();

        if (nemPos != null) {
            if (!(this.level().getBlockState(nemPos).getBlock() instanceof AnemoneBlock)) {
                this.setNemPos(null);
                this.nemSearchCooldown = 0;
            }
        }
    }


    public void setNemPos(BlockPos pos) {
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

    public BlockPos getNemPos() {
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

    public boolean hasNem() {
        //GOALS MUST CHECK HASNEM IN CANUSE AND CANCONTINUETOUSE

        BlockPos nemPos = this.getNemPos();

        if (nemPos != null) {
            if (!(this.level().getBlockState(nemPos).getBlock() instanceof AnemoneBlock)) {
                this.setNemPos(null);
                this.findAndSetNems();
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

    public void findAndSetNems() {
        List<BlockPos> list = this.findNems();
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
            this.setNemPos(closest);
        }
        //sets home nem to destination
    }

    public List<BlockPos> findNems() {
        BlockPos blockpos = this.blockPosition();
        PoiManager poimanager = ((ServerLevel)this.level()).getPoiManager();

        Stream<PoiRecord> stream = poimanager.getInRange((p_218130_) -> {
            return p_218130_.is(RRPOI.GREEN_NEM.getKey()) || p_218130_.is(RRPOI.ORANGE_NEM.getKey()) || p_218130_.is(RRPOI.YELLOW_NEM.getKey());
        }, blockpos, 100, PoiManager.Occupancy.ANY);

        return stream.map(PoiRecord::getPos).sorted(Comparator.comparingDouble((p_148811_) -> {
            return p_148811_.distSqr(blockpos);
        })).collect(Collectors.toList());
        //finds every single nem in render basically
    }
}

