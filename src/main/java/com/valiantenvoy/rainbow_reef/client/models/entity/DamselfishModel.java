package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.DamselfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Damselfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DamselfishModel extends ReefModel<Damselfish> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public DamselfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Damselfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, DamselfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, DamselfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, DamselfishAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(12, 4).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, -1.0F, 0.0F, 0.7854F, 0.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(12, 4).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 1.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.3927F, 0.0F, -0.3927F));

        body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.3927F, 0.0F, 0.3927F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

        body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(10, 7).addBox(0.0F, -4.0F, -2.0F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}