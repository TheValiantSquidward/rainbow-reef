package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.BoxfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Boxfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BoxfishModel extends ReefModel<Boxfish> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public BoxfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Boxfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, BoxfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, BoxfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, BoxfishAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -0.5F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 13).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(23, 0).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(15, 3).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(4, 20).addBox(2.0F, -3.0F, -7.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(4, 20).addBox(-2.0F, -3.0F, -7.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(23, 9).addBox(2.0F, 1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(23, 9).addBox(-2.0F, 1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(13, 22).addBox(0.0F, -6.0F, 1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(21, 13).addBox(0.0F, 1.0F, 1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.5F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 2.0F, -2.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(16, 21).mirror().addBox(-2.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 2.0F, -2.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}