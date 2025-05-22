package net.thevaliantsquidward.rainbowreef.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.EagleRayAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.RayEntity;
import net.thevaliantsquidward.rainbowreef.util.MathHelpers;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class EagleRayModel<T extends RayEntity> extends ReefModel<T> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart onlyBody;
	private final ModelPart r_fin;
	private final ModelPart r_tip;
	private final ModelPart l_fin;
	private final ModelPart l_tip;
	private final ModelPart tail;
	private final ModelPart l_tail_fin;
	private final ModelPart r_tail_fin;
	private final ModelPart tail_tip;
	private final ModelPart tail_tip2;
	private final ModelPart tail_tip3;
	private final ModelPart tail_tip4;

	public EagleRayModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.onlyBody = this.body.getChild("onlyBody");
		this.r_fin = this.onlyBody.getChild("r_fin");
		this.r_tip = this.r_fin.getChild("r_tip");
		this.l_fin = this.onlyBody.getChild("l_fin");
		this.l_tip = this.l_fin.getChild("l_tip");
		this.tail = this.body.getChild("tail");
		this.l_tail_fin = this.tail.getChild("l_tail_fin");
		this.r_tail_fin = this.tail.getChild("r_tail_fin");
		this.tail_tip = this.tail.getChild("tail_tip");
		this.tail_tip2 = this.tail_tip.getChild("tail_tip2");
		this.tail_tip3 = this.tail_tip2.getChild("tail_tip3");
		this.tail_tip4 = this.tail_tip3.getChild("tail_tip4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.5F, 0.0F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, -7.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));
		PartDefinition onlyBody = body.addOrReplaceChild("onlyBody", CubeListBuilder.create().texOffs(34, 16).addBox(-3.5F, -1.0F, -10.0F, 7.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-5.5F, -2.0F, -7.0F, 11.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));
		PartDefinition r_fin = onlyBody.addOrReplaceChild("r_fin", CubeListBuilder.create().texOffs(0, 16).addBox(-11.0F, -0.5F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.5F, 0.0F));
		PartDefinition r_tip = r_fin.addOrReplaceChild("r_tip", CubeListBuilder.create().texOffs(29, 29).addBox(-7.0F, -0.5F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 0.0F, -2.5F));
		PartDefinition l_fin = onlyBody.addOrReplaceChild("l_fin", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0.0F, -0.5F, -6.0F, 11.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.5F, 0.5F, 0.0F));
		PartDefinition l_tip = l_fin.addOrReplaceChild("l_tip", CubeListBuilder.create().texOffs(29, 29).mirror().addBox(0.0F, -0.5F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, 0.0F, -2.5F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(7, 26).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));
		PartDefinition l_tail_fin = tail.addOrReplaceChild("l_tail_fin", CubeListBuilder.create().texOffs(30, 21).addBox(0.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition r_tail_fin = tail.addOrReplaceChild("r_tail_fin", CubeListBuilder.create().texOffs(30, 21).mirror().addBox(-5.0F, 0.0F, -1.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition tail_tip = tail.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(0, 31).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));
		PartDefinition tail_tip2 = tail_tip.addOrReplaceChild("tail_tip2", CubeListBuilder.create().texOffs(10, 21).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 7.0F));
		PartDefinition tail_tip3 = tail_tip2.addOrReplaceChild("tail_tip3", CubeListBuilder.create().texOffs(1, 41).addBox(0.0F, -1.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 8.0F));
		PartDefinition tail_tip4 = tail_tip3.addOrReplaceChild("tail_tip4", CubeListBuilder.create().texOffs(1, 46).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 8.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(RayEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.swimAnimationState, EagleRayAnimations.SWIM, ageInTicks, limbSwingAmount * 4.0f);
		this.animateIdle(entity.idleAnimationState, EagleRayAnimations.IDLE, ageInTicks, 1.0f, 1 - Math.abs(limbSwingAmount));
		this.animate(entity.flopAnimationState, EagleRayAnimations.FLOP, ageInTicks, 1.0f);

		if (entity.isInWaterOrBubble()) {
			double rollOldZ = this.onlyBody.zRot;
			this.onlyBody.zRot = ((float) (MathHelpers.LerpDegrees(rollOldZ, Mth.PI - MathHelpers.getAngleForLinkTopDownFlat(entity.tail1Point, entity.tail0Point, entity.tail2Point, entity.leftRefPoint, entity.rightRefPoint) * 1.2, 0.01)));

			this.tail.yRot = -((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail1Yaw, (float) entity.tail1Angle, 0.01))));
			this.tail_tip.yRot = -((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail2Yaw, (float) entity.tail2Angle, 0.01))));
			this.tail_tip2.yRot = -((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail3Yaw, (float) entity.tail3Angle, 0.01))));
			this.tail_tip3.yRot = -((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail4Yaw, (float) entity.tail4Angle, 0.01))));
			this.tail_tip4.yRot = -((float) (Mth.PI - (MathHelpers.LerpDegrees((float) entity.currentTail5Yaw, (float) entity.tail5Angle, 0.01))));
			entity.currentTail1Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail1Yaw, (float) entity.tail1Angle, 0.01);
			entity.currentTail2Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail2Yaw, (float) entity.tail2Angle, 0.01);
			entity.currentTail3Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail3Yaw, (float) entity.tail3Angle, 0.01);
			entity.currentTail4Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail4Yaw, (float) entity.tail4Angle, 0.01);
			entity.currentTail5Yaw = (float) MathHelpers.LerpDegrees((float) entity.currentTail5Yaw, (float) entity.tail5Angle, 0.01);

			this.body.xRot = ((float) (-MathHelpers.LerpDegrees((float) entity.currentBodyPitch, (float) entity.bodyPitch, 0.01)));
			entity.currentBodyPitch = (float) MathHelpers.LerpDegrees((float) entity.currentBodyPitch, (float) entity.bodyPitch, 0.01);

			this.tail.xRot = -((float) (this.tail.xRot + MathHelpers.LerpDegrees((float) entity.currentTail1Pitch, (float) entity.tail1Pitch, 0.01)));
			this.tail_tip.xRot = -((float) (this.tail_tip.xRot + MathHelpers.LerpDegrees((float) entity.currentTail2Pitch, (float) entity.tail2Pitch, 0.01)));
			this.tail_tip2.xRot = -((float) (this.tail_tip2.xRot + MathHelpers.LerpDegrees((float) entity.currentTail3Pitch, (float) entity.tail3Pitch, 0.01)));
			this.tail_tip3.xRot = -((float) (this.tail_tip3.xRot + MathHelpers.LerpDegrees((float) entity.currentTail4Pitch, (float) entity.tail4Pitch, 0.01)));
			this.tail_tip4.xRot = -((float) (this.tail_tip4.xRot + MathHelpers.LerpDegrees((float) entity.currentTail5Pitch, (float) entity.tail5Pitch, 0.01)));
			entity.currentTail1Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail1Pitch, (float) entity.tail1Pitch, 0.01);
			entity.currentTail2Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail2Pitch, (float) entity.tail2Pitch, 0.01);
			entity.currentTail3Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail3Pitch, (float) entity.tail3Pitch, 0.01);
			entity.currentTail4Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail4Pitch, (float) entity.tail4Pitch, 0.01);
			entity.currentTail5Pitch = (float) MathHelpers.LerpDegrees((float) entity.currentTail5Pitch, (float) entity.tail5Pitch, 0.01);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}