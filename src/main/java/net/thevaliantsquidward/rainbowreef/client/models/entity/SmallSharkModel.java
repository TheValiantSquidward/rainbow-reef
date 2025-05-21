package net.thevaliantsquidward.rainbowreef.client.models.entity;

import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.SmallSharkEntity;
import net.minecraft.resources.ResourceLocation;

import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import net.thevaliantsquidward.rainbowreef.util.MathHelpers;

public class SmallSharkModel extends GeoModel<SmallSharkEntity> {
    @Override
    public ResourceLocation getModelResource(SmallSharkEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/small_shark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SmallSharkEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/smallshark/epauletteshark.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SmallSharkEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/small_shark.animation.json");
    }

    @Override
    public void setCustomAnimations(SmallSharkEntity entity, long uniqueID, AnimationState<SmallSharkEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("core");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        //ik stuff START
        if (entity.isInWater()) {
            CoreGeoBone tail1 = this.getAnimationProcessor().getBone("tail");
            CoreGeoBone tail2 = this.getAnimationProcessor().getBone("tail_fin");

            tail1.setRotY((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail1Yaw, (float) entity.tail1Yaw, 0.1))));
            tail1.setRotY((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail2Yaw, (float) entity.tail2Yaw, 0.1))));
            entity.currentTail1Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail1Yaw, (float) entity.tail1Yaw, 0.1);
            entity.currentTail2Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail2Yaw, (float) entity.tail2Yaw, 0.1);
            //No deg to rad because the arccos function used to return the angle
            //gotta set up UNIQUE NODES FOR EACH BONE

            core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
            tail1.setRotX((float) (tail1.getRotX() - MathHelpers.LerpDegrees((float) entity.currentTail1Pitch, (float) entity.tail1Pitch, 0.1)));
            tail2.setRotX((float) (tail2.getRotX() - MathHelpers.LerpDegrees((float) entity.currentTail2Pitch, (float) entity.tail2Pitch, 0.1)));
            entity.currentTail1Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail1Pitch, (float) entity.tail1Pitch, 0.1);
            entity.currentTail2Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail2Pitch, (float) entity.tail2Pitch, 0.1);
            //positive RotX is DOWNWARDS, and increasing angle swings it forwards towards the head
        }
        //ik stuff END
    }

}