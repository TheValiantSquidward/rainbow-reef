package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.SeahorseAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Seahorse;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SeahorseModel extends ReefModel<Seahorse> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public SeahorseModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnimations(Seahorse entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, SeahorseAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, SeahorseAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, SeahorseAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
		if (entity.getDeltaMovement().lengthSqr() <= 0.0002D || !entity.isInWaterOrBubble()) {
			this.applyStatic(SeahorseAnimations.TAIL_IDLE_OVERLAY);
		} else {
			this.applyStatic(SeahorseAnimations.TAIL_SWIM_OVERLAY);
		}
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition head = body_main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 10).addBox(-0.5F, 3.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 14).addBox(-0.5F, 3.0F, -2.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -1.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(22, 6).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(0.0F, -4.0F, -7.0F, 0.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        head.addOrReplaceChild("fin_head_left", CubeListBuilder.create().texOffs(26, 8).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, -1.0F, 0.0F, 0.0F, 0.3491F));

        head.addOrReplaceChild("fin_head_right", CubeListBuilder.create().texOffs(26, 8).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, -1.0F, 0.0F, 0.0F, -0.3491F));

        head.addOrReplaceChild("fin_eye_left", CubeListBuilder.create().texOffs(26, 12).addBox(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 1.0F, -3.0F, -0.7854F, -0.3491F, 0.0F));

        head.addOrReplaceChild("fin_eye_right", CubeListBuilder.create().texOffs(26, 12).addBox(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.0F, -3.0F, -0.7854F, 0.3491F, 0.0F));

        PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 20).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(12, 0).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        body.addOrReplaceChild("fin_back", CubeListBuilder.create().texOffs(20, 7).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 2.0F));

        body.addOrReplaceChild("tail_swimming", CubeListBuilder.create().texOffs(0, -3).addBox(0.0F, -2.5F, -1.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -1.55F, -6.0F, 0.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.55F, 1.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}