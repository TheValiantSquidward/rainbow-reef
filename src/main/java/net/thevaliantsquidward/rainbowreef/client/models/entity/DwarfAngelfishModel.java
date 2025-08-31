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
import net.thevaliantsquidward.rainbowreef.client.animations.DwarfAngelfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.DwarfAngelfish;

@OnlyIn(Dist.CLIENT)
public class DwarfAngelfishModel extends HierarchicalModel<DwarfAngelfish> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart l_bottom_fin;
	private final ModelPart r_bottom_fin;
	private final ModelPart tail;

	public DwarfAngelfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.l_bottom_fin = this.body.getChild("l_bottom_fin");
		this.r_bottom_fin = this.body.getChild("r_bottom_fin");
		this.tail = this.core.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(12, 12).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(7, 14).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -4.0F, -2.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 2.0F, -1.0F));

		PartDefinition l_fin_r1 = l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(6, 17).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.3927F, 0.7854F, 0.0F));

		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 2.0F, -1.0F));

		PartDefinition r_fin_r1 = r_fin.addOrReplaceChild("r_fin_r1", CubeListBuilder.create().texOffs(6, 17).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -1.0F, 0.0F, -0.3927F, -0.7854F, 0.0F));

		PartDefinition l_bottom_fin = body.addOrReplaceChild("l_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -1.0F));

		PartDefinition l_bottom_fin_r1 = l_bottom_fin.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, -0.3927F));

		PartDefinition r_bottom_fin = body.addOrReplaceChild("r_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -1.0F));

		PartDefinition r_bottom_fin_r1 = r_bottom_fin.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.3927F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(DwarfAngelfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.core.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, DwarfAngelfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 3.0F);
		this.animate(entity.flopAnimationState, DwarfAngelfishAnimations.FLOP, ageInTicks, 1);
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