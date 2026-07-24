package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.ParrotfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Parrotfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ParrotfishModel extends ReefModel<Parrotfish> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public ParrotfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Parrotfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, ParrotfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, ParrotfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, ParrotfishAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(19, 0).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

        body.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -2.0F, -1.0F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 4.0F));

        body.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -1.0F, -1.0F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 4.0F));

        PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, 2.0F));

        fin_left.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9822F, -0.1454F, 0.2618F, 0.6109F, 0.0F));

        PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, 2.0F));

        fin_right.addOrReplaceChild("l_fin_r2", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.9822F, -0.1454F, 0.2618F, -0.6109F, 0.0F));

        PartDefinition fin_bottom_right = body.addOrReplaceChild("fin_bottom_right", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 4.0F));

        fin_bottom_right.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(22, 25).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.6109F, 0.0F, 0.6109F));

        PartDefinition fin_bottom_left = body.addOrReplaceChild("fin_bottom_left", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 3.0F));

        fin_bottom_left.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(22, 25).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, -0.6109F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 11).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 10.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}