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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.ButterfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.Butterfish;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ButterfishModel<T extends Butterfish> extends ReefModel<T> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart LowerFinTip;
	private final ModelPart TopFinTip;
	private final ModelPart l_bottom_fin;
	private final ModelPart r_bottom_fin;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart Tail;

	public ButterfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.LowerFinTip = this.body.getChild("LowerFinTip");
		this.TopFinTip = this.body.getChild("TopFinTip");
		this.l_bottom_fin = this.body.getChild("l_bottom_fin");
		this.r_bottom_fin = this.body.getChild("r_bottom_fin");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.Tail = this.body.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.5F, 21.5F, 0.5F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -1.5F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(24, 14).addBox(-1.0F, -3.5F, -3.5F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 0).addBox(-1.0F, -4.5F, -3.5F, 1.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(23, 38).addBox(-1.0F, -0.5F, -4.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(11, 38).addBox(-1.0F, -0.5F, -5.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 24).addBox(-1.0F, -2.5F, -4.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 37).addBox(-1.0F, -0.5F, -6.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -8.5F, -5.5F, 0.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.5F));

		PartDefinition LowerFinTip = body.addOrReplaceChild("LowerFinTip", CubeListBuilder.create().texOffs(0, 24).addBox(-0.5F, -0.0033F, -0.0808F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, 5.5F));

		PartDefinition TopFinTip = body.addOrReplaceChild("TopFinTip", CubeListBuilder.create().texOffs(15, 27).addBox(-0.5F, 0.0F, 0.0F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 5.5F));

		PartDefinition l_bottom_fin = body.addOrReplaceChild("l_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 2.5F, -1.5F));

		PartDefinition l_bottom_fin_r1 = l_bottom_fin.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.3927F, 0.0F));

		PartDefinition r_bottom_fin = body.addOrReplaceChild("r_bottom_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 2.5F, -1.5F));

		PartDefinition r_bottom_fin_r1 = r_bottom_fin.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(0, 35).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, -0.3927F, 0.0F));

		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -1.5F));

		PartDefinition l_fin_r1 = l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(18, 38).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.5F, -1.5F));

		PartDefinition r_fin_r1 = r_fin.addOrReplaceChild("r_fin_r1", CubeListBuilder.create().texOffs(18, 38).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition Tail = body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(30, 27).addBox(0.0F, -2.5F, 0.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 2.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Butterfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.body.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, ButterfishAnimations.SWIM, ageInTicks, (float) (0.5 + limbSwingAmount * 4.0f));
		this.animate(entity.landAnimationState, ButterfishAnimations.FLOP, ageInTicks, 1);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public List<ModelPart> getAllParts() {
		return ImmutableList.of(this.root, this.core, this.body, this.LowerFinTip, this.TopFinTip, this.l_bottom_fin, this.r_bottom_fin, this.l_fin, this.r_fin, this.Tail);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}