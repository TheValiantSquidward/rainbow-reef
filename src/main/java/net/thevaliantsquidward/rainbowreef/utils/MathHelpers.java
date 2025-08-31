package net.thevaliantsquidward.rainbowreef.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;

public class MathHelpers {

    public static Vec3 quickReturn (Level level, Vec3 anchor, Vec3 point, float angleY, float perTickRate, boolean gravity, boolean returning) {

        if (returning) {
            if (angleY >= 0 && angleY < 180) {
                point = MathHelpers.rotateAroundCenterFlatDeg(anchor, point, (double) -perTickRate);
            } else if (angleY < 0 && angleY >= -180) {
                point = MathHelpers.rotateAroundCenterFlatDeg(anchor, point, (double) perTickRate);
            }
        }

        if (gravity) {
            if (level.getBlockState(new BlockPos((int) point.x(), (int) (point.y() - 0.02), (int) point.z())).isAir() ||
                    !level.getBlockState(new BlockPos((int) point.x(), (int) (point.y() - 0.02), (int) point.z())).isSolid()) {
                point = point.subtract(0, 0.02, 0);
            }
        }

        return point;

    }

    public static Vec3 distConstraintSingle(Vec3 anchor, Vec3 point, double distLim, double VertLimPercentage){
        double dist = anchor.distanceTo(point);
        Vec3 diff = anchor.subtract(point);

        Vec3 returnVec = point;

        if (dist > distLim) {
            returnVec = point.add(diff.normalize().scale(dist - distLim));
        }

        double flatDist = MathHelpers.flatDist(anchor, point);

        if (flatDist < distLim*VertLimPercentage) {
            double dX = diff.x();
            double dZ = diff.z();
            double dY = diff.y();
            returnVec = point.add(0, dY*(dist - distLim), 0);
        }

        return returnVec;
    }

    public static Vec3 driveAway(Vec3 anchor, Vec3 point, double hitboxRad, boolean flat) {
        double dist = anchor.distanceTo(point);
        Vec3 diff = anchor.subtract(point);

        Vec3 returnVec = point;

        if (dist < hitboxRad) {
            returnVec = point.subtract(diff.normalize().multiply((hitboxRad - dist), hitboxRad - dist, (hitboxRad - dist)));
        }

        double flatDist = MathHelpers.flatDist(anchor, point);

        return returnVec;
    }

    public static Vec3 distConstraint(ArrayList<Vec3> prevChain, Vec3 point, double nodeRadius) {
        Vec3 anchor = prevChain.get(prevChain.size() - 1);

        Vec3 returnStuff = anchor.add((point.subtract(anchor)).normalize().multiply(nodeRadius, nodeRadius, nodeRadius));

        for (Vec3 nextNode : prevChain) {
            if (returnStuff.distanceTo(nextNode) < nodeRadius) {
                returnStuff = anchor.add((returnStuff.subtract(nextNode)).normalize().multiply(nodeRadius, nodeRadius, nodeRadius));
            }

        }

        return returnStuff;
    }

    // Constrain the angle to be within a certain range of the anchor(Constraint entered must be positive)
    // works with both rad and deg
    public static double constrainAngle(double angle, double constraint) {
        //System.out.println("ang");
        //System.out.println(angle);

        if (angle < 0){
            angle = (360 - (angle % 360));
        }

        if (angle < 0 && angle < -constraint) {
            //System.out.println("yea");
            return -constraint;
        }

        if (angle > 0 && angle > constraint) {
            return constraint;
        }

        return angle;
    }

    public static Vec2 angleTo(Vec3 target, Vec3 mePos) {
        double d0 = target.x - mePos.x;
        double d1 = target.y - mePos.y;
        double d2 = target.z - mePos.z;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);

        double XAngle = (((float)(-(Mth.atan2(d1, d3) * 57.2957763671875))));
        double YAngle = (((float)(Mth.atan2(d2, d0) * 57.2957763671875) - 90.0F));

        return new Vec2((float) XAngle, (float) YAngle);
        //returns the y and x angle from the source location(mePos) to the target location(target) WITH RESPECT TO THE WORLD AXIS
        //try to not use this
        //Y is yaw, X is pitch
    }

    public static Vec3 rotateAroundCenterFlatDeg(Vec3 center, Vec3 me, Double angleInDeg) {
        //Rotates me around center, and sets the y coordinate to the coordinate of the center. The intake is in degrees.

        angleInDeg = -(Mth.DEG_TO_RAD*angleInDeg);

        double x1 = me.x - center.x;
        double z1 = me.z - center.z;

        double x2 = (x1 * Math.cos(angleInDeg) - z1 * Math.sin(angleInDeg));
        double z2 = (x1 * Math.sin(angleInDeg) + z1 * Math.cos(angleInDeg));

        double newMeX = (x2 + center.x);
        double newMeZ = (z2 + center.z);

        return new Vec3(newMeX, me.y, newMeZ);
    }

    public static Vec3 rotateAroundCenter3dDeg(Vec3 center, Vec3 me, float yRot, float xRot) {
        //Rotates me around center in 3 dimensions. The intake is in degrees.
        //the intake is in WORLD ROTATION - the reference plane is the serverlevel, not in relation to smthing else

        yRot = -(Mth.DEG_TO_RAD*yRot);
        xRot = -(Mth.DEG_TO_RAD*xRot);

        double x1 = me.x - center.x;
        double z1 = me.z - center.z;
        double y1 = me.y - center.y;

        double x2 = (x1 * Math.cos(yRot) - z1 * Math.sin(yRot));
        double z2 = (x1 * Math.sin(yRot) + z1 * Math.cos(yRot));
        double y2 = -(z1 * Math.sin(xRot) + y1 * Math.cos(xRot));

        double newMeX = (x2 + center.x);
        double newMeZ = (z2 + center.z);
        double newMeY = (y2 + center.y);

        return new Vec3(newMeX, newMeY, newMeZ);
    }

    public static float pitchProcess(Vec3 head, Vec3 tail) {

        if (!head.equals(tail)) {
            double ground = tail.distanceTo(head);
            double height = head.y() - tail.y();
            return (float) Math.asin(height / ground);
        }

        return 0;
    }

    public static double angleFromYdiff(Vec3 lead, Vec3 point, Vec3 trail) {
        double backHeight = point.y - trail.y;
        double frontHeight = lead.y - point.y;

        double backDist = point.distanceTo(trail);
        double frontDist = lead.distanceTo(point);

        double angFront = Math.atan(frontHeight/frontDist);
        double angBack = Math.atan(backHeight/backDist);


        return (angBack - angFront);
    }


    public static double getAngleForLinkTopDownFlat(Vec3 point, Vec3 parent, Vec3 child, Vec3 leftRef, Vec3 rightRef){
        //I AM PRETTY SURE LEFT IS NEGATIVE(down) BUT I AM TOO LAZY TO CONFIRM
        //basically this calculates the angle a tail bone that corresponds to a physics node must be set to(horizontal angle)
        //This only need to be done for the links that represent bones, links without corresponding bones(such as the link at the tip of the tail and at the head) does not need this run.
        //Since every link in between has a parent and child, an angle can be calculated.
        //If the bone's CHILD is closer in distance to the left ref, it is a negative angle, otherwise it is a positive angle.
        //Top down in this context means from parent to child

        //REMEMBER THE ENTITY'S POSITION IS ALSO A VALID BONE

        double C = Math.hypot(Math.abs(parent.x - child.x), Math.abs(parent.z - child.z));
        //distance from the parent to the link we're looking for to the child to the link we're looking for
        //hypotenuse because the flat 2d distance is just the hypotenuse of a right triangle where the other sides are the distance in x and z coords
        double A = Math.hypot(Math.abs(parent.x - point.x), Math.abs(parent.z - point.z));
        //distance from the point we're looking for to its parent
        double B = Math.hypot(Math.abs(point.x - child.x), Math.abs(point.z - child.z));
        //distance from the point we're looking for to its child

        double distToLeft = Math.abs(Math.hypot(child.x - leftRef.x, child.z - leftRef.z));
        double distToRight = Math.abs(Math.hypot(child.x - rightRef.x, child.z - rightRef.z));

        double c = Math.acos(((A*A)+(B*B)-(C*C))/(2*A*B));

        if (distToLeft >= distToRight) {
            //closer to right
            return c;
        } else {
            //closer to left
            return -c;
        }

    }

    public static double angleClamp(double angle, double poslim) {
        if (angle > 0) {
            return Mth.clamp(angle, poslim, Mth.TWO_PI - poslim);
        } else {
            return Mth.clamp(angle, -(Mth.TWO_PI - poslim), -poslim);
        }
    }

    public static double LerpDegreesConstantSpeed(double start, double end, double amount)
    {
        double difference = Math.abs(end - start);
        //System.out.println("guh");
        //System.out.println(Math.abs(end - start));

        if (difference > Mth.PI)
        {
            // We need to add on to one of the values.
            if (end > start)
            {
                // We'll add it on to start...
                start += Mth.TWO_PI;
            }
            else
            {
                // Add it on to end.
                end += Mth.TWO_PI;
            }
        }

        double value;
        // Interpolate it.

        if (start < end) {
            value = Math.max(end, (start + (amount)));
        } else {
            value = Math.min(end, (start + (amount)));
        }

        //System.out.println(value);

        // Wrap it..
        float rangeZero = Mth.TWO_PI;

        if (value >= 0 && value <= Mth.TWO_PI) {
            //System.out.println(value);
            return value;
        }

        return (value % rangeZero);
    }

    public static double LerpDegrees(double start, double end, double amount)
    {
        double difference = Math.abs(end - start);
        //System.out.println("guh");
        //System.out.println(Math.abs(end - start));

        if (difference > Mth.PI)
        {
            // We need to add on to one of the values.
            if (end > start)
            {
                // We'll add it on to start...
                start += Mth.TWO_PI;
            }
            else
            {
                // Add it on to end.
                end += Mth.TWO_PI;
            }
        }

        // Interpolate it.
        double value = (start + ((end - start) * amount));

        //System.out.println(value);

        // Wrap it..
        float rangeZero = Mth.TWO_PI;

        if (Double.isNaN(value)) {
            return 0;
        }

        if (value >= 0 && value <= Mth.TWO_PI) {
            //System.out.println(value);
            return value;
        }

        //System.out.println(value % rangeZero);

        return (value % rangeZero);
    }

    public static double flatDist(Vec3 a, Vec3 b) {
        return Math.abs(Math.hypot(a.x - b.x, a.z - b.z));
    }

}