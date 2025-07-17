package net.thevaliantsquidward.rainbowreef.client.models.entity;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.ArrowCrabAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.ArrowCrabEntity;


@OnlyIn(Dist.CLIENT)
public class ArrowCrabModel<T extends ArrowCrabEntity> extends ReefModel<T> {


	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart r_pincer;
	private final ModelPart l_pincer;
	private final ModelPart mandibles;
	private final ModelPart l_legs;
	private final ModelPart l_leg_1;
	private final ModelPart l_leg_2;
	private final ModelPart l_leg_3;
	private final ModelPart l_leg_4;
	private final ModelPart r_legs;
	private final ModelPart r_leg_1;
	private final ModelPart r_leg_2;
	private final ModelPart r_leg_3;
	private final ModelPart r_leg_4;

	public ArrowCrabModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.head = this.body.getChild("head");
		this.r_pincer = this.body.getChild("r_pincer");
		this.l_pincer = this.body.getChild("l_pincer");
		this.mandibles = this.body.getChild("mandibles");
		this.l_legs = this.core.getChild("l_legs");
		this.l_leg_1 = this.l_legs.getChild("l_leg_1");
		this.l_leg_2 = this.l_legs.getChild("l_leg_2");
		this.l_leg_3 = this.l_legs.getChild("l_leg_3");
		this.l_leg_4 = this.l_legs.getChild("l_leg_4");
		this.r_legs = this.core.getChild("r_legs");
		this.r_leg_1 = this.r_legs.getChild("r_leg_1");
		this.r_leg_2 = this.r_legs.getChild("r_leg_2");
		this.r_leg_3 = this.r_legs.getChild("r_leg_3");
		this.r_leg_4 = this.r_legs.getChild("r_leg_4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.9259F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0852F, -0.0474F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5852F, -0.9526F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5852F, -1.4526F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(32, 19).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 23).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition r_pincer = body.addOrReplaceChild("r_pincer", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.4148F, -1.9526F));

		PartDefinition r_pincer_r1 = r_pincer.addOrReplaceChild("r_pincer_r1", CubeListBuilder.create().texOffs(26, 7).mirror().addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.1745F, 0.3491F, 0.0F));

		PartDefinition l_pincer = body.addOrReplaceChild("l_pincer", CubeListBuilder.create(), PartPose.offset(2.5F, 0.4148F, -1.9526F));

		PartDefinition l_pincer_r1 = l_pincer.addOrReplaceChild("l_pincer_r1", CubeListBuilder.create().texOffs(26, 7).addBox(-1.5F, 0.0F, -5.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.1745F, -0.3491F, 0.0F));

		PartDefinition mandibles = body.addOrReplaceChild("mandibles", CubeListBuilder.create().texOffs(4, 12).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.9148F, -2.1286F));

		PartDefinition l_legs = core.addOrReplaceChild("l_legs", CubeListBuilder.create(), PartPose.offset(2.0F, 0.5F, 0.0741F));

		PartDefinition l_leg_1 = l_legs.addOrReplaceChild("l_leg_1", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, -1.0741F));

		PartDefinition l_leg_1_r1 = l_leg_1.addOrReplaceChild("l_leg_1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.5236F, -0.6109F));

		PartDefinition l_leg_2 = l_legs.addOrReplaceChild("l_leg_2", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, -0.0741F));

		PartDefinition l_leg_2_r1 = l_leg_2.addOrReplaceChild("l_leg_2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0873F, -0.6109F));

		PartDefinition l_leg_3 = l_legs.addOrReplaceChild("l_leg_3", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 0.9259F));

		PartDefinition l_leg_3_r1 = l_leg_3.addOrReplaceChild("l_leg_3_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.0873F, -0.6109F));

		PartDefinition l_leg_4 = l_legs.addOrReplaceChild("l_leg_4", CubeListBuilder.create(), PartPose.offset(0.5F, 0.0F, 1.9259F));

		PartDefinition l_leg_4_r1 = l_leg_4.addOrReplaceChild("l_leg_4_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.5236F, -0.6109F));

		PartDefinition r_legs = core.addOrReplaceChild("r_legs", CubeListBuilder.create(), PartPose.offset(-2.0F, 0.5F, 0.0741F));

		PartDefinition r_leg_1 = r_legs.addOrReplaceChild("r_leg_1", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -1.0741F));

		PartDefinition r_leg_1_r1 = r_leg_1.addOrReplaceChild("r_leg_1_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, -1.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 1.0F, 0.0F, -0.5236F, 0.6109F));

		PartDefinition r_leg_2 = r_legs.addOrReplaceChild("r_leg_2", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -0.0741F));

		PartDefinition r_leg_2_r1 = r_leg_2.addOrReplaceChild("r_leg_2_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.0873F, 0.6109F));

		PartDefinition r_leg_3 = r_legs.addOrReplaceChild("r_leg_3", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 0.9259F));

		PartDefinition r_leg_3_r1 = r_leg_3.addOrReplaceChild("r_leg_3_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.0873F, 0.6109F));

		PartDefinition r_leg_4 = r_legs.addOrReplaceChild("r_leg_4", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, 1.9259F));

		PartDefinition r_leg_4_r1 = r_leg_4.addOrReplaceChild("r_leg_4_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-7.0F, -0.5F, 0.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.5236F, 0.6109F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(ArrowCrabEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.body.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.walkAnimationState, ArrowCrabAnimations.WALK, ageInTicks, limbSwingAmount * 4.0f);
		this.animate(entity.idleAnimationState, ArrowCrabAnimations.IDLE, ageInTicks, limbSwingAmount * 4.0f);
		this.animate(entity.danceAnimationState, ArrowCrabAnimations.DANCE, ageInTicks, 1);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}