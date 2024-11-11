package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.SeahorseEntity;
import net.thevaliantsquidward.rainbowreef.entity.custom.SmallSharkEntity;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.SmallSharkEntity;
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

            tail1.setRotY((float) (tail1.getRotY() + Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail0Point, entity.position(), entity.tail1Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            tail2.setRotY((float) (tail2.getRotY() + Mth.PI - MathHelpers.angleClamp(MathHelpers.getAngleForLinkTopDownFlat(entity.tail1Point, entity.tail0Point, entity.tail2Point, entity.leftRefPoint, entity.rightRefPoint), Mth.PI*0.75)));
            //No deg to rad because the arccos function used to return the angle
            //gotta set up UNIQUE NODES FOR EACH BONE

            core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
            tail1.setRotX((float) (tail1.getRotX() - (MathHelpers.vertAngleClamp(MathHelpers.angleFromYdiff(entity.position(), entity.tail0Point, entity.tail1Point), Mth.PI*0.15))));
            tail2.setRotX((float) (tail1.getRotX() - (MathHelpers.vertAngleClamp(MathHelpers.angleFromYdiff(entity.tail0Point, entity.tail1Point, entity.tail2Point), Mth.PI*0.15))));
            //positive RotX is DOWNWARDS, and increasing angle swings it forwards towards the head
        }
        //ik stuff END
    }

}