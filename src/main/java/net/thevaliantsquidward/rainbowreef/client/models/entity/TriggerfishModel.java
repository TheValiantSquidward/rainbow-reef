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
import net.thevaliantsquidward.rainbowreef.client.animations.TriggerfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Triggerfish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class TriggerfishModel extends HierarchicalModel<Triggerfish> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart top_fin;
	private final ModelPart bottom_fin;
	private final ModelPart left_fin;
	private final ModelPart right_fin;
	private final ModelPart tail;

	public TriggerfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.top_fin = this.body.getChild("top_fin");
		this.bottom_fin = this.body.getChild("bottom_fin");
		this.left_fin = this.body.getChild("left_fin");
		this.right_fin = this.body.getChild("right_fin");
		this.tail = this.core.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.5F, 19.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(14, 14).addBox(-1.0F, 3.0F, -3.0F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -3.0F, -4.0F, 3.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(30, 29).addBox(0.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 29).addBox(0.5F, -5.0F, -3.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 3.0F));

		PartDefinition top_fin = body.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(12, 22).addBox(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -3.0F, 0.0F));

		PartDefinition bottom_fin = body.addOrReplaceChild("bottom_fin", CubeListBuilder.create().texOffs(22, 0).addBox(0.0F, -2.0F, -1.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 4.0F, 0.0F));

		PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(26, 23).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.5F, -1.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(26, 23).mirror().addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.5F, -1.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(1, 28).addBox(0.0F, -4.0F, 0.0F, 0.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Triggerfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.swimIdleAnimationState, TriggerfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.5F);

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