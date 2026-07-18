package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishLeapGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FollowVariantLeaderGoal;
import com.valiantenvoy.rainbow_reef.entity.base.VariantSchoolingFish;
import com.valiantenvoy.rainbow_reef.registry.ReefItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;

import java.util.Objects;

public class MahiMahi extends VariantSchoolingFish implements Bucketable {

    private static final float MAX_TILT = 45.0F;
    private static final float MAX_ROLL = 17.5F;
    private static final float ROLL_PER_YAW = 2.0F;

    public MahiMahi(EntityType<? extends VariantSchoolingFish> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 5, 0.05F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.85F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(1, new SwimWanderGoal(this, 1.0D, 10, 15, 7, 6, true));
        this.goalSelector.addGoal(2, new FollowVariantLeaderGoal(this));
        this.goalSelector.addGoal(3, new FishLeapGoal(this, 20, 0.6D, 0.7D));
    }

    @Override
    public void tick() {
        super.tick();
        this.tickRotations(MAX_TILT, MAX_ROLL, ROLL_PER_YAW);
        if (this.level().isClientSide) {
            if (this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.05D) {
                Vec3 viewVector = this.getViewVector(0.0F);
                this.level().addParticle(ParticleTypes.BUBBLE, this.getRandomX(0.3D) - viewVector.x * 1.1D, this.getRandomY() - viewVector.y * 0.3D, this.getRandomZ(0.3D) - viewVector.z * 1.1D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (this.getRandom().nextFloat() <= 0.33F) {
            return super.getWalkTargetValue(pos, level);
        }
        return this.getSurfacePathfindingFavor(pos, level);
    }

    @Override
    public int getMaxSchoolSize() {
        return 8;
    }

    @Override
    public boolean inRangeOfLeader() {
        return this.distanceToSqr(Objects.requireNonNull(this.leader)) <= 256.0D;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.MAHI_MAHI_BUCKET.get());
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/mahi_mahi/mahi_mahi.png");
    }
}
