package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.animations.MahiMahiAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.MahiMahi;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

@SuppressWarnings("FieldCanBeLocal, unused")
public class MahiMahiModel extends ReefModel<MahiMahi> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart fin;
	private final ModelPart fin_pelvic_left;
	private final ModelPart fin_pelvic_right;
	private final ModelPart fin_left;
	private final ModelPart fin_right;
	private final ModelPart tail1;
	private final ModelPart tail2;

	public MahiMahiModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body = this.swim_control.getChild("body");
		this.fin = this.body.getChild("fin");
		this.fin_pelvic_left = this.body.getChild("fin_pelvic_left");
		this.fin_pelvic_right = this.body.getChild("fin_pelvic_right");
		this.fin_left = this.body.getChild("fin_left");
		this.fin_right = this.body.getChild("fin_right");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(4, 6).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -5.0F));

		PartDefinition fin = body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -7.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -7.0F));

		PartDefinition fin_pelvic_left = body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition fin_pelvic_right = body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(22, -5).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, -4.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(22, -5).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 4.0F, -4.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(25, 20).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(22, -1).addBox(0.0F, -7.0F, 0.0F, 0.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(22, -3).addBox(0.0F, 3.0F, 1.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(10, 24).addBox(0.0F, -6.0F, 0.0F, 0.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(MahiMahi entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		float partialTicks = ageInTicks - entity.tickCount;
		if (entity.isInWaterOrBubble() || entity.isLeaping()) {
			this.animateWalk(MahiMahiAnimations.SWIM, limbSwing, limbSwingAmount, 1.5F, 2.5F);
		}
		this.animateIdleSmooth(entity.swimIdleAnimationState, MahiMahiAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount, 2.5F);
		this.animateSmooth(entity.flopAnimationState, MahiMahiAnimations.FLOP, ageInTicks, partialTicks);
		this.swim_control.xRot = entity.getTilt(partialTicks) * Mth.DEG_TO_RAD;
		this.swim_control.zRot = entity.getRoll(partialTicks) * Mth.DEG_TO_RAD;
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}