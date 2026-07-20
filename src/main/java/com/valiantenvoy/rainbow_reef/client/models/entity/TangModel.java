package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.TangAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Tang;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

@SuppressWarnings("FieldCanBeLocal, unused")
public class TangModel extends ReefModel<Tang> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart fin_bottom_left;
	private final ModelPart fin_bottom_right;
	private final ModelPart tail;
	private final ModelPart fin_left;
	private final ModelPart fin_right;

	public TangModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body_main = this.swim_control.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.fin_bottom_left = this.body.getChild("fin_bottom_left");
		this.fin_bottom_right = this.body.getChild("fin_bottom_right");
		this.tail = this.body_main.getChild("tail");
		this.fin_left = this.body_main.getChild("fin_left");
		this.fin_right = this.body_main.getChild("fin_right");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnimations(Tang entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, TangAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, TangAnimations.SWIM, ageInTicks, partialTicks, limbSwingAmount, 2.5F, 0.75F);
		this.animateSmooth(entity.flopAnimationState, TangAnimations.FLOP, ageInTicks, partialTicks);
		this.applyRollAndTilt(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -3.5F, 0.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.5F, 0.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -2.0F, -3.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(14, 19).addBox(-0.5F, -3.0F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(0.0F, -2.0F, -6.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 26).addBox(-0.5F, 1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(0.0F, -7.0F, -2.0F, 0.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fin_bottom_left = body.addOrReplaceChild("fin_bottom_left", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition cube_r1 = fin_bottom_left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, -0.6109F));

		PartDefinition fin_bottom_right = body.addOrReplaceChild("fin_bottom_right", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -0.5F));

		PartDefinition cube_r2 = fin_bottom_right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.6109F));

		PartDefinition tail = body_main.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(13, 5).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition fin_left = body_main.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(0.5F, 1.0F, -1.0F));

		PartDefinition cube_r3 = fin_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(1, 2).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.7854F));

		PartDefinition fin_right = body_main.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(-0.5F, 1.0F, -1.0F));

		PartDefinition cube_r4 = fin_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(1, 2).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, -0.7854F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}