package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.PipefishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Pipefish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class PipefishModel extends ReefModel<Pipefish> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart tail1;
	private final ModelPart tail2;

	public PipefishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		ModelPart body_main = this.swim_control.getChild("body_main");
		this.tail1 = body_main.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Pipefish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, PipefishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, PipefishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, PipefishAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);

		float swimPitch = entity.getSwimPitch(partialTicks);
		float tailPitch = (swimPitch - entity.getTailPitch(partialTicks)) * Mth.DEG_TO_RAD * 1.6F;

		float bodyYaw = entity.getBodyYaw(partialTicks);
		float yawLag = (bodyYaw - entity.getTailYaw(partialTicks)) * Mth.DEG_TO_RAD * 1.6F;

		this.tail1.xRot -= tailPitch * 0.25F;
		this.tail2.xRot -= tailPitch * 0.5F;

		this.tail1.yRot -= yawLag * 0.25F;
		this.tail2.yRot -= yawLag * 0.5F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -0.5F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.5F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(9, 3).addBox(-0.5F, -1.0F, -8.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -1.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(7, 2).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, -1.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(7, 2).mirror().addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 1.0F, -1.0F));

        PartDefinition tail1 = body_main.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.0F));

        tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 4.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}