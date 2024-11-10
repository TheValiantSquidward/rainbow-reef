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

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));

        //ik stuff START
        if (entity.isInWater()) {
            CoreGeoBone tail1 = this.getAnimationProcessor().getBone("tail");
            double tail1Length = 0.75;
            //measured by dividing the bone's distance to its child in blockbench by 16(everything's lined up straight in blockbench
            CoreGeoBone tail2 = this.getAnimationProcessor().getBone("tail_fin");
            double tail2Length = 0.5625;

            tail1.setRotY((float) (tail1.getRotY() + Mth.PI - MathHelpers.getAngleForLinkTopDownFlat(entity.tail0Point, entity.position(), entity.tail1Point, entity.leftRefPoint, entity.rightRefPoint)));
            tail2.setRotY((float) (tail2.getRotY() + Mth.PI - MathHelpers.getAngleForLinkTopDownFlat(entity.tail1Point, entity.tail0Point, entity.tail2Point, entity.leftRefPoint, entity.rightRefPoint)));
            //No deg to rad because the arccos function used to return the angle
            //gotta set up UNIQUE NODES FOR EACH BONE

            tail1.setRotX((float) -(MathHelpers.angleFromYdiff(Math.hypot(entity.tail0Point.x - entity.tail1Point.x, entity.tail0Point.z - entity.tail1Point.z), entity.tail0Point, entity.tail1Point)));
            tail2.setRotX((float) -(MathHelpers.angleFromYdiff(Math.hypot(entity.tail1Point.x - entity.tail2Point.x, entity.tail1Point.z - entity.tail2Point.z), entity.tail1Point, entity.tail2Point)));
            //positive RotX is DOWNWARDS, and increasing angle swings it forwards towards the head
        }
        //ik stuff END
    }

}