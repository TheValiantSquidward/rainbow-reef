package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.SharkAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Shark;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SharkModel extends ReefModel<Shark> {

	private final ModelPart root;
	private final ModelPart swim_control;
    private final ModelPart tail1;
	private final ModelPart tail2;

	public SharkModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
        ModelPart body_main = this.swim_control.getChild("body_main");
		this.tail1 = body_main.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnimations(Shark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.swimAnimationState, SharkAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.swimIdleAnimationState, SharkAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, SharkAnimations.BEACHED1, ageInTicks, partialTicks);
		this.animateSmooth(entity.attackAnimationState, SharkAnimations.BITE_BLEND, ageInTicks, partialTicks);
		this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -3.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition head = body_main.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -9.0F));

        head.addOrReplaceChild("jaw_upper", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -4.0F, -10.0F, 8.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(36, 26).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -8.0F, 10.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, -1.0F));

        body.addOrReplaceChild("fin_dorsal", CubeListBuilder.create().texOffs(0, 47).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -6.0F, 1.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(53, 8).addBox(5.0F, 0.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 40).addBox(0.0F, 0.0F, -1.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.0F, -7.0F, 0.0F, 0.0F, 0.3927F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(53, 8).mirror().addBox(-7.0F, 0.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 40).mirror().addBox(-7.0F, 0.0F, -1.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 2.0F, -7.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition tail1 = body_main.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(36, 36).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(12, 47).addBox(-0.5F, -8.0F, 1.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(52, 11).addBox(-0.5F, -6.0F, 3.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 7.0F));

        tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(26, 40).addBox(-0.5F, -7.0F, 0.0F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 0).addBox(-0.5F, -7.0F, 4.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(52, 17).addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

        tail1.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(36, 50).addBox(0.0F, 0.0F, -1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(52, 22).addBox(3.0F, 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.3927F));

        tail1.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(36, 50).mirror().addBox(-5.0F, 0.0F, -1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(52, 22).mirror().addBox(-5.0F, 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 2.0F, 2.0F, 0.0F, 0.0F, -0.3927F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}