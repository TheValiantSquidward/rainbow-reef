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
import net.thevaliantsquidward.rainbowreef.client.animations.LionfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Lionfish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class LionfishModel extends HierarchicalModel<Lionfish> {

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
	private final ModelPart head;
	private final ModelPart left_antenna;
	private final ModelPart right_antenna;

	public LionfishModel(ModelPart root) {
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
		this.head = this.core.getChild("head");
		this.left_antenna = this.head.getChild("left_antenna");
		this.right_antenna = this.left_antenna.getChild("right_antenna");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -7.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(29, 1).addBox(0.0F, -4.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition top_fin = body.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -7.0F, -7.0F, 0.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 4.0F));

		PartDefinition bottom_fin = body.addOrReplaceChild("bottom_fin", CubeListBuilder.create().texOffs(28, 11).addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 7.0F));

		PartDefinition left_pelvic_fin = body.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create().texOffs(34, 39).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F, 0.3927F, 0.0F, -0.3927F));

		PartDefinition right_pelvic_fin = body.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create().texOffs(34, 39).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F, 0.3927F, 0.0F, 0.3927F));

		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(26, 32).addBox(0.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(26, 32).mirror().addBox(-8.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition head = core.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 23).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(6, 46).addBox(1.0F, 1.0F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(6, 46).mirror().addBox(-1.0F, 1.0F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.0F, 3.0F));

		PartDefinition left_antenna = head.addOrReplaceChild("left_antenna", CubeListBuilder.create().texOffs(26, 39).addBox(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, -3.0F));

		PartDefinition right_antenna = left_antenna.addOrReplaceChild("right_antenna", CubeListBuilder.create().texOffs(26, 39).mirror().addBox(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Lionfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.swimAnimationState, LionfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.5F);

		float prevOnLandProgress = entity.prevOnLandProgress;
		float onLandProgress = entity.onLandProgress;
		float partialTicks = ageInTicks - entity.tickCount;
		float landProgress = prevOnLandProgress + (onLandProgress - prevOnLandProgress) * partialTicks;

		this.root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.root.zRot += landProgress * ((float) Math.toRadians(-90) / 5F);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}