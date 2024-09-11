package net.thevaliantsquidward.rainbowreef.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.AdvancedPathingMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import org.jetbrains.annotations.Nullable;

public class DancingEntity extends Animal implements DancesToJukebox, AdvancedPathingMob {
    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BOOLEAN);
    public float prevDanceProgress;
    public float danceProgress;
    private BlockPos jukeboxPosition;

    protected DancingEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DANCING, false);
    }
    public void tick() {
        super.tick();
        prevDanceProgress = danceProgress;
        if (this.jukeboxPosition == null || !this.jukeboxPosition.closerToCenterThan(this.position(), 15) || !this.level().getBlockState(this.jukeboxPosition).is(Blocks.JUKEBOX)) {
            this.setDancing(false);
            this.jukeboxPosition = null;
        }
        if (isDancing() && danceProgress < 5F) {
            danceProgress++;
        }
        if (!isDancing() && danceProgress > 0F) {
            danceProgress--;
        }
    }

    public boolean isDancing() {
        return this.entityData.get(DANCING);
    }

    public void setDancing(boolean bool) {
        this.entityData.set(DANCING, bool);
    }

    public void setRecordPlayingNearby(BlockPos pos, boolean playing) {
        this.onClientPlayMusicDisc(this.getId(), pos, playing);
    }

    @Override
    public void setJukeboxPos(BlockPos blockPos) {
        this.jukeboxPosition = blockPos;
    }

    public float getDanceProgress(float partialTicks) {
        return (prevDanceProgress + (danceProgress - prevDanceProgress) * partialTicks) * 0.2F;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    public boolean stopTickingPathing() {
        return this.isVehicle() || this.isDancing();
    }
}
