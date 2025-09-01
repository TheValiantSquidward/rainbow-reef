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
import net.thevaliantsquidward.rainbowreef.client.animations.SharkAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Shark;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class SharkModel extends HierarchicalModel<Shark> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart head;
	private final ModelPart upper_jaw;
	private final ModelPart jaw;
	private final ModelPart body;
	private final ModelPart dorsal_fin;
	private final ModelPart left_fin;
	private final ModelPart right_fin;
	private final ModelPart tail;
	private final ModelPart caudal_fin;
	private final ModelPart left_pelvic_fin;
	private final ModelPart right_pelvic_fin;

	public SharkModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.head = this.core.getChild("head");
		this.upper_jaw = this.head.getChild("upper_jaw");
		this.jaw = this.head.getChild("jaw");
		this.body = this.core.getChild("body");
		this.dorsal_fin = this.body.getChild("dorsal_fin");
		this.left_fin = this.body.getChild("left_fin");
		this.right_fin = this.body.getChild("right_fin");
		this.tail = this.core.getChild("tail");
		this.caudal_fin = this.tail.getChild("caudal_fin");
		this.left_pelvic_fin = this.tail.getChild("left_pelvic_fin");
		this.right_pelvic_fin = this.tail.getChild("right_pelvic_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, -2.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition head = core.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -9.0F));

		PartDefinition upper_jaw = head.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -4.0F, -10.0F, 8.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(36, 26).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -8.0F, 10.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, -1.0F));

		PartDefinition dorsal_fin = body.addOrReplaceChild("dorsal_fin", CubeListBuilder.create().texOffs(0, 47).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -6.0F, 1.0F));

		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(53, 8).addBox(5.0F, 0.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(0.0F, 0.0F, -1.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 2.0F, -7.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(53, 8).mirror().addBox(-7.0F, 0.0F, 5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 40).mirror().addBox(-7.0F, 0.0F, -1.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, 2.0F, -7.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(36, 36).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(12, 47).addBox(-0.5F, -8.0F, 1.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(52, 11).addBox(-0.5F, -6.0F, 3.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 7.0F));

		PartDefinition caudal_fin = tail.addOrReplaceChild("caudal_fin", CubeListBuilder.create().texOffs(26, 40).addBox(-0.5F, -7.0F, 0.0F, 1.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 0).addBox(-0.5F, -7.0F, 4.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(52, 17).addBox(-0.5F, 3.0F, 4.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition left_pelvic_fin = tail.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create().texOffs(36, 50).addBox(0.0F, 0.0F, -1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 22).addBox(3.0F, 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_pelvic_fin = tail.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create().texOffs(36, 50).mirror().addBox(-5.0F, 0.0F, -1.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 22).mirror().addBox(-5.0F, 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 2.0F, 2.0F, 0.0F, 0.0F, -0.3927F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Shark entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float prevOnLandProgress = entity.prevOnLandProgress;
		float onLandProgress = entity.onLandProgress;
		float partialTicks = ageInTicks - entity.tickCount;
		float landProgress = prevOnLandProgress + (onLandProgress - prevOnLandProgress) * partialTicks;

		this.root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.root.zRot += landProgress * ((float) Math.toRadians(-90) / 5F);

		this.animate(entity.swimAnimationState, SharkAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 2.0F);
		this.animate(entity.flopAnimationState, SharkAnimations.FLOP, ageInTicks);
		this.animate(entity.biteAnimationState, SharkAnimations.ATTACK, ageInTicks);
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