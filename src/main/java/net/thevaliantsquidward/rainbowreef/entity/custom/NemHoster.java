package net.thevaliantsquidward.rainbowreef.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.block.custom.AnemoneBlock;
import net.thevaliantsquidward.rainbowreef.entity.custom.base.VariantSchoolingFish;
import net.thevaliantsquidward.rainbowreef.entity.goalz.CustomizableRandomSwimGoal;
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
    private static final EntityDataAccessor<BlockPos> HOME = SynchedEntityData.defineId(NemHoster.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> HASNEM = SynchedEntityData.defineId(NemHoster.class, EntityDataSerializers.BOOLEAN);


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

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new LocateNemGoal(this));
        this.goalSelector.addGoal(2, new MoveToNemGoal(this, 0.8,16, 1));
        this.goalSelector.addGoal(1, new RestInNemGoal(this, 0.8,16, 1));
        //Anemone seeker goal plan:
        //priority of 0, but only works if the clown has a home nem and is over 10 blocks from it
        //Pathfinds back to home nem and makes it hide for 3 - 5 secs
    }

    public void tick() {

        if (this.getNemPos() != null) {
            if (!(this.level().getBlockState(new BlockPos(getNemPos())).getBlock() instanceof AnemoneBlock)) {
                this.setHasNem(false);
                this.setNemPos(null);
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

        BlockPos closest = null;

        for (BlockPos pos : list) {
            if (closest == null || this.clown.distanceToSqr(closest.getX(), closest.getY(), closest.getZ()) > this.clown.distanceToSqr(pos.getX(), pos.getY(), pos.getZ())) {
                closest = pos;
            }
        }
        if (closest != null) {
            this.clown.setNemPos(closest);
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
            return p_218130_.is(RRPOI.GREEN_NEM.getKey()) || p_218130_.is(RRPOI.ORANGE_NEM.getKey()) || p_218130_.is(RRPOI.YELLOW_NEM.getKey());
        }, blockpos, 100, PoiManager.Occupancy.ANY);

        return stream.map(PoiRecord::getPos).sorted(Comparator.comparingDouble((p_148811_) -> {
            return p_148811_.distSqr(blockpos);
        })).collect(Collectors.toList());
    }

}

class MoveToNemGoal extends Goal{

    NemHoster fims;

    int radius;
    int prox;

    double spd;

    public MoveToNemGoal(NemHoster fi, double spdmultiplier, int maxOut, int proximity) {
        this.fims = fi;
        this.radius = maxOut;
        this.prox = proximity;
        this.spd = spdmultiplier;
    }

    public boolean canUse() {
        BlockPos nem = this.fims.getNemPos();

        return !(nem == null) && this.fims.distanceToSqr(nem.getX(), nem.getY(), nem.getZ()) > radius && fims.isInWater();
    }

    public boolean canContinueToUse() {
        BlockPos nem = this.fims.getNemPos();

        return !(nem == null) && this.fims.distanceToSqr(nem.getX(), nem.getY(), nem.getZ()) > radius && fims.isInWater();
        //second part cancels the goal if the animal gets close enough, but if the distance is greater than the maximum distance it keeps running
    }

    public void tick() {
        this.fims.getNavigation().stop();
        this.fims.getMoveControl().setWantedPosition(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 1F);
        this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 1F);
    }


    public void start() {
        this.fims.getNavigation().stop();
        this.fims.getMoveControl().setWantedPosition(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 2F);
        this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 2F);
    }
}

class RestInNemGoal extends RandomStrollGoal{

    NemHoster fims;

    double spd;

    private final boolean checkNoActionTime;

    public RestInNemGoal(NemHoster fi, double spdmultiplier, int interval, boolean check) {
        super(fi, spdmultiplier, interval);
        this.fims = fi;
        this.spd = spdmultiplier;
        this.checkNoActionTime = check;
    }

    public boolean canUse() {
        BlockPos nem = this.fims.getNemPos();

        if (!this.forceTrigger) {
            if (this.checkNoActionTime && this.mob.getNoActionTime() >= 100) {
                return false;
            }

            if (this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
                return false;
            }
        }

        Vec3 $$0 = this.getPosition();
        if ($$0 == null) {
            return false;
        } else {
            this.wantedX = $$0.x;
            this.wantedY = $$0.y;
            this.wantedZ = $$0.z;
            this.forceTrigger = false;
            return !(nem == null) && fims.isInWater();
        }

        return !(nem == null) && fims.isInWater();
    }

    public boolean canContinueToUse() {
        BlockPos nem = this.fims.getNemPos();

        return !(nem == null) && fims.isInWater();
        //second part cancels the goal if the animal gets close enough, but if the distance is greater than the maximum distance it keeps running
    }

    public void tick() {
        if (this.fims.blockPosition() == this.fims.getNemPos()) {
            this.fims.setTarget(null);
            this.fims.getNavigation().stop();
        } else {
            this.fims.getNavigation().stop();
            this.fims.getMoveControl().setWantedPosition(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 1F);
            this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 1F);
        }
    }


    public void start() {
        this.fims.getNavigation().stop();
        this.fims.getMoveControl().setWantedPosition(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 2F);
        this.fims.getNavigation().moveTo(this.fims.getNemPos().getX() + 0.5F, this.fims.getNemPos().getY() + 0.1F, this.fims.getNemPos().getZ() + 0.5F, 2F);
    }
}
