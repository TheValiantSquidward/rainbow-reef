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
import net.thevaliantsquidward.rainbowreef.client.animations.DamselfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Damselfish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class DamselfishModel extends HierarchicalModel<Damselfish> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart left_fin;
	private final ModelPart right_fin;
	private final ModelPart left_pelvic_fin;
	private final ModelPart right_pelvic_fin;
	private final ModelPart tail;
	private final ModelPart fin;

	public DamselfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.left_fin = this.core.getChild("left_fin");
		this.right_fin = this.core.getChild("right_fin");
		this.left_pelvic_fin = this.core.getChild("left_pelvic_fin");
		this.right_pelvic_fin = this.core.getChild("right_pelvic_fin");
		this.tail = this.core.getChild("tail");
		this.fin = this.core.getChild("fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition left_fin = core.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(12, 4).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition right_fin = core.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(12, 4).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition left_pelvic_fin = core.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.3927F, 0.0F, -0.3927F));

		PartDefinition right_pelvic_fin = core.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create().texOffs(12, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 1.0F, 0.3927F, 0.0F, 0.3927F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition fin = core.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(10, 7).addBox(0.0F, -4.0F, -2.0F, 0.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Damselfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.swimIdleAnimationState, DamselfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.5F);

		float prevOnLandProgress = entity.prevOnLandProgress;
		float onLandProgress = entity.onLandProgress;
		float partialTicks = ageInTicks - entity.tickCount;
		float landProgress = prevOnLandProgress + (onLandProgress - prevOnLandProgress) * partialTicks;

		this.root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.root.zRot += landProgress * ((float) Math.toRadians(-90) / 5F);
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