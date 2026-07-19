package com.valiantenvoy.rainbow_reef.entity;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.FishLeapGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.goals.SwimWanderGoal;
import com.valiantenvoy.rainbow_reef.entity.ai.navigation.WaterNavigation;
import com.valiantenvoy.rainbow_reef.entity.base.ReefMob;
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
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;

public class Billfish extends ReefMob {

    private static final float MAX_TILT = 45.0F;
    private static final float MAX_ROLL = 17.5F;
    private static final float ROLL_PER_YAW = 2.0F;

    public Billfish(EntityType<? extends ReefMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new SmoothSwimmingMoveControl(this, 45, 4, 0.05F, 0.1F, false);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.MOVEMENT_SPEED, 1.0F)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new FishLeapGoal(this));
        this.goalSelector.addGoal(2, new SwimWanderGoal(this, 1.0D, 10, 20, 7, 4, true));
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        return new WaterNavigation(this, level, true);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader level) {
        if (this.getRandom().nextFloat() <= 0.33F) {
            return super.getWalkTargetValue(pos, level);
        }
        return this.getSurfacePathfindingFavor(pos, level);
    }

    @Override
    public void tick() {
        super.tick();
        this.tickRotations(MAX_TILT, MAX_ROLL, ROLL_PER_YAW);
        if (this.level().isClientSide) {
            if (this.isInWater() && this.getDeltaMovement().lengthSqr() > 0.05D) {
                Vec3 vec31 = this.getViewVector(0.0F);
                this.level().addParticle(ParticleTypes.BUBBLE, this.getRandomX(0.5D) - vec31.x * 0.8D, this.getRandomY() - vec31.y * 0.25D, this.getRandomZ(0.5D) - vec31.z * 0.8D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack((ReefItems.BILLFISH_BUCKET.get()));
    }

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return RainbowReef.location("textures/entity/billfish/billfish_swordfish.png");
    }
}
