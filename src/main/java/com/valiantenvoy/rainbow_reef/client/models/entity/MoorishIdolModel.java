package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.MoorishIdolAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.MoorishIdol;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MoorishIdolModel extends ReefModel<MoorishIdol> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public MoorishIdolModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(MoorishIdol entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, MoorishIdolAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, MoorishIdolAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, MoorishIdolAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -1.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -1.75F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(10, 14).addBox(-1.0F, -4.0F, -3.0F, 2.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(6, 0).addBox(1.0F, -3.0F, -4.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 0).addBox(-1.0F, -3.0F, -4.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.75F));

		PartDefinition fin1 = body.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(0, 6).addBox(-0.1285F, -3.9528F, -1.0071F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        fin1.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.1285F, -3.9528F, 6.9929F));

        PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, 0.0F));

        fin_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(-2, 4).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, -0.7854F));

        PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, 0.0F));

        fin_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(-2, 4).mirror().addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.7854F));

        PartDefinition fin_bottom_left = body.addOrReplaceChild("fin_bottom_left", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

        fin_bottom_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, -0.6109F));

        PartDefinition fin_bottom_right = body.addOrReplaceChild("fin_bottom_right", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 1.0F));

        fin_bottom_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.6109F));

        body.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, -1.0F, -2.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}