package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.TriggerfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Triggerfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class TriggerfishModel extends ReefModel<Triggerfish> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public TriggerfishModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Triggerfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, TriggerfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, TriggerfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, TriggerfishAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.5F, 19.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(-0.5F, 1.0F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, 3.0F, -3.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -3.0F, -4.0F, 3.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(22, 11).addBox(0.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 6).addBox(0.5F, -5.0F, -3.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        body.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(14, -5).addBox(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 0.0F));

        body.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -2.0F, -1.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 4.0F, 0.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(2, -1).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, -1.0F, 0.0F, 0.3927F, 0.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(2, -1).mirror().addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.5F, -1.0F, 0.0F, -0.3927F, 0.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(13, 6).addBox(0.0F, -4.0F, 0.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -1.0F, 4.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}