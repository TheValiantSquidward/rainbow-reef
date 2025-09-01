package net.thevaliantsquidward.rainbowreef.entity;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.SquidSwimGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.SquidMob;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.ReefSoundEvents;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

import static net.thevaliantsquidward.rainbowreef.entity.base.ReefMob.ReefRarities.*;

public class Jellyfish extends SquidMob {

    private static final EntityDataAccessor<Integer> SCALE = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.INT);

    public Jellyfish(EntityType<? extends SquidMob> entityType, Level level) {
        super(entityType, level);
        this.random.setSeed(this.getId());
        this.tentacleSpeed = 2.0F / (this.random.nextFloat() + 1.0F) * 0.2F;
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SquidSwimGoal(this, 60, 0.18F, 0.18F, 0.18F));
    }

    @Override
    public void playerTouch(@NotNull Player player) {
        if (player instanceof ServerPlayer && player.hurt(this.damageSources().mobAttack(this), 2)) {
            this.playSound(ReefSoundEvents.JELLYFISH_ZAP.get(), 1.0F, 1.0F);
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.hasCustomName()) {
            this.setScale(0);
        } else {
            if (!this.getCustomName().getString().toLowerCase().equals("squishy")) {
                this.setScale(0);
            } else {
                if ("squishy".equals(this.getName().getString().toLowerCase(Locale.ROOT)) && !this.isBaby()) {
                    this.setScale(-3);
                }
            }
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCALE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putInt("Scale", this.getModelScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setScale(Math.min(compoundTag.getInt("Scale"), 0));
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (SCALE.equals(key)) {
            this.refreshDimensions();
        }
        super.onSyncedDataUpdated(key);
    }

    private static float getScale(int scale) {
        if (scale == 1) {
            return 1.8F;
        }
        return 0.9F;
    }

    public int getModelScale() {
        return this.entityData.get(SCALE);
    }

    public void setScale(int scale) {
        this.entityData.set(SCALE, scale);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return super.getDimensions(pose).scale(getScale(this.getModelScale()));
    }

    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.JELLYFISH_BUCKET.get());
    }

    @Override
    @NotNull
    protected InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level().isClientSide) {
            if (itemstack.getItem() == Items.GLASS_BOTTLE) {
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }
                this.spawnAtLocation(ReefItems.JELLYFISH_JELLY.get());
                this.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    @Override
    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ReefSoundEvents.JELLYFISH_HURT.get();
    }

    @Override
    public int getVariantCount() {
        return JellyfishVariant.values().length;
    }

    public enum JellyfishVariant implements StringRepresentable {
        PINK(1, "pink", COMMON, null),
        ORANGE(2, "orange", COMMON, null),
        WHITE(3, "white", COMMON, null),
        YELLOW(4, "yellow", COMMON, null),
        MUDDY(5, "muddy", UNCOMMON, null),
        ABYSSAL(6, "abyssal", RARE, null),
        CHERRY(7, "cherry", UNCOMMON, null),
        MINTY(8, "minty", UNCOMMON, null),
        AZURE(9, "azure", RARE, null),
        RED(10, "red", COMMON, null);

        private final int variant;
        private final String name;
        private final ReefRarities rarity;
        @Nullable
        private final TagKey<Biome> biome;

        JellyfishVariant(int variant, String name, ReefRarities rarity, @Nullable TagKey<Biome> biome) {
            this.variant = variant;
            this.name = name;
            this.rarity = rarity;
            this.biome = biome;
        }

        public static JellyfishVariant getVariantId(int variants) {
            for (JellyfishVariant variant : values()) {
                if (variant.variant == variants) return variant;
            }
            return JellyfishVariant.PINK;
        }

        public static JellyfishVariant getRandom(RandomSource random, Holder<Biome> biome, boolean fromBucket) {
            List<JellyfishVariant> possibleTypes = getPossibleTypes(biome, WeightedRandomList.create(COMMON, UNCOMMON, RARE).getRandom(random).orElseThrow(), fromBucket);
            return possibleTypes.get(random.nextInt(possibleTypes.size()));
        }

        private static List<JellyfishVariant> getPossibleTypes(Holder<Biome> category, ReefRarities rarity, boolean fromBucket) {
            List<JellyfishVariant> variants = Lists.newArrayList();
            for (JellyfishVariant variant : JellyfishVariant.values()) {
                if ((fromBucket || variant.biome == null || category.is(variant.biome)) && variant.rarity == rarity) {
                    variants.add(variant);
                }
            }
            return variants;
        }

        public int getVariant() {
            return this.variant;
        }

        public ReefRarities getRarity() {
            return this.rarity;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        spawnData = super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
        int variant = JellyfishVariant.getRandom(this.getRandom(), this.level().getBiome(this.blockPosition()), spawnType == MobSpawnType.BUCKET).getVariant();
        if (compoundTag != null && compoundTag.contains("BucketVariantTag", 3)) {
            this.setVariant(JellyfishVariant.getVariantId(compoundTag.getInt("BucketVariantTag")).getVariant());
            return spawnData;
        }
        if (spawnData instanceof JellyfishData) {
            variant = ((JellyfishData) spawnData).variantData;
        } else {
            if (!this.fromBucket()) {
                spawnData = new JellyfishData(variant);
            }
        }
        this.setVariant(JellyfishVariant.getVariantId(variant).getVariant());
        return spawnData;
    }

    static class JellyfishData implements SpawnGroupData {
        public final int variantData;

        public JellyfishData(int variant) {
            this.variantData = variant;
        }
    }
}
