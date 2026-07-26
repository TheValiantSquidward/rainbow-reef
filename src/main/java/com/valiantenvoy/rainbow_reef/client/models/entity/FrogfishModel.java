package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.FrogfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Frogfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class FrogfishModel extends ReefModel<Frogfish> {

	private final ModelPart root;

	public FrogfishModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Frogfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateWalkSmooth(entity.walkAnimationState, FrogfishAnimations.WALK, limbSwing, limbSwingAmount, partialTicks);
		this.animateIdleSmooth(entity.idleAnimationState, FrogfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.flopAnimationState, FrogfishAnimations.FLOP, ageInTicks, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition leg_control = body_main.addOrReplaceChild("leg_control", CubeListBuilder.create(), PartPose.offset(2.0F, 6.0F, -1.0F));

        leg_control.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(12, 14).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 12).addBox(0.0F, 2.0F, -3.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        leg_control.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(12, 14).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(12, 12).mirror().addBox(-4.0F, 2.0F, -3.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 0.0F));

        PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 10).addBox(0.0F, -6.0F, -5.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 1.0F));

        body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -2.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition jaw = body.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -4.0F));

        jaw.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        body.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -4.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

        body.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(10, 14).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));

        PartDefinition fin_left = body.addOrReplaceChild("fin_left", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -2.0F));

        fin_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition fin_right = body.addOrReplaceChild("fin_right", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -2.0F));

        fin_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

        tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 6).addBox(0.0F, -2.0F, 4.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, -0.0436F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}
}