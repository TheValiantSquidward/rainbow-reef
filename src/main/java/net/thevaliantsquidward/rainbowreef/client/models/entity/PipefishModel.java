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
import net.thevaliantsquidward.rainbowreef.client.animations.PipefishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.PipefishEntity;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class PipefishModel<T extends PipefishEntity> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart tail_base;
	private final ModelPart tail_end;

	public PipefishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.tail_base = this.core.getChild("tail_base");
		this.tail_end = this.tail_base.getChild("tail_end");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.5F, 0.0F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(9, 3).addBox(-0.5F, -1.0F, -8.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 3.0F));
		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create().texOffs(7, 2).addBox(0.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, -1.0F));
		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create().texOffs(7, 2).mirror().addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 1.0F, -1.0F));
		PartDefinition tail_base = core.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 5.0F));
		PartDefinition tail_end = tail_base.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 4.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(PipefishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.swimAnimationState, PipefishAnimations.SWIM, ageInTicks, 1.25f * (limbSwingAmount * 4.0f + 0.8f));
		this.animate(entity.flopAnimationState, PipefishAnimations.FLOP, ageInTicks, 1.0f);

		this.root.xRot = headPitch * (Mth.DEG_TO_RAD);
	}

	public List<ModelPart> getAllParts() {
		return ImmutableList.of(this.root, this.core, this.body, this.l_fin, this.r_fin, this.tail_base, this.tail_end);
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