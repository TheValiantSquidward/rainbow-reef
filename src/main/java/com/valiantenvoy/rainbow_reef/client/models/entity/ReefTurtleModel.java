package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.TurtleAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.utils.TurtleAccess;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Turtle;

public class ReefTurtleModel extends ReefModel<Turtle> {

    private final ModelPart root;
    private final ModelPart swim_control;
    private final ModelPart head;
    private final ModelPart pregnant;

    public ReefTurtleModel(ModelPart root) {
        this.root = root.getChild("root");
        this.swim_control = this.root.getChild("swim_control");
        ModelPart body = this.swim_control.getChild("body");
        this.head = body.getChild("head");
        this.pregnant = body.getChild("pregnant");
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    protected void setupAnimations(Turtle entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        if (entity instanceof TurtleAccess turtleAccess) {
            this.animateWalkSmooth(turtleAccess.getWalkAnimationState(), TurtleAnimations.WALK, limbSwing, limbSwingAmount, 1.75F, 2.5F, partialTicks);
            this.animateWalkSmooth(turtleAccess.getSwimAnimationState(), TurtleAnimations.SWIM, limbSwing, limbSwingAmount, 1.75F, 2.5F, partialTicks);
            this.animateIdleSmooth(turtleAccess.getIdleAnimationState(), TurtleAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
            this.animateIdleSmooth(turtleAccess.getSwimIdleAnimationState(), TurtleAnimations.SWIM_IDLE, ageInTicks, partialTicks, limbSwingAmount);
            this.swim_control.xRot += turtleAccess.getSwimPitch(partialTicks) * Mth.DEG_TO_RAD;
            this.swim_control.zRot += -turtleAccess.getSwimRoll(partialTicks) * Mth.DEG_TO_RAD;
        }

        this.head.xRot += (headPitch * Mth.DEG_TO_RAD) / 1.5F;
        this.head.yRot += (netHeadYaw * Mth.DEG_TO_RAD) / 1.5F;
        this.pregnant.visible = !this.young && entity.hasEgg();
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 1.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

        body.addOrReplaceChild("body_rotation", CubeListBuilder.create().texOffs(7, 37).addBox(-9.5F, -9.0F, -2.0F, 19.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(31, 1).addBox(-5.5F, -9.0F, -5.0F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition pregnant = body.addOrReplaceChild("pregnant", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -1.0F));

        pregnant.addOrReplaceChild("body2_rotation", CubeListBuilder.create().texOffs(70, 33).addBox(-4.5F, 3.0F, -14.0F, 9.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, -12.0F, 1.5708F, 0.0F, 0.0F));

        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(3, 0).addBox(-3.0F, -3.0F, -6.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -10.0F));

        body.addOrReplaceChild("fin_back_right", CubeListBuilder.create().texOffs(1, 23).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 2.0F, 8.0F));

        body.addOrReplaceChild("fin_back_left", CubeListBuilder.create().texOffs(1, 12).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 2.0F, 8.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(27, 30).addBox(-13.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 1.0F, -7.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(27, 24).addBox(0.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 1.0F, -7.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }
}