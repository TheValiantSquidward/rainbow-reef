package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.GobyAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Goby;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GobyModel extends ReefModel<Goby> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public GobyModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Goby entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, GobyAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, GobyAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, GobyAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(5, 9).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(14, 22).addBox(-1.0F, -2.0F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 15).addBox(0.0F, -7.0F, -2.5F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(4, 10).addBox(1.0F, -2.0F, -2.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(4, 10).mirror().addBox(-1.0F, -2.0F, -2.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.5F, -0.5F));

		PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -0.5F));

        fin_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(5, -1).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, -0.5F));

        fin_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(5, -1).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition fin_bottom_left = body.addOrReplaceChild("fin_bottom_left", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.5F));

        fin_bottom_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, -0.2618F));

        PartDefinition fin_bottom_right = body.addOrReplaceChild("fin_bottom_right", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.5F));

        fin_bottom_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(11, -3).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.5F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}