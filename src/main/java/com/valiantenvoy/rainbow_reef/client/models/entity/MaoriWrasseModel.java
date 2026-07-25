package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.MaoriWrasseAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.MaoriWrasse;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MaoriWrasseModel extends ReefModel<MaoriWrasse> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public MaoriWrasseModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(MaoriWrasse entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, MaoriWrasseAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, MaoriWrasseAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, MaoriWrasseAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -8.0F, -8.0F, 6.0F, 15.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(8, 3).addBox(-2.0F, -8.0F, -9.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 40).addBox(-3.0F, -2.0F, -11.0F, 6.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(30, 6).addBox(-3.5F, 2.0F, -12.0F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -1.0F));

        body.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(24, 19).addBox(0.0F, -5.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        body.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(30, -3).addBox(0.0F, -3.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

        body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(6, 6).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 7.0F, -2.0F, 0.0F, 0.0F, -0.3927F));

        body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(6, 6).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 7.0F, -2.0F, 0.0F, 0.0F, 0.3927F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(10, 4).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.0F, -1.0F, 0.0F, 0.3927F, 0.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(10, 4).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 1.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -8.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 10.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}