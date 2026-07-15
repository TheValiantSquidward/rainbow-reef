package net.thevaliantsquidward.rainbowreef.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.utils.DancesToJukebox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public abstract class DancingEntity extends ReefMob implements DancesToJukebox {

    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> JX = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JY = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JZ = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);

    private final DynamicGameEventListener<JukeboxListener> dynamicJukeboxListener;

    public DancingEntity(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        VibrationSystem.User vibrationUser = new VibrationUser();
        this.dynamicJukeboxListener = new DynamicGameEventListener<>(new JukeboxListener(vibrationUser.getPositionSource(), GameEvent.JUKEBOX_PLAY.value().notificationRadius()));
    }

    public void updateDynamicGameEventListener(@NotNull BiConsumer<DynamicGameEventListener<?>, ServerLevel> listenerConsumer) {
        Level level = this.level();
        if (level instanceof ServerLevel serverlevel) {
            listenerConsumer.accept(this.dynamicJukeboxListener, serverlevel);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DANCING, false);
        builder.define(JX, 0);
        builder.define(JY, 0);
        builder.define(JZ, 0);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Dancing", this.isDancing());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setDancing(compound.getBoolean("Dancing"));
    }

    public void tick() {
        super.tick();

        if (this.getJukeboxPos() != null) {
            if (!this.getJukeboxPos().closerToCenterThan(this.position(), GameEvent.JUKEBOX_PLAY.value().notificationRadius())) {
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
        this.entityData.get(JX);
        return new BlockPos(this.entityData.get(JX) - 1, this.entityData.get(JY), this.entityData.get(JZ));
    }

    public boolean isDancing() {
        return this.entityData.get(DANCING);
    }

    public void setDancing(boolean dancing) {
        this.entityData.set(DANCING, dancing);
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
        private final PositionSource positionSource = new EntityPositionSource(DancingEntity.this, DancingEntity.this.getEyeHeight());

        VibrationUser() {
        }

        public int getListenerRadius() {
            return 16;
        }

        public @NotNull PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public boolean canReceiveVibration(@NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull Holder<GameEvent> gameEvent, GameEvent.@NotNull Context context) {
            return true;
        }
        //crab is always receptive to music

        @Override
        public void onReceiveVibration(@NotNull ServerLevel serverLevel, @NotNull BlockPos blockPos, @NotNull Holder<GameEvent> gameEvent, @Nullable Entity entity, @Nullable Entity entity1, float v) {
        }
        //unused

        public @NotNull TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.ALLAY_CAN_LISTEN;
        }
    }

    class JukeboxListener implements GameEventListener {
        private final PositionSource listenerSource;
        private final int listenerRadius;

        public JukeboxListener(PositionSource listenerSource, int listenerRadius) {
            this.listenerSource = listenerSource;
            this.listenerRadius = listenerRadius;
        }

        public @NotNull PositionSource getListenerSource() {
            return this.listenerSource;
        }

        public int getListenerRadius() {
            return this.listenerRadius;
        }

        public boolean handleGameEvent(@NotNull ServerLevel level, Holder<GameEvent> gameEvent, GameEvent.@NotNull Context context, @NotNull Vec3 pos) {
            if (gameEvent.is(GameEvent.JUKEBOX_PLAY)) {
                DancingEntity.this.setJukeboxPlaying(BlockPos.containing(pos), true);
                return true;

            } else if (gameEvent.is(GameEvent.JUKEBOX_STOP_PLAY)) {
                DancingEntity.this.setJukeboxPlaying(BlockPos.containing(pos), false);
                return true;

            } else {
                return false;

            }
        }
        //listens to the event and send info to serverside crab
    }
}
