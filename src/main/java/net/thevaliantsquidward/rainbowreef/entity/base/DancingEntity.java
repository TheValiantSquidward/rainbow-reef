package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class DancingEntity extends RRMob implements DancesToJukebox{

    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> JX = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JY = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JZ = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);

    private BlockPos jukeboxPosition;
    private final DynamicGameEventListener<JukeboxListener> dynamicJukeboxListener;
    private final VibrationSystem.User vibrationUser;

    public DancingEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.vibrationUser = new VibrationUser();
        this.dynamicJukeboxListener = new DynamicGameEventListener(new JukeboxListener(vibrationUser.getPositionSource(), GameEvent.JUKEBOX_PLAY.getNotificationRadius()));
    }

    public void updateDynamicGameEventListener(BiConsumer<DynamicGameEventListener<?>, ServerLevel> pListenerConsumer) {
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            pListenerConsumer.accept(this.dynamicJukeboxListener, serverlevel);
        }

    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DANCING, false);
        this.entityData.define(JX, 0);
        this.entityData.define(JY, 0);
        this.entityData.define(JZ, 0);
    }

    public void tick() {
        super.tick();

        if (this.getJukeboxPos() != null) {
            if (!this.getJukeboxPos().closerToCenterThan(this.position(), GameEvent.JUKEBOX_PLAY.getNotificationRadius())) {
                this.setDancing(false);
            } else {
                this.setDancing(true);
            }
        }

    }

    @Override
    public void setJukeboxPos(BlockPos pos) {
        if (pos == null){
            this.entityData.set(JX, null);
            this.entityData.set(JY, null);
            this.entityData.set(JZ, null);
        } else {
            this.entityData.set(JX, (int) pos.getCenter().x);
            this.entityData.set(JY, (int) pos.getCenter().y);
            this.entityData.set(JZ, (int) pos.getCenter().z);
        }
    }

    public BlockPos getJukeboxPos() {
        if (this.entityData.get(JX) == null) {
            return null;

        } else {
            BlockPos pos = new BlockPos(this.entityData.get(JX) - 1, this.entityData.get(JY), this.entityData.get(JZ));
            return pos;
        }
    }

    public boolean isDancing() {
        return this.entityData.get(DANCING);
    }
    public void setDancing(boolean dancing) {
        this.entityData.set(DANCING, Boolean.valueOf(dancing));
    }
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Dancing", this.isDancing());
    }
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setDancing(compound.getBoolean("Dancing"));
    }



    public void setJukeboxPlaying(BlockPos jukepos, boolean playing) {
        if (playing) {
            if (!this.isDancing()) {
                this.setJukeboxPos(jukepos);
                this.setDancing(true);
            }

        } else {
            this.setJukeboxPos(null);
            this.setDancing(false);
        }

    }



    class VibrationUser implements VibrationSystem.User {
        private static final int VIBRATION_EVENT_LISTENER_RANGE = 16;
        private final PositionSource positionSource = new EntityPositionSource(DancingEntity.this, DancingEntity.this.getEyeHeight());

        VibrationUser() {
        }

        public int getListenerRadius() {
            return 16;
        }

        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public boolean canReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, GameEvent gameEvent, GameEvent.Context context) {
            return true;
        }
        //crab is always receptive to music

        @Override
        public void onReceiveVibration(ServerLevel serverLevel, BlockPos blockPos, GameEvent gameEvent, @Nullable Entity entity, @Nullable Entity entity1, float v) {
        }
        //unused

        public TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.ALLAY_CAN_LISTEN;
        }
    }





    class JukeboxListener implements GameEventListener {
        private final PositionSource listenerSource;
        private final int listenerRadius;

        public JukeboxListener(PositionSource pListenerSource, int pListenerRadius) {
            this.listenerSource = pListenerSource;
            this.listenerRadius = pListenerRadius;
        }

        public PositionSource getListenerSource() {
            return this.listenerSource;
        }

        public int getListenerRadius() {
            return this.listenerRadius;
        }

        public boolean handleGameEvent(ServerLevel pLevel, GameEvent pGameEvent, GameEvent.Context pContext, Vec3 pPos) {
            if (pGameEvent == GameEvent.JUKEBOX_PLAY) {
                DancingEntity.this.setJukeboxPlaying(BlockPos.containing(pPos), true);
                return true;

            } else if (pGameEvent == GameEvent.JUKEBOX_STOP_PLAY) {
                DancingEntity.this.setJukeboxPlaying(BlockPos.containing(pPos), false);
                return true;

            } else {
                return false;

            }
        }
        //listens to the event and send info to serverside crab
    }

}
