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

public class SmallSharkModel extends GeoModel<SmallSharkEntity> {
    @Override
    public ResourceLocation getModelResource(SmallSharkEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "geo/small_shark.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SmallSharkEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/epauletteshark.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SmallSharkEntity animatable) {
        return new ResourceLocation(RainbowReef.MOD_ID, "animations/small_shark.animation.json");
    }

    @Override
    public void setCustomAnimations(SmallSharkEntity entity, long uniqueID, AnimationState<SmallSharkEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        CoreGeoBone core = this.getAnimationProcessor().getBone("root");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        core.setRotX(extraData.headPitch() * (Mth.DEG_TO_RAD));
    }

}