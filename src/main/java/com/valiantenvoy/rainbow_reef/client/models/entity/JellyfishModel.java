package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valiantenvoy.rainbow_reef.client.models.entity.animations.JellyfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Jellyfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class JellyfishModel extends ReefModel<Jellyfish> {

	private final ModelPart root;
	private final ModelPart swim_control;
    private final ModelPart body_outer;

	public JellyfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
        ModelPart body_main = this.swim_control.getChild("body_main");
		this.body_outer = body_main.getChild("body_outer");
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	protected void setupAnimations(Jellyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
		this.animateSmooth(entity.swimAnimationState, JellyfishAnimations.SWIM, ageInTicks, partialTicks);
		this.animateSmooth(entity.swimIdleAnimationState, JellyfishAnimations.IDLE, ageInTicks, partialTicks);
		this.animateSmooth(entity.flopAnimationState, JellyfishAnimations.FLOP, ageInTicks, partialTicks);
		this.swim_control.xRot += entity.getSwimPitch(partialTicks) * Mth.DEG_TO_RAD;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		poseStack.pushPose();
		this.body_outer.skipDraw = true;
		this.root().render(poseStack, buffer, packedLight, packedOverlay, color);
		this.body_outer.skipDraw = false;
		poseStack.popPose();
	}

	public void renderOuterBody(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
		poseStack.pushPose();
		this.root().getAllParts().forEach(part -> part.skipDraw = true);
		this.body_outer.skipDraw = false;
		this.root().render(poseStack, buffer, packedLight, packedOverlay);
		this.root().getAllParts().forEach(part -> part.skipDraw = false);
		poseStack.popPose();
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 6.25F, 0.0F));

        body_main.addOrReplaceChild("body_outer", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 11.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.75F, 0.0F));

        PartDefinition body_inner = body_main.addOrReplaceChild("body_inner", CubeListBuilder.create().texOffs(0, 27).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.25F, 0.0F));

		PartDefinition tendril = body_inner.addOrReplaceChild("tendril", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

        tendril.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 49).addBox(-5.0F, -2.0F, 0.0F, 10.0F, 20.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        tendril.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 49).addBox(-5.0F, -2.0F, 0.0F, 10.0F, 20.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        PartDefinition tentacle_control = body_main.addOrReplaceChild("tentacle_control", CubeListBuilder.create(), PartPose.offset(0.0F, 0.75F, 0.0F));

		PartDefinition tentacle1 = tentacle_control.addOrReplaceChild("tentacle1", CubeListBuilder.create(), PartPose.offset(-0.5F, -2.0F, 6.0F));

        tentacle1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 49).mirror().addBox(-5.0F, 0.0F, 0.0F, 11.0F, 23.0F, 0.0F, new CubeDeformation(0.0001F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition tentacle2 = tentacle_control.addOrReplaceChild("tentacle2", CubeListBuilder.create(), PartPose.offset(-6.0F, -2.0F, 0.5F));

        tentacle2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, 0.0F, -6.0F, 0.0F, 23.0F, 11.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.2618F));

        PartDefinition tentacle3 = tentacle_control.addOrReplaceChild("tentacle3", CubeListBuilder.create(), PartPose.offset(-0.5F, -2.0F, -6.0F));

        tentacle3.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(22, 49).addBox(-5.0F, 0.0F, 0.0F, 11.0F, 23.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition tentacle4 = tentacle_control.addOrReplaceChild("tentacle4", CubeListBuilder.create(), PartPose.offset(5.0F, -2.0F, 0.5F));

        tentacle4.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, 0.0F, -6.0F, 0.0F, 23.0F, 11.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -0.2618F));

        return LayerDefinition.create(meshdefinition, 128, 128);
	}
}