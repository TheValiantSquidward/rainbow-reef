package net.thevaliantsquidward.rainbowreef.client.models.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.SmallSharkAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.SmallShark;
import net.thevaliantsquidward.rainbowreef.utils.MathHelpers;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class SmallSharkModel extends HierarchicalModel<SmallShark> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart top_fin;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart tailRot;
	private final ModelPart tail;
	private final ModelPart tail_l_fin;
	private final ModelPart tail_r_fin;
	private final ModelPart tailFinRot;
	private final ModelPart tail_fin;

	public SmallSharkModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.top_fin = this.body.getChild("top_fin");
		this.l_fin = this.core.getChild("l_fin");
		this.r_fin = this.core.getChild("r_fin");
		this.tailRot = this.core.getChild("tailRot");
		this.tail = this.tailRot.getChild("tail");
		this.tail_l_fin = this.tail.getChild("tail_l_fin");
		this.tail_r_fin = this.tail.getChild("tail_r_fin");
		this.tailFinRot = this.tail.getChild("tailFinRot");
		this.tail_fin = this.tailFinRot.getChild("tail_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, -4.5F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 4.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(22, 25).addBox(2.0F, -3.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.001F)).texOffs(22, 25).mirror().addBox(-2.0F, -3.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, -6.0F));
		PartDefinition top_fin = body.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -3.0F, -1.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 5.0F));
		PartDefinition l_fin = core.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, -3.0F));
		PartDefinition l_fin_r1 = l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(14, 4).addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));
		PartDefinition r_fin = core.addOrReplaceChild("r_fin", CubeListBuilder.create(), PartPose.offset(-2.0F, 2.0F, -3.0F));
		PartDefinition r_fin_r1 = r_fin.addOrReplaceChild("r_fin_r1", CubeListBuilder.create().texOffs(14, 4).mirror().addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));
		PartDefinition tailRot = core.addOrReplaceChild("tailRot", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.0F));
		PartDefinition tail = tailRot.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tail_l_fin = tail.addOrReplaceChild("tail_l_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));
		PartDefinition tail_l_fin_r1 = tail_l_fin.addOrReplaceChild("tail_l_fin_r1", CubeListBuilder.create().texOffs(13, 20).addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));
		PartDefinition tail_r_fin = tail.addOrReplaceChild("tail_r_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));
		PartDefinition tail_r_fin_r1 = tail_r_fin.addOrReplaceChild("tail_r_fin_r1", CubeListBuilder.create().texOffs(13, 20).mirror().addBox(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1309F));
		PartDefinition tailFinRot = tail.addOrReplaceChild("tailFinRot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));
		PartDefinition tail_fin = tailFinRot.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -5.0F, 0.0F, 0.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(SmallShark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.core.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, SmallSharkAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 7.0F);

		if (entity.isInWaterOrBubble()) {
			this.tail.yRot = this.tail.yRot - ((float) ((MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[0], entity.tailKinematics.getTailYaws()[0], 0.1))));
			this.tail_fin.yRot = this.tail.yRot - ((float) ((MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[1], entity.tailKinematics.getTailYaws()[1], 0.1))));
			entity.tailKinematics.getCurrentTailYaws()[0] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[0], entity.tailKinematics.getTailYaws()[0], 0.1);
			entity.tailKinematics.getCurrentTailYaws()[1] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailYaws()[1], entity.tailKinematics.getTailYaws()[1], 0.1);

			this.tail.xRot = this.tail.xRot - ((float) ((MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailPitches()[0], entity.tailKinematics.getTailPitches()[0], 0.1))));
			this.tail_fin.xRot = this.tail_fin.xRot - ((float) ((MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailPitches()[1], entity.tailKinematics.getTailPitches()[1], 0.1))));
			entity.tailKinematics.getCurrentTailPitches()[0] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailPitches()[0], entity.tailKinematics.getTailPitches()[0], 0.1);
			entity.tailKinematics.getCurrentTailPitches()[1] = (float) MathHelpers.LerpDegrees(entity.tailKinematics.getCurrentTailPitches()[1], entity.tailKinematics.getTailPitches()[1], 0.1);
		}
	}

	public List<ModelPart> getAllParts() {
		return ImmutableList.of(this.root, this.core, this.body, this.top_fin, this.l_fin, this.r_fin, this.tailRot, this.tail, this.tail_l_fin, this.tail_r_fin, this.tailFinRot, this.tail_fin);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}