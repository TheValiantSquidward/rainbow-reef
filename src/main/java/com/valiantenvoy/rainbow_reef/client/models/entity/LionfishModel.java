package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.LionfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Lionfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class LionfishModel extends ReefModel<Lionfish> {

	private final ModelPart root;
	private final ModelPart swim_control;

	public LionfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Lionfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, LionfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, LionfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, LionfishAnimations.FLOP, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 1.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -4.0F));

        body.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(0, -14).addBox(0.0F, -7.0F, -7.0F, 0.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 4.0F));

        body.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(46, -5).addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 7.0F));

        body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(1, 11).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F, 0.3927F, 0.0F, -0.3927F));

        body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(1, 11).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F, 0.3927F, 0.0F, 0.3927F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(29, 10).addBox(0.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(29, 10).mirror().addBox(-8.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(29, 0).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(6, 14).addBox(1.0F, 1.0F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 14).mirror().addBox(-1.0F, 1.0F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(29, 15).addBox(2.0F, -7.0F, -6.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 15).mirror().addBox(-2.0F, -7.0F, -6.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.0F, 0.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 5).addBox(0.0F, -4.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
	}
}