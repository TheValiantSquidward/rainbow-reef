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
        CoreGeoBone yaw = this.getAnimationProcessor().getBone("onlyBody");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (entity.isUnderWater()) {
            CoreGeoBone tail1 = this.getAnimationProcessor().getBone("tail");
            CoreGeoBone tail2 = this.getAnimationProcessor().getBone("tail_tip");
            CoreGeoBone tail3 = this.getAnimationProcessor().getBone("tail_tip2");
            CoreGeoBone tail4 = this.getAnimationProcessor().getBone("tail_tip3");
            CoreGeoBone tail5 = this.getAnimationProcessor().getBone("tail_tip4");

            double yawOldZ = yaw.getRotZ();
            yaw.setRotZ((float) (MathHelpers.LerpDegrees(yawOldZ,Mth.PI - MathHelpers.getAngleForLinkTopDownFlat(entity.tail1Point, entity.tail0Point, entity.tail2Point, entity.leftRefPoint, entity.rightRefPoint)*1.2, 0.01)));
            //basically lerp smooths out the transition, the lower pDelta is, the smoother it is
            //the 1.5 is just a scale factor for visuals
            //System.out.println(yaw.getRotZ());


            tail1.setRotY((float) (Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail1Point, entity.tail0Point, entity.tail2Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            tail2.setRotY((float) (Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail2Point, entity.tail1Point, entity.tail3Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            tail3.setRotY((float) (Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail3Point, entity.tail2Point, entity.tail4Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            tail4.setRotY((float) (Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail4Point, entity.tail3Point, entity.tail5Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            tail5.setRotY((float) (Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail5Point, entity.tail4Point, entity.tail6Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            //No deg to rad because the arccos function used to return the angle
            //gotta set up UNIQUE NODES FOR EACH BONE

            body.setRotX((float) (MathHelpers.angleFromYdiff(entity.position(), entity.tail0Point, entity.tail1Point)));
            //System.out.println("call");
            tail1.setRotX((float) (Mth.PI * MathHelpers.angleFromYdiff(entity.tail0Point, entity.tail1Point, entity.tail2Point)));
            tail2.setRotX((float) (Mth.PI * MathHelpers.angleFromYdiff(entity.tail1Point, entity.tail2Point, entity.tail3Point)));
            tail3.setRotX((float) (Mth.PI * MathHelpers.angleFromYdiff(entity.tail2Point, entity.tail3Point, entity.tail4Point)));
            tail4.setRotX((float) (Mth.PI * MathHelpers.angleFromYdiff(entity.tail3Point, entity.tail4Point, entity.tail5Point)));
            tail5.setRotX((float) (Mth.PI * MathHelpers.angleFromYdiff(entity.tail4Point, entity.tail5Point, entity.tail6Point)));
            //positive RotX is DOWNWARDS, and increasing angle swings it forwards towards the head

            /*"body": {
                "rotation": ["Math.cos((query.anim_time)*100*3.14)*Math.cos((query.anim_time)*50*3.14/4)*-10", 0, "Math.cos((query.anim_time)*45)*15"],
                "position": [0, "2+Math.cos((query.anim_time)*100*3.14)*Math.cos((query.anim_time)*50*3.14/4)*0.5", 0]
            },*/
        }
    }



}