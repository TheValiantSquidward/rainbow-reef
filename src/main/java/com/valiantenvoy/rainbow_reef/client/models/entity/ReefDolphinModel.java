package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.DolphinAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.utils.DolphinAccess;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Dolphin;

public class ReefDolphinModel extends ReefModel<Dolphin> {

    private final ModelPart root;
    private final ModelPart swim_control;

    public ReefDolphinModel(ModelPart root) {
        this.root = root.getChild("root");
        this.swim_control = this.root.getChild("swim_control");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    protected void setupAnimations(Dolphin entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        if (entity instanceof DolphinAccess dolphinAccess) {
            this.animateWalkSmooth(dolphinAccess.getSwimAnimationState(), DolphinAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
            this.animateIdleSmooth(dolphinAccess.getIdleAnimationState(), DolphinAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
            this.animateSmooth(dolphinAccess.getFlopAnimationState(), DolphinAnimations.FLOP, ageInTicks, partialTicks);
            this.animateSmooth(dolphinAccess.getJumpAnimationState(), DolphinAnimations.AIRBORNE, ageInTicks, partialTicks);
            this.swim_control.xRot += dolphinAccess.getSwimPitch(partialTicks) * Mth.DEG_TO_RAD;
            this.swim_control.zRot += -dolphinAccess.getSwimRoll(partialTicks) * Mth.DEG_TO_RAD;
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -3.5F, 0.5F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.5F, -3.0F, 8.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 20).addBox(-4.0F, -7.0F, -6.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(42, 0).addBox(-3.0F, -7.0F, -8.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -3.0F));

        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(34, 40).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(18, 36).addBox(-2.0F, -7.0F, 2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(50, 40).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(44, 35).addBox(-1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 15).addBox(-2.0F, -5.0F, 2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 48).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -10.0F));

        PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 20).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 10.0F));

        tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(20, 55).addBox(-5.0F, -0.5F, 0.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

        body.addOrReplaceChild("back_fin_hump", CubeListBuilder.create().texOffs(0, 36).addBox(-1.5F, -2.75F, -1.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 4.0F, -0.5236F, 0.0F, 0.0F));

        body.addOrReplaceChild("back_fin", CubeListBuilder.create().texOffs(44, 6).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(48, 48).addBox(-0.5F, 1.25F, 0.5F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 2.0F, 1.0472F, 0.0F, 0.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(18, 40).addBox(-0.9126F, -1.5688F, -0.1111F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 46).addBox(-0.9126F, -1.5688F, -0.1111F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 3.5F, -3.0F, 0.9599F, 0.0F, 1.8675F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(18, 40).mirror().addBox(-0.0874F, -1.5688F, -0.1111F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 46).mirror().addBox(-0.0874F, -1.5688F, -0.1111F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 3.5F, -3.0F, 0.9599F, 0.0F, -1.8675F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}