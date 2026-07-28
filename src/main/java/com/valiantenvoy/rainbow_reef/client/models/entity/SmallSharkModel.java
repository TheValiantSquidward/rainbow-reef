package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.SmallSharkAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.SmallShark;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SmallSharkModel extends ReefModel<SmallShark> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart tail1;
	private final ModelPart tail2;

	public SmallSharkModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		ModelPart body = this.swim_control.getChild("body");
		this.tail1 = body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(SmallShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, SmallSharkAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, SmallSharkAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, SmallSharkAnimations.SWIM, ageInTicks, partialTicks, 1.5F);
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

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -2.5F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(22, 25).addBox(2.0F, -3.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.001F))
				.texOffs(22, 25).mirror().addBox(-2.0F, -3.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, -4.0F));

        body.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -3.0F, -1.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 5.0F));

        PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 3.0F));

        fin_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(14, 4).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, 3.0F));

        fin_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 4).mirror().addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 9.0F));

		PartDefinition fin_tail_left = tail1.addOrReplaceChild("fin_tail_left", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));

        fin_tail_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(13, 20).addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition fin_tail_right = tail1.addOrReplaceChild("fin_tail_right", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));

        fin_tail_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(13, 20).mirror().addBox(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

        tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -5.0F, 0.0F, 0.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}