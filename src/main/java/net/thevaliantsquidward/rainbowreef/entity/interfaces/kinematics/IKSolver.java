package net.thevaliantsquidward.rainbowreef.entity.interfaces.kinematics;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thevaliantsquidward.rainbowreef.utils.MathHelpers;

public class IKSolver {

    public Vec3 prevPos = new Vec3(0, 0, 0);
    public Vec3 nowPos = new Vec3(0, 0, 0);
    public float prevPitch = 0;
    public float pitch = 0;

    public boolean tailHasGravity = false;

    public boolean canReturnToCenter = false;

    public double vertLimPercentage;

    public double prevYHeadRot = 0;
    public double deltaYHeadRot = 0;
    public boolean prevHasFlipped = false;

    private final LivingEntity entity;
    private final int nodeCount;
    private final double nodeHitboxRadius;
    private final double stiffness = 60;

    private Vec3[] nodes = {};
    private Vec3[] coreNodes = {};
    private enum nodeLimits {POS_LIMIT, NEG_LIMIT}
    private double nodeDist;
    private int bodyLength;

    private double bodyPitch = 0;
    private double currentBodyPitch = 0;
    private double[] tailYaws = {};
    private double[] tailPitches = {};
    private double[] currentTailYaws = {};
    private double[] currentTailPitches = {};

    private Vec3 torsoFront;
    private Vec3 torsoFrontOffset = new Vec3(0, 0, -1);

    private Vec3 torsoBack;
    private Vec3 torsoBackOffset = new Vec3(0, 0, 1);

    private Vec3 rightRefPoint;
    private Vec3 rightRefOffset = new Vec3(1, 0, 0);

    private Vec3 leftRefPoint;
    private Vec3 leftRefOffset = new Vec3(-1, 0, 0);

    private Vec3 upRefPoint;
    private final Vec3 upRefOffset = new Vec3(0, -1, 0);

    private Vec3 downRefPoint;
    private final Vec3 downRefOffset = new Vec3(0, 1, 0);

    private Boolean shiftNodes = false;

    public IKSolver(LivingEntity entity, int nodeCount, double nodeDist, double VertLimPercentage, boolean tailHasGravity, boolean returnToCenter) {

        this.entity = entity;
        this.nodeCount = nodeCount;
        //number of nodes^
        this.nodeDist = nodeDist;
        //distance between each node^
        this.nodeHitboxRadius = nodeDist * 0.9;
        //size of the hitbox of each node
        this.tailHasGravity = tailHasGravity;
        //whether this chain is affected by gravity
        this.canReturnToCenter = returnToCenter;
        //whether this chain automatically returns to 0 rotation

        this.vertLimPercentage = VertLimPercentage;
        //how close the memebrs of this chain can get to each other on a 2d plane

        //this.bodyLength = Math.min(nodeDist, (int) (entity.getBoundingBox().getXsize()/2));
        //Length of the body used to calculate body hitbox
        this.nodes = new Vec3[nodeCount];

        //this.torsoFrontOffset = new Vec3(0, 0, -bodyLength);
        //this.torsoBackOffset = new Vec3(0, 0, bodyLength);

        this.tailYaws = new double[nodeCount];
        this.tailPitches = new double[nodeCount];
        this.currentTailYaws = new double[nodeCount];
        this.currentTailPitches = new double[nodeCount];

        this.torsoFrontOffset = this.torsoFrontOffset.multiply(1, 1, nodeDist);
        this.torsoBackOffset = this.torsoBackOffset.multiply(1, 1, nodeDist);

        leftRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(leftRefOffset), (double) -entity.getYRot());
        rightRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(rightRefOffset), (double) -entity.getYRot());
        upRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(upRefOffset), (double) -entity.getYRot());
        downRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(downRefOffset), (double) -entity.getYRot());
        torsoFront = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(torsoFrontOffset), (double) -entity.getYRot());
        torsoBack = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(torsoBackOffset), (double) -entity.getYRot());

        initTailPoints();
    }

    public IKSolver(LivingEntity entity, int nodeCount, int nodeDist, boolean shiftNodes, boolean tailHasGravity) {

        this.entity = entity;
        this.nodeCount = nodeCount;
        //number of nodes^
        this.nodeDist = nodeDist;
        //distance between each node^
        this.nodeHitboxRadius = nodeDist - 0.2;
        //size of the hitbox of each node

        //this.bodyLength = Math.min(nodeDist, (int) (entity.getBoundingBox().getXsize()/2));
        //Length of the body used to calculate body hitbox
        this.nodes = new Vec3[nodeCount];
        this.coreNodes = new Vec3[nodeCount];

        //this.torsoFrontOffset = new Vec3(0, 0, -bodyLength);
        //this.torsoBackOffset = new Vec3(0, 0, bodyLength);

        this.tailYaws = new double[nodeCount];
        this.tailPitches = new double[nodeCount];
        this.currentTailYaws = new double[nodeCount];
        this.currentTailPitches = new double[nodeCount];

        leftRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(leftRefOffset), (double) -entity.getYRot());
        rightRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(rightRefOffset), (double) -entity.getYRot());
        upRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(upRefOffset), (double) -entity.getYRot());
        downRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(downRefOffset), (double) -entity.getYRot());
        torsoFront = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(torsoFrontOffset), (double) -entity.getYRot());
        torsoBack = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), entity.position().subtract(torsoBackOffset), (double) -entity.getYRot());

        if (shiftNodes == true) {
            torsoFrontOffset = new Vec3(0, -1, -1);;
            torsoBackOffset = new Vec3(0, -1, 1);;
            this.shiftNodes = true;
        }

        initTailPoints();
    }

    public void TakePerTickAction(LivingEntity entity) {
        this.prevPos = nowPos;
        this.nowPos = this.entity.position();

        if (entity.tickCount <= 1) {
            this.initTailPoints();
        }

        this.calculateTailAngles(entity);
    }


    public void initTailPoints() {

        // Initialize the first tail point (tail0) relative to the entity and the nose point
        torsoFront = MathHelpers.rotateAroundCenter3dDeg(this.nowPos, this.nowPos.subtract(torsoFrontOffset), -this.entity.getYRot(), -this.pitch);
        torsoBack = MathHelpers.rotateAroundCenter3dDeg(this.nowPos, this.nowPos.subtract(torsoBackOffset), -this.entity.getYRot(), this.prevPitch);

        Vec3 diff = torsoBack.subtract(torsoFront);

        nodes[0] = torsoBack.add(diff);

        // Chain the rotations for subsequent tail segments.
        for (int i = 1; i < nodeCount; i++) {
            nodes[i] = torsoBack.add(diff.multiply(i + 1, 1, i + 1));
        }

    }

    /**
     * Fully update the IK angles and re-calculate the rotated positions.
     */
    public void calculateTailAngles(LivingEntity entity) {


        // torsoFront corresponds to the start of the body, torsoBack correspond to the back of the body(start of the tail).
        torsoFront = MathHelpers.rotateAroundCenter3dDeg(entity.position(), entity.position().subtract(torsoFrontOffset), -entity.getYRot(), -entity.getXRot());
        torsoBack = MathHelpers.rotateAroundCenter3dDeg(entity.position(), entity.position().subtract(torsoBackOffset), -entity.getYRot(), -entity.getXRot());
        //adds the chain that represents the creature's body first

        nodes[0] = MathHelpers.distConstraintSingle(torsoBack, nodes[0], this.nodeDist, this.vertLimPercentage);
        float angleY = (float) (Mth.RAD_TO_DEG*MathHelpers.getAngleForLinkTopDownFlat(torsoFront, torsoBack, nodes[0], this.leftRefPoint, this.rightRefPoint));
        //float angleX = (float) (Mth.RAD_TO_DEG*MathHelpers.angleFromYdiff(torsoFront, torsoBack, nodes[0]));
        nodes[0] = MathHelpers.quickReturn(entity.level(), torsoBack, nodes[0], angleY, 1, this.tailHasGravity, this.canReturnToCenter);
        //return to center for first node
        nodes[0] = MathHelpers.driveAway(torsoFront, nodes[0], entity.getBoundingBox().getXsize(), false);
        nodes[0] = MathHelpers.driveAway(entity.position(), nodes[0], entity.getBoundingBox().getXsize(), false);
        nodes[0] = MathHelpers.driveAway(torsoBack, nodes[0], nodeHitboxRadius, false);
        //collision

        nodes[1] = MathHelpers.distConstraintSingle(nodes[0], nodes[1], this.nodeDist, this.vertLimPercentage);
        angleY = (float) (Mth.RAD_TO_DEG*MathHelpers.getAngleForLinkTopDownFlat(torsoBack, nodes[0], nodes[1], this.leftRefPoint, this.rightRefPoint));
        //angleX = (float) (Mth.RAD_TO_DEG*MathHelpers.angleFromYdiff(torsoBack, nodes[0], nodes[1]));
        nodes[1] = MathHelpers.quickReturn(entity.level(), nodes[0], nodes[1], angleY, 2, this.tailHasGravity, this.canReturnToCenter);
        //return to center
        nodes[1] = MathHelpers.driveAway(torsoFront, nodes[1], entity.getBoundingBox().getXsize(), false);
        nodes[1] = MathHelpers.driveAway(entity.position(), nodes[1], entity.getBoundingBox().getXsize(), false);
        nodes[1] = MathHelpers.driveAway(torsoBack, nodes[1], nodeHitboxRadius, false);
        nodes[1] = MathHelpers.driveAway(nodes[0], nodes[1], nodeHitboxRadius, false);
        //collision


        // Chain-update subsequent tail points after no longer needing torso segments.
        for (int i = 2; i < nodeCount; i++) {
            nodes[i] = shiftNodes ? nodes[i] = nodes[i].subtract(0, -1, 0) : nodes[i];
            nodes[i] = MathHelpers.distConstraintSingle(nodes[i - 1], nodes[i], this.nodeDist, this.vertLimPercentage);

            angleY = (float) (Mth.RAD_TO_DEG*MathHelpers.getAngleForLinkTopDownFlat(nodes[i - 2], nodes[i - 1], nodes[i], this.leftRefPoint, this.rightRefPoint));
            //angleX = (float) (Mth.RAD_TO_DEG*MathHelpers.angleFromYdiff(nodes[i - 2], nodes[i - 1], nodes[i]));
            nodes[i] = MathHelpers.quickReturn(entity.level(), nodes[i - 1], nodes[i], angleY, 2, this.tailHasGravity, this.canReturnToCenter);
            //return to center

            nodes[i] = MathHelpers.driveAway(torsoFront, nodes[i], entity.getBoundingBox().getXsize(), false);
            nodes[i] = MathHelpers.driveAway(entity.position(), nodes[i], entity.getBoundingBox().getXsize(), false);
            nodes[i] = MathHelpers.driveAway(torsoBack, nodes[i], nodeHitboxRadius, false);
            for (int x = 0; i < i; i++) {
                nodes[i] = MathHelpers.driveAway(nodes[x], nodes[i], nodeHitboxRadius, false);
            }
            //collision

        }


        //visualizeNodes(entity.level());



        // Update Geckolib - usable bone angles for each node.
        tailYaws[0] = Math.toRadians(MathHelpers.angleTo(torsoFront, torsoBack).y - MathHelpers.angleTo(torsoBack, this.nodes[0]).y);
        tailYaws[1] = Math.toRadians(MathHelpers.angleTo(torsoBack, this.nodes[0]).y - MathHelpers.angleTo(this.nodes[0], this.nodes[1]).y);
        for (int i = 2; i < nodes.length - 1; i++) {
            tailYaws[i] = Math.toRadians(MathHelpers.angleTo(nodes[i - 2], nodes[i - 1]).y - MathHelpers.angleTo(nodes[i - 1], nodes[i]).y);
        }
        //Yaw

        tailPitches[0] = MathHelpers.angleFromYdiff(torsoFront, torsoBack, this.nodes[0]);
        tailPitches[1] = MathHelpers.angleFromYdiff(torsoBack, this.nodes[0], this.nodes[1]);
        for (int i = 2; i < nodes.length - 1; i++) {
            tailPitches[i] = MathHelpers.angleFromYdiff(this.nodes[i - 2], this.nodes[i - 1], this.nodes[i]);
        }
        //Pitch



        //side refs don't move vertically
        leftRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(leftRefOffset), (double) -entity.getYRot());
        rightRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(rightRefOffset), (double) -entity.getYRot());
        upRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(upRefOffset), (double) -entity.getYRot());
        downRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(downRefOffset), (double) -entity.getYRot());
        //END of IK


    }

    public void calculateTailAnglesNoConstraint(LivingEntity entity) {
        // torsoFront corresponds to the start of the body, torsoBack correspond to the back of the body(start of the tail).

        this.prevPitch = this.pitch;
        this.pitch = MathHelpers.pitchProcess(this.nowPos, this.prevPos);

        torsoFront = MathHelpers.rotateAroundCenter3dDeg(entity.position(), entity.position().subtract(torsoFrontOffset), -entity.getYRot(), Mth.RAD_TO_DEG * this.pitch);
        torsoBack = MathHelpers.rotateAroundCenter3dDeg(entity.position(), entity.position().subtract(torsoBackOffset), -entity.getYRot(), Mth.RAD_TO_DEG *this.prevPitch);

        //visualizeNodes(entity.level());

/*        System.out.println("guh");
        System.out.println(torsoFront);
        System.out.println(entity.position());
        System.out.println(torsoBack);
        System.out.println(nodes[0]);
        System.out.println(nodes[1]);
        System.out.println(entity.level().isClientSide());*/

        // Chain-update subsequent tail points after no longer needing torso segments.
        nodes[0] = MathHelpers.distConstraintSingle(torsoBack, nodes[0], nodeDist, this.vertLimPercentage);
        for (int i = 1; i < nodeCount; i++) {
            nodes[i] = MathHelpers.distConstraintSingle(nodes[i - 1], nodes[i], nodeDist, this.vertLimPercentage);
        }

        //System.out.println("---------------------------------------------------");
        for (int x = nodeCount - 1; x >= 0; x--) {
            //ensures that points do not overlap
            //System.out.println("guh");
            //System.out.println(x);

            if (x == 0) {
                nodes[x] = MathHelpers.driveAway(torsoFront, nodes[x], entity.getBoundingBox().getXsize(), false);
                nodes[x] = MathHelpers.driveAway(entity.position(), nodes[x], entity.getBoundingBox().getXsize(), false);
                nodes[x] = MathHelpers.driveAway(torsoBack, nodes[x], nodeHitboxRadius, false);
                break;
                //if it's just the tail base only check collision against the body nodes
            }

            nodes[x] = MathHelpers.driveAway(torsoFront, nodes[x], entity.getBoundingBox().getXsize(), false);
            nodes[x] = MathHelpers.driveAway(entity.position(), nodes[x], entity.getBoundingBox().getXsize(), false);
            nodes[x] = MathHelpers.driveAway(torsoBack, nodes[x], nodeHitboxRadius, false);
            for (int i = 0; i < x; i++) {
                //System.out.println(i);
                nodes[x] = MathHelpers.driveAway(nodes[i], nodes[x], nodeHitboxRadius, false);
            }
            //check against all previous nodes

        }

        nodes[0] = MathHelpers.distConstraintSingle(torsoBack, nodes[0], nodeDist, this.vertLimPercentage);
        for (int i = 1; i < nodeCount; i++) {
            nodes[i] = MathHelpers.distConstraintSingle(nodes[i - 1], nodes[i], nodeDist, this.vertLimPercentage);
        }




        // Update Geckolib - usable bone angles for each node.
        tailYaws[0] = Math.toRadians(MathHelpers.angleTo(torsoFront, torsoBack).y - MathHelpers.angleTo(torsoBack, this.nodes[0]).y);
        tailYaws[1] = Math.toRadians(MathHelpers.angleTo(torsoBack, this.nodes[0]).y - MathHelpers.angleTo(this.nodes[0], this.nodes[1]).y);
        for (int i = 2; i < nodes.length - 1; i++) {
            tailYaws[i] = Math.toRadians(MathHelpers.angleTo(nodes[i - 2], nodes[i - 1]).y - MathHelpers.angleTo(nodes[i - 1], nodes[i]).y);
        }
        //Yaw

        tailPitches[0] = MathHelpers.angleFromYdiff(torsoFront, torsoBack, this.nodes[0]);
        tailPitches[1] = MathHelpers.angleFromYdiff(torsoBack, this.nodes[0], this.nodes[1]);
        for (int i = 2; i < nodes.length - 1; i++) {
            tailPitches[i] = MathHelpers.angleFromYdiff(this.nodes[i - 2], this.nodes[i - 1], this.nodes[i]);
        }
        //Pitch




        //side refs don't move vertically
        leftRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(leftRefOffset), (double) -entity.getYRot());
        rightRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(rightRefOffset), (double) -entity.getYRot());
        upRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(upRefOffset), (double) -entity.getYRot());
        downRefPoint = MathHelpers.rotateAroundCenterFlatDeg(entity.position(), this.entity.position().subtract(downRefOffset), (double) -entity.getYRot());
        //END of IK
    }

    private boolean isSuspectedCompletedRotation(float lastRotation) {
        //lastRotation = Math.abs(lastRotation) - 270;
        float rotations = Mth.abs(lastRotation / (90f * Mth.DEG_TO_RAD));
        float partialRotation = 1 - (rotations - (int)rotations);

        return partialRotation < 0.026 * rotations;
    }

    public double[] getTailYaws() {
        return tailYaws;
    }

    public double[] getTailPitches() {
        return tailPitches;
    }

    public double[] getCurrentTailYaws() {
        return currentTailYaws;
    }

    public double[] getCurrentTailPitches() {
        return currentTailPitches;
    }

    public double getBodyPitch() {
        return bodyPitch;
    }

    public double getCurrentBodyPitch() {
        return currentBodyPitch;
    }

    public void visualizeNodes(Level level) {
        int viewOffset = 1;
        //if (!level.isClientSide()) {
        /*    ServerLevel L = (ServerLevel) level;
            //L.sendParticles(ParticleTypes.BUBBLE_POP, (entity.getX()), (entity.getY() + viewOffset), (entity.getZ()), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            L.sendParticles(ParticleTypes.BUBBLE_POP, (torsoFront.x), (torsoFront.y + viewOffset), (torsoFront.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            L.sendParticles(ParticleTypes.BUBBLE_POP, (torsoBack.x), (torsoBack.y + viewOffset), (torsoBack.z), 1, 0.0D, 0.0D, 0.0D, 0.0D);


            L.sendParticles(ParticleTypes.BUBBLE_POP, (nodes[0].x), (nodes[0].y + viewOffset), (nodes[0].z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            for (int i = 1; i < nodeCount; i++) {
                L.sendParticles(ParticleTypes.BUBBLE_POP, (nodes[i].x), (nodes[i].y + viewOffset), (nodes[i].z), 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
*/
        //} else {
            //level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_POP, (entity.getX()), (entity.getY() + viewOffset), (entity.getZ()), 0, 0.0D, 0.0D);
            level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_POP, (torsoFront.x), (torsoFront.y + viewOffset), (torsoFront.z), 0, 0.0D, 0.0D);
            level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_POP, (torsoBack.x), (torsoBack.y + viewOffset), (torsoBack.z), 0, 0.0D, 0.0D);


            level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_POP, (nodes[0].x), (nodes[0].y + viewOffset), (nodes[0].z), 0, 0.0D, 0.0D);
            for (int i = 1; i < nodeCount; i++) {
                level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_POP, (nodes[i].x), (nodes[i].y + viewOffset), (nodes[i].z), 0, 0.0D, 0.0D);
        //    }
        }
    }
}