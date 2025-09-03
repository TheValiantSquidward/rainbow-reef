package net.thevaliantsquidward.rainbowreef.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.JellyfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Jellyfish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class JellyfishModel extends HierarchicalModel<Jellyfish> {

	private final ModelPart root;
	private final ModelPart bell;
	private final ModelPart center;
	private final ModelPart thing;
	private final ModelPart tentacles;
	private final ModelPart tentacles_1;
	private final ModelPart tentacles_2;
	private final ModelPart tentacles_3;
	private final ModelPart tentacles_4;

	public JellyfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.bell = this.root.getChild("bell");
		this.center = this.bell.getChild("center");
		this.thing = this.center.getChild("thing");
		this.tentacles = this.bell.getChild("tentacles");
		this.tentacles_1 = this.tentacles.getChild("tentacles_1");
		this.tentacles_2 = this.tentacles.getChild("tentacles_2");
		this.tentacles_3 = this.tentacles.getChild("tentacles_3");
		this.tentacles_4 = this.tentacles.getChild("tentacles_4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 0.0F));

		PartDefinition bell = root.addOrReplaceChild("bell", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -5.25F, -8.0F, 16.0F, 11.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.75F, 0.0F));

		PartDefinition center = bell.addOrReplaceChild("center", CubeListBuilder.create().texOffs(0, 27).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.75F, 0.0F));

		PartDefinition thing = center.addOrReplaceChild("thing", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition thing_r1 = thing.addOrReplaceChild("thing_r1", CubeListBuilder.create().texOffs(44, 49).addBox(-5.0F, -2.0F, 0.0F, 10.0F, 20.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition thing_r2 = thing.addOrReplaceChild("thing_r2", CubeListBuilder.create().texOffs(44, 49).addBox(-5.0F, -2.0F, 0.0F, 10.0F, 20.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition tentacles = bell.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, 6.75F, 0.0F));

		PartDefinition tentacles_1 = tentacles.addOrReplaceChild("tentacles_1", CubeListBuilder.create(), PartPose.offset(-0.5F, -2.0F, 6.0F));

		PartDefinition tentacles_1_r1 = tentacles_1.addOrReplaceChild("tentacles_1_r1", CubeListBuilder.create().texOffs(22, 49).mirror().addBox(-5.0F, 0.0F, 0.0F, 11.0F, 23.0F, 0.0F, new CubeDeformation(0.0001F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tentacles_2 = tentacles.addOrReplaceChild("tentacles_2", CubeListBuilder.create(), PartPose.offset(-6.0F, -2.0F, 0.5F));

		PartDefinition tentacles_2_r1 = tentacles_2.addOrReplaceChild("tentacles_2_r1", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, 0.0F, -6.0F, 0.0F, 23.0F, 11.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.2618F));

		PartDefinition tentacles_3 = tentacles.addOrReplaceChild("tentacles_3", CubeListBuilder.create(), PartPose.offset(-0.5F, -2.0F, -6.0F));

		PartDefinition tentacles_3_r1 = tentacles_3.addOrReplaceChild("tentacles_3_r1", CubeListBuilder.create().texOffs(22, 49).addBox(-5.0F, 0.0F, 0.0F, 11.0F, 23.0F, 0.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition tentacles_4 = tentacles.addOrReplaceChild("tentacles_4", CubeListBuilder.create(), PartPose.offset(5.0F, -2.0F, 0.5F));

		PartDefinition tentacles_4_r1 = tentacles_4.addOrReplaceChild("tentacles_4_r1", CubeListBuilder.create().texOffs(0, 49).addBox(0.0F, 0.0F, -6.0F, 0.0F, 23.0F, 11.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, -0.2618F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Jellyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animate(entity.swimAnimationState, JellyfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 2.0F);
		this.animate(entity.flopAnimationState, JellyfishAnimations.FLOP, ageInTicks);
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