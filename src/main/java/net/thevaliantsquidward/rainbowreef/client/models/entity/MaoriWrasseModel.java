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
import net.thevaliantsquidward.rainbowreef.client.animations.MaoriWrasseAnimations;
import net.thevaliantsquidward.rainbowreef.entity.MaoriWrasse;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class MaoriWrasseModel extends HierarchicalModel<MaoriWrasse> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart tail;
	private final ModelPart body;
	private final ModelPart top_fin;
	private final ModelPart bottom_fin;
	private final ModelPart left_pelvic_fin;
	private final ModelPart right_pelvic_fin;
	private final ModelPart left_fin;
	private final ModelPart right_fin;

	public MaoriWrasseModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.tail = this.core.getChild("tail");
		this.body = this.core.getChild("body");
		this.top_fin = this.body.getChild("top_fin");
		this.bottom_fin = this.body.getChild("bottom_fin");
		this.left_pelvic_fin = this.body.getChild("left_pelvic_fin");
		this.right_pelvic_fin = this.body.getChild("right_pelvic_fin");
		this.left_fin = this.body.getChild("left_fin");
		this.right_fin = this.body.getChild("right_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 1.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, -8.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 9.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -8.0F, -8.0F, 6.0F, 15.0F, 18.0F, new CubeDeformation(0.0F))
				.texOffs(24, 54).addBox(-2.0F, -8.0F, -9.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(48, 21).addBox(-3.0F, -2.0F, -11.0F, 6.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(53, 34).addBox(-3.5F, 2.0F, -12.0F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition top_fin = body.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(24, 33).addBox(0.0F, -5.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition bottom_fin = body.addOrReplaceChild("bottom_fin", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition left_pelvic_fin = body.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create().texOffs(52, 39).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 7.0F, -2.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_pelvic_fin = body.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create().texOffs(52, 39).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 7.0F, -2.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(52, 51).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.0F, -1.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(52, 51).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 1.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(MaoriWrasse entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.flopAnimationState, MaoriWrasseAnimations.FLOP, ageInTicks);
		this.animate(entity.swimIdleAnimationState, MaoriWrasseAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 2.0F);

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