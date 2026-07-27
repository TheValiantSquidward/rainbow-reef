package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.BassletAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Basslet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BassletModel extends ReefModel<Basslet> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public BassletModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Basslet entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, BassletAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, BassletAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, BassletAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -2.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.0F, -2.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(6, 6).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -1.0F));

        fin_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, -1.0F));

        fin_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 2).mirror().addBox(-2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        PartDefinition fin_bottom_left = body.addOrReplaceChild("fin_bottom_left", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        fin_bottom_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, -0.3927F));

        PartDefinition fin_bottom_right = body.addOrReplaceChild("fin_bottom_right", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        fin_bottom_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(6, 0).mirror().addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.3927F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}