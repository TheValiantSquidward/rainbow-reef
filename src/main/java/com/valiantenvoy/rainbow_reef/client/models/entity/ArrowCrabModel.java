package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.ArrowCrabAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.ArrowCrab;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ArrowCrabModel extends ReefModel<ArrowCrab> {

	private final ModelPart root;

	public ArrowCrabModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(ArrowCrab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.walkAnimationState, ArrowCrabAnimations.WALK, limbSwing, limbSwingAmount, 2.5F, 2.5F, partialTicks);
		this.animateIdleSmooth(entity.idleAnimationState, ArrowCrabAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.9259F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0852F, -0.0474F));

        body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 11).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5852F, -0.9526F, -0.0873F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5852F, -1.4526F));

        head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 7).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.1745F, 0.0F, 0.0F));

        PartDefinition pincer_right = body.addOrReplaceChild("pincer_right", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.4148F, -1.9526F));

        pincer_right.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.1745F, 0.3491F, 0.0F));

        PartDefinition pincer_left = body.addOrReplaceChild("pincer_left", CubeListBuilder.create(), PartPose.offset(2.5F, 0.4148F, -1.9526F));

        pincer_left.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.1745F, -0.3491F, 0.0F));

        body.addOrReplaceChild("mandibles", CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.9148F, -2.1286F));

        PartDefinition legs_left = body_main.addOrReplaceChild("legs_left", CubeListBuilder.create(), PartPose.offset(2.0F, 0.5F, 0.0741F));

		PartDefinition leg_left1 = legs_left.addOrReplaceChild("leg_left1", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, -1.0741F));

        leg_left1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.5236F, -0.6109F));

        PartDefinition leg_left2 = legs_left.addOrReplaceChild("leg_left2", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, -0.0741F));

        leg_left2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0873F, -0.6109F));

        PartDefinition leg_left3 = legs_left.addOrReplaceChild("leg_left3", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 0.9259F));

        leg_left3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.0873F, -0.6109F));

        PartDefinition leg_left4 = legs_left.addOrReplaceChild("leg_left4", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 1.9259F));

        leg_left4.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.5236F, -0.6109F));

        PartDefinition legs_right = body_main.addOrReplaceChild("legs_right", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.5F, 0.0741F));

		PartDefinition leg_right1 = legs_right.addOrReplaceChild("leg_right1", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -1.0741F));

        leg_right1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, -1.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.0F, -0.5236F, 0.6109F));

        PartDefinition leg_right2 = legs_right.addOrReplaceChild("leg_right2", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -0.0741F));

        leg_right2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.0873F, 0.6109F));

        PartDefinition leg_right3 = legs_right.addOrReplaceChild("leg_right3", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 0.9259F));

        leg_right3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0873F, 0.6109F));

        PartDefinition leg_right4 = legs_right.addOrReplaceChild("leg_right4", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 1.9259F));

        leg_right4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.5236F, 0.6109F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}