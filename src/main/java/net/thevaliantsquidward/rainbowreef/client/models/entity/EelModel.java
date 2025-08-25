package net.thevaliantsquidward.rainbowreef.client.models.entity;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.client.animations.EelAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.Eel;
import net.thevaliantsquidward.rainbowreef.util.MathHelpers;

import java.util.List;

public class EelModel<T extends Eel> extends ReefModel<T> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart gills;
	private final ModelPart tailyawhinge;
	private final ModelPart tail;
	private final ModelPart tailyawhinge2;
	private final ModelPart tail2;
	private final ModelPart tailyawhinge3;
	private final ModelPart tail3;
	private final ModelPart tailyawhinge4;
	private final ModelPart tail4;
	private final ModelPart head;
	private final ModelPart cranium;
	private final ModelPart nose;
	private final ModelPart jaw;

	public EelModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.gills = this.body.getChild("gills");
		this.tailyawhinge = this.body.getChild("tailyawhinge");
		this.tail = this.tailyawhinge.getChild("tail");
		this.tailyawhinge2 = this.tail.getChild("tailyawhinge2");
		this.tail2 = this.tailyawhinge2.getChild("tail2");
		this.tailyawhinge3 = this.tail2.getChild("tailyawhinge3");
		this.tail3 = this.tailyawhinge3.getChild("tail3");
		this.tailyawhinge4 = this.tail3.getChild("tailyawhinge4");
		this.tail4 = this.tailyawhinge4.getChild("tail4");
		this.head = this.body.getChild("head");
		this.cranium = this.head.getChild("cranium");
		this.nose = this.cranium.getChild("nose");
		this.jaw = this.head.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(3, 3).addBox(0.0F, -5.0F, -3.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition gills = body.addOrReplaceChild("gills", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -2.0F, 0.0F, 3.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, -3.0F));

		PartDefinition tailyawhinge = body.addOrReplaceChild("tailyawhinge", CubeListBuilder.create().texOffs(0, -2).addBox(0.0F, -6.0F, 0.0F, 0.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 5.0F));
		PartDefinition tail = tailyawhinge.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(25, 3).addBox(0.0F, -5.0F, 1.0F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition tailyawhinge2 = tail.addOrReplaceChild("tailyawhinge2", CubeListBuilder.create().texOffs(4, -2).addBox(0.0F, -5.0F, 0.0F, 0.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition tail2 = tailyawhinge2.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(26, 21).addBox(0.0F, -5.0F, 1.0F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(38, 7).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tailyawhinge3 = tail2.addOrReplaceChild("tailyawhinge3", CubeListBuilder.create().texOffs(8, -2).addBox(0.0F, -4.0F, 0.0F, 0.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 5.0F));

		PartDefinition tail3 = tailyawhinge3.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(34, 36).addBox(0.0F, -4.5F, 1.0F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(38, 16).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition tailyawhinge4 = tail3.addOrReplaceChild("tailyawhinge4", CubeListBuilder.create().texOffs(12, -2).addBox(0.0F, -4.5F, 0.0F, 0.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail4 = tailyawhinge4.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(0, 38).addBox(0.0F, -4.5F, 0.0F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition cranium = head.addOrReplaceChild("cranium", CubeListBuilder.create().texOffs(17, 35).addBox(-1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition nose = cranium.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(14, 28).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -5.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(39, 1).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-1.0F, -1.5F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Eel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.swimAnimationState, EelAnimations.SWIM, ageInTicks, limbSwingAmount * 4.0f);
		this.animate(entity.landAnimationState, EelAnimations.LAND, ageInTicks, limbSwingAmount * 4.0f);
		this.animate(entity.breathAnimationState, EelAnimations.BREATH, ageInTicks, (float) (1f + 0.5*Math.toRadians(entity.getXRot())));

		this.tailyawhinge.yRot = this.tailyawhinge.yRot - ((float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[0], entity.tailKinematics.getTailYaws()[0], 0.1));
		this.tailyawhinge2.yRot = this.tailyawhinge2.yRot - ((float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[1], entity.tailKinematics.getTailYaws()[1], 0.1));
		this.tailyawhinge3.yRot = this.tailyawhinge3.yRot - ((float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[2], entity.tailKinematics.getTailYaws()[2], 0.1));
		this.tailyawhinge4.yRot = this.tailyawhinge4.yRot - ((float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[3], entity.tailKinematics.getTailYaws()[3], 0.1));
		entity.tailKinematics.getCurrentTailYaws()[0] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[0], entity.tailKinematics.getTailYaws()[0], 0.1);
		entity.tailKinematics.getCurrentTailYaws()[1] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[1], entity.tailKinematics.getTailYaws()[1], 0.1);
		entity.tailKinematics.getCurrentTailYaws()[2] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[2], entity.tailKinematics.getTailYaws()[2], 0.1);
		entity.tailKinematics.getCurrentTailYaws()[3] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[3], entity.tailKinematics.getTailYaws()[3], 0.1);

		if (entity.isInWaterOrBubble()) {
			this.body.xRot = (headPitch * (Mth.DEG_TO_RAD));

			this.tail.xRot = -((float) (this.tail.xRot + MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[0], (float) entity.tailKinematics.getTailPitches()[0], 0.1)));
			this.tail2.xRot = -((float) (this.tail2.xRot + MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[1], (float) entity.tailKinematics.getTailPitches()[1], 0.1)));
			this.tail3.xRot = -((float) (this.tail3.xRot + MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[2], (float) entity.tailKinematics.getTailPitches()[2], 0.1)));
			this.tail4.xRot = -((float) (this.tail4.xRot + MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[3], (float) entity.tailKinematics.getTailPitches()[3], 0.1)));
			entity.tailKinematics.getCurrentTailPitches()[0] = (float) MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[0], (float) entity.tailKinematics.getTailPitches()[0], 0.1);
			entity.tailKinematics.getCurrentTailPitches()[1] = (float) MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[1], (float) entity.tailKinematics.getTailPitches()[1], 0.1);
			entity.tailKinematics.getCurrentTailPitches()[2] = (float) MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[2], (float) entity.tailKinematics.getTailPitches()[2], 0.1);
			entity.tailKinematics.getCurrentTailPitches()[3] = (float) MathHelpers.LerpDegrees((float) entity.tailKinematics.getCurrentTailPitches()[3], (float) entity.tailKinematics.getTailPitches()[3], 0.1);

		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public List<ModelPart> getAllParts() {
		return ImmutableList.of(this.root, this.core, this.gills, this.tailyawhinge, this.tail, this.tailyawhinge2, this.tail2, this.tailyawhinge3, this.tail3, this.tailyawhinge4, this.tail4, this.head, this.cranium, this.nose, this.jaw);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}