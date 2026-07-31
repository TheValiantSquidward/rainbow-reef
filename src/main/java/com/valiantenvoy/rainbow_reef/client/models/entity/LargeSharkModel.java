package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.LargeSharkAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.LargeShark;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class LargeSharkModel extends ReefModel<LargeShark> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart head;
	private final ModelPart tail1;
	private final ModelPart tail2;

	public LargeSharkModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		ModelPart body_main = this.swim_control.getChild("body_main");
		this.head = body_main.getChild("head");
		this.tail1 = body_main.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnimations(LargeShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, LargeSharkAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateWalkSmooth(entity.swimFastAnimationState, LargeSharkAnimations.SWIM_FAST, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, LargeSharkAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		if ((entity.getId() & 1) == 0) {
			this.animateSmooth(entity.flopAnimationState, LargeSharkAnimations.BEACHED1, ageInTicks, partialTicks);
		} else {
			this.animateSmooth(entity.flopAnimationState, LargeSharkAnimations.BEACHED2, ageInTicks, partialTicks);
		}
		this.animateSmooth(entity.attackAnimationState, LargeSharkAnimations.BITE_BLEND, ageInTicks, partialTicks);
		this.animateSmooth(entity.rotatedAnimationState, LargeSharkAnimations.FLIPPED_OVERLAY, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
		this.bendPart(this.head, entity, 0, partialTicks);
		this.bendPart(this.tail1, entity, 1, partialTicks);
		this.bendPart(this.tail2, entity, 2, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body_main.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, -10.0F));

        head.addOrReplaceChild("jaw_upper", CubeListBuilder.create().texOffs(0, 36).addBox(-5.0F, -6.0F, -11.0F, 10.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 53).addBox(-4.0F, 0.0F, -9.0F, 8.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -16.0F, -10.0F, 14.0F, 16.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 8.0F, 0.0F));

        body.addOrReplaceChild("fin_dorsal", CubeListBuilder.create().texOffs(12, 65).addBox(-0.5F, -7.0F, 0.0F, 1.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -16.0F, 0.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(46, 65).addBox(7.0F, 0.0F, 5.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(34, 56).addBox(0.0F, 0.0F, -3.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -2.0F, -7.0F, 0.0F, 0.0F, 0.3927F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(46, 65).mirror().addBox(-10.0F, 0.0F, 5.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(34, 56).mirror().addBox(-10.0F, 0.0F, -3.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, -2.0F, -7.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition tail1 = body_main.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(42, 36).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 10.0F));

        tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 65).addBox(-0.5F, -10.0F, 0.0F, 1.0F, 18.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(56, 65).addBox(-0.5F, -10.0F, 5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(62, 65).addBox(-0.5F, 6.0F, 5.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

        tail1.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(28, 65).addBox(-0.2F, -0.5F, -2.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(34, 53).addBox(2.8F, -0.5F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 3.5F, 5.0F, 0.0F, 0.0F, 0.3927F));

        tail1.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(28, 65).mirror().addBox(-4.8F, -0.5F, -2.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(34, 53).mirror().addBox(-4.8F, -0.5F, 2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 3.5F, 5.0F, 0.0F, 0.0F, -0.3927F));

        return LayerDefinition.create(meshdefinition, 128, 128);
	}
}