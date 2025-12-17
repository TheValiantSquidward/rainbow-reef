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
import net.thevaliantsquidward.rainbowreef.client.animations.WrasseAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Wrasse;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class WrasseModel extends HierarchicalModel<Wrasse> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart tail;
	private final ModelPart bodies;
	private final ModelPart mouths;
	private final ModelPart left_fin;
	private final ModelPart right_fin;
	private final ModelPart bottom_left_fin;
	private final ModelPart bottom_right_fin;

	public WrasseModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.tail = this.core.getChild("tail");
		this.bodies = this.core.getChild("bodies");
		this.mouths = this.bodies.getChild("mouths");
		this.left_fin = this.bodies.getChild("left_fin");
		this.right_fin = this.bodies.getChild("right_fin");
		this.bottom_left_fin = this.bodies.getChild("bottom_left_fin");
		this.bottom_right_fin = this.bodies.getChild("bottom_right_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.5F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 8.0F));

		PartDefinition bodies = core.addOrReplaceChild("bodies", CubeListBuilder.create().texOffs(18, 0).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(18, 12).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, -1).addBox(0.0F, -9.0F, -4.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 4.0F));

		PartDefinition mouths = bodies.addOrReplaceChild("mouths", CubeListBuilder.create().texOffs(13, 19).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(13, 23).addBox(-0.5F, -2.0F, -2.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -5.0F));

		PartDefinition left_fin = bodies.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(12, 28).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition right_fin = bodies.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(12, 28).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition bottom_left_fin = bodies.addOrReplaceChild("bottom_left_fin", CubeListBuilder.create().texOffs(22, 23).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition bottom_right_fin = bodies.addOrReplaceChild("bottom_right_fin", CubeListBuilder.create().texOffs(22, 23).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Wrasse entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.swimIdleAnimationState, WrasseAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.5F);

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