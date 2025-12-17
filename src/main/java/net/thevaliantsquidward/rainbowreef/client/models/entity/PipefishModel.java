package net.thevaliantsquidward.rainbowreef.client.models.entity;

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
import net.thevaliantsquidward.rainbowreef.entity.Pipefish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class PipefishModel extends HierarchicalModel<Pipefish> {

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
	public void setupAnim(Pipefish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.swimIdleAnimationState, PipefishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.5F);
		this.animate(entity.flopAnimationState, PipefishAnimations.FLOP, ageInTicks);
		this.root.xRot = headPitch * (Mth.DEG_TO_RAD);
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