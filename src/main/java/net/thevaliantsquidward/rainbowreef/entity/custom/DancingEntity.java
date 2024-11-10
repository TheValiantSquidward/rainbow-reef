package net.thevaliantsquidward.rainbowreef.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.animal.allay.AllayAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.entity.goalz.*;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.AdvancedPathingMob;
import net.thevaliantsquidward.rainbowreef.entity.interfaces.DancesToJukebox;
import net.thevaliantsquidward.rainbowreef.item.ModItems;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.BiConsumer;

public class DancingEntity extends Animal implements DancesToJukebox{

    private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> JX = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JY = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> JZ = SynchedEntityData.defineId(DancingEntity.class, EntityDataSerializers.INT);

    private BlockPos jukeboxPosition;
    private final DynamicGameEventListener<JukeboxListener> dynamicJukeboxListener;
    private final VibrationSystem.User vibrationUser;

    public DancingEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
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
