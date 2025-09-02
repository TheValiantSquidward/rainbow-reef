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
import net.thevaliantsquidward.rainbowreef.client.animations.MoorishIdolAnimations;
import net.thevaliantsquidward.rainbowreef.client.animations.WrasseAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Billfish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class BillfishModel extends HierarchicalModel<Billfish> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart tail;
	private final ModelPart tail_fin;
	private final ModelPart body;
	private final ModelPart sail_fin;
	private final ModelPart bottom_fin;
	private final ModelPart jaw;
	private final ModelPart left_fin;
	private final ModelPart right_fin;
	private final ModelPart left_pelvic_fin;
	private final ModelPart right_pelvic_fin;

	public BillfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.tail = this.core.getChild("tail");
		this.tail_fin = this.tail.getChild("tail_fin");
		this.body = this.core.getChild("body");
		this.sail_fin = this.body.getChild("sail_fin");
		this.bottom_fin = this.body.getChild("bottom_fin");
		this.jaw = this.body.getChild("jaw");
		this.left_fin = this.body.getChild("left_fin");
		this.right_fin = this.body.getChild("right_fin");
		this.left_pelvic_fin = this.body.getChild("left_pelvic_fin");
		this.right_pelvic_fin = this.body.getChild("right_pelvic_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-1.0F, 20.0F, 1.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -9.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(66, 0).addBox(-0.5F, -6.0F, 5.0F, 0.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(46, 38).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 17.0F));

		PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -13.0F, 0.0F, 0.0F, 26.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 14.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 38).addBox(-2.5F, -6.0F, -9.0F, 5.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(46, 58).addBox(-0.5F, -1.0F, -25.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition sail_fin = body.addOrReplaceChild("sail_fin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -11.0F, -1.0F, 0.0F, 14.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -8.0F));

		PartDefinition bottom_fin = body.addOrReplaceChild("bottom_fin", CubeListBuilder.create().texOffs(66, 19).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.0F));

		PartDefinition jaw = body.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(66, 30).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -9.0F));

		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(18, 66).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 3.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 3.0F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition left_pelvic_fin = body.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create().texOffs(0, 66).addBox(0.0F, 0.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_pelvic_fin = body.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create().texOffs(0, 66).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(Billfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float prevOnLandProgress = entity.prevOnLandProgress;
		float onLandProgress = entity.onLandProgress;
		float partialTicks = ageInTicks - entity.tickCount;
		float landProgress = prevOnLandProgress + (onLandProgress - prevOnLandProgress) * partialTicks;

		this.root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.root.zRot += landProgress * ((float) Math.toRadians(-90) / 5F);

		this.animate(entity.swimAnimationState, WrasseAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.1F);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}