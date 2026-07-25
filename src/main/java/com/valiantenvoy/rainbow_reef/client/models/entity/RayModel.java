package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.RayAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Ray;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class RayModel extends ReefModel<Ray> {

	private final ModelPart root;
	private final ModelPart swim_control;
    private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;

	public RayModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
        ModelPart body_main = this.swim_control.getChild("body_main");
		this.tail1 = body_main.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.tail4 = this.tail3.getChild("tail4");
		this.tail5 = this.tail4.getChild("tail5");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Ray entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, RayAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, RayAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, RayAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);

		float swimPitch = entity.getSwimPitch(partialTicks);
		float tailPitch = (swimPitch - entity.getTailPitch(partialTicks)) * Mth.DEG_TO_RAD;

		float bodyYaw = entity.getBodyYaw(partialTicks);
		float yawLag = (bodyYaw - entity.getTailYaw(partialTicks)) * Mth.DEG_TO_RAD;

		this.tail1.xRot -= tailPitch * 0.2F;
		this.tail2.xRot -= tailPitch * 0.75F;
		this.tail3.xRot -= tailPitch * 0.8F;
		this.tail4.xRot -= tailPitch * 0.85F;
		this.tail5.xRot -= tailPitch * 0.9F;

		this.tail1.yRot -= yawLag * 0.2F;
		this.tail2.yRot -= yawLag * 0.3F;
		this.tail3.yRot -= yawLag * 0.4F;
		this.tail4.yRot -= yawLag * 0.5F;
		this.tail5.yRot -= yawLag * 0.6F;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(34, 16).addBox(-3.5F, -1.0F, -10.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.5F, -2.0F, -7.0F, 11.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition fin_right1 = body.addOrReplaceChild("fin_right1", CubeListBuilder.create().texOffs(0, 16).addBox(-11.0F, -0.5F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.5F, 0.0F));

        fin_right1.addOrReplaceChild("fin_right2", CubeListBuilder.create().texOffs(29, 29).addBox(-7.0F, -0.5F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 0.0F, -2.5F));

        PartDefinition fin_left1 = body.addOrReplaceChild("fin_left1", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0.0F, -0.5F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.5F, 0.5F, 0.0F));

        fin_left1.addOrReplaceChild("fin_left2", CubeListBuilder.create().texOffs(29, 29).mirror().addBox(0.0F, -0.5F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, 0.0F, -2.5F));

        PartDefinition tail1 = body_main.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(7, 26).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

        tail1.addOrReplaceChild("fin_tail_left", CubeListBuilder.create().texOffs(30, 21).addBox(0.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

        tail1.addOrReplaceChild("fin_tail_right", CubeListBuilder.create().texOffs(30, 21).mirror().addBox(-5.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 31).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(10, 21).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(1, 41).addBox(0.0F, -1.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 8.0F));

        tail4.addOrReplaceChild("tail5", CubeListBuilder.create().texOffs(1, 46).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}