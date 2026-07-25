package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.CrabAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Crab;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CrabModel extends ReefModel<Crab> {

	private final ModelPart root;

	public CrabModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Crab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		if ((entity.getId() & 1) == 0) {
			this.animateWalkSmooth(entity.walkAnimationState, CrabAnimations.WALK1, limbSwing, limbSwingAmount, partialTicks);
		} else {
			this.animateWalkSmooth(entity.walkAnimationState, CrabAnimations.WALK2, limbSwing, limbSwingAmount, partialTicks);
		}
		this.animateIdleSmooth(entity.idleAnimationState, CrabAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
		this.animateSmooth(entity.danceAnimationState, CrabAnimations.DANCE, ageInTicks, partialTicks);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-0.5F, 21.0F, -0.5F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 31).addBox(-2.5F, -5.25F, -0.8833F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.75F, -1.2667F));

        body.addOrReplaceChild("body_oval_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, -1.0F, 9.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-4.0F, -2.0F, -1.0F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-4.0F, -3.0F, -1.0F, 7.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.75F, 0.2667F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("body_tall_spikes_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, -3.0F, 1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(26, 10).addBox(-3.0F, -4.0F, 0.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.25F, 0.2667F, -0.0873F, 0.0F, 0.0F));

        body.addOrReplaceChild("mandibles", CubeListBuilder.create().texOffs(30, 7).addBox(-1.5F, -1.75F, 0.1F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, -1.2333F));

        body.addOrReplaceChild("pincer_left", CubeListBuilder.create().texOffs(26, 26).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(41, 20).addBox(-2.0F, -2.0F, -2.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.75F, -0.7333F, 0.0F, 0.3927F, 0.3927F));

        body.addOrReplaceChild("pincer_right", CubeListBuilder.create().texOffs(39, 26).mirror().addBox(-3.0F, -2.0F, -2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(26, 20).mirror().addBox(-3.0F, -2.0F, -2.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 1.75F, -0.7333F, 0.0F, -0.3927F, -0.3927F));

        PartDefinition leg_control = body_main.addOrReplaceChild("leg_control", CubeListBuilder.create(), PartPose.offset(3.5F, 1.0F, 0.0F));

        leg_control.addOrReplaceChild("leg_left1", CubeListBuilder.create().texOffs(20, 31).addBox(-0.1024F, -1.7782F, -0.0533F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 0.0F, 0.0F, 0.48F, -0.48F));

        leg_control.addOrReplaceChild("leg_left2", CubeListBuilder.create().texOffs(20, 31).addBox(-0.1154F, -1.7782F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 1.5F, 0.0F, 0.0F, -0.48F));

        leg_control.addOrReplaceChild("leg_left3", CubeListBuilder.create().texOffs(20, 31).addBox(-0.1024F, -1.7782F, 0.0533F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.0F, 3.0F, 0.0F, -0.48F, -0.48F));

        leg_control.addOrReplaceChild("leg_right1", CubeListBuilder.create().texOffs(20, 31).mirror().addBox(-4.8976F, -1.7782F, -0.0533F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.75F, 0.0F, 0.0F, 0.0F, -0.48F, 0.48F));

        leg_control.addOrReplaceChild("leg_right2", CubeListBuilder.create().texOffs(20, 31).mirror().addBox(-4.8846F, -1.7782F, 0.0F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.75F, 0.0F, 1.5F, 0.0F, 0.0F, 0.48F));

        leg_control.addOrReplaceChild("leg_right3", CubeListBuilder.create().texOffs(20, 31).mirror().addBox(-4.8976F, -1.7782F, 0.0533F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.75F, 0.0F, 3.0F, 0.0F, 0.48F, 0.48F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}