package net.thevaliantsquidward.rainbowreef.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.BoxfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.PipefishEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.RayEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import net.thevaliantsquidward.rainbowreef.util.MathHelpers;
import software.bernie.shadowed.eliotlash.mclib.utils.Interpolations;
import software.bernie.shadowed.eliotlash.mclib.utils.MathUtils;

public class RayModel extends GeoModel<RayEntity> {
    @Override
    public ResourceLocation getModelResource(RayEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/eagle_ray.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RayEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/eagleray/spotted_eagle_ray.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RayEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/eagle_ray.animation.json");
    }

    @Override
    public void setCustomAnimations(RayEntity entity, long uniqueID, AnimationState<RayEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone body = this.getAnimationProcessor().getBone("body");
        CoreGeoBone roll = this.getAnimationProcessor().getBone("onlyBody");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (entity.isUnderWater()) {
            CoreGeoBone tail1 = this.getAnimationProcessor().getBone("tail");
            CoreGeoBone tail2 = this.getAnimationProcessor().getBone("tail_tip");
            CoreGeoBone tail3 = this.getAnimationProcessor().getBone("tail_tip2");
            CoreGeoBone tail4 = this.getAnimationProcessor().getBone("tail_tip3");
            CoreGeoBone tail5 = this.getAnimationProcessor().getBone("tail_tip4");

            double rollOldZ = roll.getRotZ();
            roll.setRotZ((float) (MathHelpers.LerpDegrees(0.01, rollOldZ,Mth.PI - MathHelpers.getAngleForLinkTopDownFlat(entity.tail1Point, entity.tail0Point, entity.tail2Point, entity.leftRefPoint, entity.rightRefPoint)*1.2)));
            //basically lerp smooths out the transition, the lower pDelta is, the smoother it is
            //the 1.2 is just a scale factor for visuals
            //System.out.println(yaw.getRotZ());

            tail1.setRotY((float) (Mth.PI - MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail1OldY, (float) entity.tail1Angle, 0.25), Mth.PI*0.75)));
            tail2.setRotY((float) (Mth.PI - MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail2OldY, (float) entity.tail2Angle, 0.25), Mth.PI*0.75)));
            tail3.setRotY((float) (Mth.PI - MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail3OldY, (float) entity.tail3Angle, 0.25), Mth.PI*0.75)));
            tail4.setRotY((float) (Mth.PI - MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail4OldY, (float) entity.tail4Angle, 0.25), Mth.PI*0.75)));
            tail5.setRotY((float) (Mth.PI - MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail5OldY, (float) entity.tail5Angle, 0.25), Mth.PI*0.75)));
            entity.tail1OldY = MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail1OldY, (float) entity.tail1Angle, 0.25), Mth.PI*0.75);
            entity.tail2OldY = MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail2OldY, (float) entity.tail2Angle, 0.25), Mth.PI*0.75);
            entity.tail3OldY = MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail3OldY, (float) entity.tail3Angle, 0.25), Mth.PI*0.75);
            entity.tail4OldY = MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail4OldY, (float) entity.tail4Angle, 0.25), Mth.PI*0.75);
            entity.tail5OldY = MathHelpers.angleClamp(Interpolations.lerpYaw((float) entity.tail5OldY, (float) entity.tail5Angle, 0.25), Mth.PI*0.75);
            //this runs BETWEEN TICKS
            //0.25 means it interpolates to a quarter of the way to the target
            //THIS FUCKING WORKS LESGOOO

            //No deg to rad because the arccos function used to return the angle
            //gotta set up UNIQUE NODES FOR EACH BONE

            //System.out.println(tail1OldY - tail1Angle);

            double oldBodyPitch = body.getRotX();
            double BodyPitch = ((float) (MathHelpers.angleFromYdiff(entity.position(), entity.tail0Point, entity.tail1Point)));
            body.setRotX((float) (Interpolations.lerpYaw((float) oldBodyPitch, (float) BodyPitch, 0.01)));
            //System.out.println("call");

            //tail1.setRotX((float) (Interpolations.lerpYaw((float) entity.tail1OldPitch, (float) entity.tail1Pitch, 0.01)));
            //tail2.setRotX((float) (Interpolations.lerpYaw((float) entity.tail2OldPitch, (float) entity.tail2Pitch, 0.01)));
            //tail3.setRotX((float) (Interpolations.lerpYaw((float) entity.tail3OldPitch, (float) entity.tail3Pitch, 0.01)));
            //tail4.setRotX((float) (Interpolations.lerpYaw((float) entity.tail4OldPitch, (float) entity.tail4Pitch, 0.01)));
            //tail5.setRotX((float) (Interpolations.lerpYaw((float) entity.tail5OldPitch, (float) entity.tail5Pitch, 0.01)));
            entity.tail1OldPitch = (Interpolations.lerpYaw((float) entity.tail1OldPitch, (float) entity.tail1Pitch, 0.01));
            entity.tail2OldPitch = (Interpolations.lerpYaw((float) entity.tail2OldPitch, (float) entity.tail2Pitch, 0.01));
            entity.tail3OldPitch = (Interpolations.lerpYaw((float) entity.tail3OldPitch, (float) entity.tail3Pitch, 0.01));
            entity.tail4OldPitch = (Interpolations.lerpYaw((float) entity.tail4OldPitch, (float) entity.tail4Pitch, 0.01));
            entity.tail5OldPitch = (Interpolations.lerpYaw((float) entity.tail5OldPitch, (float) entity.tail5Pitch, 0.01));

            //positive RotX is DOWNWARDS, and increasing angle swings it forwards towards the head

            /*"body": {
                "rotation": ["Math.cos((query.anim_time)*100*3.14)*Math.cos((query.anim_time)*50*3.14/4)*-10", 0, "Math.cos((query.anim_time)*45)*15"],
                "position": [0, "2+Math.cos((query.anim_time)*100*3.14)*Math.cos((query.anim_time)*50*3.14/4)*0.5", 0]
            },*/
        }
    }



}