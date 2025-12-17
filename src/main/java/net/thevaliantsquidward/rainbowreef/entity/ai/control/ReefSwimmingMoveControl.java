package net.thevaliantsquidward.rainbowreef.entity.ai.control;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ReefSwimmingMoveControl extends MoveControl {

	private final int maxTurnX;
    private final int maxTurnY;
    private final float inWaterSpeedModifier;
	private final float outsideWaterSpeedModifier;
	private Vec3 targetPos = Vec3.ZERO;

	public ReefSwimmingMoveControl(Mob mob, int maxTurnX, int maxTurnY, float inWaterSpeedModifier, float outsideWaterSpeedModifier) {
		super(mob);
		this.maxTurnX = maxTurnX;
        this.maxTurnY = maxTurnY;
        this.inWaterSpeedModifier = inWaterSpeedModifier;
		this.outsideWaterSpeedModifier = outsideWaterSpeedModifier;
	}

	@Override
	public void tick() {
		if (this.operation == Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
			if (this.mob.tickCount % 60 == 0) {
				this.generateNewTarget();
			}
			double d0 = this.targetPos.x - this.mob.getX();
			double d1 = this.targetPos.y - this.mob.getY();
			double d2 = this.targetPos.z - this.mob.getZ();
			double d3 = d0 * d0 + d1 * d1 + d2 * d2;
			if (d3 < (double) 2.5000003E-7F) {
				this.mob.setZza(0.0F);
			} else {
				float f = (float) (Mth.atan2(d2, d0) * (double) (180.0F / (float) Math.PI)) - 90.0F;
				this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, maxTurnY));
				this.mob.yBodyRot = this.mob.getYRot();
				this.mob.yHeadRot = this.mob.getYRot();
				float f1 = (float) (this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if (this.mob.isInWater()) {
					this.mob.setSpeed(f1 * this.inWaterSpeedModifier);
					double d4 = Math.sqrt(d0 * d0 + d2 * d2);
					if (Math.abs(d1) > (double) 1.0E-5F || Math.abs(d4) > (double) 1.0E-5F) {
						float f3 = -((float) (Mth.atan2(d1, d4) * (double) (180.0F / (float) Math.PI)));
						f3 = Mth.clamp(Mth.wrapDegrees(f3), (float) (-this.maxTurnX), (float) this.maxTurnX);
						this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f3, 2.0F));
					}
					float f6 = Mth.cos(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					float f4 = Mth.sin(this.mob.getXRot() * ((float) Math.PI / 180.0F));
					this.mob.zza = f6 * f1;
					this.mob.yya = -f4 * f1;
				} else {
					float f5 = Math.abs(Mth.wrapDegrees(this.mob.getYRot() - f));
					float f2 = this.getTurningSpeedFactor(f5);
					this.mob.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
				}
			}
		} else {
			this.mob.setSpeed(0.0F);
			this.mob.setXxa(0.0F);
			this.mob.setYya(0.0F);
			this.mob.setZza(0.0F);
		}
	}
    
    private void generateNewTarget() {
        Level level = this.mob.level();
        for (int i = 0; i < 10; i++) {
        	Vec3 pos = this.getSpreadPosition(this.mob, new Vec3(35, 10, 35));
        	HitResult hitResult = this.mob.level().clip(new ClipContext(this.mob.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.mob));
        	if (hitResult instanceof BlockHitResult blockHit) {
                BlockPos targetPos = blockHit.getBlockPos();
                BlockState blockState = level.getBlockState(targetPos);
                if (blockState.is(Blocks.WATER)) {
                	this.targetPos = blockHit.getLocation();
                	break;
                }
        	}
        }
    }

    private Vec3 getSpreadPosition(Entity entity, Vec3 range) {
        double x = entity.getX() + (entity.level().random.nextDouble() - entity.level().random.nextDouble()) * range.x + 0.5D;
        double y = entity.getY() + (entity.level().random.nextDouble() - entity.level().random.nextDouble()) * range.y + 0.5D;
        double z = entity.getZ() + (entity.level().random.nextDouble() - entity.level().random.nextDouble()) * range.z + 0.5D;
        return new Vec3(x, y, z);
    }

	private float getTurningSpeedFactor(float v) {
		return 1.0F - Mth.clamp((v - 10.0F) / 50.0F, 0.0F, 1.0F);
	}
}