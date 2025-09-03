//package net.thevaliantsquidward.rainbowreef.entity.ai.goals;
//
//import net.minecraft.util.Mth;
//import net.minecraft.world.entity.ai.goal.Goal;
//import net.thevaliantsquidward.rainbowreef.entity.base.SquidMob;
//
//public class SquidSwimGoal extends Goal {
//
//    private final SquidMob squidMob;
//    private final int interval;
//    private final float xSpeed;
//    private final float ySpeed;
//    private final float zSpeed;
//
//    public SquidSwimGoal(SquidMob squidMob, int interval, float xSpeed, float ySpeed, float zSpeed) {
//        this.squidMob = squidMob;
//        this.interval = interval;
//        this.xSpeed = xSpeed;
//        this.ySpeed = ySpeed;
//        this.zSpeed = zSpeed;
//    }
//
//    public boolean canUse() {
//        return true;
//    }
//
//    public void tick() {
//        if (this.squidMob.getRandom().nextInt(reducedTickDelay(interval)) == 0 || !this.squidMob.isInWater() || !this.squidMob.hasMovementVector()) {
//            var f = this.squidMob.getRandom().nextFloat() * (float) (Math.PI * 2);
//            var tx = Mth.cos(f) * xSpeed;
//            var ty = -0.1F + this.squidMob.getRandom().nextFloat() * ySpeed;
//            var tz = Mth.sin(f) * zSpeed;
//            this.squidMob.setMovementVector(tx, ty, tz);
//        }
//    }
//}
