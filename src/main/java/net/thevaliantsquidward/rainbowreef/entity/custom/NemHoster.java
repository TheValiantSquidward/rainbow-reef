package net.thevaliantsquidward.rainbowreef.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.block.custom.AnemoneBlock;
import net.thevaliantsquidward.rainbowreef.entity.custom.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.util.RRPOI;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NemHoster extends VariantSchoolingFish {
    private static final EntityDataAccessor<BlockPos> HOME = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> HASNEM = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BOOLEAN);


    int nemSearchCooldown;

    public NemHoster(EntityType<? extends VariantSchoolingFish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME, new BlockPos(0,0,0));
        this.entityData.define(HASNEM, false);
    }

    public void tick() {

        if (this.getNemPos() != null) {
            if (!(this.level().getBlockState(new BlockPos(getNemPos())).getBlock() instanceof AnemoneBlock)) {
                this.setHasNem(false);
            } else {
                this.setHasNem(true);
            }
        }

        super.tick();
    }


    public void setNemPos(BlockPos pos) {
        if (pos == null){
            this.entityData.set(HASNEM, false);
            this.entityData.set(HOME, null);
        } else {
            this.entityData.set(HOME, pos);
        }
    }

    public BlockPos getNemPos() {
        if (this.entityData.get(HOME) == null) {
            return null;

        } else {
            BlockPos pos = this.entityData.get(HOME);
            return pos;
        }
    }

    public boolean hasNem() {
        return this.entityData.get(HASNEM);
    }
    public void setHasNem(boolean has) {
        this.entityData.set(HASNEM, Boolean.valueOf(has));
    }


}

class LocateNemGoal extends Goal {

    NemHoster clown;


    LocateNemGoal(NemHoster fish) {
        super();
        this.clown = fish;
    }

    @Override
    public boolean canUse() {
        return !this.clown.hasNem() && this.clown.nemSearchCooldown == 0;
    }


    public boolean canContinueToUse() {
        return false;
    }

    public void start() {
        this.clown.nemSearchCooldown = 200;
        List<BlockPos> list = this.findNems();

        BlockPos target = new BlockPos(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

        if (!list.isEmpty()) {
            Iterator var2 = list.iterator();

            while(var2.hasNext()) {
                BlockPos blockpos = (BlockPos)var2.next();


                if (clown.distanceToSqr(target.getX(), target.getY(), target.getZ()) < clown.distanceToSqr(blockpos.getX(), blockpos.getY(), blockpos.getZ()) ) {
                    target = blockpos;
                }
            }

            this.clown.setNemPos(target);
            this.clown.setHasNem(true);

        } else {
            this.clown.setNemPos(null);
            this.clown.setHasNem(false);

        }

    }

    private List<BlockPos> findNems() {
        BlockPos blockpos = this.clown.blockPosition();
        PoiManager poimanager = ((ServerLevel)clown.level()).getPoiManager();

        Stream<PoiRecord> stream = poimanager.getInRange((p_218130_) -> {
            return p_218130_.is(RRPOI.GREEN_NEM.getId());
        }, blockpos, 20, PoiManager.Occupancy.ANY);

        return (List)stream.map(PoiRecord::getPos).sorted(Comparator.comparingDouble((p_148811_) -> {
            return p_148811_.distSqr(blockpos);
        })).collect(Collectors.toList());
    }

}

class MoveToNemGoal extends RandomStrollGoal {

    NemHoster fims;
    Vec3 wantedPos;

    int radius;
    int height;
    int prox;

    double spd;

    public MoveToNemGoal(NemHoster fi, double spdmultiplier, int radius, int proximity) {
        super(fi, spdmultiplier, 1);
        this.fims = fi;
        this.radius = radius;
        this.height = height;
        this.prox = proximity;
        this.spd = spdmultiplier;
    }

    @Override
    public boolean canUse() {
        BlockPos nem = this.fims.getNemPos();

        return this.fims.distanceToSqr(nem.getX(), nem.getY(), nem.getZ()) < radius && fims.isInWater();
    }

    @Override
    public boolean canContinueToUse() {
        BlockPos nem = this.fims.getNemPos();
        wantedPos = new Vec3(nem.getX(), nem.getY(), nem.getZ());

        return super.canContinueToUse() && fims.isInWater() && !(this.wantedPos.distanceTo(this.fims.position()) <= this.fims.getBbWidth() * prox);
        //second part cancels the goal if the animal gets close enough
    }

    public void tick() {
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Nullable
    protected Vec3 getPosition() {
        BlockPos nem = this.fims.getNemPos();
        wantedPos = new Vec3(nem.getX(), nem.getY(), nem.getZ());

        return wantedPos;
    }
}
