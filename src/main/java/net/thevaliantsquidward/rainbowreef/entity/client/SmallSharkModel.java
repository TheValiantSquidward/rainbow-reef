package net.thevaliantsquidward.rainbowreef.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
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
import software.bernie.geckolib.model.GeoModel;

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

}